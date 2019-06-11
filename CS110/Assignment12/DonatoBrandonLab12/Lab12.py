from tkinter import *
from counterWP import *
from tkinter.messagebox import *

class CounterWPGUI:
  
  def __init__(self):

    self.__win=Tk()

    self.__topFrame = Frame(self.__win)
    self.__midFrame = Frame(self.__win)

    self.__bottomFrame = Frame(self.__win)

    self.__model = CounterWP()


    self.__upButton = Button(self.__topFrame, text =\
                             'C O U N T  U P!', command=self.countUp)
    self.__downButton = Button(self.__topFrame, text =\
                               'C O U N T  D O W N!', command = self.countDown)

    self.__upButton.pack(side='left')
    self.__downButton.pack(side='left')

    self.__resetButton = Button(self.__midFrame, text =\
                                'RESET counter!', command=self.reset)

    self.__promptLabel = Label(self.__midFrame, text = \
                               'Set Counter')

    self.__entryBox = Entry(self.__midFrame, width=7)

    self.__entryBox.bind('<Return>', self.set)

    self.__resetButton.pack(side='left')
    self.__promptLabel.pack(side='left')
    self.__entryBox.pack(side='left')


    self.__staticLabel = Label(self.__bottomFrame, text = 'Count =')

    self.__iVal = IntVar()
    self.__iVal.set(self.__model.getValue())

    self.__value = Label(self.__bottomFrame, textvariable = self.__iVal)

    self.__staticLabel.pack(side='left')
    self.__value.pack(side='left')

    self.__topFrame.pack()
    self.__midFrame.pack()
    self.__bottomFrame.pack()

    mainloop()

  def countUp(self):
    self.__model.increment()
    self.__iVal.set( self.__model.getValue())

  def countDown(self):
    if self.__model.isPositive():
      self.__model.decrement()
      self.__iVal.set(self.__model.getValue())
    else:
      self.__warn()

  def reset(self):
    self.__model.reset()
    self.__iVal.set(self.__model.getValue())

  def set(self, event):
    self.__updateDisplay()
    self.__entryBox.delete(0, END)

  def __isNotNegative(self):
    return self.get() >= 0

  def __canDecrement(self):
    return self.get() > 0

  def __updateDisplay(self):
    self.__model.set(int(self.__entryBox.get()))
    if self.__model.isPositive():
      self.__iVal.set(self.__model.getValue())
    else:
      self.__model.reset()
      self.__warn()

  def __warn(self):
    messagebox.showwarning("An error has occurred!")

CounterWPGUI()
    
    
    
  

    

    
 
  
