 #include "FBLPostLL.h"

FBLPostLL::FBLPostLL(){
	this->listHead = nullptr;
	this->listTail = nullptr;
}

void FBLPostLL::addPost(string text){
	FBLPost *temp = new FBLPost(text);
	if(this->listHead == nullptr){
		this->listHead = temp;
		this->listTail = temp;
		temp = nullptr;
	}else{
		this->listTail->nextPost = temp;
		this->listTail = temp;
	} 
}

void FBLPostLL::deleteTail(){
	if(this->listTail == nullptr){
		return;
	}else{
		if(this->listHead == this->listTail){
			this->listHead = nullptr;
			this->listTail = nullptr;
		}else{
			FBLPost *temp = this->listHead;
			while(temp->nextPost != this->listTail){
				temp = temp->nextPost;
			}
			this->listTail = temp;
			this->listTail->nextPost = nullptr;
		}
	}
}
