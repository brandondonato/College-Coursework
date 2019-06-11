#include "FBLUser.h"

FBLUser::FBLUser(string user, string pass, string fName, string lName){
	userid = user;
	password = pass;
	firstName = fName;
	lastName = lName;
	postListLength = 0;
	
	nextUser = nullptr;
	postList = new FBLPostLL();
	//feedList = new FBLUserFeedLL();
}

FBLUser::FBLUser(){
	postListLength = 0;
	nextUser = nullptr;
	postList = new FBLPostLL();
	//feedList = new FBLUserFeedLL();
}
//Getters and setters
string FBLUser::getUsername(){
	return this->userid;
}
string FBLUser::getPassword(){
	return this->password;
}
FBLUser* FBLUser::getNextUser(){
	return this->nextUser;
}
FBLPostLL* FBLUser::getPostList(){
	return this->postList;
}
vector<FBLUser*> FBLUser::getFriendsList(){
	return this->friendsList;
}
int FBLUser::getPostListLength(){
	return this->postListLength;
}
//FBLUserFeedLL* FBLUser::getFeedList(){
//	return this->feedList;
//}

void FBLUser::setNextUser(FBLUser *newUser){
	this->nextUser = newUser;
}

void FBLUser::addFriend(FBLUser *newFriend){
	this->friendsList.push_back(newFriend);
}
void FBLUser::addToFeed(FBLUser *newPost){
	this->feedList.push_back(newPost);
}

void FBLUser::incrementPosts(){
	this->postListLength++;
}
void FBLUser::decrementPosts(){
	this->postListLength--;
}






