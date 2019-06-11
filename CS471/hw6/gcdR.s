//Brandon Donato
//Seung Woo Lee
    .file	"gcdR.c"
	.text
	.globl	gcdR
	.type	gcdR, @function
gcdR:
.LFB0:
	.cfi_startproc
	pushq	%rbp                //This is where the stack frame begins
	.cfi_def_cfa_offset 16
	.cfi_offset 6, -16
	movq	%rsp, %rbp          //Keep track of the top of the stack
	.cfi_def_cfa_register 6
	subq	$16, %rsp           //Subtract 16 from the
	movl	%edi, -4(%rbp)      //Move "i" into the register %edi
	movl	%esi, -8(%rbp)      //Move "j" into the register %esi
	cmpl	$0, -8(%rbp)        //Compare if "j" is 0
	jne	.L2                     //If not equal to 0, go to .L2
	movl	-4(%rbp), %eax      //Otherwise, move "i" into %eax
	jmp	.L3                     //Go to .L3
.L2:
	movl	-4(%rbp), %eax      //Move "i" into -4(%rbp)
	cltd                        //Convert the signed integer to a signed long value
	idivl	-8(%rbp)            //Perform modular division of %eax("i") by "j"
    movl	-8(%rbp), %eax      //Move the remainder of i%j into -8(%rbp)
    movl	%edx, %esi          //Move "j" into %edx
	movl	%eax, %edi          //Move "i" into %eax
	call	gcdR                //Recursively call gcdR
.L3:
	leave                       //Exit the program
	.cfi_def_cfa 7, 8
	ret                         //Return from the program
	.cfi_endproc
.LFE0:
	.size	gcdR, .-gcdR
	.ident	"GCC: (Debian 6.3.0-18+deb9u1) 6.3.0 20170516"
	.section	.note.GNU-stack,"",@progbits
