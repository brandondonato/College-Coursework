#include "FBLUser.h"

FBLUser::FBLUser(string user, string pass, string fName, string lName){
	userid = user;
	password = pass;
	firstName = fName;
	lastName = lName;
	postListLength = 0;
	feedListLength = 0;
	
	nextUser = nullptr;
	previousUser = nullptr;
	postList = new FBLPostLL();
	feedList = new FBLPostLL();
}

FBLUser::FBLUser(){
	postListLength = 0;
	feedListLength = 0;

	nextUser = nullptr;
	previousUser = nullptr;
	postList = new FBLPostLL();
	feedList = new FBLPostLL();
}
//Getter functions//
string FBLUser::getUsername(){
	return this->userid;
}
string FBLUser::getPassword(){
	return this->password;
}
string FBLUser::getFullName(){
	string retString = this->firstName + " " + this->lastName;
	return retString;
}
string FBLUser::getUserInfo(){
	string retString = this->lastName + ", " + this->firstName + " <" + this->userid +">";
	return retString;
}
string FBLUser::getLastName(){
	return this->lastName;
}
FBLUser* FBLUser::getNextUser(){
	return this->nextUser;
}
FBLUser* FBLUser::getPreviousUser(){
	return this->previousUser;
}
FBLPostLL* FBLUser::getPostList(){
	return this->postList;
}
FBLPostLL* FBLUser::getFeedList(){
	return this->feedList;
}
vector<FBLUser*> FBLUser::getFriendsList(){
	return this->friendsList;
}
int FBLUser::getPostListLength(){
	return this->postListLength;
}

//Setter functions//
void FBLUser::setNextUser(FBLUser *newUser){
	this->nextUser = newUser;
}
void FBLUser::setPreviousUser(FBLUser *previous){
	this->previousUser = previous;
}
void FBLUser::addFriend(FBLUser *newFriend){
	this->friendsList.push_back(newFriend);
}
//void FBLUser::addToFeed(FBLPost *newPost){
//	this->feedList->addPost(*newPost);
//}
void FBLUser::incrementPosts(){
	this->postListLength++;
}
void FBLUser::decrementPosts(){
	this->postListLength--;
}
void FBLUser::incrementFeed(){
	this->feedListLength++;
}
void FBLUser::decrementFeed(){
	this->feedListLength--;
}
void FBLUser::setNullUser(){
	this->nextUser = nullptr;
}






