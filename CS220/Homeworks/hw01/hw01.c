#include <stdio.h>
#include <string.h>

int fib(int n);
int diffSq(int a, int b);

int main(int argc, char **argv) {
	int x=atoi(argv[1]);
  	int y=atoi(argv[2]);
  	unsigned int v=x;
  	unsigned int w=y;
  	int s_sum=x+y;
  	usinged int u_sum=v+w;
  	if (s_sum==u_sum) printf("Unsigned and signed are the same\n");
  	else printf("Unsigned and signed are different\n");
	return 0;
}

int fib(int n) {
	/* Write the code to return the nth fibonacci number here */
	int i;
	int num1 = 0;
	int num2 = 1;
	int next = 0;	
	for(i = 1; i <= n; i++){
		next = num1 + num2;
		num1 = num2;
		num2 = next; 
	}
	return num1;
}

int diffSq(int a, int b) {
	/* Write the code to return the difference between the square of a and square of b here */
	return ((a*a) - (b*b));
}
