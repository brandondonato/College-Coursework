#include <fstream>
#include <iostream>
#include <stdlib.h>
#include <time.h>

using namespace std;

/*
Command line must have input in the following format:
	1. <executable> <output_file> <sparce> or <dense> or <random> <verticies> (OPT)
	OPTIONAL ARGUMENTS: #5 - <sparce_percentage> (y or n) or - <undirected>
*/
int main(int argc, char** argv){
	string filename; //This is the variable used to store the name of the file we will output to
	string graph_type; //This string will hold whether the graph is sparce, dense or randomly populated
	int num_verticies; //This variable is used to store the number of verticies for the graph
	int random_factor; //This variable is used to store the randomness of the graph
	int sparse_percentage = 20; //This will hold the number out where 1/sparce_percentage == chance for an edge
	bool undirected = false; //This will determine if the graph is directed or not

	if(argc < 4 || argc > 5){
		cerr << "Please enter a name to output the graph to," << 
		" whether the graph is sparce, dense, or random and the number of vertices you want." << endl;
		return 1; //Return with 1 for an error
	}else if(argc == 4){ 
		filename = argv[1]; //Get the output file name from the command line
		graph_type = argv[2]; //Get the type of graph from the command line
		if(graph_type == "Sparce" || graph_type == "sparce" || graph_type == "s"){
			cout << "SPARCE GRAPH";
			graph_type = "sparce"; //Set the bool to false for sparce
		}else if(graph_type == "Dense" || graph_type == "dense" || graph_type == "d"){
			cout << "DENSE GRAPH";
			graph_type = "dense"; //Set the bool to true for dense
		}else if(graph_type == "Random" || graph_type == "random" || graph_type == "r"){
			cout << "RANDOM GRAPH";
			graph_type = "random";
		}else{
			cerr << "Please enter a valid state (sparce or dense)." << endl;
			return 1; //Return with 1 for an error
		}
		num_verticies = stoi(argv[3]); //Get the number of verticies you want
		cout << " WITH " << num_verticies << " NODES." << endl;
	}else if(argc == 5){
		filename = argv[1]; //Get the output file name from the command line

		graph_type = argv[2]; //Get the type of graph from the command line
		if(graph_type == "Sparce" || graph_type == "sparce" || graph_type == "s"){
			cout << "SPARCE GRAPH";
			graph_type = "sparce"; //Set the bool to false for sparce
		}else if(graph_type == "Dense" || graph_type == "dense" || graph_type == "d"){
			cout << "DENSE GRAPH";
			graph_type = "dense"; //Set the bool to true for dense
		}else if(graph_type == "Random" || graph_type == "random" || graph_type == "r"){
			cout << "RANDOM GRAPH";
			graph_type = "random";
		}else{
			cerr << "Please enter a valid state (sparce or dense)." << endl;
			return 1; //Return with 1 for an error
		}

		num_verticies = stoi(argv[3]); //Get the number of verticies you want
		sparse_percentage = stoi(argv[4]); //Get the 1/sparce_percentage you want
		/*
		try{
			sparce_percentage = stoi(argv[4]); //Get the 1/sparce_percentage you want
		}catch(std::invalid_argument){
			string temp = argv[4]; //Holds the undirected argument
			if(temp == "y" || temp == "yes" || temp == "Yes"){
				undirected = true;
			}else{
				undirected = false;
			}
		}
		*/
		cout << " WITH " << num_verticies << " NODES AND A 1/" << sparse_percentage
		<< " CHANCE FOR AN EDGE."<< endl;
	}
//----------------------------Dense Graph Generation--------------------------------------------
	if(graph_type == "dense"){
		srand(time(NULL)); //Initialize the random seed
		int random_num; //This will hold the random number generated from the algorithm
		int random_neg; //This is used in case you generate negative edges

		ofstream outfile; //Create an object for the output stream
		outfile.open(filename); //Open up the file to write to
		outfile << num_verticies << endl; //Write the number of verticies to the file

		//Create a complete graph using the number of nodes given
		for(int i = 0; i < num_verticies; i++){//For each vertex, give a length to every other vertex
			for(int j = 0; j < num_verticies; j++){
				if(i==j){//Length from any vertex to itself is 0
					outfile << 0 << " "; //Write edge to file
				}else{ //Only positive edges
					random_num = (rand() % 1000) + 1; //Random number between 1 and 1000
					outfile << random_num << " "; //Write edge to file
				}
			}
			outfile << endl;
		}
		outfile.close(); //Make sure to close the file when done writing

	}else if (graph_type == "sparce"){
//----------------------------Sparse Graph Generation-------------------------------------------
		srand(time(NULL)); //Initialize the random seed
		int random_num; //This will hold the random number generated from the algorithm

		ofstream outfile; //Create an object for the output stream
		outfile.open(filename); //Open up the file to write to
		outfile << num_verticies << endl; //Write the number of verticies to the file

		//Create a sparce graph by creating a circular tree, and then iterating through again randomly adding edges
		for(int i = 0; i < num_verticies; i++){
			for(int j = 0; j < num_verticies; j++){
				if(i==j){//Length from any vertex to itself is 0
					outfile << 0 << " "; 
				}else{
					random_num = (rand() % sparse_percentage) + 1; //Random number between 1 and the percentage
					if(j == i+1){//This edge has to exist
						random_num = (rand() % 1000) + 1; //Randomly generate the edge length
						outfile << random_num << " ";  //Write edge to file
					}else if(random_num == sparse_percentage){// 1/percentage of the time, create a new edge
						random_num = (rand() % 1000) + 1; //Randomly generate the edge length
						outfile << random_num << " "; //Write edge to file
					}else {
						outfile << "INF" << " "; //Write INF for when an edge to a node does not exist
					}
				}
			}
			outfile << endl;
		}
		outfile.close();//Close the outfile when done writing to it
	}
	return 0;
}