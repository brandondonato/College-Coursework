In order to compile please enter "make Prog1" or "make all" or "make" and the program will compile. In order to run the program, enter information as so:

Prompt> BestFirstSearch <input.txt> <output.txt>

If you do not provide 1 text files and an output file (2 arguments), the program will not run. Once given the proper arguments (1 text files with a list of weights and profits of items and an output file name) the program will write its output into the specified output file. This directory DOES NOT come with text files.


This program utilizes filestreams in order to get information from and write information to outside text files. The program also uses many arrays to store the information obtained from the file. Also, we create a struct called Node to store information during the traversal of the Branch-and-Bound search, as well as the sorting of items. We also make use of a priority queue in order to find the most efficient node to traverse to next. Utilizing the Branch-and-Bound search algorithm with pruning based on a Best-First-Search, we are able to solve the 0-1 knapsack problem.

The computation time for this algorithm is O(b^s) where b is the branching factor and s is the depth of the tree. This upper bound can be better optimized by the branching factor, however, if the branching factor does not aide our search, we simply are computing breadth first search on the tree. 

This code contains 0 classes.