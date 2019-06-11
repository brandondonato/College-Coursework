#include "DonorDatabase.h"

DonorDatabase::DonorDatabase(int maxAmount){	
	this->donorArray = new Donor[maxAmount];
	totalDonors = 0;
	maxDonors = maxAmount;
}
DonorDatabase::DonorDatabase(int maxAmount, string filename){
	this->donorArray = new Donor[maxAmount];
	totalDonors = 0;
	Load(filename);
}

bool DonorDatabase::Login(string userid, string pass){
	for(int i = 0; i < totalDonors; i++){
		if(donorArray[i].getUser() == userid){
			if(donorArray[i].getPass() == pass){
				return true;				
			}
		}
	}
	cout << "Your userid is not valid, please try again. " << endl;
	return false;
}

Donor* DonorDatabase::getDonors(){
	return this->donorArray;
}

//Check to see if the password works and follows the qualifications
bool DonorDatabase::PassCheck(string pass){ // 
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

//Check the username
bool DonorDatabase::userCheck(string name){ //
	string::iterator it;
	if(name.length() < 5 || name.length() > 10){
		return false;
	}
	for(it = name.begin(); it < name.end(); it++){
		if(ispunct(*it) || isspace(*it)){	
			return false;
		}	
	}
	return true;
}

//Ask the user for all of the information 
void DonorDatabase::Add(){
	if(totalDonors == maxDonors){
		cout << "The list is full." << endl;
	}else{
		cout << "What username do you want?" << endl;
		cout << "Your username must be at least 5 characters long, but less than 10." << endl;
		cout << "It can only contain numbers and letters." << endl; 
		cin >> tempUser;

		//Check to see if valid username
		bool goodName = this->userCheck(tempUser);

		//Check to see if username is taken
		bool check = true;	
		for(int i = 0; i < totalDonors; i++){
			if(tempUser.compare(donorArray[i].getUser()) == 0){
				check = false;
			}
		}
		
		//If name is good and unused, continue on
		while(goodName == false || check == false){
			cout << endl << "Your username is not valid. Please try again." << endl;

			cout << "What username do you want?" << endl;
			cout << "Your username must be at least 5 characters long, but less than 10." << endl;
			cout << "It can only contain numbers and letters." << endl; 
			cin >> tempUser;

			//Check to see if valid username
			goodName = this->userCheck(tempUser);
		
			//Check to see if username is taken
			bool check;	
			for(int i = 0; i < totalDonors; i++){
				if(tempUser.compare(donorArray[i].getUser()) == 0){
					check = false;
				}else{
					check = true;
				} 
			}
		}
		
		//User gives a password and we check
		cout << endl << "What is your password?" << endl;
		cout << "Your password should be at least 6 characters long and should include " <<
		        "one digit and one symbol. " << endl << endl;
		cin >> tempPass;
		bool goodPass = this->PassCheck(tempPass);
			
		//If pass is good, continue with making the profile.
		while(goodPass == false){
			cout << "Your password is not valid. Please try making another." << endl;
			
			cout << "What is your password?" << endl;
			cout << "Your password should be at least 6 characters long and should include\
				one digit and one symbol. " << endl << endl;
			cin >> tempPass;
			goodPass = this->PassCheck(tempPass);
		}


		//Create the new donor
		if(totalDonors < maxDonors){
			Donor donor = Donor(tempUser, tempPass, totalDonors);
			donor.Add();
			donorArray[totalDonors] = donor;
		}

		//Add the donor to the array
		totalDonors++;
		if(totalDonors < maxDonors){
			//donorArray[totalDonors] = donor;
		}else{
			cout << "The list of donors is full" << endl;
		}
	}	
}

void DonorDatabase::Save(string filename){
	ofstream outputFile;
	outputFile.open(filename);

	outputFile << to_string(totalDonors) << endl;

	for(int i = 0; i < totalDonors; i++){
		Donor donor = donorArray[i];
		string donorInfo = donor.getInfo();
		outputFile << donorInfo << endl;
	} 
	outputFile.close();
}

void DonorDatabase::Load(string filename){
	string line;
	ifstream inFile;
	inFile.open(filename);
	int donorAmounts;
	string size;

	inFile >> size;
	istringstream buf(size);
	buf >> donorAmounts;

	if(this->maxDonors >= donorAmounts){
		totalDonors = donorAmounts;
	}else{
		donorAmounts = this->maxDonors;
	}
	//if(inFile.is_open()){
		for(int i = 0; i < donorAmounts; i++){
			//Create the donors here
			string last;
			string first;
			string id;
			string pass;
			int age;
			int sNum;
			string sName;
			string town;
			State state = NY;
			string zip;
			float donation;
			int totalDonors = 0;

			inFile >> id; //id
			inFile >> pass; //pass
			inFile >> first; //first name
			inFile >> last; // last name

			string temp;
			inFile >> temp; //age
			istringstream buf1(temp);
			buf1 >> age;

			string temp1;
			inFile >> temp1; //street number
			istringstream buf2(temp1);
			buf2 >> sNum;

			inFile.ignore();
			getline(inFile, sName); //street
			getline(inFile, town); //town



			std::map<string, State> mp;
			mp["NY"] = NY;
			mp["PA"] = PA;
			mp["RI"] = RI;
			mp["NH"] = NH;
			mp["VT"] = VT;
			mp["MA"] = MA;
			mp["CT"] = CT;
			mp["ME"] = ME;
			
			string temp2;
			inFile >> temp2;
			state = mp[temp2];

			inFile >> zip;

			string temp3;
			inFile >> temp3;
			istringstream buf3(temp3);
			buf3 >> donation;

			Donor donor = Donor(id, pass, last, first, age, sNum, sName, town, state, zip, donation, i);
			this->donorArray[i] = donor;
		}
		//inFile.close();
	//}else{
	//	cout << "Unable to open file" << endl;
	//}
}

void DonorDatabase::Report(){
	float totalDonations;
	cout <<"The total amount of donors is: " << totalDonors << endl;
	for(int i = 0; i < totalDonors; i++){
		totalDonations += this->donorArray[i].getDonations();
	}
	cout << fixed << setprecision(2) << "Total donations are: " << totalDonations << endl;
}


