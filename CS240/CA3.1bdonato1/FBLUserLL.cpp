#include "FBLUserLL.h"

FBLUserLL::FBLUserLL(){
	this->head = nullptr;
	this->tail = nullptr;
}

void FBLUserLL::addUser(string user, string pass, string fName, string lName){
	FBLUser *temp = new FBLUser(user, pass, fName, lName);
	if(this->head == nullptr){
		this->head = temp;
		this->tail = temp;
		temp = nullptr;
	}else{
		this->tail->nextUser = temp;
		this->tail = temp;
	} 
}

bool FBLUserLL::nameCheck(string user){
	for(FBLUser *next = this->head; next != nullptr; next = next->nextUser){
		if((user) == (next->userid)){
			return false;//Userid is taken
		}
	}
	return true;//Userid is NOT taken
}

bool FBLUserLL::login(string user, string pass){
	for(FBLUser *next = this->head; next != nullptr; next = next->nextUser){
		if((next->userid == user) && (next->password == pass)){
			return true; //Successful username and password match
		}
	}
	return false;
}

FBLUser* FBLUserLL::getUser(string user, string pass){
	for(FBLUser *next = this->head; next != nullptr; next = next->nextUser){
		if((next->userid == user) && (next->password == pass)){
			return next; //Successful username and password match
		}
	}
}
/*
void FBLUserLL::insertUser(FBLUser *user){
	if(user == nullptr){
		return false;
	}else{
		user->nextUser = head;
			
	}
	
}
*/
