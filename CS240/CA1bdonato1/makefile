#
# Specifiy the target
all:	Donate

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
Donate:	Donor.o CA1.o
	g++  Donor.o CA1.o -o Donate

# Specify how the object files should be created from source files
Donor.o:	Donor.cpp Donor.h
	g++ -c Donor.cpp 

CA1.o:	CA1.cpp Donor.h
	g++ -c CA1.cpp 

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o Donate

