#include "DonorDatabase.h"

bool checkFile(string incomingFile){
	ifstream file(incomingFile);
	return file.good();
}
bool checkPass(string pass){ 
	string::iterator it;
	int digitC;
	int punctC;

	if(pass.length() < 6){
		return false;
	}
	for(it = pass.begin(); it < pass.end(); it++){
		if(isdigit(*it)){
			digitC++;
		}else if(ispunct(*it)){
			punctC++;
		}
	}
	if(digitC >= 1 && punctC >=1){
		return true;
	}else{
		return false;
	}
}

void menu2(Donor &donor){
	string commands2[] = {"Manage", "Passwd", "View", "Donate", "Total", "Logout"};
	bool breakMen2 = false;

	while(breakMen2 == false){
		string command;
		bool valid = false;
		cout << "Enter a command: Manage, Passwd, View, Donate, Total, Logout" << endl;
		cin >> command;
		while(valid == false){
			for(int i = 0; i < 6; i++){
				if(command.compare(commands2[i]) == 0){
					valid = true;
					break;
				}
			}
			if(valid == false){
				cout << "Please enter a valid command." << endl << endl;
				cout << "Enter a command: Manage, Passwd, View, Donate, Total, Logout" << endl;
				cin >> command;
			}
		}
		if(command.compare("Manage") == 0){
			donor.Manage();
		}else if(command.compare("Passwd") == 0){
			string oldPass;
			bool finished = false;
			while(finished == false){
				cout << "What is your old password?" << endl << endl;
				cin >> oldPass;

				if(donor.getPass().compare(oldPass) == 0){
					string newPass;
					string newPass2;

					bool changeDone = false;
					while(changeDone == false){
						cout << "Enter your new password" << endl << endl;
						cin >> newPass;

						if(checkPass(newPass) == true){
							cout << "Please enter your password again." << endl << endl;
							cin >> newPass2;

							if(newPass2.compare(newPass) == 0){
								donor.setPass(newPass);
								cout << "Changed." << endl;
								changeDone = true;
							}else{
								cout <<"The passwords don't match." << endl;
								changeDone = true;
							}
						}else{
							cout << "Please enter a valid password." << endl << endl;
						}
					}
					finished = true;
				}else{
					cout << "Incorrect password. Please try again." << endl << endl;
				}
			}
		}else if(command.compare("View") == 0){
			donor.View();
		}else if(command.compare("Donate") == 0){
			if(donor.getDonations() == 5000.00){
				cout << "Donation max has been reached." << endl << endl;
			}else{
				float donation;
				bool done = false;

				while(done == false){
					cout << "Enter the amount to donate. Max is $5000.00." <<
					" Enter in the form: $X.XX"<< endl << endl;
					cin >> donation;
					if(cin.fail()){
						cout << "Please enter the donation with the proper form" << endl;
						cin.clear();
					}else if(donation < 0){
						cout << "You cannot donate a negative amount. Please try again." << endl; 
					}else if((donation + donor.getDonations()) > 5000.00){
						cout << "Donations cannot exceed $5000.00. Please try again." << endl << endl;
					}else{
						donor.Donate(donation);
						done = true;
					}
				}
			}
		}else if(command.compare("Total") == 0){
			cout << donor.getLast() << " $" << fixed << setprecision(2) << 
													(float)donor.getDonations() << endl << endl;
		}else{//Logout
			return;
		}
	}
}
void menu1(DonorDatabase *database){

	string commands1[] = {"Login", "Save", "Load", "Add", "Report", "Quit"};
	string command;
	int index;
	bool quitParam = false;
	bool breakMen1 = false;

	while(breakMen1 == false){ //Gets valid commmand from the user
		bool valid = false;
		cout << "Enter a command: Login, Save, Load, Add, Report, Quit" << endl;
		cin >> command;
		while(valid == false){
			for(int i = 0; i < 6; i++){
				if(command.compare(commands1[i]) == 0){
					valid = true;
					break;
				}
			}
			if(valid == false){
				cout<< "Please enter a valid command." << endl;
				cout << "Enter a command: Login, Save, Load, Add, Report, Quit" << endl;
				cin >> command;
			}
		}
		if(command.compare("Login")==0){
			string tempUser;
			string tempPass;
			bool loggedIn = false;
			bool loggedOut = false;
			while(loggedIn == false){
				cout << "What is your userid? " << endl;
				cin >> tempUser;

				cout << "What is your password? " << endl;
				cin>> tempPass;
				loggedIn = database->Login(tempUser,tempPass);
			}
			cout << "Logging in..." << endl;
			Donor donor = database->getDonors()[donor.getDex()];
			menu2(donor);

		}else if(command.compare("Save")==0){
			string filename;
			bool stop = false;
			bool overwrite = false;

			while(stop == false){
				string filename;
				cout << "Where would you like to save your donor information?" << endl;
				cin >> filename;

				if(checkFile(filename) == true){
					bool success = false;
					while(success == false){
						string fileCheck;
						cout << "This file already exists. Do you want to overwrite?" << endl;
						cout << "Type <Yes> for yes and <No> for no." << endl;
						cin >> fileCheck;

						if(fileCheck.compare("Yes") == 0){
							overwrite = true;
							success = true;
						}else if(fileCheck.compare("No") == 0){
							overwrite = false;
							success = true;
						}else{
							cout << "Please enter the correct command." << endl;
						}
					}
				}
				if(overwrite == true || checkFile(filename) == false){
					cout << "File saved." << endl;
					database->Save(filename);
					stop = true;
				}
			}
		}else if(command.compare("Load")==0){
			string filename;
			bool stop = false;

			while(stop == false){
				string filename;
				cout << "What is the name of the file you would like to load?" << endl;
				cin >> filename;

				if(checkFile(filename) == true){
					database->Load(filename);
					stop =  true;
				}else{
					cout << "Your file does not exist. Please try again." << endl;
				}
			}
		}else if(command.compare("Add")==0){
			database->Add();
		}else if(command.compare("Report")==0){
			database->Report();	
		}else if(command.compare("Quit")==0) { //Quit
			string saveCheck;
			bool quitLoop = true;
			while(quitLoop == true){
				cout << "Would you like to save before quitting out?" << endl;
				cout <<"Enter <Yes> for yes and <No> for no" << endl;
				cin >> saveCheck;

				if(saveCheck.compare("Yes") == 0){
					bool stop = false;
					bool overwrite = false;

					while(stop == false){
						string filename;
						cout << "Where would you like to save your donor information?" << endl;
						cin >> filename;

						if(checkFile(filename) == true){
							bool success = false;
							while(success == false){
								string fileCheck;
								cout << "This file already exists. Do you want to overwrite?" << endl;
								cout << "Type <Yes> for yes and <No> for no." << endl;
								cin >> fileCheck;

								if(fileCheck.compare("Yes") == 0){
									overwrite = true;
									success = true;
								}else if(fileCheck.compare("No") == 0){
									overwrite = false;
									success = true;
								}else{
									cout << "Please enter the correct command." << endl;
								}
							}
						}
						if(overwrite == true || checkFile(filename) == false){
							cout << "File saved." << endl;
							database->Save(filename);
							stop = true;
							quitLoop = false;
							breakMen1 = true;
						}
					}
				}else if(saveCheck.compare("No") == 0){
					quitLoop = false;
					breakMen1 = true;
				}else{
					cout << "Please enter a valid command." << endl;
				}
			}
		}
	}
}

int main(int argc, char *argv[]){
	int maxDonors;
	stringstream geek(argv[1]);
	geek >> maxDonors;

	if(maxDonors > 1000 || maxDonors < 0){
		cout << "There can only be from 1-1000 donors." << endl;
		return 1;
	}
	
	if(argc == 3){
		string filename = argv[2];
		DonorDatabase *database;
		database = new DonorDatabase(maxDonors, filename);
		menu1(database);
	}else if(argc == 2){
		DonorDatabase *database;
		database = new DonorDatabase(maxDonors);
		menu1(database);
	}else{
		cout << "Wrong amount of arguments given." << endl;
		return 1;
	}
}
