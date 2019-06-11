'''
Devon Randall
drandal1@binghamton.edu
Lab section# B57
CA name: Tom Donohue
Final Project
'''
'''
A GUI that is used to play blackjack. Uses a pygame display to show cards
being dealt and modified based on the users interactions with the buttons
on the GUI.
'''
from tkinter import *
from Display import *

class BlackJackGUI:
  MAX_FOR_PLAYER=21

  def __init__(self):
    #Creates the display showing the cards
    self.__display=Display()


    #creates the title label
    self.__titleLabel=Label(text='A Game of BlackJack').grid(row=0,column=0)


    #Creating the Dealer display showing its total card amount
    self.__dealerNameLabel=Label(text='Dealer').grid(row=1,column=1)
    self.__dealerCardValueLabel=Label(text='Total Card Value:').grid\
                                 (row=2,column=1)

    self.__dealerCardValueVar=IntVar()
    self.__dealerCardValueVar.set(self.__display.getDealerCardsValue())


    self.__dealerCardValue=Label(textvariable=self.__dealerCardValueVar).\
                            grid(row=3,column=1)


    #Set up the Player display

    self.__playerNameLabel=Label(text='Player').grid(row=9,column=1)
    self.__playerCardValueLabel=Label(text='Total Card Value:').grid\
                                 (row=10,column=1)

    self.__playerCardValueVar=IntVar()
    self.__playerCardValueVar.set(self.__display.getPlayerCardsValue())


    self.__playerCardValue=Label(textvariable=self.__playerCardValueVar).\
                            grid(row=11,column=1)


    #Creating the buttons
    self.__resetGameButton=Button(text='Reset Game', state='normal',command=\
                                  self.resetGame)
    self.__resetGameButton.grid(row=6,column=0)

    self.__standButton=Button(text='Stand',state='normal',command=self.stand)
    self.__standButton.grid(row=8,column=1)

    self.__hitButton=Button(text='Hit Me',state='normal',command=\
                            self.addCard)
    self.__hitButton.grid(row=8,column=0)

    self.__quitButton=Button(text='Exit Card Display',\
                             state='normal',command=self.quitDisplay)
    self.__quitButton.grid(row=6,column=1)

 

    #Mainloop started
    mainloop()



  #Resets the Game (partially works)
  def resetGame(self):
    self.__display.reset()
    self.__dealerCardValueVar.set(self.__display.getDealerCardsValue())
    self.__playerCardValueVar.set(self.__display.getPlayerCardsValue())
    self.__hitButton.config(state='normal')
    self.__standButton.config(state='normal')
    

  #Upon hitting stand button will have the dealer continue dealing itself cards
  #until the total value is >=17
  def stand(self):
    self.__display.stand()
    self.__dealerCardValueVar.set(self.__display.getDealerCardsValue())
    self.__standButton.config(state='disabled')
    self.__hitButton.config(state='disabled')


  #Adds a card to the players hand, will stop and disable the button if 
  #clicked once the total value of all the players cards is >= 21


  def addCard(self):
    if self.__display.getPlayerCardsValue() < BlackJackGUI.MAX_FOR_PLAYER:
      self.__display.addCard()
      self.__playerCardValueVar.set(self.__display.getPlayerCardsValue())
    else:
      self.__hitButton.config(state='disabled')


  #closes the display window
  def quitDisplay(self):
    self.__display.exitDisplay()

    


BlackJackGUI()
