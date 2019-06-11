#ifndef SUPERBLOCK_H
#define SUPERBLOCK_H

using namespace std;

typedef struct superblock_t {
	int num_inodes;
	int num_blocks;
	
} superblock_t;

int init_superblock(superblock_t& super, int num_inodes, int num_blocks);
