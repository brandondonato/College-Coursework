#include "FBLComment.h"

FBLComment::FBLComment(){
	nextComment = nullptr;
	previousComment = nullptr;
}
FBLComment::FBLComment(string text){
	comment = text;
	nextComment = nullptr;
	previousComment = nullptr;
}
FBLComment::FBLComment(string text, string userID){
	comment = text;
	authorUserName = userID;
	nextComment = nullptr;
	previousComment = nullptr;
}
FBLComment::FBLComment(string text, string userID, string fName, string lName){
	comment = text;
	authorUserName = userID;
	nextComment = nullptr;
	previousComment = nullptr;
	firstName = fName;
	lastName = lName;
}
//Getters and Setters
void FBLComment::setAuthorUserName(string userID){
	this->authorUserName = userID;
}
void FBLComment::setFirstName(string fName){
	this->firstName = fName;
}
void FBLComment::setLastName(string lName){
	this->lastName = lName;
}
string FBLComment::getAuthor(){
	return this->authorUserName;
}
string FBLComment::getFirstName(){
	return this->firstName;
}
string FBLComment::getLastName(){
	return this->lastName;
}
