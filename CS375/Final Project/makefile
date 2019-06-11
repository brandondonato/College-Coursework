
all: floyd-warshall graph-generator

floyd-warshall: floyd-warshall.o
	g++ floyd_warshall.o -o a.out

graph-generator: graph-generator.o
	g++ graph_generator.o -o b.out

floyd-warshall.o: floyd_warshall.cpp
	g++ -c floyd_warshall.cpp

graph-generator.o: graph_generator.cpp
	g++ -c graph_generator.cpp

clean:
	rm *.o a.out b.out
