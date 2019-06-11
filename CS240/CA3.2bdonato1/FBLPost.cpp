#include "FBLPost.h"

FBLPost::FBLPost(){
	nextPost = nullptr;
}

FBLPost::FBLPost(string text){
	post = text;
	nextPost = nullptr;
}