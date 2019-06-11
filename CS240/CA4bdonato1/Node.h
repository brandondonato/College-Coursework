#include <iostream>
#include <locale>
#include <vector>

using namespace std;

class Node{
	public:
		//Constructors
		Node();
		Node(int i, string s);
	
		//Getters and Setters
		void setIndex(int i);
		int getIndex();
		
		void setString(string s);
		string getInfoStr();
	private:
		int indexNum;
		string infoStr;
};
