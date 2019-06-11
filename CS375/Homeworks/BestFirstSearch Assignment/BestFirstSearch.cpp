#include <algorithm>
#include <fstream>
#include <iomanip>
#include <iostream>
#include <queue>
#include <string>
#include <unordered_map>
#include <vector>


using namespace std;
/*
This is a struct used to help with the solution of the branch and bound problem as well as the sorting problem 
earlier on. It is used to both store information valuable to the branch and bound problem as well as for 
sorting the items by profit/weight ratio

It uses a operator overload to create a comparison between other nodes in order to be properly selected from
the priority queue. It contains no other functions.
*/
struct Node{
	//BRANCH AND BOUNC INFORMATION
	int profit; //This is the profit found inside this node
	int weight; //This is the weight found inside this node
	float bound; //This is the upper bound from this node and to its children node

	vector<int> itemList; //This is the list of items that the node has used
	int nodeNum; //This is the number of the node (the number in order visited)
	int nodeLevel; //This is the level of the node (Which level of the tree it is on)
	
	int totNodes; //This is for the final node to get the total nodes visited
	int totLeaves; //This is for the final node to get the total leaves visited
	//SORTING INFORMATION
	float ratio; //This is the ratio of profit/weight of a given item
	int index; //This is the index into the profit and weight array of a given item

	//This function is used in conjunction with the priority queue from the C++ library
	bool operator<(const Node &otherNode) const{
		return (bound < otherNode.bound);
	}
};
/*
	computeBound is used to compute the bound at each node during the branch-and-bound process. It uses 
	the concept of fractional knapsack in order to get the most efficient bound. Because we are solving
	a 0-1 Knapsack Problem, the best possible solution to the 0-1 problem is less than or equal to the 
	Fractional Knapsack solution.

	This computes the bound by seeing if the knapsack can hold the given item (check its weight). If it
	can hold the item, then add that item to the knapsack, increase the weight by the item's weight and
	increase the bound by the profit of the item. If the knapsack runs out of items, the loop stops. If
	an item cannot fit in the knapsack, you take the fraction of the item that does fit. You add the 
	fraction of the item that does fit, multiply that by its profit and add that to the bound. Then 
	increase the weight to the max capacity (C). When the loop stops, return the bound.

	int itemNum - This is the current item number we are evaluating, starting at item 0. We assume the 
					items are numbered from in decreasing order with the greatest profit/weight first.
	int weight - This is the current weight of the knapsack. This weight should be < the capacity of the 
					knapsack. 
	int profit - This is the current weight of the knapsack. This profit is used to determine the upper
					bound since the profit is less than or equal to the upper bound.
	int sortedP[] - This is the array of the profits. We assume this list is in the same decreasing order
					as the itemNum so that sortedP[itemNum] corresponds to the profit of itemNum
	int sortedW[] - This is the array of the weights. We assume this list is in the same decreasing order
					as the itemNum so that sortedW[itemNum] corresponds to the weight of itemNum
	int C - This is the maximum capacity of the knapsack. 
	int n - This is the total number of items we can attempt to add to the knapsack.
*/
float computeBound(int itemNum, int weight, int profit, int sortedP[], int sortedW[], int C, int n){
	float bound = (float)profit; //Create the bound variable that we will compute
	//cout << "Computing Bound with " << itemNum << " items used" << endl;
	if(weight >= C){
		return 0;
	}else{
		while((itemNum <= n) && (weight + sortedW[itemNum] <= C)){
			//cout << "Item number: " << itemNum << endl;
			weight = weight + sortedW[itemNum]; //Update the weight
			bound = bound + sortedP[itemNum]; //Update the bound (profit)
			//cout << "New Weight: " << weight << " New Bound: " << bound << endl;
			itemNum++; //Go on to the next item
		}
		if(itemNum < n){//Items are still left, so you need a fraction
			//cout << "Item number for fractional part of the bound: " << itemNum << endl;
			//Max out the bound using a factional part of the next item
			bound = bound + ((C - weight) * (float)sortedP[itemNum]/sortedW[itemNum]);
			weight = C;
			//cout << "Final Weight: " << weight << " Final Bound: " << bound << endl;
		}
		return bound;
	}
}
/*
	BestFirstBranchAndBound is used to perform a branch-and-bound algorithm that also abides by the best-first-search
	concept.

	This method creates a node tree beginning at the root and attempts to get the best possible profit to weight 
	based on solving the 0-1 knapsack problem. The method utilizes a priority queue in order to ensure the tree
	searches in the most efficient manner, expanding only nodes that could lead to a better solution until the optimal
	solution is found.

	int sortedP[] - This is the array of the profits. We assume this list is in the same decreasing order
					as the itemNum so that sortedP[itemNum] corresponds to the profit of itemNum
	int sortedW[] - This is the array of the weights. We assume this list is in the same decreasing order
					as the itemNum so that sortedW[itemNum] corresponds to the weight of itemNum
	int weight - This is the current weight of the knapsack. This weight should be < the capacity of the 
					knapsack.
	int numItems - This is the number of items that exist for the given knapsack problem
	int maxProfit - This is the current maximum profit that the exists for the given problem
	int maxWeight - This is the maximum capacity of the given knapsack for the current problem
	Node finalSolution - This is the node returned by the function that contains all of the information on
							the best possible solution for this problem
*/
Node BestFirstBranchAndBound(int sortedP[], int sortedW[], int weight, int numItems, int maxProfit, int maxWeight, Node finalSolution){
	int finalSize = 0; //Create the final size of the knapsack
	int numNodes = 1; //Create the counter for the number of nodes visited (1 for root)
	int numLeaves = 0; //Create the counter for the number of leaves visited
	priority_queue<Node> Queue; //Create the queue used for the nodes when choosing which one to traverse next

	maxProfit = 0; //Set the maximum profit earned equal to 0 since you have not filled the knapsack


//Set up and initialize the nodes used to traverse through the tree
	Node u,v; //Create nodes u and v, where u will be used for the active traversal and v is the root
	
	for(int i = 0; i < numItems; i++){
		u.itemList.push_back(0); //Fill the vector with all 0s (no items in knapsack)
	}
	
	v.profit = 0; //Set the profit of the first node to 0
	v.weight = 0; //Set the weight of the first node to 0
	v.nodeNum = 1; //Set the number of the node (when it was visited)
	for(int i = 0; i < numItems; i++){
		v.itemList.push_back(0); //Fill the vector with all 0s (no items in knapsack)
	}
	//Calculate the bound of the node v and store it inside the node
	//	Use 0 as the itemNum since this is technically the first item(0 indexable items)
	v.bound = computeBound(0, weight, maxProfit, sortedP, sortedW, maxWeight, numItems);
	Queue.push(v); //Place v into the queue
	
	while(Queue.empty() == false){
		v = Queue.top(); //Set v as the Node with highest priority based on its bound
		Queue.pop(); //Remove this node from the Queue since we've already explored it

		if(v.bound > maxProfit){
			//Expand v if it can lead to a better solution
			u.nodeLevel = v.nodeLevel + 1; //Its on the level below v
			u.nodeNum = v.nodeNum + 1; //Its the next node in the traversal
			numNodes++;
			
//This is the node that takes the item on its level and adds it to the knapsack
			u.weight = v.weight + sortedW[u.nodeLevel-1]; //Update the weight to the child node
			u.profit = v.profit + sortedP[u.nodeLevel-1]; //Update the profit of the child node
			u.itemList = v.itemList; //Set the item list the same as its parent

			if((u.weight <= maxWeight) && (u.profit > maxProfit)){
				maxProfit = u.profit; //Update the max profit if it is higher
				u.itemList[u.nodeLevel-1] = 1; //This indicates the item at this index was used
				finalSolution = u;
			}

			//If the bound is higher than the maxProfit at this time, the node is 
			//	promising, so add it to the queue
			u.bound = v.bound;
			if(u.bound > maxProfit && u.weight < maxWeight){
				Queue.push(u);
			}else{
				numLeaves++; //This becomes a leaf because it is not expanded
			}
			
//This is the node that chooses to omit this item from the knapsack
			u.nodeNum = v.nodeNum + 2; //Its the second node in the traversal
			numNodes++;
			u.weight = v.weight; //Update the weight to the child node
			u.profit = v.profit; //Update the profit to the child node
			u.itemList = v.itemList; //Set the item list the same as its parent
			
			//If the bound is higher than the maxProfit at this time, the node is 
			//	promising, so add it to the queue
			u.bound = computeBound(u.nodeLevel, u.weight, u.profit, sortedP, sortedW, maxWeight, numItems);
			cout << "No Node: " << u.profit << " " << u.weight << " " << u.bound << " " << u.nodeLevel << " " << endl;
			
			if(u.bound > maxProfit && u.weight < maxWeight){
				Queue.push(u);
			}else{
				numLeaves++; //This becomes a leaf because it is not expanded
			}
			
		}else{//Node is not expanded
			numLeaves++;
		}
	}//Queue is empty
	finalSolution.totNodes = numNodes; //Set number of nodes visited
	finalSolution.totLeaves = numLeaves; //Set number of leaves visited
	return finalSolution;
}

/*
This function is used in conjunction with the C++ sort method.
It just compares whether or not a variable is greater than the other

I assume both parameters are floats and are the profits/weights to sort the items

Node i - This is used for the first item that is compared
Node j - This is used for the second item that is compared
*/
bool sortComparison(Node i, Node j){
	return (i.ratio > j.ratio);
}

int main(int argc, char** argv){
	string infile = argv[1]; //The name for the input file
	string outfile = argv[2]; //The name for the output file

	int numItems; //This is the number of items we will have for the knapsack problem
	int sizeKnap; //This is the total capacity of the knapsack
	int count = 0; //Used to keep track which file line we are on
	string inputLine; //The string we will use to parse the input file
	int *profit; //Create a dynamically allocated array of profits
	int *weight; //Create a dynamically allocated array of weights
	Node *ratios; //Create a dynamically allocated array of Nodes storing ratios and indicies
	
//Begin reading from the file
	ifstream fileStream(infile); //Open the input file
	while(getline(fileStream, inputLine)){ //Read only the first line
		float ratio; //This will be used to store the ratio of profit to weight for each item
		string item; //Used for storing the weight and profit before passing it into the vector
		stringstream ss(inputLine); //Turn the string into a stream to get items we need without ','
		
		if(count == 0){ //If this is the first line, get the total items and the max size
			if(getline(ss,item,',')){
				numItems = stoi(item); //Get the number of the total items as an int
				profit = new int[numItems];//Set the size of the profit array
				weight = new int[numItems];//Set the size of the weight array 
				ratios = new Node[numItems]; //Set the size of the profit/weight ratio array
				//cout << "Number of items: " << numItems << endl;
			}
			if(getline(ss,item,',')){	
				sizeKnap = stoi(item); //Set the max capacity of the knapsack
				//cout << "Size of knapsack: " << sizeKnap << endl;
			}
			count++;//Increment the line count
		}else{ //Every line in the file except the first
			int tempW; //This is the temporary weight before stored in the array
			int tempP; //This is the temporary profit before stored in the array
			float tempR; //This is the temporary profit/weight ratio before stored in the Node
			Node tempN; //This is the temporary Node used to store ratio and index before being put into the array
			if(getline(ss,item,',')){
				stringstream lineSS(item); //Turn the line into a string stream
				lineSS >> tempW; //Get the weight from the file
				weight[count-1] = tempW; //Store the weight in the weight array
				//cout << "Item Weight: " << tempW;
			}
			if(getline(ss,item,',')){
				stringstream lineSS(item); //Turn the line into a string stream
				lineSS >> tempP; //Get the profit from the file
				profit[count-1] = tempP; //Store the profit in the profit array
				//cout << " Item Profit: " << tempP << endl;	
			}
			tempR = (float)profit[count-1]/weight[count-1]; //Calculate the profit to weight ratio
			tempN.ratio = tempR; //Set the ratio inside the node
			tempN.index = count-1; //Set the index inside the node
			ratios[count-1] = tempN; //Place the temporary node inside the array
			count++; //Increment the line count
		}
	}
	/*
	cout << "Items and Nodes from the arrays:" << endl;
	for(int i = 0; i < numItems; i++){
		cout << weight[i] << " " << profit[i] << endl;
		cout << "Node info: " << ratios[i].ratio << " " << ratios[i].index << endl << endl;
	}
	*/
//Sort the array of ratios and then properly order the weight and ratios
	/*
	cout << endl << "Before the sorting: " << endl;
	for(int i = 0; i < numItems; i++){
		cout << "Node info: " << ratios[i].ratio << " " << ratios[i].index << endl;
	}
	cout << endl;
	*/

	//Sort the Nodes by the ratios
	sort(ratios, ratios+numItems, sortComparison);
	
	//cout << "After the sorting: " << endl;
	for(int i = 0; i < numItems; i++){
	//	cout << "Node info: " << ratios[i].ratio << " " << ratios[i].index << endl;
	}
	//cout << endl;
	

	int sortedP[numItems]; //The sorted array of item profits in decreasing order
	int sortedW[numItems]; //The sorted array of item weights in decreasing order

	//cout << "Sorted order from the sortedW and sortedP arrays: " << endl;
	for(int i = 0; i < numItems; i++){//Iterate through the ratios array
		sortedW[i] = weight[ratios[i].index]; //Using the index of the sorted Node, place the weight in the sortedW array
		sortedP[i] = profit[ratios[i].index]; //Using the index of the sorted Node, place the profit in the sortedP array

		//cout << sortedW[i] << " " << sortedP[i] << endl;
	}
	//cout << endl;
	//Free up the dynamically allocated memory since we are done using it
	/*
	delete weight;
	delete profit;
	delete ratios;
	*/


//Do the Best-First-Search
	int currentWeight = 0; //Set the current weight of the knapsack
	int currentProfit = 0; //Set the current profit of the knapsack 
	Node finalSolution; //Keep track of the best node and all of its information
	//This should return the best node and all of the info should be stored inside
	finalSolution = BestFirstBranchAndBound(sortedP, sortedW, currentWeight, numItems, currentProfit, sizeKnap, finalSolution);

//Write to output file
	ofstream outputFile(outfile); //Open up the output file
	int finalSize = 0; //This gets the final size of the knapsack
	//Calculate final knapsack size
	for(int i = 0; i < finalSolution.itemList.size(); i++){
		if(finalSolution.itemList[i] == 1){
			finalSize++;
		}
	}
	//Write everything to the output file
	outputFile << numItems << "," << finalSolution.profit << "," << finalSize << endl;
	outputFile << finalSolution.totNodes << "," << finalSolution.totLeaves << endl;
	for(int i = 0; i < finalSolution.itemList.size(); i++){
		if(finalSolution.itemList[i] == 1){
			outputFile << sortedW[i] << "," << sortedP[i] << endl;
		}
	}
	outputFile.close();

	return 0;
}