#include <math.h>
#include <pthread.h>
#include <stdbool.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>

pthread_mutex_t imap_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t dmap_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t request_lock = PTHREAD_MUTEX_INITIALIZER;
pthread_mutex_t finish_lock = PTHREAD_MUTEX_INITIALIZER;

pthread_cond_t request_full = PTHREAD_COND_INITIALIZER;
pthread_cond_t request_empty = PTHREAD_COND_INITIALIZER;

pthread_cond_t content_full = PTHREAD_COND_INITIALIZER;
pthread_cond_t content_empty = PTHREAD_COND_INITIALIZER;

FILE* disk; //disk for the entire program

bool shutdown = false;

int thread_id = 1;

int num_block = 0;
int block_size = 0;
int start_block = 0; //beginning of the free block (blocks before are filled with data for file system
int blocks_left = 0; //number of free blocks left
int num_inode = 0; //number of inodes currently in disk

int* imap; //map for where the inodes are located
char* datamap; //table for free blocks

//ONLY DISK CONTROLLER CREATES INODES
typedef struct inode_t {
	char filename[33];
	int file_size;
	int last; //last block of file
	int direct[12];
	int indirectptr;
	int doubleptr;
} inode_t;

typedef struct requested_content_t {
	bool fill; //whether it is filled or not
	int id; //id of the thread who needs the specified file content
	inode_t inode;
	char* file_content; //content of the file
	bool successful;
} requested_content_t;

typedef struct request_t {
	int id; //id of the thread requesting
	char request_type; //'R' for read, 'W' for write, 'C' for create, 'D' for delete, 'L' for list 'A'
	char filename1[33]; //file name of the requested action
	char filename2[33]; //file name of the unix (replacing) action
	char ch; //character to be written (for WRITE ONLY)
	int start_byte; //start byte to read or write
	int num_bytes; //number of bytes to read or write
} request_t;

int r_ctr = 0;
int r_fill_pointer = 0; //pointer to the next available spot in the request array
int r_use_pointer = 0; //pointer to the next spot to use in the request array
request_t requests[4]; //maximum of four request to the disk controller
requested_content_t requested_content;

void save_imap();
void save_dmap();
void update_block(int block_pos, int type);
int find_free_block();
int find_free_imap();
void delete_inode(int inode_pos);
inode_t find_inode(int ilocation);
int find_inode_location(char* filename);
bool file_exists(char* filename);
int create(char* filename);
int write_DISK(char* filename, char ch, int start_byte, int num_bytes);
int read_DISK(int id, char*filename, int start_byte, int num_bytes);
int cat_DISK(int id, char* filename);
int import_DISK(char* filename1, char* filename2);
int list();
int delete(char* filename);

int init_inode(inode_t inode, char* filename);
inode_t init_doubleptr(inode_t inode);

int init_requested_content();

void put_request(int id, char request_type, char* filename1, char* filename2, char ch, int start_byte, int num_bytes);
request_t get_request();

void* disk_controller();
void* disk_op(void* command_file);

/*
MAIN ----------------------------------------------------------------------------------------------
*/
int main(int argc, char** argv) {
	char* disk_name = argv[1];
	int counter = 0;
	for(int i = 1; i < argc; i++) {
		if(!file_exists(argv[i])) {
			fprintf(stderr, "Error File %s does not exist.\n", argv[i]);
			return 1;
		}
		counter++;
	}
	if(counter > 4) return 1;
	
	disk = fopen(disk_name, "rb+");
	
	fread(&num_block, sizeof(int), 1, disk); //set the number of blocks
	fread(&block_size, sizeof(int), 1, disk); //set the block size
	//printf("FIRST: 1. %d -- 2. %d\n", num_block, block_size);
	
	if(init_requested_content() == -1) {
		printf("Initializing the requested content failed;.");
		return 1;
	}
	
	//allocate memory for the inode map and the free block table
	imap = (int*) malloc(sizeof(int)*256); 
	datamap = (char*) malloc(sizeof(char)*num_block); 
	
	//fseek(disk, 2 * sizeof(int), SEEK_SET); 
	fread(imap, sizeof(int), 256, disk); //populate the inode map
	fread(datamap, sizeof(char), num_block, disk); //populate the free block map
/*	
	printf("IMAP SIZE: %d\n", sizeof(imap) / sizeof(int));
	for(int i = 0; i < 256; i++) {
		printf("%d ", imap[i]);
	}
	printf("\n");
	
	printf("DATAMAP:\n");
	for(int i = 0; i < num_block; i++) {
		printf("%c ", datamap[i]);
	}
	printf("\n");
*/
	double temp = ((double) 2 * sizeof(int) + 256 * sizeof(int) + num_block * sizeof(char))  / block_size;
	start_block = ceil(temp); //the start of the free blocks
	//printf("START: %d\n", start_block);
	blocks_left = num_block - start_block; //initialize number of free blocks left
/*
	for(int i = 0; i < num_block; i++) {
		printf("%c\n", datamap[i]);
	}
*/	
	//WILL INCLUDE THREADS (ONE DISK CONTROLLER AND THREE DISK OPS
/*	
	if(create("new file") != 0) printf("\nAWDAWDASDAWDQWDQD\n");

	//if(create("HELLO") == -1) printf("FAILED TO CREATE HELLO\n");
	printf("IMAP:\n");
	for(int i = 0; i < 256; i++) {
		printf("%d ", imap[i]);
	}
	printf("\n");

	if(write_DISK("new file", 'A', 9856, 128*2) == -1) {
		fprintf(stderr, "Error on write_DISK");
	}

	inode_t temp_inode;
	int location = find_inode_location("new file");
	printf("LOCATION: %d\n", location);
	fseek(disk, location * block_size, SEEK_SET);
	fread(&temp_inode, sizeof(inode_t), 1, disk);
	
	if(read_DISK(2, "new file", 9856, 10) == -1){
		fprintf(stderr, "Error on read_DISK");
	}
	fprintf(stderr, "CHAR USING read_DISK: %s\n", requested_content.file_content);
	char from_disk[10 + 1];
	//fseek(disk, 2658, SEEK_SET);
	fread(&from_disk, sizeof(char), 10, disk);
	
	//fprintf(stderr, "CHAR FROM DISK: %s\n", from_disk);
	fprintf(stderr, "FILESIZE: %d\n", temp_inode.file_size);
	
	//delete("new file");
	//char after[10 + 1];
	//fseek(disk, 30 * block_size, SEEK_SET);
	//fread(&after, sizeof(char), 10, disk);
	
	//fprintf(stderr, "CHAR FROM DISK AFTER: %s\n", after);
	//printf("IMAP AFTER:\n");
	//for(int i = 0; i < 256; i++) {
	//	printf("%d ", imap[i]);
	//}
	//fprintf(stderr, "FILESIZE: %d\n", temp_inode.file_size);
	//list();
	//disk_op("input1.txt");
*/
	pthread_t controller, op1, op2, op3;
	pthread_create(&controller, NULL, disk_controller, NULL);
	if(counter == 2) {
		pthread_create(&op1, NULL, disk_op, (void*) argv[2]);	
		
		pthread_join(controller, NULL);
		pthread_join(op1, NULL);
	}
	else if(counter == 3) {
		pthread_create(&op1, NULL, disk_op, (void*) argv[2]);
		pthread_create(&op2, NULL, disk_op, (void*) argv[3]);
		
		pthread_join(controller, NULL);
		pthread_join(op1, NULL);
		pthread_join(op2, NULL);
	}
	else {
		pthread_create(&op1, NULL, disk_op, (void*) argv[2]);
		pthread_create(&op2, NULL, disk_op, (void*) argv[3]);
		pthread_create(&op3, NULL, disk_op, (void*) argv[4]);
		
		pthread_join(controller, NULL);
		pthread_join(op1, NULL);
		pthread_join(op2, NULL);
		pthread_join(op3, NULL);
	}
	
	fclose(disk);
}
//END MAIN ----------------------------------------------------------------------------------------

/*
FOR DISK CONTROLLER ONLY
Method to initialize inodes for DISK CONTROLLER. Sets direct and indirect blocks and updates both the inide map and the free block table. Will need both imap_lock and dmap_lock. Return -1 if there is no space to create new file or return -2 if file already exists or 0 if successful.
*/
int init_inode(inode_t inode, char* filename) {
	//inode.filename;
	strncpy(inode.filename, filename, sizeof(inode.filename));
	//fprintf(stderr, "FILENAME IN INIT_INODE: %s\n", inode.filename);
	//fprintf(stderr, "FILENAME SIZE: %d\n", strlen(inode.filename));
	//inode.filename[strlen(inode.filename) - 1] = '\0';
	pthread_mutex_lock(&dmap_lock);
	//printf("HELLO");
	//fflush(stdout);
	int inode_space;
	if(blocks_left >= 15 + block_size / sizeof(int) + (block_size / sizeof(int) * block_size / sizeof(int))) { //12 direct, one indirect, one double indirect, one inode (will take block size space) = 15 
		inode_space = find_free_block();
		update_block(inode_space, 0); //update datamap for the index of this inode
		pthread_mutex_lock(&imap_lock);
		if(find_inode_location(filename) != -1) {
			pthread_mutex_unlock(&imap_lock);
			pthread_mutex_unlock(&dmap_lock);
			return -2; //file already exists
		}
		int index = find_free_imap();
		//printf("HELLO");
		if(index == -1) { //there is a maximum number of inodes -- FAIL
			pthread_mutex_unlock(&imap_lock);
			pthread_mutex_unlock(&dmap_lock);
			return -1; 
		}
		imap[index] = inode_space; //set the imap for this new inode
		num_inode++; //increment number of inodes
		//fprintf(stderr,"NUM INODE IN INIT_INODE %d\n", num_inode);
		//datamap[9] = '2';
		
		pthread_mutex_unlock(&imap_lock);
		
		for(int i = 0; i < 12; i++) { //set the direct block pointers
			int free = find_free_block();
			//printf("FREE %d: %d\n", i, free);
			inode.direct[i] = free;
			update_block(free, 0); //update datamap for the index of this direct block
		}
		
	}
	else {
		pthread_mutex_unlock(&dmap_lock);
		//printf("HELLO");
		return -1; //not enough space for new file
	}
	int free_indirect = find_free_block();
	//printf("FREE INDIRECT: %d\n", free_indirect);
	inode.indirectptr = free_indirect; //update inode indirect block
	int indirect_block[block_size / sizeof(int)];
	update_block(free_indirect, 0); //update datamap for the index of this indirect block
	
	for(int i = 0; i < block_size / sizeof(int); i++) {
		int free = find_free_block();
		indirect_block[i] = free;
		update_block(free, 0); //update datamap for the index of this block the indirect is pointing
	}
	
	fseek(disk, free_indirect * block_size, SEEK_SET);
	for(int i = 0; i < block_size / sizeof(int); i++) {
		fwrite(&indirect_block[i], sizeof(int), 1, disk); //write indirect block into disk
	}
	
	inode.file_size = 0; //set file size to 0 (nothing in file)
	inode.last = 0; //set last lock of the file to 0 (nothing in file)
	
	//Initialize the double indirect for the inode
	inode.doubleptr = find_free_block(); //initialize the double indirect for the inode
	update_block(inode.doubleptr, 0);
		
	int doubleptr_block[block_size /sizeof(int)];
	for(int i = 0; i < block_size / sizeof(int); i++) {
		int free_indirect = find_free_block(); //find free indirect blocks to store in double indirect
		doubleptr_block[i] = free_indirect;
		update_block(free_indirect, 0);
	}
	fseek(disk, inode.doubleptr * block_size, SEEK_SET);
	for(int i = 0; i < block_size / sizeof(int); i++) {
		fwrite(&doubleptr_block[i], sizeof(int), 1, disk); //write in the double indirect into disk
	}
		
	for(int i = 0; i < block_size / sizeof(int); i++) {
		int indirect[block_size / sizeof(int)];
		for(int j = 0; j < block_size / sizeof(int); j++) {
			indirect[j] = find_free_block(); //find free block for the indirect blocks
			update_block(indirect[j], 0);
		}
		fseek(disk, doubleptr_block[i] * block_size, SEEK_SET);
		for(int k = 0; k < block_size / sizeof(int); k++) {
			fwrite(&indirect[k], sizeof(int), 1, disk); //write the indirect block into disk
		}
	}
	
	if(sizeof(inode_t) > block_size) return 1;
	fseek(disk, inode_space * block_size, SEEK_SET);
	fwrite(&inode, sizeof(inode_t), 1, disk); //write inode into disk
	
	pthread_mutex_unlock(&dmap_lock);

	
	//printf("END\n");
	return 0;
}

/*
Method to initialize the buffer of the requested file. Returns 0 if successful, else -1
*/
int init_requested_content() {
	requested_content.fill = false;
	requested_content.file_content = (char*) malloc(block_size * (12 + block_size / sizeof(int) + (block_size / sizeof(int) * block_size / sizeof(int)))); //size of all the blocks
	if(requested_content.file_content == NULL) return -1;
	return 0;
}

/*
Method to save the inode map to the disk
*/
void save_imap() {
	pthread_mutex_lock(&imap_lock);
	fseek(disk, 2 * sizeof(int), SEEK_SET);
	for(int i = 0; i < 256; i++) {
		fwrite(&imap[i], sizeof(int), 1, disk);
	}
	pthread_mutex_unlock(&imap_lock);
	
}

/*

*/
void save_dmap() {
	/*for(int i = 0; i < num_block; i++) {
		printf("%c ", datamap[i]);
	}
	printf("\n");*/
	pthread_mutex_lock(&dmap_lock);
	fseek(disk, 2 * sizeof(int) + 256 * sizeof(int), SEEK_SET);
	for(int i = 0; i < num_block; i++) {
		fwrite(&datamap[i], sizeof(char), 1, disk);
	}
	pthread_mutex_unlock(&dmap_lock);
}
/*
Method to update the free block table whether it is for adding new data or removing data. 
type is 0 for adding and 1 for removing
MAKE SURE THREAD HAS dmap_lock
*/
void update_block(int block_pos, int type) {
	if(type == 0) {
		datamap[block_pos] = '1';
		blocks_left--;
	}
	else {
		datamap[block_pos] = '0';
		blocks_left++;
	}
}

/*
Method to find next free block. Returns the block number. If there is no free blocks, return -1
MAKE SURE THREAD HAS dmap_lock
*/
int find_free_block() {
	//fprintf(stderr, "HELLLLO %d, %d\n", start_block, num_block);
	
	for(int i = start_block; i < num_block; i++) {
		//fprintf(stderr, "VALUE: %d", (int) datamap[i]);
		if(datamap[i] == '0') {
			//fprintf(stderr, "%d\n", i);
			return i;
		}
	}
	return -1; //there is no space
}

/*
Method to find the next free inode map location. Return the inode map location index. If there is no free location index, return -1.
MAKE SURE THREAD HAS imap_lock
*/
int find_free_imap() {
	for(int i = 0; i < 256; i++) {
		if(imap[i] == 0) {
			return i;
		}
	}
	return -1;
}

/*
Method to delete the inode position in the inode map.
MAKE SURE THREAD HAS imap_lock
*/
void delete_inode(int inode_pos) {
	for(int i = 0; i < 256; i++) {
		if(imap[i] == inode_pos) {
			imap[i] = 0;
			break;
		}
	}
}

/*
FOR DISK CONTROLLER ONLY
Method to retreive the inode at the specified block number. Returns the inode at the ilocation
*/
inode_t find_inode(int ilocation) {
	inode_t inode;
	fseek(disk, ilocation * block_size, SEEK_SET);
	fread(&inode, sizeof(inode_t), 1, disk);
	return inode;
}
/*
Method to find the inode with the same filename as the parameter. Returns the inode block number of filenames are equal or -1 if not
MAKE SURE THREAD HAS imap_lock
*/
int find_inode_location(char* filename) {
	for(int i = 0; i < 256; i++) {
		if(imap[i] != 0) {
			inode_t inode;
			//printf("FILENAME PARAM: %s\n", filename);
			//printf("IMAP LOCATION: %d\n", imap[i]);
			fseek(disk, imap[i] * block_size, SEEK_SET);
			fread(&inode, sizeof(inode_t), 1, disk);
			//printf("FILENAME FROM INODE: %s\n", inode.filename);
			//printf("FILESIZE FROM INODE: %d\n", inode.file_size);
			if(strcmp(inode.filename, filename) == 0) return imap[i];
		}
	}
	return -1;
}

/*
Method to check if a file exists with the same name as the parameter 'filename'. Return true if it exists, false otherwise.
*/
bool file_exists(char* filename) {
	FILE* file = fopen(filename, "rb");
	if(file == NULL) return  false;
	return true;
}

/*
FOR DISK CONTROLLER ONLY
Create method for the DISK CONTROLLER. Creates an inode and updates both imap and datamap. Returns a 0 if successful or returns -1 if there is no space to create or return -2 if file exists
*/
int create(char* filename) {
	FILE* file;

	//fprintf(stderr, "FILENAME IN CREATE: %s\n", filename);
	inode_t inode; 
	return init_inode(inode, filename);
}
int cat_DISK(int id, char* filename){
	pthread_mutex_lock(&imap_lock);
	//printf("HELLO\n");
	int ilocation = find_inode_location(filename);
	if(ilocation == -1) return -1;
	
	inode_t inode = find_inode(ilocation);
	pthread_mutex_unlock(&imap_lock);
	//printf("FILE SIZE IN CAT: %d\n", inode.file_size);
	int retValue = read_DISK(id, filename, 0, inode.file_size);
	//printf("HELLO\n");
	return retValue;
}
/*
FOR DISK CONTROLLER ONLY
Method to write into blocks in the disk. Returns -1 if there is no space or 0 if successful
*/
int write_DISK(char* filename, char ch, int start_byte, int num_bytes) {
	bool start = false;
	//printf("%s\n", filename);
	/*printf("IMAP SIZE: %d\n", sizeof(imap) / sizeof(int));
	for(int i = 0; i < 256; i++) {
		printf("%d ", imap[i]);
	}
	printf("\n");*/
	pthread_mutex_lock(&imap_lock);
	int ilocation = find_inode_location(filename); //find the block number of the inode
	//fprintf(stderr, "ILOCATION IN WRITE_DISK: %d\n", ilocation);
	if(ilocation == -1) {
		pthread_mutex_unlock(&imap_lock);
		return -1;
	}
	pthread_mutex_unlock(&imap_lock);
	
	inode_t inode;
	
	//fprintf(stderr, "HERE1\n");
	fseek(disk, ilocation * block_size, SEEK_SET);
	fread(&inode, sizeof(inode_t), 1, disk); //read in the inode from disk
	
	//fprintf(stderr, "HERE2\n");
	int block_start = 0;
	while(start_byte >= block_size) {
		block_start++;
		start_byte -= block_size;
	}
	//printf("BLOCKSTART: %d\n", block_start);
	//fprintf(stderr, "block_start: %d\n", block_start);
	//fprintf(stderr, "start_byte: %d\n", start_byte);
	//fprintf(stderr, "num_bytes: %d\n", num_bytes);
	//fprintf(stderr, "HERE3\n");
	
	char filesize_check; //looks for null, if null, increment filesize, otherwise, don't
	if(block_start < 12) {
		//fprintf(stderr, "HERE4\n");
		for(int i = block_start; i < 12; i++) {
			if(i == block_start) {
				start = true;
				fseek(disk, inode.direct[i] * block_size + start_byte, SEEK_SET); //go to the start byte of the file
			}
			else {
				fseek(disk, inode.direct[i] * block_size, SEEK_SET);
			}
			//int ctr = 0;
			while(num_bytes != 0 && start_byte != block_size) {
				//printf("CH: %c\n", ch);
				//ctr++;
				
				//fprintf(stderr, "num_bytes: %d\n", num_bytes);
				//fprintf(stderr, "start_byte: %d\n", start_byte);
				fread(&filesize_check, sizeof(char), 1, disk);
				if(filesize_check == '\0'){
					inode.file_size++; //increment the filesize
				}
				fseek(disk, -1, SEEK_CUR);
				fwrite(&ch, sizeof(char), 1, disk);
				
				num_bytes--;
				start_byte++;
			}
			//fprintf(stderr, "CTR: %d\n", ctr);
			if(num_bytes == 0) {
				inode.last = inode.direct[i];
				goto FINISH;
			}
			if(start_byte == block_size) start_byte = 0;
		}
	}
	//fprintf(stderr, "HERE5\n");
	if(num_bytes != 0) { //check the indirect block if you still need to write
		//printf("HERE========================\n");
		if(block_start >= 12 && block_start < block_size / sizeof(int) + 12) {
			block_start -= 12;
		}
		else if(block_start >= block_size / sizeof(int) + 12) {
			goto DOUBLE;
		}
		else {
			block_start = 0;
		}
		int indirect[block_size / sizeof(int)];
		fseek(disk, inode.indirectptr * block_size, SEEK_SET);
		fread(&indirect, sizeof(int), block_size / sizeof(int), disk);
		
		for(int i = block_start; i < block_size / sizeof(int); i++) {
			//printf("INDIRECT BLOCK IN WRITE: %d\n", indirect[i]);
			if(!start) {
				start = true;
				fseek(disk, indirect[i] * block_size + start_byte, SEEK_SET);
			}
			else {
				fseek(disk, indirect[i] * block_size, SEEK_SET);
			}
			while(num_bytes != 0 && start_byte != block_size) {
				fread(&filesize_check, sizeof(char), 1, disk);
				//printf("CH: %c\n", ch);
				if(filesize_check == '\0'){
					inode.file_size++; //increment the filesize
				}
				fseek(disk, -1, SEEK_CUR);
				fwrite(&ch, sizeof(char), 1, disk);
				
				//char temp_ch;
				//fseek(disk, -1, SEEK_CUR);
				//fread(&temp_ch, sizeof(char), 1, disk);
				//printf("READING %c\n", temp_ch);
				num_bytes--;
				start_byte++;
			}
			if(num_bytes == 0) {
				inode.last = indirect[i];
				goto FINISH;
			}
			if(start_byte == block_size) start_byte = 0;
		}
	}
	if(num_bytes != 0) { //check the double indirect block if you still need to write
		DOUBLE:
		if(block_start >= block_size / sizeof(int) + 12) {
			block_start -= (block_size / sizeof(int) + 12);
		}
		else {
			block_start = 0;
		}
		int indirect_ptr = 0;
		while(block_start >= block_size / sizeof(int)) {
			indirect_ptr++;
			block_start -= block_size / sizeof(int);
		}
		//fprintf(stderr, "HERE---------------------------------------------------\n");
		int doubleptr_block[block_size / sizeof(int)];
		fseek(disk, inode.doubleptr * block_size, SEEK_SET);
		fread(&doubleptr_block, sizeof(int), block_size / sizeof(int), disk); //read in double indirect block
		 
		for(int i = indirect_ptr; i < block_size / sizeof(int); i++) { //iterates through the indirect blocks
			int indirect[block_size / sizeof(int)];
			fseek(disk, doubleptr_block[i] * block_size, SEEK_SET);
			fread(&indirect, sizeof(int), block_size / sizeof(int), disk);
			
			for(int j = block_start; j < block_size / sizeof(int); j++) { //iterates through each block to write
				if(!start) {
					start = true;
					fseek(disk, indirect[j] * block_size + start_byte, SEEK_SET);
				}
				else {
					fseek(disk, indirect[j] * block_size, SEEK_SET);
				}
				while(num_bytes != 0 && start_byte != block_size) {
					fread(&filesize_check, sizeof(char), 1, disk);
					//printf("CH: %c\n", ch);
					if(filesize_check == '\0'){
						inode.file_size++; //increment the filesize
					}
					fseek(disk, -1, SEEK_CUR);
					fwrite(&ch, sizeof(char), 1, disk);
					num_bytes--;
					start_byte++;
				}
				if(num_bytes == 0) {
					inode.last = indirect[j];
					goto FINISH;
				}
				if(start_byte == block_size) start_byte = 0;
			}
		}
	}
	if(num_bytes != 0) {
		fprintf(stderr, "Out of Space.\n");
		return -1;
	}
	FINISH:
	//fprintf(stderr, "file_size: %d\n", inode.file_size);
	fseek(disk, ilocation * block_size, SEEK_SET); 
	fwrite(&inode, sizeof(inode_t), 1, disk); //write updated inode to disk
	return 0;
}
/*
FOR DISK CONTROLLER ONLY
Method to read from the blocks in the disk. Returns -1 if you cannot read that section or if there is nothing to read. Returns 0 if successful
*/
int read_DISK(int id, char* filename, int start_byte, int num_bytes){
	bool start = false;
	//fprintf(stderr, "start_byte: %d\n", start_byte);
	//fprintf(stderr, "num_bytes: %d\n", num_bytes);
	requested_content.id = id; //Set the request id
	char temp;	
	char* buff = (char*) malloc(sizeof(char) * num_bytes + 1); //This is where you store the contents of the file you read
	
	pthread_mutex_lock(&imap_lock);
	int ilocation = find_inode_location(filename); //Find the filename you want to read from
	//printf("read_DISK FILE LOCATION: %d\n", ilocation);
	
	if(ilocation == -1){
		return -1; //This file does not exist
	}
	pthread_mutex_unlock(&imap_lock);
	
	inode_t inode;
	
	fseek(disk, ilocation * block_size, SEEK_SET); //Set the read/write head at the beginning
	fread(&inode, sizeof(inode_t), 1, disk); //Get the inode we need from disk
	
	requested_content.inode = inode; //Set the inode to the file in the content

	int count = 0;
	
	int block_start = 0;
	while(start_byte > block_size) { //determines which block to start reading from
		block_start++; 
		start_byte -= block_size; 
	}
	
	//fprintf(stderr, "block_start: %d\n", block_start);
	//fprintf(stderr, "start_byte: %d\n", start_byte);
	//fprintf(stderr, "num_bytes: %d\n", num_bytes);
	
	if(block_start < 12) {
		for(int i = block_start; i < 12; i++) {
			if(i == block_start) {
				//Sets head at the beginning of where we need to read from
				start = true;
				fseek(disk, inode.direct[block_start] * block_size + start_byte, SEEK_SET); 
			}
			else {
				//Move the head to the front of the next block we are reading from
				fseek(disk, inode.direct[i] * block_size, SEEK_SET);
			}
			//Read the contents of this block 
			while(num_bytes != 0 && start_byte != block_size) {
				fread(&temp, sizeof(char), 1, disk); //Read the contents into the buffer
				buff[count] = temp;
				//printf("%c", temp);
				//printf("BUFF FROM read_DISK in while loop: %s\n", buff);
				//fprintf(stderr, "start_byte: %d\n", start_byte);
				
				num_bytes--;
				count++;
				start_byte++;
				//fprintf(stderr, "num_bytes: %d\n", num_bytes);
			}
			if(num_bytes == 0){
				goto FINISH;
/*
				buff[count] = '\0';
				fprintf(stderr, "HERE in read_DISK\n");
				printf("BUFF FROM read_DISK: %s\n", buff);
				strncpy(requested_content.file_content, buff, sizeof(buff));
				printf("REQUESTED_CONTENT.FILE_CONTENT: %s\n", requested_content.file_content);
				final_content.fill = true; //The request has been filled
				requested_content = final_content; //Set the global content equal to the content we just made
				pthread_cond_signal(&content_full); //Signal the full content thread
*/
				
			} //Nothing left to read
			if(start_byte == block_size) {
				start_byte = 0; //Reset start byte before reading next block
				//printf("DONE\n");
			}
		}
	}
	if(num_bytes != 0) { //Reading from indirect block
		//printf("HERE========================\n");
		if(block_start >= 12 && block_start < block_size / sizeof(int) + 12) {
			block_start -= 12;
		}
		else if(block_start >= block_size / sizeof(int) + 12) {
			goto DOUBLE;
		}
		else {
			block_start = 0;
		}
		int indirect[block_size / sizeof(int)];
		fseek(disk, inode.indirectptr * block_size, SEEK_SET);
		fread(&indirect, sizeof(int), block_size / sizeof(int), disk); //Create our own indirect pointer
		
		for(int i = block_start; i < block_size / sizeof(int); i++) { //Read through the indirect block in the same way			
			if(!start) {
				start = true;
				//printf("INDIRECT IN READ %d: %d\n", i, indirect[i]);
				fseek(disk, indirect[i] * block_size + start_byte, SEEK_SET);
			}
			else {
				fseek(disk, indirect[i] * block_size, SEEK_SET);
			}
				
			while(num_bytes != 0 && start_byte != block_size){
				fread(&temp, sizeof(char), 1, disk); //Read the contents into the buffer
				//printf("%c", temp);
				buff[count] = temp;
				num_bytes--; //Decrement how many bytes left you need to read
				count++;
				start_byte++;
				//fprintf(stderr, "num_bytes: %d\n", num_bytes);
			}
			if(num_bytes == 0) goto FINISH; 
			if(start_byte == block_size) start_byte = 0;
		}
	}
	if(num_bytes != 0) { //check the double indirect block if you still need to read
		DOUBLE:
		if(block_start >= block_size / sizeof(int) + 12) {
			block_start -= (block_size / sizeof(int) + 12);
		}
		else {
			block_start = 0;
		}
		int indirect_ptr = 0;
		while(block_start >= block_size / sizeof(int)) {
			indirect_ptr++;
			block_start -= block_size / sizeof(int);
		}
		//fprintf(stderr, "HERE---------------------------------------------------\n");
		int doubleptr_block[block_size / sizeof(int)];
		fseek(disk, inode.doubleptr * block_size, SEEK_SET);
		fread(&doubleptr_block, sizeof(int), block_size / sizeof(int), disk); //read in double indirect block
		 
		for(int i = indirect_ptr; i < block_size / sizeof(int); i++) { //iterates through the indirect blocks
			int indirect[block_size / sizeof(int)];
			fseek(disk, doubleptr_block[i] * block_size, SEEK_SET);
			fread(&indirect, sizeof(int), block_size / sizeof(int), disk);
			
			for(int j = block_start; j < block_size / sizeof(int); j++) { //iterates through each block to write
				if(!start) {	
					start = true;
					fseek(disk, indirect[j] * block_size + start_byte, SEEK_SET);
				}
				else {
					fseek(disk, indirect[j] * block_size, SEEK_SET);
				}
				while(num_bytes != 0 && start_byte != block_size) {
					fread(&temp, sizeof(char), 1, disk); //Read the contents into the buffer
					//printf("%c", temp);
					buff[count] = temp;
					num_bytes--; //Decrement how many bytes left you need to read
					count++;
					start_byte++;
				}
				if(num_bytes == 0) goto FINISH;
				if(start_byte == block_size) start_byte = 0;
			}
		}
	}
	//printf("\n");
	FINISH:
	//printf("%s\n", buff);

	buff[count] = '\0';
	//fprintf(stderr, "HERE in read_DISK\n");
	//printf("BUFF FROM read_DISK: %s\n", buff);
	requested_content.file_content = buff;
	//strncpy(requested_content.file_content, buff, sizeof(buff));
	//printf("REQUESTED_CONTENT.FILE_CONTENT: %s\n", requested_content.file_content);
	requested_content.fill = true; //The request has been filled
	//requested_content = final_content; //Set the global content equal to the content we just made
	//pthread_cond_signal(&content_full); //Signal the full content thread
	printf("%s\n", buff);
	
	return 0;
}
/*
Method to import content from filename2 and replacing the content in filename1. If filename1 does not exist, create a new one.
*/
int import_DISK(char* filename1, char* filename2) {
	FILE* replace;
	if(file_exists(filename2)) {
		replace = fopen(filename2, "r+");
	}
	else {
		//printf("-------------------------------------------------------\n");
		return -1;
	}
	
	int retVal = create(filename1);
	if(retVal == -1) { //there is no space to create file
		return -1;
	}
	else if(retVal == -2) {
		pthread_mutex_lock(&imap_lock);
		int ilocation = find_inode_location(filename1);
		if(ilocation != -1) {
			delete(filename1);
		}
		pthread_mutex_unlock(&imap_lock);
	}
	char ch;
	//printf("CH: %c\n", ch);
	int start_byte = 0;
	while(true) {
		ch = getc(replace);
		if(ch == EOF) break;
		if(write_DISK(filename1, ch, start_byte, 1) == -1) {
			//printf("-------------------------------------------------------\n");
			return -1;
		}
		start_byte++;
	}
	//printf("START BYTE: %d\n", start_byte);
	return 0;
}

/*
FOR DISK CONTROLLER ONLY
Method to delete a specified file from the disk. Return a 0 if successful, else -1
*/
int delete(char* filename) {
	pthread_mutex_lock(&imap_lock);
	int ilocation = find_inode_location(filename); //find the inode location
	if(ilocation == -1) { //there is no file with the file name
		pthread_mutex_unlock(&imap_lock);
		return -1;
	}
	pthread_mutex_unlock(&imap_lock);
	
	inode_t inode;
	fseek(disk, ilocation * block_size, SEEK_SET); //go to the location of the inode
	fread(&inode, sizeof(inode_t), 1, disk); //read in inode
	
	write_DISK(filename, 0, 0, inode.file_size); //write null character to file (essentially deleting contents in the file
	
	pthread_mutex_lock(&imap_lock);
	pthread_mutex_lock(&dmap_lock);
	num_inode--;
	for(int i = 0; i < 12; i++) { //free up the direct blocks of the inode 
		update_block(inode.direct[i], 1);
	}
	
	int indirect[block_size / sizeof(int)];
	fseek(disk, inode.indirectptr * block_size, SEEK_SET);
	fread(&indirect, sizeof(int), block_size / sizeof(int), disk); //read in indirect block from disk
	
	for(int i = 0; i < block_size / sizeof(int); i++) { //free up all the blocks of the indirect
		update_block(indirect[i], 1);
	}
	update_block(inode.indirectptr, 1); //free up the indirect block
	
	int doubleptr[block_size / sizeof(int)];
	fseek(disk, inode.doubleptr * block_size, SEEK_SET);
	fread(&doubleptr, sizeof(int), block_size / sizeof(int), disk); //reading the double indirect block from disk
	
	for(int i = 0; i < block_size / sizeof(int); i++) {
		int indirect_block[block_size / sizeof(int)]; //indirect block from double indirect
		fseek(disk, doubleptr[i] * block_size, SEEK_SET);
		fread(&indirect_block, sizeof(int), block_size / sizeof(int), disk);
		
		for(int j = 0; j < block_size / sizeof(int); j++) {
			update_block(indirect_block[j], 1); //free up the blocks of the indirect
		}
		update_block(doubleptr[i], 1); //free up the indirect block
	}
	update_block(inode.doubleptr, 1); //free up block of the double indirect
	update_block(ilocation, 1); //free up block of the inode
	delete_inode(ilocation); //free up inode in the inode map
	
	pthread_mutex_unlock(&dmap_lock);
	pthread_mutex_unlock(&imap_lock);
	return 0;
}

/*
FOR DISK OP
Method to list all the file names and their sizes in the disk. Will request inodes from the DISK CONTROLLER to print the file names and sizes. Return -1 if there is no files in disk, else 0

int list(int id) {
	fprintf(stderr, "NUM INODES: %d\n", num_inode);
	pthread_mutex_lock(&imap_lock);
	char space = ' ';
	printf("FILE NAME:%*cFILE SIZE:\n", 23, space);
	for(int i = 0; i < num_inode; i++) {
		while(!requested_content.fill && requested_content.id != id) {
			pthread_cond_wait(&content_full, &imap_lock);
		}
		inode_t inode = requested_content.inode;
		printf("%-*s%d\n", 33, inode.filename, inode.file_size);
		requested_content.fill = false;
	}
	pthread_cond_signal(&content_empty);
	pthread_mutex_unlock(&imap_lock);
	return 0;
}*/

int list() {
	//fprintf(stderr, "NUM INODES: %d\n", num_inode);
	pthread_mutex_lock(&imap_lock);
	char space = ' ';
	printf("FILE NAME:%*cFILE SIZE:\n", 23, space);
	for(int i = 0; i < 256; i++) {
		if(imap[i] != 0) {
			inode_t inode = find_inode(imap[i]);
			printf("%-*s%d\n", 33, inode.filename, inode.file_size);
		}
	}
	pthread_mutex_unlock(&imap_lock);
	return 0;
}



/*

*/
void put_request(int id, char request_type, char* filename1, char* filename2, char ch, int start_byte, int num_bytes) {
	request_t new_request;
	new_request.id = id;
	new_request.request_type = request_type;
	strncpy(new_request.filename1, filename1, sizeof(new_request.filename1));
	strncpy(new_request.filename2, filename2, sizeof(new_request.filename2));
	new_request.ch = ch;
	new_request.start_byte = start_byte;
	new_request.num_bytes = num_bytes;
	
	requests[r_fill_pointer] = new_request;
	r_fill_pointer = (r_fill_pointer + 1) % 4;
	r_ctr++;
}

/*

*/
request_t get_request() {
	request_t ret_request = requests[r_use_pointer];
	r_use_pointer = (r_use_pointer + 1) % 4;
	r_ctr--;
	return ret_request;
}
/*

*/
void* disk_controller() {
	while(true) {
		if(shutdown && r_ctr == 0) return NULL;
		
		//printf("R_CTR BEFORE COMMAND: %d\n", r_ctr);
		pthread_mutex_lock(&request_lock);
		while(r_ctr == 0) { //request buffer is empty
			//printf("CONTROLLER SLEEPING\n");
			pthread_cond_wait(&request_empty, &request_lock);
		}
		//fprintf(stderr, "CONTROLLER GOT ONE REQUEST\n");
		//printf("R_CTR AFTER REQUEST: %d\n", r_ctr);
		request_t nr = get_request(); //get the new request from the buffer
		pthread_mutex_unlock(&request_lock);
		
		switch(nr.request_type) {
			case 'R':
				//fprintf(stderr, "CONTROLLER AT R\n");
				if(read_DISK(nr.id, nr.filename1, nr.start_byte, nr.num_bytes) == -1) {
					fprintf(stderr, "Error: reading %s was unsucessful.\n", nr.filename1);
					requested_content.successful = false;
				}
				else requested_content.successful = true;
				//fprintf(stderr, "CONTENT: %s\n", requested_content.file_content);
				pthread_cond_signal(&content_full);
				break;
				
			case 'W':
				//fprintf(stderr, "CONTROLLER AT W\n");
				if(write_DISK(nr.filename1, nr.ch, nr.start_byte, nr.num_bytes) == -1) {
					fprintf(stderr, "Error: writing %s was unsucessful.\n", nr.filename1);
					requested_content.successful = false;
				}
				else requested_content.successful = true;
				break;
				
			case 'C': ;
				//fprintf(stderr, "CONTROLLER AT C\n");
				int retVal = create(nr.filename1);
				if(retVal == -1) {
					fprintf(stderr, "Error: No space to create file %s\n.", nr.filename1);
					requested_content.successful = false;
				}
				else if(retVal == -2) { 
					fprintf(stderr, "Error: %s already exists.\n", nr.filename1);
					requested_content.successful = false;
				}
				else requested_content.successful = true;
				//printf("ENDING CREATE\n");
				break;
				
			case 'D':
				//fprintf(stderr, "CONTROLLER AT D\n");
				if(delete(nr.filename1) == -1) {
					fprintf(stderr, "Error: deleting %s was unsucessful.\n", nr.filename1);
					requested_content.successful = false;
				}
				else requested_content.successful = true;
				break;
			case 'L':
				//fprintf(stderr, "CONTROLLER AT L\n");
				/*for(int i = 0; i < 256; i++) {
					pthread_mutex_lock(&imap_lock);
					if(imap[i] != 0) {
						while(requested_content.fill) {
							pthread_cond_wait(&content_empty, &imap_lock);
						}
						requested_content.id = nr.id;
						requested_content.inode = find_inode(imap[i]);
						requested_content.fill = true;
						pthread_mutex_unlock(&imap_lock);
						pthread_cond_signal(&content_full);
					}
					else pthread_mutex_unlock(&imap_lock);
				}*/
				list();
				break;
			case 'A':
				if(cat_DISK(nr.id, nr.filename1) == -1) {
					fprintf(stderr, "Error: cat %s was unsucessful.\n", nr.filename1);
					requested_content.successful = false;
				}
				else {
					requested_content.successful = true;
				}
				pthread_cond_signal(&content_full);
				break;
			case 'I':
				if(import_DISK(nr.filename1, nr.filename2) == -1) {
					fprintf(stderr, "Error: import %s %s was unsucessful.\n", nr.filename1, nr.filename2);
					requested_content.successful = false;
				}
				else {
					requested_content.successful = true;
				}
				break;
		}
		//printf("CONTENT: %s\n", requested_content.file_content);
		//printf("Done with one command in controller\n");
		save_imap();
		//printf("AFTER SAVE IMAP\n");
		save_dmap();
		//pthread_mutex_unlock(&request_lock);
		pthread_cond_signal(&request_full);
		//printf("HERE?\n");
	}
	//printf("Out of while loop in controller\n");
}

/*

*/
void* disk_op(void* arg) {
	char* command_file = (char*) arg;
	int id = thread_id; //each thread will get its own unique id
	thread_id++; //increment global variable so no two thread have the same id
	
	//printf("%s\n", command_file);
	if(!file_exists((char*) command_file)) {
		fprintf(stderr, "File %s does not exist.\n", command_file);
		return NULL;
	}
	FILE* cfile = fopen(command_file, "r+");
	char line[100];
	while(fgets(line, sizeof(line), cfile)) {
		
		if(shutdown) break;
		
		char* command = strtok(line, " \n");
		//printf("COMMAND: %s\n", command);
		
		if(strcmp(command, "CREATE") == 0) {
			char* filename = strtok(NULL, " \n");
			//filename[strlen(filename) - 1] = '\0';
			//fprintf(stderr, "FILENAME IN DISK OP: %s\n", filename);
			if(filename == NULL) {
				fprintf(stderr, "Error: filename could not be found for CREATE.\n");
				goto END;
			}
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'C',  filename, " ", 0, 0, 0);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
		}
		else if(strcmp(command, "DELETE") == 0) {
			char* filename = strtok(NULL, " \n");
			if(filename == NULL) {
				fprintf(stderr, "Error: filename could not be found for DELETE.\n");
				goto END;
			}
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'D',  filename, " ", 0, 0, 0);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
		}
		else if(strcmp(command, "WRITE") == 0) {
			char* filename = strtok(NULL, " \n");
			if(filename == NULL) {
				fprintf(stderr, "Error: filename could not be found for WRITE.\n");
				goto END;
			}
			char* chh = strtok(NULL, " \n");
			//printf("CHH: %s; SIZE OF: %d\n", chh, strlen(chh));
			if(chh == NULL) {
				fprintf(stderr, "Error: character could not be found for WRITE.\n");
				goto END;
			}
			if(strlen(chh) != 1) {
				fprintf(stderr, "Error: string found when needs to be a character for WRITE.\n");
				goto END;
			} 
			char ch = chh[0];

			char* num1 = strtok(NULL, " \n");
			if(num1 == NULL) {
				fprintf(stderr, "Error: start byte could not be found for WRITE.\n");
				goto END;
			}
			int start_byte = atoi(num1);
			
			char* num2 = strtok(NULL, " \n");
			if(num2 == NULL) {
				fprintf(stderr, "Error: number of bytes could not be found for WRITE.\n");
				goto END;
			}
			int num_bytes = atoi(num2);
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'W',  filename, " ", ch, start_byte, num_bytes);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
		}
		else if(strcmp(command, "READ") == 0) {
			char* filename = strtok(NULL, " \n");
			if(filename == NULL) {
				fprintf(stderr, "Error: filename could not be found for READ.\n");
				goto END;
			}

			char* num1 = strtok(NULL, " \n");
			if(num1 == NULL) {
				fprintf(stderr, "Error: start byte could not be found for READ.\n");
				goto END;
			}
			int start_byte = atoi(num1);
			
			char* num2 = strtok(NULL, " \n");
			if(num2 == NULL) {
				fprintf(stderr, "Error: number of bytes could not be found for READ.\n");
				goto END;
			}
			int num_bytes = atoi(num2);
			
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'R',  filename, " ", 0, start_byte, num_bytes);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
			
	/*		pthread_mutex_lock(&imap_lock);
			while(!requested_content.fill || requested_content.id != id) {
				pthread_cond_wait(&content_full, &imap_lock);
			}
			//printf("THIS SECOND\n");
			//fprintf(stderr, "FILE CONTENT FOR FILE %s\n", filename);
			//printf("%s\n", requested_content.file_content);
			requested_content.fill = false;
			pthread_mutex_unlock(&imap_lock);*/
		}
		else if(strcmp(command, "LIST") == 0) {
			//fprintf(stderr, "HELLO\n");
			char* test = strtok(NULL, " \n");
			if(test != NULL) {
				fprintf(stderr, "Error: there should be no parameters for LIST.\n");
				goto END;
			}
			
			//fprintf(stderr, "HELLO\n");
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'L',  " ", " ", 0, 0, 0);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
			
			//fprintf(stderr, "HELLO\n");
			/*if(list(id) == -1) {
				printf("There are no files in the disk.");
			}*/
		}
		else if(strcmp(command, "SHUTDOWN") == 0) {
			shutdown = true;
			break;
		}
		else if(strcmp(command, "CAT") == 0){
			char* filename = strtok(NULL, " \n");
			if(filename == NULL) {
				fprintf(stderr, "Error: filename could not be found for CAT.\n");
				goto END;
			}
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'A',  filename, " ", 0, 0, 0);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
			
	/*		pthread_mutex_lock(&imap_lock);
			while(!requested_content.fill || requested_content.id != id) {
				pthread_cond_wait(&content_full, &imap_lock);
			}
			//printf("THIS SECOND\n");
			//fprintf(stderr, "FILE CONTENT FOR FILE %s\n", filename);
			//printf("%s\n", requested_content.file_content);
			requested_content.fill = false;
			pthread_mutex_unlock(&imap_lock);*/
		}
		else if(strcmp(command, "IMPORT") == 0) {
			char* filename1 = strtok(NULL, " \n");
			if(filename1 == NULL) {
				fprintf(stderr, "Error: filename could not be found for IMPORT.\n");
				goto END;
			}
			char* filename2 = strtok(NULL, " \n");
			if(filename2 == NULL) {
				fprintf(stderr, "Error: filename could not be found for IMPORT.\n");
				goto END;
			}
			pthread_mutex_lock(&request_lock);
			while(r_ctr == 4) { //check if request buffer is filled or not
				pthread_cond_wait(&request_full, &request_lock);
			}
			put_request(id, 'I',  filename1, filename2, 0, 0, 0);
			pthread_mutex_unlock(&request_lock);
			pthread_cond_signal(&request_empty);
			
		}
		END:;
		//printf("THREAD with id(%d) done with one command\n", id);
	}
	return NULL;
}












