#include "slots.h"
#include <stdio.h>

int main(int argc, char ** argv) {

	int bin;
	int done;
	int slots[NUMSLOTS] = {-1,-1,-1,-1};
	int i;
	//int binList[1000];
	int freq[100] = {0};
	
	initSlots();
	while(1==scanf("%d",&bin)) {
		if(-1==findSlot(bin)) {//Bin is not on worktable
			for(i = 0; i < sizeof(slots); i++){//Go through worktable
				done = 0;
				if(slots[i] == -1){//If slot is empty, use it
					getBin(bin, i);
					slots[i] = bin;
					freq[bin] += 1;
					done = 1;
					break;
				}
			}
			if(done != 1){
				for(i = 0; i < sizeof(slots); i++){
					if(freq[bin] >= freq[slots[i]]){
						getBin(bin, i);
						slots[i] = bin;
						freq[bin] += 1;
						done = 1;
						break;
					}else if(i == 3) {
						getBin(bin, 1);
						slots[1] = bin;
						freq[bin] += 1;
						done = 1;
						break;
					}
				}
			}/*
			if(done != 1){
				getBin(bin, 1);
				slots[1] = bin;
				freq[bin] += 1;
			}*/
		}else{
			freq[bin] += 1;
		}
		getWidget(bin);
	}
	printEarnings();
	return 0;
}

