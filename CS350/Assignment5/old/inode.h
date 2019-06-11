#ifndef INODE_H
#define INODE_H
#include <string>

using namespace std;

typedef struct inode_t {
	string filename;
	int file_size;
	int bptr[12];
	int indirect;
	int double_indirect;
} inode_t;

int init_inode(inode_t& inode, string filename);

#endif
