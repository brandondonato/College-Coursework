'''Donato,Brandon,bdonato1@binghamton.edu,CS 110, B57, Jia Yang,\
assignment3Ex3'''

import summations

def main():
  numSides = input("How many sides does the polygon have? ")
  numSides = int(numSides)
  if numSides<=2:
    print("You must choose an integer greater than 2")
  else:
    print("The polygon has",summations.diagonalCalc(numSides),"diagonals.")

main()
