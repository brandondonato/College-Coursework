#include "FBLCommentLL.h"

FBLCommentLL::FBLCommentLL(){
	this->listHead = nullptr;
	this->listTail = nullptr;
}

void FBLCommentLL::addComment(string text, string userID){
	FBLComment *temp = new FBLComment(text, userID);
	if(this->listHead == nullptr){
		this->listHead = temp;
		this->listTail = temp;
		temp = nullptr;
	}else{
		this->listTail->nextComment = temp;
		//Set the previous tail to the previous comment of the new tail
		this->listTail->nextComment->previousComment = this->listTail;
		//Set the new tail
		this->listTail = temp;
	} 
}

void FBLCommentLL::deleteTail(){
	if(this->listTail == nullptr){
		return;
	}else{
		if(this->listHead == this->listTail){
			this->listHead = nullptr;
			this->listTail = nullptr;
		}else{
			FBLComment *temp = this->listHead;
			while(temp->nextComment != this->listTail){
				temp = temp->nextComment;
			}
			this->listTail = temp;
			this->listTail->nextComment = nullptr;
		}
	}
}

FBLComment* FBLCommentLL::findComment(string postCompare){
	for(FBLComment *next = this->listHead; next != nullptr; next = next->nextComment){
		if(next->comment == postCompare){
			return next;
		}
	}	
}

void FBLCommentLL::headToTailComments(){
	for(FBLComment *next = this->listHead; next != nullptr; next = next->nextComment){
		cout << next->comment << endl;
	}	
}

void FBLCommentLL::tailToHeadComments(){
	for(FBLComment *next = this->listTail; next != nullptr; next = next->previousComment){
		cout << next->comment << endl;
	}	
}


