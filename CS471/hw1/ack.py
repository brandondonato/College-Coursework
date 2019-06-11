def ackermann(M, N):
    if(M == 0):
        return(N+1)
    elif( (N == 0) and (M>0) ):
        return( ackermann(M-1, 1) )
    elif( (N>0) and (M>0) ):
        return( ackermann(M-1, ackermann(M, N-1) ) )

def main():
    N = input("Please enter an integer number: ")
    print("Your number (N) is: %d\n" % N)
    print("This is the ackerman number for ack(3, N): %d\n" % ackermann(3,N))

main()
i = 10
print("This is a big mistake %d" % i)
