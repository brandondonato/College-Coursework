#include "Dish.h"
#include "Node.h"

int main(){
	Dish dish = Dish();

	cout << "Inserting the following strings: alpaca, doo, man, rarr, for, loser, ala" << endl;
	//Listed next to the strings are the inserted orders
	dish.insert("alpaca"); //0
	dish.insert("doo"); //1
	dish.insert("man"); //2
	dish.insert("rarr"); //3
	dish.insert("for"); //4
	dish.insert("loser"); //5
	dish.insert("ala"); //6
	
	cout << "Sorting the heap" << endl;
	cout << "The format for the output will be: getshortest(), getfirst()" << endl << endl;
	dish.heapSort(dish.getSize());

	cout << "Capitalizing 'ala'" << endl;
	cout << "Expected Output: doo, Ala" << endl;
	dish.capitalize(6);
	cout << "Actual Output: ";
	cout << dish.getshortest() << ", " << dish.getfirst() << endl << endl;


	cout << "Allcaps string 'alpaca' " << endl;
	cout << "Expected Output: doo, ALPACA" << endl;
	dish.allcaps(0);
	cout << "Actual Output: ";
	cout << dish.getshortest() << ", " << dish.getfirst() << endl << endl;

	cout << "Truncating 'ALPACA' to 'AL' " << endl;
	cout << "Expected Output: AL, AL" << endl;
	dish.truncate(0, 2);
	cout << "Actual Output: ";
	cout << dish.getshortest() << ", " << dish.getfirst() << endl << endl;

	dish.printHeap();

	
	cout << "Looking for the following strings in order: rar, do, doo, AL" << endl;
	cout << "Expected output: -1, -1, 1, 0" << endl;
	int i = dish.find("rar");
	int j = dish.find("do");
	int k = dish.find("doo");
	int l = dish.find("AL");

	cout << "Actual Output: ";
	cout << i<< ", " << j << ", " << k << ", " << l << endl;

	return 0;
}
