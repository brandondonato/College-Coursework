//#ifdef CA3_CPP
//#define CA3_CPP
#include "FBLUser.h"
#include "FBLUserLL.h"
#include "FBLPost.h"
#include "FBLPostLL.h"
#include <sstream>
#include <ostream>
#include <iostream>
#include <string>
#include <vector>

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

	string input1[5];
	string input2[2];
	int inputIndex = 0;

	FBLUserLL *linkedList = new FBLUserLL(); //The actual linked list

	while(menuOne == true){
		menuTwo = true;
		cout << "Please enter a command: "<<endl<<"CREATE <Userid> <Password> <First> <Last>"
		<<endl<<"LOGIN <Userid> <Password>" <<endl<<"QUIT" << endl << endl;
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

					cout << input2[0] << " " << input2[1] << endl;

					if(input2[0] == "POST" && (sizeof(input2)/sizeof(input2[0]) == 2)){
						//Add the post to the linked list
						user->getPostList()->addPost(input2[1]);
						//Increment the amount of posts a user has
						user->incrementPosts();
						cout << "Succesfully posted." << endl << endl;
						user->feedList.push_back(user); //Add post to the user's feed

						//Clear the list
						for(int i = 0; i < 2; i++){
							input2[i].clear();
						}
					}else if(input2[0] == "READ"){
						FBLPost *temp = user->getPostList()->listHead;
						if(temp == nullptr){//If there are no posts left
							cout << "This user has no posts" << endl << endl;
						}else{//Delete and read the post
							while(temp->nextPost != nullptr){
								temp = temp->nextPost;
							}
							cout << temp->post << endl;
							user->getPostList()->deleteTail();
						}
						user->decrementPosts();
						for(int i = 0; i < 1; i++){
								input2[i].clear();
							}
					}else if(input2[0] == "LOGOUT"){
						menuTwo = false;
						cout<< "Succesfully logged out." << endl << endl;
					}else if(input2[0] == "FRIEND"){
						string friendID = input2[1];
						if(friendID == user->getUsername()){//You cannot friend yourself
							cout << "You cannot friend yourself." << endl << endl;
						}else if(linkedList->nameCheck(friendID) == false){//Name exists
							user->friendsList.push_back(linkedList->getUserFriend(friendID));
							cout << "Succesfully added friend!" << endl << endl;
						}else{
							cout << "No such user exists" << endl << endl;
						}
					}else if(input2[0] == "MYFRIENDS"){
						cout << "These are your friends:" << endl;
						//Iterates through the list to show all friends
						for(int i = 0; i < user->friendsList.size(); i++){
							cout << user->friendsList.at(i)->getUsername() << endl;
						}
					}else if(input2[0] == "MYWALL"){
						//Iterate and show each user's posts
						cout << "Your posts: " << endl;
						for(FBLPost *next = user->getPostList()->listHead;
							next != nullptr; next = next->nextPost){
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
						//For each user in the feed vector
						cout << "Your feed: " << endl;
						for(int i = 0; i < user->friendsList.size(); i++){
							//Getting each user to go through
							FBLUser *temp = user->friendsList.at(i);
							//cout << " " << temp->getUsername() << endl;
							//For each person in the list 
							cout << temp->getUsername() << ":" << endl;
							for(FBLPost *next = temp->getPostList()->listHead;
								next != nullptr; next = next->nextPost){
								//Print the posts
								cout << next->post << endl;
							}
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
		}else{
			cout << "Please enter a valid command."<< endl << endl;
		}
	}
	return 1;
}
//#endif
