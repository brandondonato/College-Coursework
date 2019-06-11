#include <stdlib.h>
#include <iostream>
#include <iomanip>
#include <typeinfo>
#include <map>
#include <string>
#include <fstream>
#include <sstream>
#include <iomanip>
#include <vector> 
#include <iterator>


using namespace std;

enum State{NY, PA, RI, NH, VT, MA, CT, ME};

class Donor
{
	public:
		Donor();
		Donor(string user, string password, int dex); //done
		Donor(string user, string password, string lName, string fName,
				int a, int sNum, string sName, string t, State s, string z, 
				float dono, int dex); //done
		void Passwd(); //done
		void Manage(); //done
		void View(); //done
		void Donate(float monies); //done
		void Total();//done
		void Add(); //done
		//User functions	
		string getUser(); //done
		string getPass(); //done
		bool nameCheck(string name); //done
		bool stateCheck(string s); //done
		bool zipCheck(string zip); //done
		bool streetCheck(string street); //done
		bool passCheck(string pass); //done
		string getInfo(); //done
		float getDonations(); // done
		void setPass(string password); // done
		string getLast(); //done
		int getDex();
	private:
		string lastName;
		string firstName;
		string id;
		string pass;
		int age;
		int streetNum;
		string streetName;
		string town;
		State state;
		string zip;
		float donations;
		int index;
};
