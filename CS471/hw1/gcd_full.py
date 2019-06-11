#import sys

#Iterative gcd function
def gcdI(i, j):
    while i != j:
        if i > j:
            i = i-j
        else:
            j = j-i
    return i

#Recursive gcd function
def gcdR(i, j):
    if j != 0:
        return gcdR(j, i%j)
    else:
        return i


def main():
    firstI = int(input("Please enter an integer number: "))
    secondI = int(input("Please enter a second integer number: "))
    print("This is gcdI: %d" % gcdI(firstI, secondI))
    print("This is gcdR: %d" % gcdR(firstI, secondI))


main()
