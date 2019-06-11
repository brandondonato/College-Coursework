#include <stdio.h>
#include <stdlib.h>

//This is a function that defines the Ackermann Function, as named after Wilhelm Ackermann
int ack(int m, int n){
    //Condition 1
    if(m == 0) return n+1;
    
    //Condition 2
    if(n == 0 && m > 0) return ack(m-1, 1);
    
    //Condition 3
    if(n > 0 && m > 0) return ack(m-1, ack(m, n-1));
}

int main(int argc, char** argv){
    int num = atoi(argv[1]);
    printf("Value of ack(1,0): %d\n", ack(1,0));
    printf("Value of ack(0,3): %d\n", ack(0,3));
    printf("Your num (N): %d\n", num);
    printf("This is the ackerman number for ack(3, N): %d\n", ack(3,num));
}
