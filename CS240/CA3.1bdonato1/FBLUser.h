#ifndef FBLUSER_H
#define FBLUSER_H
#include <iostream>
#include "FBLPostLL.h"

using namespace std;

class FBLPostLL;

class FBLUser{
	public:
		FBLUser(string user, string pass, string fName, string lName);
		FBLUser();

		void newPost(string text); //Adds a new post associated with this user
		
		FBLUser *nextUser; //Next user Node
		FBLPostLL *postList; //Post Linked List
		
		string userid;
		string password;
		string firstName;
		string lastName;
	private:
};
#endif
