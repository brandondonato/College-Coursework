#include "Donor.h"

int main(int argc, char *argv[]){
	Donor *donorPtr = new Donor();
	int exitParam = 1;
	string inputStr;
	while(exitParam != 0){
		cout << endl;
		cout << "Enter a command." << endl;
		cout << "Choose from [\"Add\", \"Manage\", \"View\", \"Donate\", \"Total\", \"Quit\"]" << endl;
		cout << endl << ": ";
		
		cin >> inputStr;
		if(argc > 1){ // Only need one command
			cout << "Sorry, command not recognized. Please try again." << endl;
		}else{ //If there is only one input
			if(inputStr.compare("Add") == 0){
				cout << endl;
				donorPtr->Add();
			}else if(inputStr.compare("Manage") == 0){
				cout << endl;
				donorPtr->Manage();
			}else if(inputStr.compare("View") == 0){
				cout << endl;
				donorPtr->View();
			}else if(inputStr.compare("Donate") == 0){
				cout << endl;
				donorPtr->Donate();
			}else if(inputStr.compare("Total") == 0){
				cout << endl;
				donorPtr->Total();
			}else if(inputStr.compare("Quit") == 0){
				cout << endl;
				exitParam = 0;
			}else{
				cout << endl;
				cout << "Sorry, command not recognized. Please try again." << endl;
			}
		}
	}
}