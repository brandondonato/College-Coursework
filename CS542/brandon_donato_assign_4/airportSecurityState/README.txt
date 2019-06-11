# CSX42: Assignment 4
## Name: Brandon Donato

-----------------------------------------------------------------------
-----------------------------------------------------------------------


Following are the commands and the instructions to run ANT on your project.
#### Note: build.xml is present in airportSecurityState/src folder.

-----------------------------------------------------------------------
## Instruction to clean:

####Command: ant -buildfile airportSecurityState/src/build.xml clean

Description: It cleans up all the .class files that were generated when you
compiled your code.

-----------------------------------------------------------------------
## Instruction to compile:

####Command: ant -buildfile airportSecurityState/src/build.xml all

Description: Compiles your code and generates .class files inside the BUILD folder.

-----------------------------------------------------------------------
## Instruction to run:

####Command: ant -buildfile airportSecurityState/src/build.xml run -Darg0=<input_file.txt> -Darg1=<output_file.txt> -Darg2=<DEBUG_VALUE>

Note: Arguments accept the absolute path of the files.
	  The DEBUG_VALUE must be between 0-4, inclusive.

-----------------------------------------------------------------------
## Description:
	This assignment implements the State pattern. It uses a class "AirportContextI" as an interface to use
	with the context class, "Airport". It also has the class, "AirportStateI" as the interface each class must
	implement. The 3 possible states are "LowRisk", "ModerateRisk", and "HighRisk", where each of the states
	also has their own respective class.
	
	The debug values correspond as follows: The debug values are: 0 - Nothing, 1 - Raised States,
	2 - Lowered States, 3 - Average Traffic, 4 - Average Items.
-----------------------------------------------------------------------
### Academic Honesty statement:
-----------------------------------------------------------------------

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date: 11/12/18
