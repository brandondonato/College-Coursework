#include <fstream>
#include <iomanip>
#include <iostream>
#include <time.h>

using namespace std;

int main(int argc, char** argv){
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
	/*
	cout << strX << endl;
	cout << strY << endl;
	cout << strX.length() << endl;
	cout << strY.length() << endl;
	*/
	int matrix[strX.length()+1][strY.length()+1]; //MxN matrix for the table
	int arrowMatrix[strX.length()+1][strY.length()+1]; //The arrow matrix

	for(int i = 0; i < strX.length()+1; i++){//Initialize the first number in each row to 0
		matrix[i][0] = 0; 
		arrowMatrix[i][0] = -2;
	}
	for(int j = 0; j < strY.length()+1; j++){//Initizlize the first number in each column 0
		matrix[0][j] = 0;
		arrowMatrix[0][j] = -1;
	}
	
	cycles = clock();//Begin the timer
	for(int i = 1; i < strX.length()+1; i++){
		for(int j = 1; j < strY.length()+1; j++){
			//Use i-1, j-1 to index the string so you don't miss the first character
			if(strX[i-1] == strY[j-1]){//The are the same character
				matrix[i][j] = matrix[i-1][j-1] + 1;
				arrowMatrix[i][j] = j-1;//Stores index into the string
			}else if(matrix[i-1][j] >= matrix[i][j-1]){ //If the number above is larger
				matrix[i][j] = matrix[i-1][j];
				arrowMatrix[i][j] = -1;
			}else{ //If the number to the left is larger
				matrix[i][j] = matrix[i][j-1];
				arrowMatrix[i][j] = -2;
			}
		}
	}
	cycles = clock() - cycles;//Stop the timer with the difference (runtime)
	float runtime = (float)cycles/CLOCKS_PER_SEC; //Time in seconds
	//cout << runtime << endl;
	//cout << fixed << setprecision(6) << runtime << endl; 

	//Debug for viewing the matrix created by the bottom up algorithm
	/*
	for(int i = 0; i < strX.length()+1; i++){
		for(int j = 0; j < strY.length()+1; j++){
			cout << matrix[i][j] << " ";
		}
		cout << endl;
	}
	*/
	
	string retString; //This will be used to get the longest common substring
	string reverseString; //This will be the longest common substring, but in reverse order
	int i = strX.length(); //Get the length of string x
	int j = strY.length(); //Get the length of string y
	//Go through the arrowMatrix until the LCS is found
	while(i > 0 && j > 0){
		if(arrowMatrix[i][j] >= 0){ //The characters were the same
			//cout << strY[arrowMatrix[i][j]] << endl;
			reverseString = reverseString + strY[arrowMatrix[i][j]]; //Add the char to the reversed string
			i--; //Decrease rows by one
			j--; //Decrease columns by one
		}else if(arrowMatrix[i][j] == -1){ //The character points upwards
			i--; //Decrease rows by one
		}else{ //Character points leftwards
			j--; //Decrease columns by one
		}
	}
	//The substring was obtained in reverse order, so flip it so you have the proper substring
	for(int k = reverseString.length()-1; k > -1; k--){
		retString = retString + reverseString[k];
	}
	//Reversed substring
	//cout << reverseString << endl;
	//Substring in order
	//cout << retString << endl;
	
	
	if(strX.length() <= 10 || strY.length() <= 10){//Strings equal to or shorter than 10 characters
		ofstream output(outputFile); //Create a stream to write to the output file
		//Write the matrix created into the file
		for(int i = 0; i < strX.length()+1; i++){
			for(int j = 0; j < strY.length()+1; j++){
				output << matrix[i][j] << " ";
			}
			output << endl;
		}
		output << retString << endl; //Write the LCS to the file
		//Time in seconds
		output << fixed << setprecision(8) << runtime << " seconds." << endl; 
		output.close(); //Close the file

	}else{//Strings longer than 10 characters
		ofstream output(outputFile); //Create a stream to write to the output file
		//Store length of LCS
		output << matrix[strX.length()][strY.length()] << endl;
		//Time in seconds
		output << fixed << setprecision(8) << runtime << " seconds." << endl;
		output.close(); //Close the file
	}
	return 0;
}