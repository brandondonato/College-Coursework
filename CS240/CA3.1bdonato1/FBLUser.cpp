#include "FBLUser.h"

FBLUser::FBLUser(string user, string pass, string fName, string lName){
	userid = user;
	password = pass;
	firstName = fName;
	lastName = lName;
	
	nextUser = nullptr;
	postList = new FBLPostLL();
}

FBLUser::FBLUser(){
	nextUser = nullptr;
	postList = new FBLPostLL();
}


