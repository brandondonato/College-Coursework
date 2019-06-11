"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
FinalProjectCardClass
"""

class Card():
#-- Constants ----------------------------------------------------------------

  # These are the values of each of the cards in the game
  TWOS = 2
  THREES = 3
  FOURS = 4
  FIVES = 5
  SIXES = 6
  SEVENS = 7
  EIGHTS = 8
  NINES = 9
  TENS = 10
  JACKS = 10
  QUEENS = 10
  KINGS =  10

  ACES = 11
#-- Constructor --------------------------------------------------------------

  # Creates the individual cards and assigns them a value
  # params: suit (str) - The suit of the card
  #         value (str) - The value (Two - Ace) of the card
  def __init__(self, suit, value):
    self.__suit = suit
    self.__value = value
    self.__valueDict = {"Two": TWOS, "Three": THREES, "Four": FOURS, \
                        "Five": FIVES, "Six": SIXES,"Seven": SEVENS, \
                        "Eight": EIGHTS, "Nine": NINES, "Ten": TENS,\
                        "Jack": JACKS, "Queen": QUEENS, "King": KINGS,\
                        "Ace" : ACES}
    self.__cardValue = self.__valueDict[self.__value]
    self.__cardImage = '%s_of_%s' % (self.__value, self.__suit)

#-- Accessors -----------------------------------------------------------------

  # Returns the numeric value of the card
  def getCardValue(self):
    return self.__cardValue

  #Returns the face value of the card
  def getCardFaceValue(self):
    return self.__value

  # Returns the suit of the card
  def getSuit(self):
    return self.__suit

  #Returns the name of the cards image
  def getCardImage(self):
    return self.__cardImage
  
#-- Convert to String --------------------------------------------------------

  # Returns the string representation of the card
  def __str__(self):
    return '%s of %s' % (self.__value, self.__suit)
