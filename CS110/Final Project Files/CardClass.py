"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
FinalProjectCardClass
"""

class Card:

#-- Constructor --------------------------------------------------------------

  # Creates the individual cards and assigns them a value
  # params: suit (str) - The suit of the card
  #         value (str) - The value (Two - Ace) of the card
  def __init__(self, suit, value):
    self.__suit = suit
    self.__value = value
    self.__valueDict = {"Two": 2, "Three": 3, "Four": 4, "Five": 5, "Six": 6,\
                        "Seven": 7, "Eight": 8, "Nine": 9, "Ten": 10,\
                        "Jack": 10, "Queen": 10, "King": 10, "Ace" : 11}
    self.__cardValue = self.__valueDict[self.__value]

#-- Accessors -----------------------------------------------------------------

  # Returns the numeric value of the card
  def getCardValue(self):
    return self.__cardValue

  # Returns the suit of the card
  def getSuit(self):
    return self.__suit
  
#-- Convert to String --------------------------------------------------------

  # Returns the string representation of the card
  def __str__(self):
    return '%s of %s' % (self.__value, self.__suit)
