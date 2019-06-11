	.file	"gcd.c"
	.text
	.globl	gcdI
	.type	gcdI, @function
gcdI:
.LFB0:
	.cfi_startproc
	pushq	%rbp            //This is where the stack frame begins/is created
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp      //Keep track of the top of the stack
	.cfi_def_cfa_register 6
	movl	%edi, -4(%rbp)  //This is moving variable "i" into the register
	movl	%esi, -8(%rbp)  //This is moving variable "j" into the register
	jmp	.L2                 //Enter the while loop
.L4:
	movl	-4(%rbp), %eax  //Move "i" into register %eax
	cmpl	-8(%rbp), %eax  //Compare variable "i" with "j"
	jle	.L3                 //If less than or equal, jump to section .L3, else continue
	movl	-8(%rbp), %eax  //Move "j" into %eax
	subl	%eax, -4(%rbp)  //Subtract "i" from "j"
	jmp	.L2                 //Go to section .L2
.L3:
	movl	-4(%rbp), %eax  //Move "i" into register %eax
	subl	%eax, -8(%rbp)  //Subtract "j" from "i"
.L2:
	movl	-4(%rbp), %eax  //Move "i" into register %eax
	cmpl	-8(%rbp), %eax  //Make a comparison between "i" and "j"
	jne	.L4                 //If they are not equal, jump to section .L4, else continue
                            //  If you continue, you have exited the while loop
	movl	-4(%rbp), %eax  //Move "i" into register %eax
	popq	%rbp            //This is where the stack frame is removed and there is no longer a pointer
	.cfi_def_cfa 7, 8
	ret                     //This is where the functions ends and where it exits
                            //  It will return the variable left in register %eax ("i")
	.cfi_endproc
.LFE0:
	.size	gcdI, .-gcdI
	.ident	"GCC: (Debian 6.3.0-18+deb9u1) 6.3.0 20170516"
	.section	.note.GNU-stack,"",@progbits
