#
# Specifiy the target
all:	FBL

# Specify the object files that the target depends on
# Also specify the object files needed to create the executable
FBL:	FBLPostLL.o FBLPost.o FBLUser.o FBLUserLL.o FBLCommentLL.o FBLComment.o CA3.o
	g++  FBLPostLL.o FBLPost.o FBLUser.o FBLUserLL.o FBLCommentLL.o FBLComment.o CA3.o -o FBL

# Specify how the object files should be created from source files
CA3.o:	CA3.cpp FBLUser.h FBLUserLL.h FBLPost.h FBLPostLL.h
	g++ -c CA3.cpp 

FBLPostLL.o:	FBLPostLL.cpp FBLPostLL.h FBLPost.h
	g++ -c FBLPostLL.cpp

FBLPost.o:	FBLPost.cpp FBLPost.h FBLPostLL.h
	g++ -c FBLPost.cpp

FBLUser.o:	FBLUser.cpp FBLUser.h FBLUserLL.h
	g++ -c FBLUser.cpp

FBLUserLL.o:	FBLUserLL.cpp FBLUserLL.h FBLUser.h
	g++ -c FBLUserLL.cpp

FBLCommentLL.o:	FBLCommentLL.cpp FBLCommentLL.h FBLComment.h
	g++ -c FBLCommentLL.cpp

FBLComment.o:	FBLComment.cpp FBLComment.h
	g++ -c FBLComment.cpp

# Specify the object files and executables that are generated
# and need to be removed to re-compile the whole thing
clean:
	rm -f *.o FBL

