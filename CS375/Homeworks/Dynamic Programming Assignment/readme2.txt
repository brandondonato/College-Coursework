In order to compile please enter "make Prog2" or "make all" or "make" and the program will compile. In order to run the program, enter information as so:

Prompt> program2 <filex.txt> <filey.txt> <output2.txt> 

If you do not provide 2 text files and an output file (3 arguments), the program will not run. Once given the proper arguments (2 text files with a single string in each one and an output file name) the program will write its output into the specified output file. This directory DOES NOT come with text files.


This program utilizes filestreams in order to get information from and write information to outside text files. This program uses recursion to determine the longest common subsequence (LCS) of two separate strings. Because of the nature of recursion, there is one function that continuously calls itself until the problem is solved 


The computation time for this algorithm is O(2^(N or M, whichever is larger)) where M and N are the length of 2 separate strings. This is the worst case runtime and it occurs when the full recursive tree is filled out and neither string shares a common subsequence. 


This code contains 0 classes and one outside function, LCS, which does the recursion to determine the LCS of the two strings.