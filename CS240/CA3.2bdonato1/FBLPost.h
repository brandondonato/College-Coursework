#ifndef FBLPOST_H
#define FBLPOST_H
#include <iostream>

using namespace std;

class FBLPost{
	public:
		FBLPost();
		FBLPost(string text);

		FBLPost *nextPost;
		string post;
	private:
};
#endif
