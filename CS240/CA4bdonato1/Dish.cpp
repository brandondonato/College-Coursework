#include "Dish.h"

Dish::Dish(){
	this->size = 0;
}

//Heap Functions for strings
void Dish::minHeapify(int parentIndex, int size){
	int leftChild = 2*parentIndex + 1;
	int rightChild = 2*parentIndex + 2;
	int smallestAlpha = parentIndex;
	int smallestLen = parentIndex;
	//Debug
	//cout << leftChild << " "<< rightChild << endl;

	if(leftChild < this->size && strOrder.at(alphaHeap.at(leftChild)) 
		< strOrder.at(alphaHeap.at(smallestAlpha))){
		//If the left child is smaller, make it smallest
		smallestAlpha = leftChild;
	}else{
		//Else, make the parent smallest
		smallestAlpha = parentIndex;
	}

	if(leftChild < this->size && strOrder.at(lenHeap.at(leftChild)).length() 
		< strOrder.at(lenHeap.at(smallestLen)).length()){
		//If the left child is smaller, make it smallest
		smallestLen = leftChild;
	}else{
		//Else, make the parent smallest
		smallestLen = parentIndex;
	}

	if(rightChild < this->size && strOrder.at(alphaHeap.at(rightChild)) 
		< strOrder.at(alphaHeap.at(smallestAlpha))){
		//If the right child is smaller than the parent, the right child is smallest
		smallestAlpha = rightChild;
	}


	if(rightChild < this->size && strOrder.at(lenHeap.at(rightChild)).length() 
		< strOrder.at(lenHeap.at(smallestLen)).length()){
		//If the right child is smaller than the parent, the right child is smallest
		smallestLen = rightChild;
	}
	
	//Recursive length
	if(smallestLen != parentIndex){
		//Swap the smallest with the parent
		int tempI = this->lenHeap.at(parentIndex);
		this->lenHeap.at(parentIndex) = this->lenHeap.at(smallestLen);
		this->lenHeap.at(smallestLen) = tempI;
		//Continue recursively
		minHeapify(smallestLen, size);
	}
	
	//Recursive alphabetical
	if(smallestAlpha != parentIndex){
		//Swap the smallest with the parent
		int tempI = this->alphaHeap.at(parentIndex);
		this->alphaHeap.at(parentIndex) = this->alphaHeap.at(smallestAlpha);
		this->alphaHeap.at(smallestAlpha) = tempI;
		//Continue recursively
		minHeapify(smallestAlpha, size);
	}
}

void Dish::heapSort(int size){
	//Build the actual heap
	for(int i = (size/2)-1; i>=0; i--){
		minHeapify(i, size);
	}

	for(int i=size-1; i>=0; i--){
		//Move the current root to the end
		int tempI = this->alphaHeap.at(0);
		this->alphaHeap.at(0) = this->alphaHeap.at(i);
		this->alphaHeap.at(i) = tempI;	
		//minHeapify the reduced heap
		minHeapify(0,i);
	}

	for(int i=size-1; i>=0; i--){
		//Move the current root to the end
		int tempI = this->lenHeap.at(0);
		this->lenHeap.at(0) = this->lenHeap.at(i);
		this->lenHeap.at(i) = tempI;	
		//minHeapify the reduced heap
		minHeapify(0,i);
	}
	for(int i = 0; i < this->size; i++){
		string s = this->strOrder.at(i);
		//Find the indicies of each
		int lengthLoc = lenHeapFind(s);
		int alphaLoc = alphaHeapFind(s);
		this->lenLocation.push_back(lengthLoc);
		this->alphaLocation.push_back(alphaLoc);
	}
}

//Assignment Functions
int Dish::insert(string s){
	//Inserts a string to the end of the heap
	if(this->size >= 1024){
		return -1;
	}else{
		this->pushStr(s);
		//Sort the heap
		heapSort(this->size);
		return this->size;
	}
}

int Dish::find(string s){
	//Finds the string in the heap and returns the address in log(N) time
	for(int i = 0; i < this->size; i++){
		if(s == strOrder.at(i)){
			return i;
		}
	}
	return -1;
}

int Dish::alphaHeapFind(string s){
	//Find index value of the given string
	int indexValue;
	int alphaLocation;
	for(int i = 0; i < this->size; i++){
		if(s == strOrder.at(i)){
			indexValue = i;
		}
	}

	//Using the index value, find the index of the string in the alphabetical heap
	for(int i = 0; i < this->size; i++){
		if(indexValue == alphaHeap.at(i)){
			alphaLocation = alphaHeap.at(i);
		}
	}

	//Return the location of the string in the alphabetical heap
	return alphaLocation;
}

int Dish::lenHeapFind(string s){
	//Find index value of the given string
	int indexValue;
	int lenLocation;
	for(int i = 0; i < this->size; i++){
		if(s == strOrder.at(i)){
			indexValue = i;
		}
	}

	//Using the index value, find the index of the string in the alphabetical heap
	for(int i = 0; i < this->size; i++){
		if(indexValue == lenHeap.at(i)){
			lenLocation = lenHeap.at(i);
		}
	}

	//Return the location of the string in the alphabetical heap
	return lenLocation;
}

bool Dish::capitalize(int k){
	if(k >= strOrder.size()){
		return false;
	}else{
		//Go to the string at index k and capitalize it
		string capCopy = this->strOrder.at(k);
		//cout << capCopy << " ";
		capCopy[0] = toupper(capCopy[0]);

		//Print out the string to verify
		//cout << capCopy << endl;

		//Insert the copied string into the vector
		this->strOrder.at(k) = capCopy;

		//Heapify
		int heapifyNode = this->alphaLocation.at(k);
		cout << "Node: " << heapifyNode << endl << endl;
		minHeapify(0, this->size);
		return true;
	}
}
bool Dish::allcaps(int k){
	if(k >= strOrder.size()){
		return false;
	}else{
		//Create a copy of the string
		string modifyStr = this->strOrder.at(k);
		//cout << modifyStr << " ";
		//Go through each letter and capitalize the string
		for(int i = 0; i < modifyStr.length(); i++){
			modifyStr[i] = toupper(modifyStr[i]);
		}
		//Print the string to verify
		//cout << modifyStr << endl;

		//Insert the copied string back into the vector
		this->strOrder.at(k) = modifyStr;

		//Heapify
		int heapifyNode = this->alphaLocation.at(k+1);
		cout << "Node: " << heapifyNode << endl << endl;
		minHeapify(0, this->size);
		return true;
	}
}

bool Dish::truncate(int k, int i){
	if(k >= strOrder.size()){
		return false;
	}else{
		//Create a copy of the string
		string modifyStr = this->strOrder.at(k);
		//cout << modifyStr << " ";
		//Verify the length of the string is > i
		if(modifyStr.length() > i){
			//Create a new string
			string newStr;
			//Add up to i letters in the new string
			for(int a = 0; a < i; a++){
				newStr += modifyStr[a];
			}
			//Print the string to verify
			//cout << newStr << endl;

			//Insert the new string back into the vector
			this->strOrder.at(k) = newStr;

			//Heapify
			int heapifyNode = this->lenLocation.at(k);
			cout << "Node: " << heapifyNode << endl << endl;
			minHeapify(0, this->size);
			return true;
		}
	}
}

string Dish::getshortest(){
	int index = lenHeap.at(0);
	string retString = this->strOrder.at(index);
	return retString;
}

string Dish::getfirst(){
	int index = alphaHeap.at(0);
	string retString = this->strOrder.at(index);
	return retString;
}

//Getters and Setters
void Dish::setSize(int i){
	this->size = i;
}
int Dish::getSize(){
	return this->size;
}
void Dish::pushStr(string s){
	this->strOrder.push_back(s);
	this->lenHeap.push_back(this->size);
	this->alphaHeap.push_back(this->size);
	this->size++;
}
void Dish::printHeap(){
	//In the format: String, lengthHeap position, alphabeticalHeap position
	for(int i = 0; i < this->size; i++){
		cout << this->strOrder.at(i) << " " << this->lenHeap.at(i) << " "
				 << this->alphaHeap.at(i) << endl;
	}
	cout << endl;
}

