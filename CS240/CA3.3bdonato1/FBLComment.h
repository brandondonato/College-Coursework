#ifndef FBLCOMMENT_H
#define FBLCOMMENT_H
#include <iostream>

using namespace std;

class FBLComment{
	public:
		FBLComment();
		FBLComment(string text);
		FBLComment(string text, string userID);
		FBLComment(string text, string userID, string fName, string lName);
		//Getters and Setters
		void setAuthorUserName(string userID);
		void setFirstName(string fName);
		void setLastName(string lName);
		string getAuthor();
		string getFirstName();
		string getLastName();

		FBLComment *nextComment;
		FBLComment *previousComment;
		string comment;
	private:
		string authorUserName;
		string firstName;
		string lastName;
};
#endif
