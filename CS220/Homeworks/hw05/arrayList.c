#include "arrayList.h"
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <assert.h>
#define INITSIZE 16

bool arrayListEnlarge(arrayList list);

arrayList arrayListCreate() {
	arrayList list = (arrayList)malloc(sizeof(struct arrayListStruct));
	if (list==NULL) return list;
	list->data=(int *)malloc(sizeof(int)*INITSIZE);
	list->numUsed=0;
	list->numAlloc=INITSIZE;
	return list;
}

bool arrayListEnlarge(arrayList list) {
	list->data=(int *)realloc(list->data,sizeof(int) * (2*list->numAlloc));
	if (list->data==NULL) return false;
	list->numAlloc *=2;
	return true;
}

/* Put definitions of the other arrayList methods in arrayList.h here */

void arrayListFree(arrayList list) {
	free(list->data);
	free(list);
}

bool arrayListAdd(arrayList list, int item){
	if(list->numAlloc >= INITSIZE){
		arrayListEnlarge(list);
		list->numAlloc *=2;
	}
	int *temp = list->data;
	temp += list->numUsed;
	*temp = item;
	list->numUsed += 1;
	return true;
}

void arrayListClear(arrayList list){
	int i;
	int *temp = list->data; 

	while(i != list->numUsed){
		*temp = 0;
		temp+=1;
		i+=1;
	}
	list->numUsed = 0;
}

bool arrayListContains(arrayList list, int item){
	int *temp = list->data;
	int i = 0;

	while(i != list->numUsed){
		if(*temp == item){
			return true;
		}
		i++;
		temp++;
	}
	return false;
}

int arrayListGet(arrayList list, int index){
	assert(index >= 0 && index < list->numUsed);
	int *temp = list->data;

	temp += index;
	return *temp;
}

int arrayListIndexOf(arrayList list, int item){
	int *temp = list->data; 
	int retVal = -1;
	int count = 0;
	
	while(count < list->numUsed){
		if(*temp == item){
			retVal = count;
			return retVal;
		}
		count+=1;
		temp+=1;
	}
	return retVal;
}

bool arrayListIsEmpty(arrayList list){
	if(list->numUsed != 0){
		return true;
	}else{
		return false;
	}
}

int arrayListSet(arrayList list, int index, int item){
	assert(index >=0 && index < list->numUsed);//Not outside of the array;
	int *temp = list->data;
	int retVal;
	
	temp += index;
	retVal = *temp;
	*temp = item;

	return retVal;
}

int arrayListSize(arrayList list){
	return list->numUsed;
}

char * arrayListToString(arrayList list,char *buffer){
	int *temp = list->data;
	//int inx = 0;
	int i;
	//int j;
	//char newStr[256];
	buffer[0] = '0';
	for(i = 0; i < strlen(buffer); i++){
		buffer[i] = 0;
	}
	strcat(buffer, "[");
	for(i = 0; i < list->numUsed; i++){
		char newStr[32] = {0};
		sprintf(newStr, "%d", *temp);
		strcat(buffer, newStr);
		if(i != list->numUsed - 1){
			strcat(buffer, ", ");
		}
		temp+=1;
	}
	strcat(buffer, "]\0");
	return buffer;

}
