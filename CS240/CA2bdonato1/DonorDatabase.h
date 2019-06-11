#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <typeinfo>
#include <map>
#include <string>
#include "Donor.h"
#include <fstream>
#include <iomanip>
#include <sstream>

using namespace std;

class DonorDatabase{
	public:
		DonorDatabase(int maxDonors); //dome
		DonorDatabase(int maxDonors, string filename); //done
		bool Login(string userid, string pass); //done
		void Add(); //done
		void Save(string filename); 
		void Load(string filename);
		void Report();
		//void Quit();
		//Verification Functions
		bool PassCheck(string pass); //done
		bool userCheck(string name); //done
		bool nameCheck(string name); //done
		bool zipCheck(string zip); //done
		void runDatabase(int maxDonors);
		Donor* getDonors(); // done
	private:
		int totalDonors;
		int maxDonors;
		string tempUser;
		string tempPass;
		Donor *donorArray;
};
