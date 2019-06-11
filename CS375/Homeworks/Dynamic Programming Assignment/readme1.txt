In order to compile please enter "make Prog1" or "make all" or "make" and the program will compile. In order to run the program, enter information as so:

Prompt> program1 <filex.txt> <filey.txt> <output1.txt> 

If you do not provide 2 text files and an output file (3 arguments), the program will not run. Once given the proper arguments (2 text files with a single string in each one and an output file name) the program will write its output into the specified output file. This directory DOES NOT come with text files.


This program utilizes filestreams in order to get information from and write information to outside text files. The program also uses a M x N matrix in order to store information that is necessary in calculating the LCS of two strings where M and N are the length of 2 separate strings. This matrix stores the LCS of the 2 strings given indices i and j (i being an index into string X and j being an index into string Y) so its faster to calculate the LCS of the entirety of both strings. 


The computation time for this algorithm is O(M*N) where M and N are the length of 2 separate strings. This is because it must iterate through the entirety of the M x N matrix to determine the length of the LCS, and it does it again when determining the string that comprises the LCS. 


This code contains 0 classes.