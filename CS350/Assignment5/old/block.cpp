#include "block.h"
#include <iostream>

int init_block(block_t& block, int size) {
	if(size > MAX_SIZE || size < MIN_SIZE) {
		cerr << "Error: block size out of range" << endl;
		return -1;
	}
	block.block_size = size;
	return 1;
}
