#include "superblock.h"

int init_superblock(superblock_t& super, int num_inode, int num_block) {
	super.num_inode = num_inode;
	super.num_block = num_block;
	return 0
}
