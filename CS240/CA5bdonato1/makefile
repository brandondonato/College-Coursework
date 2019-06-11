#
# Specifiy the target
all:	schedule.exe

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
schedule.exe:	Graph.o CA5.o
	g++  Graph.o CA5.o -o schedule.exe

# Specify how the object files should be created from source files
Graph.o:	Graph.h Graph.cpp
	g++ -c Graph.cpp

CA5.o:	Graph.h CA5.cpp
	g++ -c CA5.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o schedule.exe

