#ifndef FBLUSERLL_H
#define FBLUSERLL_H
#include <iostream>
#include "FBLUser.h"

using namespace std;

class FBLUserLL{
	public:
		FBLUserLL(); //Creates the head and tail of the User Linked List
		void addUser(string user, string pass, string fName, string lName);
			//Adds user to the end of the list
		void insertUser(string user, string pass, string fName, string lName, int index); 
		//Adds user	to the linked list at a specific postion
		bool nameCheck(string user); //Checks to see if id has been used already
		bool login(string user, string password);

		void setHead(FBLUser *head);
		void setTail(FBLUser *tail);
		FBLUser* getUserFriend(string user);
		FBLUser* getHead();
		FBLUser* getTail();
		FBLUser* getUser(string user, string pass);
	private:
		FBLUser *head; //Head
		FBLUser *tail; //Tail
};
#endif
