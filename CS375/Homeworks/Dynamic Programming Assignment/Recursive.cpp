#include <fstream>
#include <iomanip>
#include <iostream>
#include <time.h>

using namespace std;

/*	
	Method LCS is used to calculate the longest common subsequence(LCS) of two strings. Using recursion
	the method calls itself until the indicies (int i, j) have reached 0, thus 
	reaching the end of one of the strings. At this point, the recursion ends, and 
	the LCS is determined by the recursion collapsing upon itself. 

	string X - This is the string from "filex.txt." It is assumed the length of the string
	is <= 100. This is one of the two strings used to determine the longest common
	subsequence.

	string Y - This is the string from "filey.txt." It is assumed the length of the string
	is <= 100. This is the other of the two strings used to calculate the longest common
	subsequence.

	int i - This is used as the index for string X. Assume it is <= 100.

	int j - This is used as the index for string Y. Assume it is <= 100.
*/
int LCS(string X, string Y, int i, int j){
	if(i == 0 || j == 0){
		return 0;
	}else if(X[i-1] == Y[j-1]){
		return 1 + LCS(X,Y,i-1,j-1);
	}else{
		//Create variables so the program doesn't have to perform the recursion twice
		int left = LCS(X, Y, i, j-1); //This points to what would be the left index
		int above = LCS(X, Y, i-1, j); //This points to what would be the above index
		if(left > above){
			return left;
		}else{
			return above;
		}
	}
}

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
	fileStream.close();

	fileStream.open(fileY); //Open fileY
	while(getline(fileStream, inputLine)){
		strY = inputLine; // Get the string from fileY
	}
	fileStream.close();

	int i = strX.length(); //Getting the length of string x
	int j = strY.length(); //Getting the length of string y
	//Begin timer
	cycles = clock();
	int retValue = LCS(strX, strY, i, j); //The length of the LCS is calculated here
	//End timer
	cycles = clock() - cycles;
	float runtime = (float)cycles/CLOCKS_PER_SEC; //Get runtime in seconds

	//Value of LCS for debug
	//cout << retValue << endl;

	ofstream output(outputFile); //Create an output stream to write to the output file
	output << retValue << endl;  //Write the LCS value
	output << fixed << setprecision(8) << runtime << " seconds." << endl; //Write the runtime
	output.close(); //Close the stream

	//Time in seconds for debug
	//cout << fixed << setprecision(8) << runtime << " seconds." << endl;

	return 0;
}
