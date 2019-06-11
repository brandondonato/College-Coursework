
all: ssfs_mkdsk.o ssfs.o
	./a.out 65536 128 hello
	./b.out hello test.txt import_cat.txt read_write.txt

ssfs_mkdsk.o: ssfs_mkdsk
	gcc ssfs_mkdsk.o -o a.out -lm 

ssfs.o: ssfs
	gcc ssfs.o -o b.out -lm -pthread

ssfs_mkdsk: ssfs_mkdsk.c
	gcc -c ssfs_mkdsk.c

ssfs: ssfs.c
	gcc -c ssfs.c

clean:
	rm *.o a.out b.out hello
