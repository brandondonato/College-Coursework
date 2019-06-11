#include <string.h>
#include <math.h>
#define FP2BIN_STRING_MAX 1077
//#include "fp2bin.h"


int main()
{
    int x = 1;
    int y = 0;
    long int z = 16843009;

    if(z!=htonl(z)){
    	printf("%d\n", y);
    }else{
    	printf("%d\n", x);
    }
 return(0);
}