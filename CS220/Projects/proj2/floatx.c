#include "floatx.h"
#include <math.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void binaryWhole(double whole, char * bStr){
	int count = 0;
	int i;
	char temp[1077];

	while(whole > 0){
		temp[count++] = '0' + (int)fmod(whole,2);
		//printf("%s\n", temp);
		whole = floor(whole/2);
	}
	for(i = 0; i<count; i++){
		bStr[i] = temp[count-i-1];
	}
	bStr[count]= 0;
}


void fracBinary(double frac, char * bStr){
	int count = 0;
	double temp;
	while(frac != 0){
		frac = frac*2;
		frac = modf(frac, &temp);
		bStr[count++] = '0' + (int)temp;
	}
	bStr[count] = 0;
}

void toBinary(double value, char * bStr){
	double whole;
	double frac = modf(value, &whole);

	if(whole != 0){
		binaryWhole(value, bStr);
	}else{
		strcpy(bStr, "0");
	}

	strcat(bStr, ".");

	if(frac!=0){
		fracBinary(frac, bStr+strlen(bStr));
	}else{
		strcpy(bStr+strlen(bStr), "0");
	}
}
/*--------------------------------------------------------------------------------
	Return floatx representation (as defined by *def) which
	best approximates value
-------------------------------------------------------------------------------- */
floatx doubleToFloatx(const floatxDef *def, double value) {
	int expBits = def->expBits;
	int totBits = def->totBits;
	int fracBits = totBits - expBits;
	char bStr[1077];

	if(value<0){
		value = value * -1;
	}
	toBinary(value, bStr);
	double num = atof(bStr);
	printf("%lf\n", num);
	printf("%s\n\n", bStr);
	return num;
}

/** Return C double with value which best approximates that of floatx fx
 *  (as defined by *def).
 */
double floatxToDouble(const floatxDef *def, floatx fx) {
	/* Put your code here */
	
	return NAN;
}
