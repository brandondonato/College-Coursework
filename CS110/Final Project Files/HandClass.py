"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
FinalProjectHandClass
"""

class Hand():

#-- Constructor --------------------------------------------------------------

  # Creates the starting hand for the player and the dealer
  # Invokes: shuffleDeck() (Deck)
  #          drawCard() (Deck)
  #          append()
  def __init__(self):
    self.__playerHand = []
    self.__dealerHand = []
    deck = Deck()
    deck.shuffleDeck()
    for i in range(2):
      self.__playerHand.append(deck.drawCard())
      self.__dealerHand.append(deck.drawCard())

#-- Accessors ----------------------------------------------------------------

  # Returns the string view of the player's current hand
  # Invokes: append()
  #          str()
  def getPlayersHand(self):
    self.__playerListStr = []
    for cards in self.__playerHand:
      self.__playerListStr.append(str(cards))
    return self.__playerListStr

  # Returns the string view of the dealer's current hand
  # Invokes: append()
  #          str()
  def getDealersHand(self):
    self.__dealerListStr = []
    for cards in self.__dealerHand:
      self.__dealerListStr.append(str(cards))
    return self.__dealerListStr

  # Returns whether or not a card (Card) is in the player's hand (Bool)
  def isInPlayersHand(self,card):
    return card in self.__playerHand

  # Returns whether or not a card (Card) is in the dealer's hand (Bool)
  def isInDealersHand(self,card):
    return card in self.__dealerHand

  # Clears the hands of both the player and the dealer
  # Invokes: del
  def clearHands(self):
    del self.__playerHand[:]
    del self.__dealerHand[:]

#-- Convert to String --------------------------------------------------------
  def __str__(self):
    return ("The player's cards: " + str(self.getPlayersHand()) + \
            "\nThe dealer's cards: " + str(self.getDealersHand()))

hand = Hand()
deck = Deck()
cardOne = Card("Clubs","Two")
cardTwo = Card("Spades", "Ace")
##print(hand.getPlayersHand())
##print(hand.getDealersHand())
##print(hand.isInPlayersHand(card))
##print(hand.isInDealersHand(card))
##hand.clearHands()
##print(hand.getPlayersHand())
##print(hand.getDealersHand())
##print(hand)
