'''Donato,Brandon,bdonato1@binghamton.edu,CS 110, B57, Jia Yang,\
assignment3Ex2'''

def allConnections(polygonSides):
  runningTotal = 0
  for counter in range(1,polygonSides):
    runningTotal = runningTotal + counter
  return runningTotal

def diagonalCalc(polygonSides):
  diagonals = allConnections(polygonSides) - polygonSides
  return diagonals

def main():
  numSides = input("How many sides does the polygon have? ")
  numSides = int(numSides)
  if numSides<=2:
    print("You must choose an integer greater than 2")
  else:
    print("The polygon has",diagonalCalc(numSides),"diagonals.")

main()
