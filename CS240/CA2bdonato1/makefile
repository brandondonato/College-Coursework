#
# Specifiy the target
all:	Donate

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
Donate:	Donor.o CA2.o DonorDatabase.o
	g++  Donor.o CA2.o DonorDatabase.o -o Donate

# Specify how the object files should be created from source files
Donor.o:	Donor.cpp Donor.h
	g++ -c Donor.cpp 

CA2.o:	CA2.cpp Donor.h DonorDatabase.h
	g++ -c CA2.cpp 

DonorDatabase.o:	DonorDatabase.cpp DonorDatabase.h Donor.h
	g++ -c DonorDatabase.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o Donate

