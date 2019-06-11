#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <math.h>

char inputCh[10] = "";
int inputI; 
int thousandC;
int hundC;
int tenC;
char returnCh[100] = "";

 
void SingleD(int inputI){
	if(inputI == 1 || inputI == 2 || inputI == 6){
		strcat(returnCh, "123");
	}else if(inputI == 0 || inputI == 4 || inputI == 5 || inputI == 9){
		strcat(returnCh, "nine");
	}else{
		strcat(returnCh, "12345");
	}
}

void TenToTwenty(int inputI){
	if(inputI == 10){
		strcat(returnCh, "123");
	}else if(inputI == 11 || inputI == 12){
		strcat(returnCh, "123456");
	}else if(inputI == 15 || inputI == 16){
		strcat(returnCh, "1234567");
	}else if(inputI == 13 || inputI == 14 || inputI == 18 || inputI == 19){
		strcat(returnCh, "12345678");
	}else{
		strcat(returnCh, "123456789");
	}
}

void TwentyPlus(int inputI){
	tenC = inputI / 10;
	if(tenC == 4 || tenC == 5 || tenC == 6 ){
		strcat(returnCh, "12345");
	}else if(tenC == 2 || tenC == 3 || tenC == 8 || tenC == 9){
		strcat(returnCh, "ninety");
	}else{
		strcat(returnCh, "1234567");
	}
	inputI = inputI % 10;
	SingleD(inputI);
}

void HundredPlus(int inputI){
	hundC = inputI / 100;
	strcat(returnCh, "hundred");
	if(hundC != 0){
		SingleD(hundC);
	}
	inputI = inputI % 100;
	if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else if(inputI != 0){
		SingleD(inputI);
	}
}

void ThousPlus(int inputI){
	thousandC = inputI / 1000;
	strcat(returnCh, "thousand");
	if(thousandC !=0 ){
		SingleD(thousandC);
	}
	inputI = inputI % 1000;
	if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else if(inputI != 0){
		SingleD(inputI);
	}
}

void TenThousPlus(){
	thousandC = inputI / 10000;
	if(thousandC > 19){
		TwentyPlus(thousandC);
	}else if(thousandC > 9 && thousandC < 20){
		TenToTwenty(thousandC);
	}
	inputI = inputI % 10000;
	printf("%d\n", inputI);
	if(inputI > 999){
		ThousPlus(inputI);
	}else if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else if(inputI != 0){
		SingleD(inputI);
	}
}

void HunThousPlus(int inputI){
	hundC = inputI / 100000;
	if(hundC > 99){
		SingleD(hundC);
	}else if(hundC > 19){
		TwentyPlus(hundC);
	}else if(hundC > 9){
		TenToTwenty(hundC);
	}else if(inputI != 0){
		SingleD(hundC);
	}
	
	inputI = inputI % 100000;
	
	if(inputI > 9999){
		TenThousPlus(inputI);
	}else if(inputI > 999){
		ThousPlus(inputI);
	}else if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else if(inputI != 0){
		SingleD(inputI);
	}
}
void Mil(int inputI){
	thousandC = inputI / 1000000;
	strcat(returnCh, "million");
	if(thousandC !=0 ){
		SingleD(thousandC);
	}
	inputI = inputI % 1000000;
	if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else if(inputI != 0){
		SingleD(inputI);
	}
}
void TenMil(int inputI){
	thousandC = inputI / 10000;
	if(thousandC > 19){
		TwentyPlus(thousandC);
	}else if(thousandC > 9 && thousandC < 20){
		TenToTwenty(thousandC);
	}
	inputI = inputI % 10000;
	printf("%d\n", inputI);
	if(inputI > 999){
		ThousPlus(inputI);
	}else if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else{
		SingleD(inputI);
	}
}
void HundMil(int inputI){
	hundC = inputI / 100000;
	if(hundC > 99){
		SingleD(hundC);
	}else if(hundC > 19){
		TwentyPlus(hundC);
	}else if(hundC > 9){
		TenToTwenty(hundC);
	}else{
		SingleD(hundC);
	}
	
	inputI = inputI % 100000;
	
	if(inputI > 9999){
		TenThousPlus(inputI);
	}else if(inputI > 999){
		ThousPlus(inputI);
	}else if(inputI > 99){
		HundredPlus(inputI);
	}else if(inputI > 19){
		TwentyPlus(inputI);
	}else if (inputI > 9 && inputI < 20){
		TenToTwenty(inputI);
	}else{
		SingleD(inputI);
	}
}

int main(int argc, char **argv){
	strcat(inputCh, argv[1]);
	inputI = atoi(inputCh);
	if((int)strlen(inputCh) == 1){
		SingleD(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 2 && inputI < 20){
		TenToTwenty(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 2 && inputI > 20){
		TwentyPlus(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 3){
		HundredPlus(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 4){
		ThousPlus(inputI);
		printf("%s\n", returnCh);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 5){
		TenThousPlus(inputI);
		printf("%s\n", returnCh);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 6){
		HunThousPlus(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 7){
		Mil(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 8){
		TenMil(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 9){
		HundMil(inputI);
		printf("%s takes %d letters\n", inputCh, (int)strlen(returnCh));
	}else if((int)strlen(inputCh) == 10){
		printf("%s takes %d letters\n", inputCh, (int)strlen("onebillion") );
	}
	return 0;
}