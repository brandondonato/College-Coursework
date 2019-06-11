#
# Specifiy the target
all:	Prog1 Prog2 Prog3

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable

Prog1:	BottomUp.o
	g++ BottomUp.o -o program1
Prog2:	Recursive.o
	g++ Recursive.o -o program2
Prog3:	TopDown.o
	g++ TopDown.o -o program3

# Specify how the object files should be created from source files

program1.o:	LCS.h BottomUp.cpp
	g++ -c BottomUp.cpp
program2.o:	LCS.h Recursive.cpp
	g++ -c Recursive.cpp
program3.o:	LCS.h TopDown.cpp
	g++ -c TopDown.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o program1 program2 program3