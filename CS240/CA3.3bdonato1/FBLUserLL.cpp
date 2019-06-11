#include "FBLUserLL.h"

FBLUserLL::FBLUserLL(){
	this->head = nullptr;
	this->tail = nullptr;
}

FBLUser* FBLUserLL::getHead(){
	return this->head;
}
FBLUser* FBLUserLL::getTail(){
	return this->tail;
}
void FBLUserLL::setHead(FBLUser* newHead){
	this->head = newHead; 
}
void FBLUserLL::setTail(FBLUser* newTail){
	this->tail = newTail; 
}
void FBLUserLL::addUser(string user, string pass, string fName, string lName){
	FBLUser *temp = new FBLUser(user, pass, fName, lName);
	if(this->head == nullptr){
		this->head = temp;
		this->tail = temp;
		temp = nullptr;
	}else{
		this->tail->setNextUser(temp);
		this->tail->getNextUser()->setPreviousUser(this->tail);
		this->tail = temp;
	} 
}

bool FBLUserLL::nameCheck(string user){
	for(FBLUser *next = this->head; next != nullptr; next = next->getNextUser()){
		if((user) == (next->getUsername())){
			return false;//Userid is taken
		}
	}
	return true;//Userid is NOT taken
}

bool FBLUserLL::login(string user, string pass){
	for(FBLUser *next = this->head; next != nullptr; next = next->getNextUser()){
		if((next->getUsername() == user) && (next->getPassword() == pass)){
			return true; //Successful username and password match
		}
	}
	return false;
}

FBLUser* FBLUserLL::getUser(string user, string pass){
	for(FBLUser *next = this->head; next != nullptr; next = next->getNextUser()){
		if((next->getUsername() == user) && (next->getPassword() == pass)){
			return next; //Successful username and password match
		}
	}
}

FBLUser* FBLUserLL::getUserFriend(string user){
	for(FBLUser *next = this->head; next != nullptr; next = next->getNextUser()){
		if((next->getUsername() == user)){
			return next; //Successful username and password match
		}
	}
}

void FBLUserLL::printUsers(FBLUser *head){
	/*
	if(head == nullptr){
		return;
	}
	printUsers(head->getNextUser());
	cout << head->getUserInfo() << endl;
	*/
	
	
	for(FBLUser *next = this->head; next != nullptr; next = next->getNextUser()){
		cout << next->getUserInfo() << endl;
	}
	
}
//Source is the head of the list
void FBLUserLL::listSplit(FBLUser *source, FBLUser **frontAdd, FBLUser **backAdd){
	FBLUser *first, *second;
	//For case of less than 2 members in the linked list
	if(source == nullptr || source->getNextUser() == nullptr){
		*frontAdd = source;
		*backAdd = nullptr;
	}else{
		second = source;
		first = source->getNextUser();

		//First traverses ahead 2 users, second only traverses one
		//This puts second just before the middle and first at the end
		while(first != nullptr){
			first = first->getNextUser();
			if(first != nullptr){
				second = second->getNextUser();
				first = first->getNextUser();
			}
		}
		//Place second at the middle of the list, beginning a "new" list
		*frontAdd = source;
		*backAdd = second->getNextUser();
		second->setNullUser();
	}
}

FBLUser* FBLUserLL::merge(FBLUser *a, FBLUser *b){
	FBLUser *temp;

	//Base case conditions are satisfied
	if(a == nullptr){
		return b; 
	}else if(b == nullptr){
		return a;
	}
	//Make the comparison and call recursively
	//If the string is less, it is earlier in the alphabet
	if(a->getLastName() <= b->getLastName()){
		temp = a;
		temp->setNextUser(merge(a->getNextUser(), b));
	}else{
		temp = b;
		temp->setNextUser(merge(b->getNextUser(), a));
	}
	//Returns the sorted list
	return temp;
}

void FBLUserLL::mergeSort(FBLUser **headRef){
	FBLUser *tempHead = *headRef;
	FBLUser *a, *b;

	//Base case
	if(tempHead == nullptr || tempHead->getNextUser() == nullptr){
		return;
	}

	//Splits the head of the list into the 2 sublists
	this->listSplit(tempHead, &a, &b);

	//Sort the sublists
	this->mergeSort(&a);
	this->mergeSort(&b);

	//THE FINAL SORTED LIST
	*headRef = merge(a, b);
}

