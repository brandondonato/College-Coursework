#include "Donor.h"

Donor::Donor(){
	donations = 0;
}
Donor::Donor(string user, string password, int dex){
	id = user;
	pass = password;
	index = dex;
}
Donor::Donor(string user, string password, string lName, string fName,
				int a, int sNum, string sName, string t, State s, string z, 
				float donation, int dex){
	id = user;
	pass = password;
	lastName = lName;
	firstName = fName;
	age = a;
	streetNum = sNum;
	streetName = sName;
	town = t;
	state = s;
	zip = z;
	donations = donation;
	index = dex;
}

//Ask the user for all of the information 
void Donor::Add(){
	//Get last name
	string lName;
	cout << "Last Name: " << endl;
	cin >> lName; 
	//Check the last name
	bool goodLName = this->nameCheck(lName);
	while(goodLName == false){
		cout << "Please enter a valid name. " << endl;
		cout << "Last Name: ";
		cin >> lName; 	
		goodLName = this->nameCheck(lName);
	}
	lastName = lName;

	//Get first name
	string fName;
	cout << "First Name: " << endl;
	cin >> fName;
	//Check first name
	bool goodFName = this->nameCheck(fName);
	while(goodFName == false){
		cout << "Please enter a valid name. " << endl;
		cout << "First Name: ";
		cin >> fName;
		goodFName = this->nameCheck(fName); 	
	}
	firstName = fName;

	//Get age
	string inputStr;
	int a;
	cout << "You must be at least 18 years old. Age: " << endl;
	cin >> inputStr;
	stringstream newAge(inputStr);

	newAge >> a;
	bool properAge;
	
	if(a < 18){
		properAge = false;
	}else{
		properAge  = true;
	}
	while(properAge == false){
		
		cout << "You must be at least 18 years old. Age: " << endl;
		cin >> inputStr;
		stringstream newAge(inputStr);
		newAge >> a;
		
		if(a >= 18){
			properAge = true;
		}
	}
	age = a;
	
	//Get street name
	string streetN;
	cout << "Street Name: ";
	cin.ignore();
	getline(cin, streetN);
	bool goodStreet = this->streetCheck(streetN);
	//Check the Street Name
	while(goodStreet == false){
		cout << "This street name is invalid. Please try again." << endl;
		cout << "Street Name: ";
		cin.ignore();
		getline(cin, streetN);
		goodStreet = this->streetCheck(streetN);
	}
	streetName = streetN;
	
	//Get street number
	string houseInput;
	int sNum;
	cout << "House Number: ";
	cin >> houseInput;
	stringstream house(houseInput);
	house >> sNum;
	bool properHouse;

	if(sNum <= 0){
		properHouse = false;
	}else{
		properHouse = true;
	}
	//Check street number
	while(properHouse == false){
		cout << "Please enter a valid house number." << endl;
		
		cout << "House Number: ";
		cin >> houseInput;
		stringstream house(houseInput);
		house >> sNum;
		if(sNum > 0){
			properHouse = true;
		}
	}
	streetNum = sNum;

	//Get town name
	string t;
	cout << "Town: ";
	cin >> t;
	bool goodTown = this->nameCheck(t);
	//Check the Town name
	while(goodTown == false){
		cout <<"This town is invalid. Please try again." << endl;
		cout << "Town: ";
		cin >> t;
		goodTown = this->nameCheck(t);
	}
	town = t;
		
	//Get zip code	
	string z;	
	cout << "Zip Code: ";
	cin >> z; 
	bool goodZip = this->zipCheck(z);
	//Check the zip code
	while(goodZip == false){
		cout << "This zip code is invalid. Please try again." << endl;
		cout << "Zip Code: ";
		cin >> z; 
		goodZip = this->zipCheck(z);
	}
	zip = z;

	std::map<string, State> mp;
	mp["NY"] = NY;
	mp["PA"] = PA;
	mp["RI"] = RI;
	mp["NH"] = NH;
	mp["VT"] = VT;
	mp["MA"] = MA;
	mp["CT"] = CT;
	mp["ME"] = ME;
	//Get state
	string s;
	cout << "What state are you from?" << endl <<
			"Choices are: NY, PA, RI, NH, VT, MA, CT, ME" << endl << endl;
	cin >> s;
	bool goodState = this->stateCheck(s);
	//Check the state
	while(goodState == false){
		cout << "What state are you from? " << 
			"Choices are: NY, PA, RI, NH, VT, MA, CT, ME" << endl;
		cin >> s;
		goodState = this->stateCheck(s);
	}
	state = mp[s];
}

//Show the user all of the information and allow changes
void Donor::Manage(){
	bool exitParam = false;
	string inputStr;
	while(exitParam == false){
		cout << "What would you like to edit? " << 
			"Choices are: Address, Age or Name. " << "Enter <Quit> to exit" << endl;
		cout << endl << ": ";
		cin >> inputStr;
		if(inputStr.compare("Address") == 0){
			//Get street name
			string streetN;
			cout << "Street Name: ";
			cin.ignore();
			getline(cin, streetN);
			bool goodStreet = this->streetCheck(streetN);
			//Check the Street Name
			while(goodStreet == false){
				cout << "This street name is invalid. Please try again." << endl;
				cout << "Street Name: ";
				cin.ignore();
				getline(cin, streetN);
				goodStreet = this->streetCheck(streetN);
			}
			streetName = streetN;
			
			//Get street number
			string houseInput;
			int sNum;
			cout << "House Number: ";
			cin >> houseInput;
			stringstream house(houseInput);
			house >> sNum;
			bool properHouse;

			if(sNum <= 0){
				properHouse = false;
			}else{
				properHouse = true;
			}
			//Check street number
			while(properHouse == false){
				cout << "Please enter a valid house number." << endl;
				
				cout << "House Number: ";
				cin >> houseInput;
				stringstream house(houseInput);
				house >> sNum;
				if(sNum > 0){
					properHouse = true;
				}
			}
			streetNum = sNum;

			//Get town name
			string t;
			cout << "Town: ";
			cin >> t;
			bool goodTown = this->nameCheck(t);
			//Check the Town name
			while(goodTown == false){
				cout <<"This town is invalid. Please try again." << endl;
				cout << "Town: ";
				cin >> t;
				goodTown = this->nameCheck(t);
			}
			town = t;
				
			//Get zip code	
			string z;	
			cout << "Zip Code: ";
			cin >> z; 
			bool goodZip = this->zipCheck(z);
			//Check the zip code
			while(goodZip == false){
				cout << "This zip code is invalid. Please try again." << endl;
				cout << "Zip Code: ";
				cin >> z; 
				goodZip = this->zipCheck(z);
			}
			zip = z;

			std::map<string, State> mp;
			mp["NY"] = NY;
			mp["PA"] = PA;
			mp["RI"] = RI;
			mp["NH"] = NH;
			mp["VT"] = VT;
			mp["MA"] = MA;
			mp["CT"] = CT;
			mp["ME"] = ME;
			//Get state
			string s;
			cout << "What state are you from?" << endl <<
					"Choices are: NY, PA, RI, NH, VT, MA, CT, ME" << endl << endl;
			cin >> s;
			bool goodState = this->stateCheck(s);
			//Check the state
			while(goodState == false){
				cout << "What state are you from? " << 
					"Choices are: NY, PA, RI, NH, VT, MA, CT, ME" << endl;
				cin >> s;
				goodState = this->stateCheck(s);
			}
			state = mp[s];
			exitParam = true;
		}else if(inputStr.compare("Age") == 0){
			//Get age
			string inputStr;
			int a;
			cout << "You must be at least 18 years old. Age: " << endl;
			cin >> inputStr;
			stringstream newAge(inputStr);

			newAge >> a;
			bool properAge;
			
			if(a < 18){
				properAge = false;
			}else{
				properAge  = true;
			}
			while(properAge == false){
				
				cout << "You must be at least 18 years old. Age: " << endl;
				cin >> inputStr;
				stringstream newAge(inputStr);
				newAge >> a;
				
				if(a >= 18){
					properAge = true;
				}
			}
			age = a;
			exitParam  = true;
		}else if(inputStr.compare("Name") == 0){
			string lName;
			cout << "Last Name: " << endl;
			cin >> lName; 
			//Check the last name
			bool goodLName = this->nameCheck(lName);
			while(goodLName == false){
				cout << "Please enter a valid name. " << endl;
				cout << "Last Name: ";
				cin >> lName; 	
				goodLName = this->nameCheck(lName);
			}
			lastName = lName;

			//Get first name
			string fName;
			cout << "First Name: " << endl;
			cin >> fName;
			//Check first name
			bool goodFName = this->nameCheck(fName);
			while(goodFName == false){
				cout << "Please enter a valid name. " << endl;
				cout << "First Name: ";
				cin >> fName;
				goodFName = this->nameCheck(fName); 	
			}
			firstName = fName;
			exitParam = true;

		}else if(inputStr.compare("Exit") == 0){
			exitParam = true;
		}else{
			cout << "You did not enter a valid command. Please try again." << endl;
		}
	}
}

void Donor::Passwd(){
	string oldPass;
	string newPass;
	string newPass2;

	cout << "What is your old password?" << endl;
	cin >> oldPass;
	if(oldPass.compare(this->getPass()) == 0){
		cout << "What do you want your new password to be? " << endl;
		cin >> newPass;
		bool goodPass = this->passCheck(newPass);
		while(goodPass == false){
			cout << "This is not a valid password. Please try again." << endl;
			cout << "What do you want your new password to be? " << endl;
			cin >> newPass;
			goodPass = this->passCheck(newPass);
		}
		cout << "Please type the new password again." << endl;
		cin >> newPass2;
		if(newPass.compare(newPass2) == 0){
			cout << "Your password has been changed." << endl;
			pass = newPass;
		}else{
			cout << "The new passwords did not match" << endl;
		}
	}else{
		cout << "That password is incorrect." << endl;	
	}
}

//Show all of the user the information you currently have
void Donor::View(){
	cout << "Username: " << id << endl;
	cout << "Password: " << pass << endl;
	cout << "Name: " << firstName << " " << lastName 
															<< endl;
	cout << "Age: " << age << endl;
	cout << "Street: " << streetName << " " << streetNum
															<< endl;
	cout << "Town: " << town << endl;
	cout << "Zip Code: " << zip << endl;

	std::map<State, string> mp;
			mp[NY] = "NY";
			mp[PA] = "PA";
			mp[RI] = "RI";
			mp[NH] = "NH";
			mp[VT] = "VT";
			mp[MA] = "MA";
			mp[CT] = "CT";
			mp[ME] = "ME";
	cout << "State: " << mp[state] << endl;
	cout << fixed << setprecision(2) << "Total Donations: $" << (float)donations << endl << endl;
}

//Accounting for the users donation
void Donor::Donate(float monies){
	donations += monies;
	cout << fixed << setprecision(2) << "$" << (float)monies << " donated." << endl;
}

//Print the total donations the donor has
void Donor::Total(){
	cout << lastName << " "
	 << fixed << setprecision(2) << "Total = $"<< (float)donations << endl;
}

//Return the username of the Donor
string Donor::getUser(){
	return id;
}
//Return the password of the Donor
string Donor::getPass(){
	return pass;
}
//Check the name given
bool Donor::nameCheck(string name){
	string::iterator it;
	for(it = name.begin(); it < name.end(); it++){
		if(isalpha(*it) == false){
			return false; 	
		}	
	}
	return true;
}

//Check the street name
bool Donor::streetCheck(string street){
	if(street.length() == 0){
		return false;
	}
	string::iterator it;
	for(it = street.begin(); it < street.end(); it++){
		if(!(isalpha(*it)) && !(isspace(*it))){
			return false; 	
		}
	}
	return true;
}

//Check the incoming zip code
bool Donor::zipCheck(string zip){
	if(zip.length() != 5){
		return false;
	}
	string::iterator it;
	for(it = zip.begin(); it < zip.end(); it++){
		if(isdigit(*it) != true){
			return false;
		}
	}
	return true;
}

//Check the incoming state: NY, PA, RI, NH, VT, MA, CT, ME
bool Donor::stateCheck(string s){
	if(s.compare("NY") == 0 || s.compare("PA") == 0 || s.compare("RI") == 0 ||\
		s.compare("NH") == 0 || s.compare("VT") == 0 || s.compare("MA") == 0 ||\
		s.compare("CT") == 0 || s.compare("ME") == 0){
		return true;
	}else{
		return false;
	}
}

bool Donor::passCheck(string pass){
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

string Donor::getInfo(){
	string donationString;
	stringstream stream1;
	stream1 << fixed << setprecision(2) << donations;
	donationString = stream1.str();

	string zipString;
	stringstream stream2;
	stream2 << zip;
	zipString = stream2.str();

	string stateString;
	stringstream stream3;
	stream3 << state;
	stateString = stream3.str();

	string streetString;
	stringstream stream4;
	stream4 << streetNum;
	streetString = stream4.str();

	string ageString;
	stringstream stream5;
	stream5 << age;
	ageString = stream5.str();

	return (id + '\n' + pass + '\n' + firstName + '\n' + lastName + '\n' +
	ageString + '\n' + streetString + '\n' + streetName + '\n' + town + '\n' 
	+ stateString + '\n' + zipString + '\n' + donationString); 
}

float Donor::getDonations(){
	return this->donations;
}

void Donor::setPass(string password){
	pass = password;
}

string Donor::getLast(){
	return lastName;
}

int Donor::getDex(){
	return index;
}