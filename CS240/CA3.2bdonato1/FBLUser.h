#ifndef FBLUSER_H
#define FBLUSER_H
#include <iostream>
#include "FBLPostLL.h"
#include "FBLPost.h"
#include <vector>

using namespace std;

class FBLPostLL;

class FBLUser{
	public:
		FBLUser(string user, string pass, string fName, string lName);
		FBLUser();
		//Getters
		string getUsername();
		string getPassword();
		FBLUser* getNextUser();
		FBLPostLL* getPostList();
		vector<FBLUser*> getFriendsList();
		int getPostListLength();
		//Setters
		void setNextUser(FBLUser *newUser);
		void incrementPosts();
		void decrementPosts();
		//void newPost(string text); //Adds a new post associated with this user
		void addFriend(FBLUser *newFriend);
		void addToFeed(FBLUser *newPost);
		vector<FBLUser*> friendsList;
		vector<FBLUser*> feedList;

	private:
		FBLUser *nextUser; //Next user Node
		FBLPostLL *postList; //Post Linked List
		
		string userid;
		string password;
		string firstName;
		string lastName;
		int postListLength;
		//Vector of friends
		//vector<FBLUser*> friendsList;
};
#endif
