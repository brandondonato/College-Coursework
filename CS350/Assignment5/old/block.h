#ifndef BLOCK_H
#define BLOCK_H
#include <string>
#define MAX_SIZE 512
#define MIN_SIZE 128

using namespace std;

typedef struct block_t {
	int block_size;
	string data;
} block_t;

int init_block(block_t& block, int size);

#endif
