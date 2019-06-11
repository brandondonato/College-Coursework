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
		//Getters for the user values
		string getUsername();
		string getPassword();
		string getFullName();
		string getUserInfo();
		string getLastName();
		FBLUser* getNextUser();
		FBLUser* getPreviousUser();
		FBLPostLL* getPostList();
		FBLPostLL* getFeedList();
		vector<FBLUser*> getFriendsList();
		int getPostListLength();


		//Setters for the user values
		void setNextUser(FBLUser *newUser);
		void setPreviousUser(FBLUser *previousUser);
		void incrementPosts();
		void decrementPosts();
		void incrementFeed();
		void decrementFeed();
		void setNullUser();
		//void newPost(string text); //Adds a new post associated with this user
		void addFriend(FBLUser *newFriend);
		void addToFeed(FBLUser *newPost);
		vector<FBLUser*> friendsList;

		//vector<FBLUser*> feedList;

	private:
		FBLUser *nextUser; //Next user Node
		FBLUser *previousUser; //Last user Node
		FBLPostLL *postList; //Post Linked List
		FBLPostLL *feedList; //User's feed
		
		string userid;
		string password;
		string firstName;
		string lastName;
		int postListLength;
		int feedListLength;
		//Vector of friends
		//vector<FBLUser*> friendsList;
};
#endif
