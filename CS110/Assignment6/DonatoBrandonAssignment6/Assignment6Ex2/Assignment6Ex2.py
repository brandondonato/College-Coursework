'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment6Ex2
'''

'''
Analysis:

Create a game where the player inputs the amount of money he or she is
willing to bet and that is added to the pot. The program then simulates
a dice roll for each turn and if the sum of the dice is 7, the player
wins $4. If not, the player loses $1. The aformentioned amounts are
added or subtracted from the pot as the program runs. The game ends when
there is no money left in the pot.

Output to monitor:
  rollNumber (int) - What number roll it is.
  diceValue (int) - Sum of the dice.
  pot (int) - The amount of money in the pot.
  bestPot (int) - The most amount of money in the pot.
  bestRoll (int) - The number roll that the user had the most money in
                     the pot.

Input from keyboard:
  pot (int) - The starting value of the pot.

Tasks allocated to Functions:
  diceSum()
  luckySevensGame()
  initalPotValidation()

'''

import random

# Constants
RANGE_OF_DICE_1 = 1
RANGE_OF_DICE_7 = 7
MONEY_ADDED_TO_POT = 4
DICE_ROLL_OF_7 = 7

# Validates starting pot value
# param initalPot (str)
# return True when the starting pot is only made of digits and greater
#   than 0
def initalPotValidation(initalPot):
  return initalPot.isdigit() and int(initalPot) > 0

# Simulates rolling 2 dice 
# returns the sum of two die
def diceSum():
  dice1 = random.randrange(RANGE_OF_DICE_1,RANGE_OF_DICE_7)
  dice2 = random.randrange(RANGE_OF_DICE_1,RANGE_OF_DICE_7)
  diceSum = (dice1 + dice2)
  return diceSum

# Plays the Lucky Sevens Game
# param pot (int)
# returns the roll, the value of the roll, the amount of money in the pot
#   the best roll and the most money in the pot
def luckySevensGame(pot):
  rollNumber = 0
  bestRoll = 0
  bestPot = pot
  while pot > 0:
    diceValue = diceSum()
    if diceValue == DICE_ROLL_OF_7:
      pot = pot + MONEY_ADDED_TO_POT
    else:
      pot = pot - 1
    rollNumber += 1
    if pot > bestPot:
      bestPot = pot
      bestRoll = rollNumber
    print(rollNumber, '\t', diceValue, '\t', pot)
  print("Your best roll was roll", bestRoll, "and you had", bestPot, \
        "dollars.")
  print("You lost all your money after", rollNumber, "rolls.")

# The program takes in the inital betting value and then plays the Lucky
#   Sevens Game.
def main():
  # Priming read 
  pot = input("Please enter a starting bet in whole dollars\
 or press <Enter> to quit. ")

  while pot:
    # Validates the users inital pot input
    while not initalPotValidation(pot):
      print("Your input is incorrect.\
 Please enter an amount in whole dollars.")
      pot = input("Please enter a starting bet in whole dollars\
 or press <Enter> to quit.")

    # Prints the head of the table
    print("Roll",'\t',"Value", '\t', "Dollars in Pot")

    # Converts the inital pot value into the 
    pot = int(pot)

    # Plays the Lucky Sevens Game
    rounds = luckySevensGame(pot)

    # Continuation of the loop
    pot = input("Please enter a starting bet in whole dollars\
 or press <Enter> to quit. ")
    
main()
