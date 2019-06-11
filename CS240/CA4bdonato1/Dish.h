#include <iostream>
#include <locale>
#include <vector>

using namespace std;

class Dish{
	public:
		//Constructor
		Dish();
		//Heap functions

		//Heapsorting for shortest string
		void minHeapify(int parentIndex, int size); //Works
		void heapSort(int n);//Works

		//Heapsorting for alphabetical order
		void minHeapifyAlpha(int parentIndex, int size); //Works
		void heapSortAlpha(int n); //Works

		//Finding items in the heap
		int alphaHeapFind(string s);
		int lenHeapFind(string s);

		//Assignment fucntions
		int insert(string s); //Works
		int find(string s); //Works
		bool capitalize(int k); //Works
		bool allcaps(int k); //Works
		bool truncate(int k, int i);
		string getshortest(); //Works
		string getfirst(); //Works

		//Getters and Setters
		void setSize(int i);
		int getSize();
		void pushStr(string s);

		//Print function
		void printHeap();
		

	private:
		//Assignment variables
		vector<string> strOrder;
		vector<int> lenHeap;
		vector<int> alphaHeap;
		//Keeping track of the locations of each
		vector<int> lenLocation;
		vector<int> alphaLocation;
		int size;
};
