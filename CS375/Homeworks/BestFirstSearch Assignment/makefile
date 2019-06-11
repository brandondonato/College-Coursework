#
# Specifiy the target
all:	Prog1

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable

Prog1:	BestFirstSearch.o
	g++ BestFirstSearch.o -o BestFirstSearch

# Specify how the object files should be created from source files

program1.o:	BestFirstSearch.cpp
	g++ -c BestFirstSearch.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o BestFirstSearch