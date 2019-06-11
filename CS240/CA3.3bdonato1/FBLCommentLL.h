#ifndef FBLCOMMENTLL_H
#define FBLCOMMENTLL_H
#include <iostream>
#include "FBLComment.h"

using namespace std;

class FBLCommentLL{
	public:
		FBLCommentLL(); // Creates the linked list
		void addComment(string text, string userID); //Adds a post to the linked list
		void deleteTail();
		FBLComment* findComment(string comment);

		//Printing out the list
		void headToTailComments();
		void tailToHeadComments();

		FBLComment *listHead;
		FBLComment *listTail;
	private:
};
#endif
