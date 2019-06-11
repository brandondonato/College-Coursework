#include "Donor.h"

Donor::Donor(){
	donations = 0.0;
}

//Ask the user for all of the information 
void Donor::Add(){
	cout << "Last Name: ";
	cin >> lastName; 
	
	cout << "First Name: ";
	cin >> firstName;

	cout << "Age: ";
	cin >> age;
	
	cout << "Street Name: ";
	cin >> streetName;
	
	cout << "House Number: ";
	cin >> streetNum;
	
	cout << "Town: ";
	cin >> town;
	
	cout << "Zip Code: ";
	cin >> zip;
}

//Show the user all of the information and allow changes
void Donor::Manage(){
	cout << "Last Name: ";
	cin >> lastName; 
	
	cout << "First Name: ";
	cin >> firstName;

	cout << "Age: ";
	cin >> age;
	
	cout << "Street Name: ";
	cin >> streetName;
	
	cout << "House Number: ";
	cin >> streetNum;
	
	cout << "Town: ";
	cin >> town;
	
	cout << "Zip Code: ";
	cin >> zip;
}

//Show all of the user the information you currently have
void Donor::View(){
	cout << "Name: " << firstName << " " << lastName 
															<< endl;
	cout << "Age: " << age << endl;
	cout << "Street: " << streetNum << " " << streetName
															<< endl;
	cout << "Town: " << town << endl;
	cout << "Zip Code: " << zip << endl;
	cout << fixed << setprecision(2) << "Total Donations: $" << (float)donations << endl;
}

//Accounting for the users donation
void Donor::Donate(){
	float i;
	cout << "How much money would you like to donate?" << endl;
	cin >> i;
	donations += i;
	cout << fixed << setprecision(2) << "$" << (float)i << " donated." << endl;
}

//Print the total donations the donor has
void Donor::Total(){
	cout << fixed << setprecision(2) << "Total = $"<< (float)donations << endl;
}