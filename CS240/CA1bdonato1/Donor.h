#include <stdlib.h>
#include <iostream>
#include <iomanip>

using namespace std;

class Donor
{
	public:
		Donor();
		void Add();
		void Manage();
		void View();
		void Donate();
		void Total();
	private:
		string lastName;
		string firstName;
		int age;
		int streetNum;
		string streetName;
		string town;
		string zip;
		float donations;
};