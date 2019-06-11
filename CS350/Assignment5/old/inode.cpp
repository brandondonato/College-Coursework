#include "inode.h"

//return 0 if successful
int init_inode(inode_t& inode, string filename) {
	inode.filename = filename;
	for(int i = 0; i < 12; i++) {
		inode.bptr[i] = i;
	}
	return 0;
}
