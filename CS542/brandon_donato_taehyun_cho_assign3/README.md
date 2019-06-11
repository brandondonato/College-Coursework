# CSX42: Assignment 3
## Name: Brandon Donato, Taehyun Cho

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in checkPrimesMultithreading/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile checkPrimesMultithreading/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile checkPrimesMultithreading/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile checkPrimesMultithreading/src/build.xml run -Darg0=<input_file.txt> -Darg1=<NUM_THREADS> -Darg2=<DEBUG_VALUE>

Note: Arguments accept the absolute path of the files.

-----------------------------------------------------------------------
## Description:
The choice of data structure used in the Results class was an ArrayList to store integer prime values. In this assignment, prime values were defined as numbers not divisible by 2 (odd numbers). ArrayList allows numbers to be stored multiple times, and can expand as we add numbers. Determining the sum of the ArrayList takes O(n) time and also takes O(n) time complexity, which are both the best possible respective complexities.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"We have done this assignment completely on our own. We have not copied
it, nor have we given our solution to anyone else. We understand that if
we am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 10/26/18
