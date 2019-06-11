'''Donato,Brandon,bdonato1@binghamton.edu,CS 110, B57, Jia Yang,\
assignment3Ex3'''

def handshakes(people):
  return (people * (people - 1))//2

def handshakeLoop(people):
  runningTotal = 0
  for counter in range(1,people):
    runningTotal = runningTotal + counter
  return runningTotal

def compareInts(first,second):
  return first==second

def allConnections(polygonSides):
  runningTotal = 0
  for counter in range(1,polygonSides):
    runningTotal = runningTotal + counter
  return runningTotal

def diagonalCalc(polygonSides):
  diagonals = allConnections(polygonSides) - polygonSides
  return diagonals
