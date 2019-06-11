# CSX42: Assignment 5
## Name: Brandon Donato

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in genericCheckpointing/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile genericCheckpointing/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile genericCheckpointing/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile genericCheckpointing/src/build.xml run -Darg0=<mode> -Darg1=<N> -Darg2=<checkpointFileName.txt>

Note: Arguments accept the absolute path of the files.
	  Files must exist prior to running, otherwise program will crash.
	  To help this, an empty "checkpoint.txt" is included in submission.
	  <mode> is either "serdeser" or "deser", otherwise the program exits.
	  Empty the file between runs to ensure accurate results

-----------------------------------------------------------------------
## Description:
	This assignment implements the Proxy, Visitor, and Reflection patterns. 
	
	The Proxy pattern uses a Dynamic Proxy to invoke a InvocationHandler, "StoreRestoreHandler",
	which processes all of the reading and writing commands.
	
	The visitor pattern uses an interface "VisitorI" which is then implemented in "PalindromVisitor"
	and "PrimeVistorImpl." These classes are visitor objects which overload a "visit()" function in
	order to properly execute the visitor pattern. From there, each visitor calculate the number of
	palindromes and the number of distinct primes, repectively.
	
	The Reflection pattern is used when serializing and deserializing the information from the text files.
	The pattern parses through the given .txt file, determines the class of the object and then proceeds
	to set the value if deserializing, or gets the value if serializing. Then, the object is cast to a 
	SerializableObject and returned if deserializing. 
	Reflection is used to determine the class of the given object, the method the object needs to get
	or set the value, and to call the method of the object.
	
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 12/09/18
