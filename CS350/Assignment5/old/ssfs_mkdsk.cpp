#include "inode.h"
#include "block.h"
#include <fstream>
#include <string>
#include <iostream>

using namespace std;

bool isPower(int n){
	if( (n!=0) && !(n & (n-1)) ) return true;
	else return false;
}

int main(int argc, char** argv) {
	int num_block;
	int block_size;
	string filename;
//------------------------------------Input Error checking-----------------------------------------------------------
	if(argc < 3) {
		cerr << "Error: number of arguments cannot be less than three." << endl;
		cerr << "ssfs_mkdsk <num_block> <block_size> < (optional) disk_file_name>" << endl;
		return 1;
	}
	if(argc > 4) {
		cerr << "Error: number of arguments cannot be greater than four." << endl;
		cerr << "ssfs_mkdsk <num_block> <block_size> < (optional) disk_file_name>" << endl;
		return 1;
	}
	if(argc == 3){
		try{
			num_block = stoi(argv[1]);
			block_size = stoi(argv[2]);
			filename = "DISK";
			
			if(!isPower(block_size)){
				cerr << "Error: <block_size> must be a power of 2." << endl; 
			}		
		}catch(std::invalid_argument& e){
			cerr << "Error: must specify an integer number for <num_block> and <block_size>" << endl;
			return 1;		
		}
	}else if(argc == 4){
		try{
			num_block = stoi(argv[1]);
			block_size = stoi(argv[2]);
			filename = argv[3];
			
			if(!isPower(block_size)){
				cerr << "Error: <block_size> must be a power of 2." << endl; 
			}		
		}catch(std::invalid_argument& e){
			cerr << "Error: must specify an integer number for <num_block> and <block_size>" << endl;
			return 1;		
		}
	}
//--------------------------------------Input Error Checking Ends--------------------------------------------------
	/*
	ofstream disk;
	disk.open(disk_name);
	disk.write();
	*/
}
