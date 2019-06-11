#include <fstream>
#include <iomanip>
#include <iostream>
#include <time.h>

using namespace std;
/*
	LCS is used to calculate the longest common subsequence (LCS) between two strings.
	It will use memoization to determine if a past value has been calculated so it
	avoids doing work more than once. LCS uses recursion to solve for each value, and 
	checks the matrix variable to see if one of its subproblems has already been solved.
	If the values has not been solved for, it calculates it by adding 1 to the previous
	answer if the two values in the string are the same. 

	If the values in the string are not the same, the larger of the values between the
	cell to the left or the cell above the current cell will be used. Then, it will return 
	the current value stored at the indicies in order to recursively solve the problem.
	Eventually, it will return the max value of the matrix as the LCS between the 2 strings.	
	
	string X - This is the string from "filex.txt." It is assumed the length of the string
	is <= 100. This is one of the two strings used to determine the longest common
	subsequence.

	string Y - This is the string from "filey.txt." It is assumed the length of the string
	is <= 100. This is the other of the two strings used to calculate the longest common
	subsequence.

	int matrix[101][101] - This is the matrix that we will use to store cached 
	values that we have already determined. It is of size 101, assuming that the 
	size of the largest string we will handle will be 100. 

	int i - This is used as the index for string X. Assume it is <= 100.

	int j - This is used as the index for string Y. Assume it is <= 100.
*/
int LCS(string X, string Y, int i, int j, int matrix[101][101]){
	if(matrix[i][j] < 0){//Checks to see if the value is already in the table
		if(X[i] == Y[j]){ //If they are the same, add one to the previous answer
			matrix[i][j] = 1 + LCS(X, Y, i+1, j+1, matrix); 
		}else{ //Find the max between the one above or the one to the left on the table
			if(LCS(X, Y, i+1, j, matrix) > LCS(X, Y, i, j+1, matrix)){
				matrix[i][j] = LCS(X, Y, i+1, j, matrix); //Above is larger
			}else{
				matrix[i][j] = LCS(X, Y, i, j+1, matrix); //Left is larger
			}
		}
		return matrix[i][j]; //Return the new calculated value
	}else{
		return matrix[i][j]; //Return value if its already in the table
	}
}
/*
	initializeMatrix is used to initialize the matrix we will use for memoization. 
	We iterate through each cell in the matrix (that we will be using) and set the 
	value to -1 so when LCS reads the value in the matrix, we know we do not have 
	the value cached. We then set the values for the first row and first column of
	the matrix to 0, in order for the LCS algorithm to work.

	int matrix[101][101] - This is the matrix that we will use to store cached 
	values that we have already determined. It is of size 101, assuming that the 
	size of the largest string we will handle will be 100. 

	string X - This is the string from "filex.txt." It is assumed the length of the string
	is <= 100. This is one of the two strings used to determine the longest common
	subsequence.

	string Y - This is the string from "filey.txt." It is assumed the length of the string
	is <= 100. This is the other of the two strings used to calculate the longest common
	subsequence. 
*/
int initializeMatrix(int matrix[101][101], string X, string Y){
	for(int i = 0; i < X.length()+1; i++){//Initialize the new matrix to be -1
		for(int j = 0; j < Y.length()+1; j++){
			matrix[i][j] = -1;
		}
	}
	for(int i = 0; i < X.length(); i++){//Initialize first column as 0
		matrix[i][0] = 0;
	}
	for(int j = 0; j < Y.length(); j++){//Initialize first row as 0
		matrix[0][j] = 0;
	}
	return LCS(X, Y, 1, 1, matrix); //Call the LCS Phase
}


int main(int argc, char **argv){
	if(argc != 4){
		printf("Error: 3 arguments needed for program 1\n");
	}
	clock_t cycles; //Used for the timer
	string fileX = argv[1]; //The name for file x
	string fileY = argv[2]; //The name for file y
	string outputFile = argv[3]; //The name of the output file

	string strX = ""; //The string we will get from file x
	string strY = ""; //The string we will get from file y

	string inputLine; //The string we will use to parse through both files
	ifstream fileStream(fileX); //Open fileX
	while(getline(fileStream, inputLine)){
		strX = inputLine; //Get the string from fileX
	}
	fileStream.close(); //Close fileX

	fileStream.open(fileY); //Open fileY
	while(getline(fileStream, inputLine)){
		strY = inputLine; // Get the string from fileY
	}
	fileStream.close(); //Close fileY

	int matrix[101][101]; //Initialize the matrix
	//Start timer
	cycles = clock();
	int retValue = initializeMatrix(matrix, strX, strY); //Calculate the LCS value
	//Stop timer
	cycles = clock() - cycles;
	float runtime = (float)cycles/CLOCKS_PER_SEC; //Convert the runtime to seconds
	
	/* Debug values
	cout << retValue << endl;
	cout << runtime << endl;
	*/
	//Write everything to the output file
	ofstream output(outputFile); //Open a stream to write to the output file
	output << retValue << endl;  //Write the LCS value to the file
	//Write the algorithm time to the file
	output << fixed << setprecision(8) << runtime << " seconds." << endl;
	output.close(); //Close the file

	return 0;
}