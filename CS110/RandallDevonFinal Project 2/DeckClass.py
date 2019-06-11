"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
FinalProjectDeckClass
"""
from random import *
from HandClass import *
from CardClass import *
class Deck():

#-- Constructor --------------------------------------------------------------

  # Creates a deck of cards
  # Invokes: append()
  def __init__(self):
    self.__suitList = ["Clubs", "Diamonds", "Hearts","Spades"]
    self.__valueList = ["Two","Three","Four","Five","Six","Seven"\
                        ,"Eight","Nine","Ten","Jack","Queen","King","Ace"]
    self.__deck = []
    for suit in self.__suitList:
      for value in self.__valueList:
        self.__newCard = Card(suit,value) 
        self.__deck.append(self.__newCard)
    

#-- Accessors ----------------------------------------------------------------

  # Returns a string representation of what cards are left in the deck
  # Invokes: str()
  #          append()
  def getDeck(self):
    self.__deckListStr = []
    for card in self.__deck:
      self.__deckListStr.append(str(card))
    return self.__deckListStr

#-- Predicates ---------------------------------------------------------------

  # Checks to see if a card is in the current deck
  # param: card (Card) - The card in question
  def isInDeck(self, card):
    return (card in self.__deck)

  # Checks to see if one card has a greater value than another
  # params: cardOne (Card) - Card in the deck being compared
  #         cardTwo (Card) - Card in the deck being compared
  # Invokes: getCardValue() (Card)
  def isGreater(self, cardOne, cardTwo):
    return cardOne.getCardValue() > cardTwo.getCardValue()

  # Checks to see if one card has a lower value than another
  # params: cardOne (Card) - Card in the deck being compared
  #         cardTwo (Card) - Card in the deck being compared
  # Invokes: getCardValue() (Card)
  def isLower(self, cardOne, cardTwo):
    return cardOne.getCardValue() < cardTwo.getCardValue()

#-- Mutators -----------------------------------------------------------------

  # Resets the deck back to its original state
  # Invokes: append()
  def restoreDeck(self):
    self.__deck = []
    for suit in self.__suitList:
      for value in self.__valueList:
        newCard = Card(suit,value) 
        self.__deck.append(newCard)

  # Returns a randomized list of the original deck of cards
  # Invokes: shuffle() (random module)
  def shuffleDeck(self):
    self.restoreDeck()
    shuffle(self.__deck)  ## This is a feature not covered in class
    return self.__deck

  # Returns the first card (Card) in the deck list and removes it from the
  #   deck list
  # Invokes: pop()
  def drawCard(self):
    self.__drawnCard = self.__deck.pop(0)
    return self.__drawnCard

#-- Convert to String --------------------------------------------------------

  # Returns a string representation of the deck of cards
  # Invokes: str() 
  def __str__(self):
    return str(self.getDeck())
    
'''
deck = Deck()
deckList=deck.getDeck()
cardOne = Card("Clubs","Two")
#cardTwo = Card("Spades", "Ace")
print(deckList[0])
print(deck)
print(type(cardOne))
print(deck.isInDeck(cardOne))
print(cardOne==deckList[0])
#cardTwo = Card("Spades", "Ace")
#print(deck.isInDeck(cardTwo))
'''
'''
deck.shuffleDeck()
print(deck.getDeck())
print(deck.isInDeck(cardOne))
print(deck.isInDeck(cardTwo))
print(deck.drawCard())

deck.restoreDeck()
print(deck)
print(deck.isGreater(cardOne, cardTwo))
print(deck.isLower(cardOne, cardTwo))
'''
