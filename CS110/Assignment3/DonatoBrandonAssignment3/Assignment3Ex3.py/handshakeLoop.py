'''Donato,Brandon,bdonato1@binghamton.edu,CS 110, B57, Jia Yang,\
assignment3Ex3'''

import summations

def main():
  
  numPeople = input("How many people are there? ")
  numPeople = int(numPeople)
  print("There are",summations.handshakes(numPeople),"handshakes.")
  print("There are",summations.handshakeLoop(numPeople),"handshakes.")
  print("The formula and iteration methods compute %s" % \
         ("the same value" if summations.compareInts\
          (summations.handshakes(numPeople), \
          summations.handshakeLoop(numPeople)) \
          else "different values"))

main()
