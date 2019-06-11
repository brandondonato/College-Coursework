//#ifdef CA3_CPP
//#define CA3_CPP
#include "FBLUser.h"
#include "FBLUserLL.h"
#include "FBLPost.h"
#include "FBLPostLL.h"
#include "FBLComment.h"
#include "FBLCommentLL.h"
#include <sstream>
#include <ostream>
#include <iostream>
#include <string>
#include <vector>
void menuThree(FBLPost *thePost, FBLUserLL *linkedList){

	bool menuThree = true; //Menu 3 Exit param
	string threeInput; // Menu 3 input
	string input3[2]; //Menu 3 input list
	int inputIndex = 0;

	while(menuThree == true){
		//User's choice of commands aka Third Level Menu
		cout << "Please enter a command: "<<endl<<"LIKE"<<endl<<"COMMENT <text>"
		<<endl<<"READ_AZ"<< endl<<"READ_ZA"<<endl <<"DONE"<<endl<< endl;
		//Put the command in the 3rd level command list
		getline(cin, threeInput);
		stringstream ssin(threeInput);
		while(ssin.good() && (inputIndex < 1)){
			ssin >> input3[inputIndex];
			inputIndex++;
		}
		input3[inputIndex] = ssin.str(); //Rest of the post goes here
		if(input3[0] == "COMMENT"){
			input3[1] = input3[1].erase(0, 8);
		}
		inputIndex = 0;

		if(input3[0] == "LIKE"){
			//Find the original user
			FBLUser *tempUser = linkedList->getUserFriend(thePost->getAuthor());
			//Find the post that the original user made
			FBLPost *tempPost = tempUser->getPostList()->findPost(thePost->post);
			//Now like the post from the original user's wall
			tempPost->incrementLikes();

			//cout << "Likes from feed: " << thePost->getNumLikes() << endl;
			//cout << "Likes from original users post: " << tempPost->getNumLikes() << endl;
			
			cout << "Post liked." << endl << endl;
			//Clear the list
			for(int i = 0; i < 2; i++){
				input3[i].clear();
			}
		}else if(input3[0] == "COMMENT"){
			//Get the comment the user made
			string comment = input3[1];
			cout << comment << endl;

			//Find the original user
			FBLUser *tempUser = linkedList->getUserFriend(thePost->getAuthor());
			//Find the post that the original user made
			FBLPost *tempPost = tempUser->getPostList()->findPost(thePost->post);
			//Add the comment to the original post's comment list
			tempPost->commentList->addComment(comment, tempUser->getUsername());
			
			cout << "Comment successfully posted." << endl << endl;

			//Clear the list
			for(int i = 0; i < 2; i++){
				input3[i].clear();
			}
		}else if(input3[0] == "READ_AZ"){
			//View comments in the order posted by users(Head->Tail)
			//Find the original user
			FBLUser *tempUser = linkedList->getUserFriend(thePost->getAuthor());
			//Find the post that the original user made
			FBLPost *tempPost = tempUser->getPostList()->findPost(thePost->post);
			
			cout << "Here are the posts comments in chronological order:" << endl;
			tempPost->commentList->headToTailComments();
			cout << endl << endl;
			
			//Clear the list
			for(int i = 0; i < 2; i++){
				input3[i].clear();
			}
		}else if(input3[0] == "READ_ZA"){
			//View the comments where the most recent comment is first (Tail->Head)

			FBLUser *tempUser = linkedList->getUserFriend(thePost->getAuthor());
			//Find the post that the original user made
			FBLPost *tempPost = tempUser->getPostList()->findPost(thePost->post);
			
			cout << "Here are the posts comments in reverse chronological order:" << endl << endl;
			tempPost->commentList->tailToHeadComments();
			cout << endl << "Done." << endl << endl;
			
			//Clear the list
			for(int i = 0; i < 2; i++){
				input3[i].clear();
			}
		}else if(input3[0] == "DONE"){
			menuThree = false;
			cout << "Finished." << endl << endl;
		}else{
			cout << "Please enter a valid command." << endl << endl;
		}
	}
}

int main(){
	bool menuOne = true; //Menu 1 Exit param
	bool menuTwo = true; //Menu 2 Exit param

	string oneInput; // Menu 1 input
	string twoInput; // Menu 2 input

	string command; //Command Name
	string userId;
	string pass;
	string fName;
	string lName;

	string input1[5]; //Menu 1 input list
	string input2[2]; //Menu 2 input list

	int inputIndex = 0;

	FBLUserLL *linkedList = new FBLUserLL(); //The actual linked list

	while(menuOne == true){
		menuTwo = true;
		cout << "Please enter a command: "<<endl<<"CREATE <Userid> <Password> <First> <Last>"
		<<endl<<"LOGIN <Userid> <Password>" <<endl<<"QUIT" << endl<<"USERS" <<endl<<"SORTUSERS" 
		<<endl<< endl;
		//Fills the array with a list of commands
		getline(cin, oneInput);
		stringstream ssin(oneInput);
		while(ssin.good() && (inputIndex < 5)){
			ssin >> input1[inputIndex];
			inputIndex++;
		}
		inputIndex = 0;

		// cout << input1[0] << input1[1] << input1[2];
		if((input1[0] == "CREATE") && (sizeof(input1)/sizeof(input1[0]) == 5) ){
			userId = input1[1];
			pass = input1[2];
			fName = input1[3];
			lName = input1[4];
			//Create the new user
			if(linkedList->nameCheck(userId) == true){
				linkedList->addUser(userId, pass, fName, lName);
				cout << "Succesfully created new user." << endl;
			}else{
				cout << "This username has been taken. Please try again." << endl << endl;
			}
			
			//Clear the array
			for(int i = 0; i < 5; i++){
				input1[i].clear();
			}
		}else if((input1[0] == "LOGIN")){
			userId = input1[1];
			pass = input1[2];

			if(linkedList->login(userId, pass) == true){
				//Get the user we need for the posts
				FBLUser *user = linkedList->getUser(userId, pass);
				int inputIndex2 = 0;
				cout << "Login Successful." << endl << endl;
				while(menuTwo == true){
					//Second level menu
					cout << "Please enter a command: "<<endl<<"POST <text>"<<endl<<"READ"
					<<endl<<"LOGOUT"<< endl<<"FRIEND <userid>"<<endl <<"MYFRIENDS"<< endl << "MYFEED" 
					<< endl << "MYWALL"<< endl << endl;
					getline(cin, twoInput); //Gets just the command from the rest of the input
					stringstream ssin2(twoInput);
					while(ssin2.good() && (inputIndex2 < 1)){
						ssin2 >> input2[inputIndex2];
						inputIndex2++;
					}
					input2[inputIndex2] = ssin2.str(); //Rest of the post goes here
					inputIndex2 = 0; //Reset the index

					//Fix the post by removing POST/FRIEND from it
					if(input2[0] == "POST"){
						input2[1] = input2[1].erase(0, 5);
					}
					if(input2[0] == "FRIEND"){
						input2[1] = input2[1].erase(0, 7);
					}

					//cout << input2[0] << " " << input2[1] << endl;

					if(input2[0] == "POST" && (sizeof(input2)/sizeof(input2[0]) == 2)){
						//Add the post to the linked list
						user->getPostList()->addPost(input2[1], user->getUsername());
						//Increment the amount of posts a user has and give the post an author
						user->incrementPosts();
						//Add this post to the feed of all the user's current friends
						for(int i = 0; i < user->friendsList.size(); i++){
							user->friendsList.at(i)->getFeedList()->addPost(input2[1], user->getUsername());
							//Increment the length of the feed for the current friends of the user
							user->friendsList.at(i)->incrementFeed();
						}
						cout << "Succesfully posted." << endl << endl;
						//Get the post for menu three operations
						FBLPost *thePost = user->getPostList()->findPost(input2[1]);
						//call menu three
						menuThree(thePost, linkedList);

						//Remove this post from the current user's feed
						while(thePost->nextPost != nullptr){
							thePost = thePost->nextPost;
						}
						user->getFeedList()->deleteTail();
						user->decrementFeed();
						//Clear the list
						for(int i = 0; i < 2; i++){
							input2[i].clear();
						}
					}else if(input2[0] == "READ"){
						//First, get the post and display the post
						FBLPost *thePost = user->getFeedList()->listHead;
						if(thePost == nullptr){//If there are no posts left
							cout << "Nothing to READ." << endl << endl;
						}else{//Delete and read the post
							while(thePost->nextPost != nullptr){
								thePost = thePost->nextPost;
							}
							cout << thePost->post << endl;
							user->getFeedList()->deleteTail();
						}
						user->decrementFeed();
						//ENTER MENU THREE
						menuThree(thePost, linkedList);
						
						//Clear the list
						for(int i = 0; i < 1; i++){
								input2[i].clear();
							}
					}else if(input2[0] == "LOGOUT"){
						menuTwo = false;
						cout<< "Succesfully logged out." << endl << endl;
					}else if(input2[0] == "FRIEND"){
						string friendID = input2[1];
						if(friendID == user->getUsername()){
							//You cannot friend yourself
							cout << "You cannot friend yourself." << endl << endl;

							//Name exists
						}else if(linkedList->nameCheck(friendID) == false){
							//Get the other user
							FBLUser *otherUser = linkedList->getUserFriend(friendID);
							//Add this person to the friend's list of the logged in user
							user->friendsList.push_back(otherUser);
							//Add the logged in user to the friends list of the other user
							otherUser->friendsList.push_back(user);
							cout << "Succesfully added friend!" << endl << endl;
						}else{
							cout << "No such user exists" << endl << endl;
						}
					}else if(input2[0] == "MYFRIENDS"){
						cout << "These are your friends:" << endl;
						//Iterates through the list to show all friends
						for(int i = 0; i < user->friendsList.size(); i++){
							cout << user->friendsList.at(i)->getFullName() << endl;
						}
						cout << endl;
					}else if(input2[0] == "MYWALL"){
						//Iterate and show each user's posts
						cout << "Your posts: " << endl;
						for(FBLPost *next = user->getPostList()->listHead;
							next != nullptr; next = next->nextPost){
							cout << "Likes: " << next->getNumLikes() << " ";
							cout << next->post << endl;
						}
						cout << endl;
					}else if(input2[0] == "MYFEED"){
						//Show each users posts
						cout << "Your posts: " << endl;
						for(FBLPost *next = user->getPostList()->listHead;
							next != nullptr; next = next->nextPost){
							cout << next->post << endl;
						}
						//Show your feed linked list
						cout << "Your feed: " << endl;
						//Goes through the linked list to show all posts
						for(FBLPost *next = user->getFeedList()->listHead;
							next != nullptr; next = next->nextPost){
							//Print the posts
							cout << next->post << endl;
						}
						cout << endl;
						//Use the same method as above, but implement for each user
					}else{
						cout << "Please enter a valid command." << endl << endl;
					}
				}
			}else{
				cout << "Please enter a valid username and password" << endl << endl;
			}
			for(int i = 0; i < 3; i++){
				input1[i].clear();
			}
		}else if((input1[0] == "QUIT")){
			//Perform the exit operation
			menuOne = false;
			for(int i = 0; i < 1; i++){
				input1[i].clear();
			}
		}else if(input1[0] == "USERS"){
			//Call the linked list function for listing all users
			cout << "Here are the current users: " << endl <<endl;
			linkedList->printUsers(linkedList->head);
			cout<<endl;

		}else if(input1[0] == "SORTUSERS"){
			cout << "Sorted the users alphabetically by last name" << endl << endl;
			//FBLUser *temp = linkedList->getHead();
			linkedList->mergeSort(&linkedList->head);
		}else{
			cout << "Please enter a valid command."<< endl << endl;
		}
	}
	return 1;
}
//#endif
