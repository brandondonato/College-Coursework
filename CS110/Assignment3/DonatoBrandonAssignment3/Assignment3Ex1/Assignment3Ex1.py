'''Donato,Brandon,bdonato1@binghamton.edu,CS 110, B57, Jia Yang,\
assignment3Ex1'''

def handshakes(people):
  return (people * (people - 1))//2

def handshakeLoop(people):
  runningTotal = 0
  for counter in range(1,people):
    runningTotal = runningTotal + counter
  return runningTotal

def compareInts(first,second):
  return first==second

def main():
  numPeople = input("How many people are there? ")
  numPeople = int(numPeople)
  if numPeople <=1:
    print("There are 0 handshakes")
  else:
    print("There are",handshakes(numPeople),"handshakes.")
    print("There are",handshakeLoop(numPeople),"handshakes.")
    print("The formula and iteration methods compute %s" % \
         ("the same value" if compareInts(handshakes(numPeople),\
                                          handshakeLoop(numPeople)) \
                          else "different values"))

main()
