#include <stdio.h>
#include "gcd.h"


/* Imperative style modification of example 1.20  */

int gcdI(int i, int j) {
       while ( i != j) { 
           if (i > j) {
              i = i - j;
           } else {
              j = j - i;
           }
       }
       //printf("gcdI: %d\n", i);
       return i;
}

int gcdM(int i, int j) {
    while ( i != j ) {
        if (i > j) {
            i = i % j;
            //My lines
            if(i == 0){
                i = j;
                break;
            }
            //
        } else {
            j = j % i;
            //My lines
            if(j == 0){
                break;
            }
            //
        }
    }
    //printf("gcdM: %d\n", i);
    return i;
}
//My Main function to verify the differences between the gcd funtions
int main(){
    int a = 32;
    int b = 16;
    
    int c = gcdI(a,b);
    int d = gcdM(a,b);
    
    //printf("%d\n", c);
   // printf("%d\n", d);
}

