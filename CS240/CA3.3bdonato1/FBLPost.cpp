#include "FBLPost.h"

FBLPost::FBLPost(){
	nextPost = nullptr;
	numLikes = 0;
	commentList = new FBLCommentLL();
}
FBLPost::FBLPost(string text){
	post = text;
	nextPost = nullptr;
	numLikes = 0;
	commentList = new FBLCommentLL();
}
FBLPost::FBLPost(string text, string userID){
	post = text;
	authorUserName = userID;
	nextPost = nullptr;
	numLikes = 0;
	commentList = new FBLCommentLL();
}

int FBLPost::getNumLikes(){
	return this->numLikes;
}

void FBLPost::incrementLikes(){
	this->numLikes++;
}

void FBLPost::setAuthorUserName(string userID){
	this->authorUserName = userID;
}

string FBLPost::getAuthor(){
	return this->authorUserName;
}
