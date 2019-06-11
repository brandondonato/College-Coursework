#ifndef FBLPOSTLL_H
#define FBLPOSTLL_H
#include <iostream>
#include "FBLPost.h"

using namespace std;

class FBLPostLL{
	public:
		FBLPostLL(); // Creates the linked list
		void addPost(string text, string userID); //Adds a post to the linked list
		void deleteTail();
		FBLPost* findPost(string post);

		FBLPost *listHead;
		FBLPost *listTail;
	private:
};
#endif
