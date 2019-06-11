#ifndef FBLPOST_H
#define FBLPOST_H
#include <iostream>
#include "FBLCommentLL.h"

using namespace std;

class FBLPost{
	public:
		FBLPost();
		FBLPost(string text);
		FBLPost(string text, string userID);
		//Getters and Setters
		int getNumLikes();
		void incrementLikes();
		void setAuthorUserName(string userID);
		string getAuthor();

		FBLPost *nextPost;
		FBLCommentLL *commentList;
		string post;
	private:
		int numLikes;
		string authorUserName;
};
#endif
