'''
Devon Randall
drandal1@binghamton.edu
Lab section# B57
CA name: Tom Donohue
Final Project
'''
'''
Used to display a black jack game by showing the cards being dealt
'''
import pygame
from CardClass import *
from DeckClass import *
from HandClass import *
from pygame.locals import *


class Display:
  DISPLAY_WINDOW_COORD=750 #for setting the size of the main display window
  
  CARD_LOCATION=375  #main coordinate used in place dealer and player cards

  DISTANCE_BETWEEN_CARDS=40
  #distance between each card displayed on the X-Axis(left or right) 

  MAX_FOR_DEALER=17  
  #total card value where the dealer stops hitting himself with another card

  MAXIMUM=21
  # Maximum total card value for dealer or player, going over means you lose

  GAME_FINISHED=False
  #Used for ensuring you only have one ending per game

  FONT_SIZE=50
  #Font size for winning/losing messages

  GREEN_COLOR=150
  #coloring of the background

#Constructor------------------------------------------------------------------
  def __init__(self):
    self.__cardDeck=Deck()
  
    #Initializing the screen
    pygame.init()
    self.__window=pygame.display.set_mode\
        ((Display.DISPLAY_WINDOW_COORD,Display.DISPLAY_WINDOW_COORD))
    pygame.display.set_caption('A Game of BlackJack')

    #creating the background
    self.__background= pygame.Surface(self.__window.get_size())
    self.__background = self.__background.convert()
    self.__background.fill((0,Display.GREEN_COLOR,0))

    
    #Deals out the hands and places them on the background(and from that the
    #surface)
    self.dealHands()

    #checks for instant win
    self.wonImmediately()

    #updates window to show changes in background 
    self.updateDisplay()


#Accessors--------------------------------------------------------------------
  #returns the current value of the dealers cards
  def getDealerCardsValue(self):
    return self.__hands.getDealersHandsValue()
  #returns the current value of the players cards    
  def getPlayerCardsValue(self):
    return self.__hands.getPlayersHandsValue()


#Mutators----------------------------------------------------------------------
  
#Deals out both the dealers and players initial hands, loads their 
#corresponding images, and places them in the display
  def dealHands(self):
    self.__hands=Hand(self.__cardDeck)
    self.__dealerHand=self.__hands.getDealersHand()
    for card in self.__dealerHand:
      self.__dealerCardImageLocation=card.getCardImage()
      self.__dealerCardImage=\
                pygame.image.load('%s.png'%(self.__dealerCardImageLocation))
      
    self.__background.blit(self.__dealerCardImage, (Display.CARD_LOCATION,0))
    pygame.display.flip()


    self.__playerHand=self.__hands.getPlayersHand()
    numOfCards=len(self.__playerHand)
    count=0
    for card in self.__playerHand:
      self.__playerCardImageLocation=card.getCardImage()
      self.__playerCardImage=\
                pygame.image.load('%s.png'%(self.__playerCardImageLocation))
      self.__background.blit(self.__playerCardImage,\
        (Display.CARD_LOCATION+(Display.DISTANCE_BETWEEN_CARDS*count)\
         ,Display.CARD_LOCATION))
      pygame.display.flip()
      count+=1
      
      self.wonImmediately()


  
#Adds a card to the players hand, adds it to the display and updates 
#to show the change
  def addCard(self):
    self.__hands.addCardToPlayerHand()
    newHand=self.__hands.getPlayersHand()
    newCard=newHand[len(newHand)-1]
    numOfCards=len(newHand)-1
    self.__playerCardImageLocation=newCard.getCardImage()
    self.__playerCardImage=\
                pygame.image.load('%s.png'%(self.__playerCardImageLocation))
    
    self.__background.blit(self.__playerCardImage,\
        (Display.CARD_LOCATION+(Display.DISTANCE_BETWEEN_CARDS*numOfCards)\
                            ,Display.CARD_LOCATION))
    self.updateDisplay()
    self.wonImmediately()
    self.hasWon()

#has the dealer keep drawing cards until his card total is >= 17
  def stand(self):
    while self.getDealerCardsValue() < Display.MAX_FOR_DEALER:
      self.__hands.addCardToDealerHand()
      newHand=self.__hands.getDealersHand()
      newCard=newHand[len(newHand)-1]
      numOfCards=len(newHand)-1
      self.__dealerCardImageLocation=newCard.getCardImage()
      self.__dealerCardImage=\
                pygame.image.load('%s.png'%(self.__dealerCardImageLocation))
    
      self.__background.blit(self.__dealerCardImage,\
      (Display.CARD_LOCATION+(Display.DISTANCE_BETWEEN_CARDS*numOfCards),0))
      self.updateDisplay()
      self.hasWon()
 

#for updating the window itself with the background(which all of 
# the other stuff is placed onto) to show any changes
  def updateDisplay(self):
    self.__window.blit(self.__background, (0,0))
    pygame.display.flip()
    
#resets the game, clears the players hands, restores the deck,
#resets the background surface, deals new hands and updates the display
  def reset(self):
    self.__hands.clearHands()
    self.__cardDeck.restoreDeck()
    self.__background= pygame.Surface(self.__window.get_size())
    self.__background = self.__background.convert()
    self.__background.fill((0,Display.GREEN_COLOR,0))

    self.dealHands()
    self.updateDisplay()
    Display.GAME_FINISHED=False

#A Predicate checking to see if either the dealer or the player meet
#the conditions to declare a win,loss, or tie
  def hasWon(self):
    if Display.GAME_FINISHED == False:

      #these three are checked only if the dealer has a card total greater
      #than 17 
      if self.getDealerCardsValue() > Display.MAX_FOR_DEALER and \
         self.getDealerCardsValue() < Display.MAXIMUM\
      and self.getPlayerCardsValue()<Display.MAXIMUM:

        #if the dealer has a greater total card value than the player, the
        #player loses
        if self.getDealerCardsValue() > self.getPlayerCardsValue():
          self.__font= pygame.font.Font(None,Display.FONT_SIZE)
          self.__text = self.__font.render\
          ("You've Lost! Press Reset Game to Play Again",1,(0,0,0))
          self.__textPos = self.__text.get_rect(center=\
                            (Display.CARD_LOCATION,Display.CARD_LOCATION))
          self.__background.blit(self.__text,self.__textPos)
          Display.GAME_FINISHED = True
          self.updateDisplay()

        # If the players total is greater than the dealers,the player wins
        elif self.getPlayerCardsValue() > self.getDealerCardsValue():
          self.__font= pygame.font.Font(None,Display.FONT_SIZE)
          self.__text = self.__font.render\
          ("You've Won! Press Reset Game to Play Again",1,(0,0,0))
          self.__textPos = self.__text.get_rect(center=\
                    (Display.CARD_LOCATION,Display.CARD_LOCATION))
          self.__background.blit(self.__text,self.__textPos)
          Display.GAME_FINISHED = True
          self.updateDisplay()

        #If the player and dealer have both gone and have the same total
        #card values, they've tied
        elif self.getPlayerCardsValue() == self.getDealerCardsValue():
          self.__font= pygame.font.Font(None,Display.FONT_SIZE)
          self.__text = self.__font.render\
          ("You've Tied! Press Reset Game to Play Again",1,(0,0,0))
          self.__textPos = self.__text.get_rect(center=\
                    (Display.CARD_LOCATION,Display.CARD_LOCATION))
          self.__background.blit(self.__text,self.__textPos)
          Display.GAME_FINISHED = True 
          self.updateDisplay()

    # if the dealer has 21 and the player does not, the player loses
      elif self.getDealerCardsValue() == Display.MAXIMUM and \
           self.getPlayerCardsValue()!= Display.MAXIMUM:
        self.__font= pygame.font.Font(None,Display.FONT_SIZE)
        self.__text = self.__font.render\
        ("You've Lost! Press Reset Game to Play Again",1,(0,0,0))
        self.__textPos = self.__text.get_rect(center=\
                      (Display.CARD_LOCATION,Display.CARD_LOCATION))
        self.__background.blit(self.__text,self.__textPos)
        Display.GAME_FINISHED = True
        self.updateDisplay()
        
    # if the  dealer has a total card value of over 21, the player wins
      elif self.getDealerCardsValue() > Display.MAXIMUM:
        self.__font= pygame.font.Font(None,Display.FONT_SIZE)
        self.__text = self.__font.render\
        ("You've Won! Press Reset Game to Play Again",1,(0,0,0))
        self.__textPos = self.__text.get_rect(center=\
                        (Display.CARD_LOCATION,Display.CARD_LOCATION))
        self.__background.blit(self.__text,self.__textPos)
        Display.GAME_FINISHED = True
        self.updateDisplay()
       
    #if the player has a total card value over 21, they lose
      elif self.getPlayerCardsValue() > Display.MAXIMUM:
        self.__font= pygame.font.Font(None,Display.FONT_SIZE)
        self.__text = self.__font.render\
        ("You've Lost! Press Reset Game to Play Again",1,(0,0,0))
        self.__textPos = self.__text.get_rect(center=\
            (Display.CARD_LOCATION,Display.CARD_LOCATION))
        self.__background.blit(self.__text,self.__textPos)
        Display.GAME_FINISHED = True
        self.updateDisplay()

 
   


#Another predicate like hasWon() but is used when initially dealing out cards
# in dealHands() to check and mark a win, otherwise does nothing
  def wonImmediately(self):
    
#if the player is initially dealt 21, they win automatically
    if self.getPlayerCardsValue() == Display.MAXIMUM:
      self.__font= pygame.font.Font(None,Display.FONT_SIZE)
      self.__text = self.__font.render\
      ("You've Won! Press Reset Game to Play Again",1,(0,0,0))
      self.__textPos = self.__text.get_rect(center=\
                      (Display.CARD_LOCATION,Display.CARD_LOCATION))
      self.__background.blit(self.__text,self.__textPos)
      Display.GAME_FINISHED = True
      self.updateDisplay()


#exits and closes the display window
  def exitDisplay(self):
    pygame.quit()


      



