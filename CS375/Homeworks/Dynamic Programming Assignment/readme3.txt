In order to compile please enter "make Prog3" or "make all" or "make" and the program will compile. In order to run the program, enter information as so:

Prompt> program3 <filex.txt> <filey.txt> <output3.txt> 

If you do not provide 2 text files and an output file (3 arguments), the program will not run. Once given the proper arguments (2 text files with a single string in each one and an output file name) the program will write its output into the specified output file. This directory DOES NOT come with text files.


This program utilizes filestreams in order to get information from and write information to outside text files. The program also uses a M x N matrix in order to store information that is necessary in calculating the LCS of two strings where M and N are the length of 2 separate strings. This matrix stores the solution to the size of the problem at index (i,j). This matrix is used in memoization so that when the function LCS makes a recursive call, it only needs to solve the subproblem it has not completed yet, thus saving time in the recursion. 


The computation time for this algorithm is O(M*N) where M and N are the length of 2 separate strings. This is because it must iterate through the entirety of the M x N matrix to determine the length of the LCS, based upon the previous values stored in the matrix.