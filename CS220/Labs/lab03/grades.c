#include <stdio.h>
#include <string.h>

#define MAXSTUDENTS 20
#define NAMELEN 12
#define NUMGRADES 9

char name[NAMELEN];
int getName();
int getGrade();
void printHeader();
void printSep();

/* Your main function goes here */

int getName() {
	/* --------------------------------------------------------------------------------------------------
		Reads the next name from stdin, and saves it in the "name" global array
		Returns a 1 if name has been updated, or 0 if there was a problem.
	-----------------------------------------------------------------------------------------------------*/
	if (1==scanf("%s ",name)) return 1;
	return 0;
}

int getGrade() {
	/* --------------------------------------------------------------------------------------------------
		Reads the next grade from stdin,
		Returns the grade if succesful, or -1 if there was a problem.
	-----------------------------------------------------------------------------------------------------*/
	int grade;
	if (1==scanf("%d ",&grade)) return grade;
	return -1;
}

void printHeader() {
	printf("%*s | %3s | %3s | %3s | %3s | %3s | %3s | %3s | %3s | %3s | %6s |\n",
		NAMELEN,"Name","HW1","HW2","HW3","LB1","LB2","LB3","TS1","TS2","TS3","Avg");
	printSep();
}

void printSep() {
	/* Print a separator row */
	int i;
	for(i=0;i<NAMELEN; i++) putchar('-');
	printf("-+-----+-----+-----+-----+-----+-----+-----+-----+-----+--------+\n");
}

int main(){
	char students[MAXSTUDENTS][NAMELEN];
	int grades[MAXSTUDENTS][NUMGRADES];
	int totNames = 0;
	int rowCount;
	int columnCount;
	float currentAvg;
	float highAvg;
	float lowAvg;
	int minSt;
	int maxSt;
	
	printHeader();
	for(rowCount = 0; rowCount < MAXSTUDENTS; rowCount++){
		if(getName()){ 
			for(columnCount = 0; columnCount < NUMGRADES; columnCount++){
				int grade = getGrade();
				students[rowCount][columnCount] = name[columnCount];				
				grades[rowCount][columnCount] = grade;
			}
			totNames += 1;	
		}
	}
	
	highAvg = 0; lowAvg = 100;
	for(rowCount = 0; rowCount < totNames; rowCount++){
		currentAvg = 0;
		printf("%*s |",NAMELEN,students[rowCount]);
		for(columnCount = 0; columnCount < NUMGRADES; columnCount++){
			currentAvg += grades[rowCount][columnCount];
			printf(" %3d |",grades[rowCount][columnCount]);
		}
		currentAvg = (currentAvg / NUMGRADES);
		printf(" %6.2f |\n",currentAvg);
		
		if(currentAvg < lowAvg){
			lowAvg = currentAvg;
			minSt = rowCount;
		}
		if(currentAvg > highAvg){
			highAvg = currentAvg;
			maxSt = rowCount;
		}
	}
	printSep();
	printf("Minimum avg=%6.2f earned by %s\n",lowAvg,students[minSt]);
	printf("Maximum avg=%6.2f earned by %s\n",highAvg,students[maxSt]);
	
	return 0;
}
