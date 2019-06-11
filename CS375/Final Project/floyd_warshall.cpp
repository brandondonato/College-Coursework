#include <ctime>
#include <fstream>
#include <iostream>
#include <iomanip>
#include <sstream>
#include <vector>
#define INF 999999999
#define DEBUG 0

using namespace std;
/*
Method to find the minimum distances between each node within the graph using dynamic programming.
Check if the current node's edge to one node is greater than if the current node went through a different route to one node.
Needs a matrix and size of the matrix as parameters.
*/
void floyd_warshall_matrix(int** graph, int size) {
	for(int i = 0; i < size; i++) { //to calculate for other routes through other nodes
		for(int j = 0; j < size; j++) { //current node 
			for(int k = 0; k < size; k++) { //destination node's distance from other nodes
				if(graph[j][k] > graph[j][i] + graph[i][k]) { //relaxing the edges
					graph[j][k] = graph[j][i] + graph[i][k];
				}
			}
		}
		if(DEBUG) { //for printing values to debug
			cout <<"I = " << i  << ":" << endl;
			for(int i = 0; i < size; i++) {
				for(int j = 0; j < size; j++) {
					if(graph[i][j] == INF) cout << setw(5) << "INF" << " ";
					else cout << setw(5) << graph[i][j] << " ";
				}
				cout << endl;
			}
			cout << endl;
		}
	}
}

/*
Method to check if the file exists 
*/
bool file_exists(string filename) {
	ifstream infile(filename); //open the file
	return infile.good(); //returns true if it exists, false otherwise
}

/*
Command line can have either zero, one, two or three arguments 
	1. <executable> 
	2. <executable> <input_file>
	3. <executable> <input_file> <output_file>
*/
int main(int argc, char** argv) {	
	bool read_stdin = false; //whether to read from stdin
	bool read_file = false; //whether to read from input file that was specified
	bool write_to_file = false; //whether to write to output file that was specified
	//Default graph type is matrix
	bool graph_type = false; //The type of graph utilized matrix or adj. list (false = matrix, true = adj. list)
	double time_finished; //This will be the runtime of the algorithm
	double avg_time_finished = 0.0; //This will be the average runtime of the trials
	
	int size; //number of vertices in the graph (size)
	ifstream infile; //stores the input file
	
	if(argc == 1) { //if no arguments in command line, read from stdin
		cout << "Input number of vertices: ";
		cin >> size;
		read_stdin = true;
	}
	else if(argc < 4) { //check if it has arguments
		if(!file_exists(argv[1])) { //check if input file exists
			cerr << "Error: Input file does not exist." << endl;
			return 1;
		}
		infile.open(argv[1]); //Open the input file
		if(argc == 3) write_to_file = true; //if output file has been specified, otherwise print to stdout
		read_file = true; //Set the read file as true
		infile >> size; //read in first value of input file for size
	}
	else {
		cerr << "Error: Cannot have four arguments in command line." << endl;
		return 1;
	}
	
	//start reading and populating the graph
	int value; //value to be added into the graph
//---------------------------This is for the matrix representation-------------------------------
	//allocate memory for the graph
	int** graph = new int*[size]; //will be the adjacency matrix for the graph
	for(int i = 0; i < size; i++) { //loop lasts as long as there are verticies
		graph[i] = new int[size];
	}

	string temp; //used to store in string value read from either stdin or input file
	if(read_stdin) {
		for(int i = 0; i < size; i++) {
			cout << "Write values for row " << i + 1 << ":" << endl;
			for(int j = 0; j < size; j++) {
				cout << "Value " << j + 1 << ": ";
				cin >> temp;
				stringstream buf(temp); //used to convert string to int
				if(temp == "INF") value = INF;
				else buf >> value; //convert string to int
				graph[i][j] = value;
			}
			cout << endl;
		}
	}
	if(read_file) {
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				infile >> temp;
				stringstream buf(temp); //used to convert string to int
				if(temp == "INF") value = INF;
				else buf >> value; //convert string to int
				graph[i][j] = value;
			}
		}
	}
	
	//start writing updated values to either stdout or output file
	
	if(!write_to_file) {
		//print values in graph before floyd warshall to stdout
		/*
		cout << "BEFORE FLOYD WARSHALL:" << endl;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(graph[i][j] == INF) cout << setw(5) << "INF" << " ";
				else cout << setw(5) << graph[i][j] << " ";
			}
			cout << endl;
		}
		cout << endl;
		*/
		
		double trials[20]; //This will store the values of the 20 individual trials run
		for(int i = 0; i < 20; i++){
			//Begin the clock for the time of the algorithm
			clock_t begin = clock();
			floyd_warshall_matrix(graph, size); //find the minimum distance between all nodes
			//Get the end time of the algorithm
			clock_t end = clock();
			//This gets the runtime in seconds
			time_finished = double(end-begin)/CLOCKS_PER_SEC; 
			//Store the time in the array
			trials[i] = time_finished;
		}
		//Calculate the average of the times
		for(int i = 0; i < 20; i++){
			avg_time_finished = avg_time_finished + trials[i];
		}
		avg_time_finished = avg_time_finished/20.0;

		
		//print values in graph after floyd warshall to stdout
		/*
		cout << "AFTER FLOYD WARSHALL:" << endl;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(graph[i][j] == INF) cout << setw(5) << "INF" << " ";
				else cout << setw(5) << graph[i][j] << " ";
			}
			cout << endl;
		}
		*/
		
		for(int i = 0; i < 20; i++){
			cout << "Trial " << i << " " << trials[i] << endl;
		}
		cout << "Average time in seconds: " << avg_time_finished << endl;
	}else{
		//open output file
		ofstream outfile; //stores the ouput file
		outfile.open(argv[2]);
		
		//print values in graph before floyd warshall to output file
		/*outfile << "BEFORE FLOYD WARSHALL:" << endl;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(graph[i][j] == INF) outfile << setw(5) << "INF" << " ";
				else outfile << setw(5) << graph[i][j] << " ";
			}
			outfile << endl;
		}
		outfile << endl;
		*/
		double trials[20]; //This will store the values of the 20 individual trials run
		for(int i = 0; i < 20; i++){
			//Begin the clock for the time of the algorithm
			clock_t begin = clock();
			floyd_warshall_matrix(graph, size); //find the minimum distance between all nodes
			//Get the end time of the algorithm
			clock_t end = clock();
			//This gets the runtime in seconds
			time_finished = double(end-begin)/CLOCKS_PER_SEC; 
			//Store the time in the array
			trials[i] = time_finished;
		}
		//Calculate the average of the times
		for(int i = 0; i < 20; i++){
			avg_time_finished = avg_time_finished + trials[i];
		}
		avg_time_finished = avg_time_finished/20.0; 
		
		//print values in graph after floyd warshall to output file
		
		outfile << "AFTER FLOYD WARSHALL:" << endl;
		for(int i = 0; i < size; i++) {
			for(int j = 0; j < size; j++) {
				if(graph[i][j] == INF) outfile << setw(5) << "INF" << " ";
				else outfile << setw(5) << graph[i][j] << " ";
			}
			outfile << endl;
		}
	}
	//deallocate the adjacency matrix
	for(int i = 0; i < size; i++) {
		delete[] graph[i];
		graph[i] = nullptr; //set deallocated pointer to null
	}
	delete[] graph;
	graph = nullptr; //set deallocated pointer to null

	return 0;
}
