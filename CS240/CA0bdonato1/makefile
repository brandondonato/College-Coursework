#
# Specifiy the target
all:	Donato_Brandon_bdonato1_Hello

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
Donato_Brandon_bdonato1_Hello:	Donato_Brandon_bdonato1_Hello.o Hello_Class.o
	g++  Donato_Brandon_bdonato1_Hello.o Hello_Class.o -o Donato_Brandon_bdonato1_Hello.exe

# Specify how the object files should be created from source files
Donato_Brandon_bdonato1_Hello.o:	Donato_Brandon_bdonato1_Hello.cpp Hello_Class.h
	g++ -c Donato_Brandon_bdonato1_Hello.cpp

Hello_Class.o:	Hello_Class.cpp Hello_Class.h
	g++ -c Hello_Class.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o Donato_Brandon_bdonato1_Hello.exe

