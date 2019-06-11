#include <math.h>
#include <stdlib.h>
#include <stdio.h>

/*
Method to check whether a number is a power of two. The parameter 'num' is the value that will be check if it is a power of two. Return a 0 if it is a power of two, 1 otherwise.
*/
int check_pow_two(int num) {
	if( (num!=0) && !(num & (num-1)) ) return 0;
	else return 1;
}
/*
Create the simple disk that will be used by another executable (ssfs) to simulate a file system
*/
int main(int argc, char** argv) {
	char* usage_string = "./ssfs_mkdsk <number of blocks> <block size> <file name (optional)>";
	if(argc > 4) {
		fprintf(stderr, "Error: number of arguments cannot be greater than four.\n");
		fprintf(stderr, "%s\n", usage_string);
		return 1;
	}
	else if (argc < 3) {
		fprintf(stderr, "Error: number of arguments cannot be less than three.\n");
		fprintf(stderr, "%s\n", usage_string);
		return 1;
	}
	int num_block = atoi(argv[1]);
	int block_size = atoi(argv[2]);
	char* disk_name = "DISK";
	if(argc == 4) disk_name = argv[3];
	if(num_block < 1024 || num_block > 128000) {
		fprintf(stderr, "Error: number of blocks has to be within the range of 1024 - 128,000.\n");
		fprintf(stderr, "%s\n", usage_string);
		return 1;
	}
	if(block_size < 128 || block_size > 512) {
		fprintf(stderr, "Error: block size has to be within the range of 128 - 512.\n");
		fprintf(stderr, "%s\n", usage_string);
		return 1;
	}
	if(check_pow_two(num_block) != 0 || check_pow_two(block_size) != 0) {
		fprintf(stderr, "Error: block size and number of blocks has to be a power of two.\n");
		fprintf(stderr, "%s\n", usage_string);
		return 1;
	}
	FILE* disk; //disk
	disk = fopen(disk_name, "wb");
	char* buffer = (char*) malloc(sizeof(char) * num_block * block_size); //to fill out the file with '0'
	for(int i = 0; i < num_block * block_size; i++) {
		buffer[i] = 0;
	}
	fseek(disk, 0, SEEK_SET);
    for(int i = 0; i < num_block * block_size; i++){
        fwrite(&buffer[i], sizeof(char), num_block * block_size, disk); //write zeros to file
    }
	
	fseek(disk, 0, SEEK_SET);
	fwrite(&num_block, sizeof(int), 1, disk); //write number of blocks to the start
	//fseek(disk, sizeof(int), SEEK_SET);
	fwrite(&block_size, sizeof(int), 1, disk); //write block size after number of blocks
	
	int imap[256] = {0};
	fwrite(&imap, sizeof(int), 256, disk); //write zeros (imap) 
	//fseek(disk, 2 * sizeof(int) + 256 * sizeof(int), SEEK_SET);
	
	//figure out how many blocks everything takes (superblock and imap) to fill datamap with 1
	int used = 1;
	double xxx = ((double) 2 * sizeof(int) + 256 * sizeof(int) + num_block * sizeof(char)) ;
	if(2 * sizeof(int) + 256 * sizeof(int) > block_size) {
		double temp = ((double) 2 * sizeof(int) + 256 * sizeof(int) + num_block * sizeof(char))  / block_size;
		used = ceil(temp);
	}
	//printf("USED: %d\n", used);
	//printf("CHAR SIZE: %d\n", sizeof(char));
	char temp2[num_block];
	for(int i = 0; i < num_block; i++) {
		if(i < used) temp2[i] = '1';
		else temp2[i] = '0';
	}
	//fwrite(&temp2, sizeof(char), used, disk);
	
	fwrite(&temp2, sizeof(char), num_block, disk);

	fclose(disk);
}














