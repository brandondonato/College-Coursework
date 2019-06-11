import random
import tkinter

class Die:

  def __init__(self):
    self.__value = 1

  def getState(self):
    return self.__value

  def roll(self):
    self.__value = random.randrange(1,7)

  def isEven(self):
    return (self.__value % 2 == 0)

  def __str__(self):
    return str(self.__value)


class DieGUI:
  def __init__(self):
    self.__model = Die()

    # Creates Window
    self.__mainWindow = tkinter.Tk()

    # Creates Frames
    self.__topFrame = tkinter.Frame(self.__mainWindow)
    self.__middleFrame = tkinter.Frame(self.__mainWindow)
    self.__bottomFrame = tkinter.Frame(self.__mainWindow)

    # Create and Pack Label
    self.__promptLabel = tkinter.Label(self.__topFrame, text =\
                                       "This is your die. \
Press the button to roll the dice!")
    self.__promptLabel.pack(side='left')

    # Create and Pack Display for Dice
    self.__valueDiscription = tkinter.Label(self.__middleFrame, text =\
                                      "The current value is: ")
    self.__value = tkinter.StringVar()
    self.__value.set("%-1s" % self.__model)

    self.__valueLabel = tkinter.Label(self.__middleFrame,\
                                      textvariable=self.__value)
    self.__valueDiscription.pack(side='left')
    self.__valueLabel.pack(side='top')

    # Create and Pack Buttons
    self.__rollButton = tkinter.Button(self.__bottomFrame, text = 'Roll\
 Dice', command = self.roll)
    self.__quitButton = tkinter.Button(self.__bottomFrame, text = 'Quit',
                                       command = self.__mainWindow.destroy)

    self.__rollButton.pack(side='left')
    self.__quitButton.pack(side='left')

    # Pack Frames
    self.__topFrame.pack(side='left')
    self.__middleFrame.pack(side='left')
    self.__bottomFrame.pack(side='left')


    # Start Listener
    tkinter.mainloop()


  def roll(self):
    self.__model.roll()
    self.__value.set("%-1s" % self.__model)



def main():
  dice = Die()
  print(dice.getState())
  print(dice)
  dice.roll()
  print(dice)
  print(dice.isEven())

DieGUI()
  
