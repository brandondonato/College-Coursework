# cs240
This contains the code for projects and assignments from my cs240 class. 

The code for this class was all written in C/C++ where every single assignment file has a makefile and the source code included. 

There are a total of 8 assignments, where the details of each assignment are laid out below.

Assignment 0: This assignment was very primative and was an introduction to the submission system of the class.

Assignment 1: This was an assignment that helped me get a better handle on C++ as a language and creating my own makefiles. For this assignment I created a donor class that holds information such as name, age, and how much money each individual has donated over time.

Assignment 2: This assignment builds on the previous, creating a container to store each donor in a donor database, which requires a password and username. The container simply stores the users created from the previous assignment. By logging into the database with a username and password, one can edit the information in a single user stored in the database.

Assignment 3.1: The assignment is the beginning of a social media project that spans 3 total assignments. In this part, I create a user class, a class for a linked list of users and a class for posts created by each user. The linked list allows for an individual to login with a password and username. Each user can create a post and it will be stored as information that belongs to the user.

Assignment 3.2: In the second part of this overarching assignment, I created a friends list and user feed to the user class. The user feed will show all of the posts of the present logged in user, while also showing the posts of all of the user's friends. Each user has their own friend's list where the friends are stored in a vector.

Assignment 3.3: For the last part of the assignment, I created a class of comments and a class for a linked list of comments. Each post has a linked list of comments that each user can use to comment on the posts of other users. While a user is logged in, a user can go through their feed, post comments and read them from each post. 

Assignment 4: This assignment dealt with the implementation of a heap and ways to sort and parse information out of a heap. The heap takes in strings and implements functions such as: heapify, heapsort, and insert. The heap can return items such as: the smallest string and the first string by alphabetical order. 

Assignment 5: This assignment created a graph map from a text file of classes. The purpose of the graph map is to traverse the graph in a way such that it will allow someone to graduate, given the prerequisites, given in another text file, have all been met. The graph uses hash maps to store all of the information for each class, where all of the information concerning a class is also given in a text file. Based upon a given text file that shows a simulated class schedule for a student, the graph can tell you whether or not the student will graduate, based upon prerequisites and if they cannot graduate, how the student must fix their schedule in order to graduate.

