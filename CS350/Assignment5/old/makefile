
all: inode.o block.o ssfs-mkdsk

ssfs-mkdsk: ssfs_mkdsk.o
	g++ ssfs_mkdsk.o -o ssfs-mkdsk

inode: inode.h inode.cpp
	g++ -c inode.cpp

block: block.h block.cpp
	g++ -c block.cpp

ssfs_mkdsk: ssfs_mkdsk.cpp
	g++ -c ssfs_mkdsk.cpp

clean:
	rm *.o ssfs-mkdsk

