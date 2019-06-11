"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
Assignment12Ex1
"""

"""
To create a working GUI using the patron, book and library classes from
Assignment 11
"""

from tkinter import *
from tkinter.messagebox import *
from patronStudentVersion import *
from bookStudentVersion import *
from libraryStudentVersion import *

class LibraryGUI:

  def __init__(self):

    # Initial Frames and Window Created --------------------------------------
    self.__win = Tk()
    self.__homeFrame = Frame(self.__win)
    self.__searchFrame = Frame(self.__win)
    self.__membershipFrame = Frame(self.__win)
    self.__bookFrame = Frame(self.__win)

    self.__patronDict = {}
    self.__bookDict = {}
##    self.__libraryDict = {}
    self.__libraryModel = Library('Pythonville Hometown Library' )
    self.__bookModel = Book('title', 'author')
    self.__buttonStrVar = StringVar()
    self.__buttonStrVar.set("disabled")

    # Sub 1 Frames + Home Frame Header Created -------------------------------
    self.__homeHeader = Label(self.__homeFrame, text ='PYTHONVILLE HOMETOWN \
LIBRARY')
    self.__homeHeader.pack(side='top')
    self.__homeFrameSub1 = Frame(self.__homeFrame)
    self.__homeFrameSub1Header = Frame(self.__homeFrameSub1)
    self.__homeFrameSub1Title = Frame(self.__homeFrameSub1)
    self.__homeFrameSub1Patron = Frame(self.__homeFrameSub1)
    self.__homeFrameSub1Button = Frame(self.__homeFrameSub1)
    self.__homeFrameSub1Status = Frame(self.__homeFrameSub1)
    self.__homeFrameSub2 = Frame(self.__homeFrame)

    # Sub 1 Items Created ----------------------------------------------------
    self.__sub1HeaderLabel = Label(self.__homeFrameSub1Header, \
                                   text = 'Checkout Books')
    self.__sub1TitleLabel = Label(self.__homeFrameSub1Title, text = \
                                  'Title    ')
    self.__sub1TitleEntryBox = Entry(self.__homeFrameSub1Title, width = 40)
    self.__sub1TitleEntryBox.bind("<Return>", self.checkoutBookActivation)
    self.__sub1PatronLabel = Label(self.__homeFrameSub1Patron, text = 'Patron')
    self.__sub1PatronEntryBox = Entry(self.__homeFrameSub1Patron, width = 40)
    self.__sub1PatronEntryBox.bind("<Return>", self.checkoutBookActivation)
    self.__sub1CheckoutButton = Button(self.__homeFrameSub1Button, text =\
                  'Checkout Book', command = self.checkoutBook, state =\
                                       'disabled')
    self.__sub1StatusStaticLabel = Label(self.__homeFrameSub1Status,\
          text = 'Status')
    self.__sub1StrVar = StringVar()
    self.__sub1StrVar.set("No transactions")
    self.__sub1StatusDynamicLabel = Label(self.__homeFrameSub1Status, \
           textvariable = self.__sub1StrVar)

    # Sub 1 Packing ----------------------------------------------------------
    self.__sub1HeaderLabel.pack(side='top')
    self.__sub1TitleLabel.pack(side='left')
    self.__sub1TitleEntryBox.pack(side='left')
    self.__sub1PatronLabel.pack(side='left')
    self.__sub1PatronEntryBox.pack(side='left')
    self.__sub1CheckoutButton.pack(side='top')
    self.__sub1StatusStaticLabel.pack(side='left')
    self.__sub1StatusDynamicLabel.pack(side='left')
    self.__homeFrameSub1Header.pack()
    self.__homeFrameSub1Title.pack()
    self.__homeFrameSub1Patron.pack()
    self.__homeFrameSub1Button.pack()
    self.__homeFrameSub1Status.pack()
    self.__homeFrameSub1.pack(side='left')

    # Sub 2 Frames Created ---------------------------------------------------
    self.__homeFrameSub2Header = Frame(self.__homeFrameSub2)
    self.__homeFrameSub2Title = Frame(self.__homeFrameSub2)
    self.__homeFrameSub2Patron = Frame(self.__homeFrameSub2)
    self.__homeFrameSub2Button = Frame(self.__homeFrameSub2)
    self.__homeFrameSub2Status = Frame(self.__homeFrameSub2)

    # Sub 2 Items Created ----------------------------------------------------
    self.__sub2HeaderLabel = Label(self.__homeFrameSub2Header, \
                                   text = 'Return Books')
    self.__sub2TitleLabel = Label(self.__homeFrameSub2Title, text = 'Title')
    self.__sub2TitleEntryBox = Entry(self.__homeFrameSub2Title, width = 40)
    self.__sub2TitleEntryBox.bind('<Return>', self.returnBookActivation)
    self.__sub2PatronLabel = Label(self.__homeFrameSub2Patron, text = 'Patron')
    self.__sub2ReturnButton = Button(self.__homeFrameSub2Button, text =\
                  'Return Book', command = self.returnBook, state=\
                                     'disabled')
    self.__sub2StatusStaticLabel = Label(self.__homeFrameSub2Status,\
          text = 'Status')
    self.__sub2StrVar = StringVar()
    self.__sub2StrVar.set("No transactions")
    self.__sub2StatusDynamicLabel = Label(self.__homeFrameSub2Status, \
           textvariable = self.__sub2StrVar)

    # Sub 2 Frames and Home Frame Packed -------------------------------------
    self.__sub2HeaderLabel.pack(side='top')
    self.__sub2TitleLabel.pack(side='left')
    self.__sub2TitleEntryBox.pack(side='left')
    self.__sub2PatronLabel.pack(side='left')
    self.__sub2ReturnButton.pack(side='top')
    self.__sub2StatusStaticLabel.pack(side='left')
    self.__sub2StatusDynamicLabel.pack(side='left')
    self.__homeFrameSub2Header.pack()
    self.__homeFrameSub2Title.pack()
    self.__homeFrameSub2Patron.pack()
    self.__homeFrameSub2Button.pack()
    self.__homeFrameSub2Status.pack()
    self.__homeFrameSub2.pack(side='left')
    
    # Search Sub 3 Frames + Search Frame Header Created -----------------------
    self.__searchHeader = Label(self.__searchFrame, text ='SEARCH')
    self.__searchHeader.pack(side='top')
    self.__searchFrameSub3 = Frame(self.__searchFrame)
    self.__searchFrameSub3Header = Frame(self.__searchFrameSub3)
    self.__searchFrameSub3Title = Frame(self.__searchFrameSub3)
    self.__searchFrameSub3Status = Frame(self.__searchFrameSub3)
    self.__searchFrameSub3BookStatus = Frame(self.__searchFrameSub3)
    self.__searchFrameSub3Book = Frame(self.__searchFrameSub3)

    # Sub 3 Items Created ----------------------------------------------------
    self.__sub3HeaderLabel = Label(self.__searchFrameSub3Header, \
                                   text = 'Book')
    self.__sub3TitleLabel = Label(self.__searchFrameSub3Title, \
                                  text = 'Title')
    self.__sub3TitleEntryBox = Entry(self.__searchFrameSub3Title, width = 40)
    self.__sub3TitleEntryBox.bind('<Return>', self.searchBooks)
    self.__sub3StatusStaticLabel = Label(self.__searchFrameSub3Status, \
                                         text = 'Status')
    self.__sub3StrVarLib = StringVar()
    self.__sub3StrVarLib.set("No transactions")
    self.__sub3StrVarBookStatus = StringVar()
    self.__sub3StrVarBookStr = StringVar()
    self.__sub3StatusDynamicLabel = Label(self.__searchFrameSub3Status, \
                     textvariable = self.__sub3StrVarLib)
    self.__sub3BookStatusDynamicLabel = Label(\
      self.__searchFrameSub3BookStatus, textvariable = \
      self.__sub3StrVarBookStatus)
    self.__sub3BookDynamicLabel = Label(self.__searchFrameSub3Book,\
                      textvariable = self.__sub3StrVarBookStr)

    # Sub 3 Frames Packed ----------------------------------------------------
    self.__sub3HeaderLabel.pack(side='top')
    self.__sub3TitleLabel.pack(side='left')
    self.__sub3TitleEntryBox.pack(side='right')
    self.__sub3StatusStaticLabel.pack(side='left')
    self.__sub3StatusDynamicLabel.pack(side='right')
    self.__sub3BookStatusDynamicLabel.pack(side='right')
    self.__sub3BookDynamicLabel.pack(side='right')
    self.__searchFrameSub3Header.pack()
    self.__searchFrameSub3Title.pack()
    self.__searchFrameSub3Status.pack()
    self.__searchFrameSub3Book.pack()
    self.__searchFrameSub3BookStatus.pack()
    self.__searchFrameSub3.pack(side='left')

    # Sub 4 Frames Created ---------------------------------------------------
    self.__searchFrameSub4 = Frame(self.__searchFrame)
    self.__searchFrameSub4Header = Frame(self.__searchFrameSub4)
    self.__searchFrameSub4Name = Frame(self.__searchFrameSub4)
    self.__searchFrameSub4Status = Frame(self.__searchFrameSub4)
    self.__searchFrameSub4Patron = Frame(self.__searchFrameSub4)
    self.__searchFrameSub4PatronStatus = Frame(self.__searchFrameSub4)

    # Sub 4 Items Created ----------------------------------------------------
    self.__sub4HeaderLabel = Label(self.__searchFrameSub4Header, \
                                   text = 'Patron')
    self.__sub4NameLabel = Label(self.__searchFrameSub4Name, \
                                 text = '     Name')
    self.__sub4NameEntryBox = Entry(self.__searchFrameSub4Name, width = 40)
    self.__sub4NameEntryBox.bind('<Return>', self.searchPatrons)
    self.__sub4StatusStaticLabel = Label(self.__searchFrameSub4Status, \
                                         text ='Status')
    self.__sub4StrVarLib = StringVar()
    self.__sub4StrVarLib.set("No transactions")
    self.__sub4StrVarPatronStr = StringVar()
    self.__sub4StatusDynamicLabel = Label(self.__searchFrameSub4Status, \
                            textvariable = self.__sub4StrVarLib)
    self.__sub4PatronDynamicLabel = Label(\
      self.__searchFrameSub4Patron, \
      textvariable=self.__sub4StrVarPatronStr)

    # Sub 4 Frames Packed ----------------------------------------------------
    self.__sub4HeaderLabel.pack(side='top')
    self.__sub4NameLabel.pack(side='left')
    self.__sub4NameEntryBox.pack(side='right')
    self.__sub4StatusStaticLabel.pack(side='left')
    self.__sub4StatusDynamicLabel.pack(side='right')
    self.__sub4PatronDynamicLabel.pack(side='right')
    self.__searchFrameSub4Header.pack()
    self.__searchFrameSub4Name.pack()
    self.__searchFrameSub4Status.pack()
    self.__searchFrameSub4Patron.pack()
    self.__searchFrameSub4PatronStatus.pack()
    self.__searchFrameSub4.pack(side='right')

    # Sub 5 Frames + Membership Frame Header Created -------------------------
    self.__membershipHeader = Label(self.__membershipFrame, text='MEMBERSHIP')
    self.__membershipHeader.pack(side='top')
    self.__membershipFrameSub5 = Frame(self.__membershipFrame)
    self.__membershipFrameSub5Header = Frame(self.__membershipFrameSub5)
    self.__membershipFrameSub5Name = Frame(self.__membershipFrameSub5)
    self.__membershipFrameSub5Status = Frame(self.__membershipFrameSub5)

    # Sub 5 Items Created ----------------------------------------------------
    self.__sub5HeaderLabel = Label(self.__membershipFrameSub5Header, \
                                   text = 'Join')
    self.__sub5NameLabel = Label(self.__membershipFrameSub5Name, \
                                 text = 'Name')
    self.__sub5NameEntryBox = Entry(self.__membershipFrameSub5Name, width=40)
    self.__sub5NameEntryBox.bind('<Return>', self.addPatron)
    self.__sub5StatusStaticLabel = Label(self.__membershipFrameSub5Status, \
                                         text = 'Status')
    self.__sub5StrVar = StringVar()
    self.__sub5StrVar.set("No transactions")
    self.__sub5StatusDynamicLabel = Label(self.__membershipFrameSub5Status, \
                                          textvariable = self.__sub5StrVar)

    # Sub 5 Frames Packed ----------------------------------------------------
    self.__sub5HeaderLabel.pack(side='top')
    self.__sub5NameLabel.pack(side='left')
    self.__sub5NameEntryBox.pack(side='right')
    self.__sub5StatusStaticLabel.pack(side='left')
    self.__sub5StatusDynamicLabel.pack(side='right')
    self.__membershipFrameSub5Header.pack()
    self.__membershipFrameSub5Name.pack()
    self.__membershipFrameSub5Status.pack()
    self.__membershipFrameSub5.pack(side='left')

    # Sub 6 Frames Created ---------------------------------------------------
    self.__membershipFrameSub6 = Frame(self.__membershipFrame)
    self.__membershipFrameSub6Header = Frame(self.__membershipFrameSub6)
    self.__membershipFrameSub6Name = Frame(self.__membershipFrameSub6)
    self.__membershipFrameSub6Status = Frame(self.__membershipFrameSub6)

    # Sub 6 Items Created ----------------------------------------------------
    self.__sub6HeaderLabel = Label(self.__membershipFrameSub6Header, \
                                   text = 'Leave')
    self.__sub6NameLabel = Label(self.__membershipFrameSub6Name, \
                                 text = 'Name')
    self.__sub6NameEntryBox = Entry(self.__membershipFrameSub6Name, width=40)
    self.__sub6NameEntryBox.bind('<Return>', self.removePatron)
    self.__sub6StatusStaticLabel = Label(self.__membershipFrameSub6Status, \
                                         text = 'Status')
    self.__sub6StrVar = StringVar()
    self.__sub6StrVar.set("No transactions")    
    self.__sub6StatusDynamicLabel = Label(self.__membershipFrameSub6Status, \
                                          textvariable = self.__sub6StrVar)

    # Sub 6 Frames Packed ----------------------------------------------------
    self.__sub6HeaderLabel.pack(side='top')
    self.__sub6NameLabel.pack(side='left')
    self.__sub6NameEntryBox.pack(side='right')
    self.__sub6StatusStaticLabel.pack(side='left')
    self.__sub6StatusDynamicLabel.pack(side='right')
    self.__membershipFrameSub6Header.pack()
    self.__membershipFrameSub6Name.pack()
    self.__membershipFrameSub6Status.pack()
    self.__membershipFrameSub6.pack(side='right')

    # Sub 7 Frames + Book Frame Header Created -------------------------------
    self.__bookHeader = Label(self.__bookFrame, text='BOOK COLLECTION')
    self.__bookHeader.pack(side='top')
    self.__bookFrameSub7 = Frame(self.__bookFrame)
    self.__bookFrameSub7Header = Frame(self.__bookFrameSub7)
    self.__bookFrameSub7Title = Frame(self.__bookFrameSub7)
    self.__bookFrameSub7Author = Frame(self.__bookFrameSub7)
    self.__bookFrameSub7Button = Frame(self.__bookFrameSub7)
    self.__bookFrameSub7Status = Frame(self.__bookFrameSub7)

    # Sub 7 Items Created ----------------------------------------------------
    self.__sub7HeaderLabel = Label(self.__bookFrameSub7Header, \
                                   text = 'Add Books')
    self.__sub7TitleLabel = Label(self.__bookFrameSub7Title, text = \
                                  'Title    ')
    self.__sub7TitleEntryBox = Entry(self.__bookFrameSub7Title, width = 40)
    self.__sub7TitleEntryBox.bind('<Return>', self.addBookActivation)
    self.__sub7AuthorLabel = Label(self.__bookFrameSub7Author, text = 'Author')
    self.__sub7AuthorEntryBox = Entry(self.__bookFrameSub7Author, width = 40)
    self.__sub7AuthorEntryBox.bind('<Return>', self.addBookActivation)
    self.__sub7AddButton = Button(self.__bookFrameSub7Button, text =\
                  'Add Book', command = self.addBook, state= 'disabled')
    self.__sub7StatusStaticLabel = Label(self.__bookFrameSub7Status,\
          text = 'Status')
    self.__sub7StrVar = StringVar()
    self.__sub7StrVar.set("No transactions")
    self.__sub7StatusDynamicLabel = Label(self.__bookFrameSub7Status, \
           textvariable = self.__sub7StrVar)

    # Sub 7 Frames Packed ----------------------------------------------------
    self.__sub7HeaderLabel.pack(side='top')
    self.__sub7TitleLabel.pack(side='left')
    self.__sub7TitleEntryBox.pack(side='right')
    self.__sub7AuthorLabel.pack(side='left')
    self.__sub7AuthorEntryBox.pack(side='right')
    self.__sub7AddButton.pack(side='top')
    self.__sub7StatusStaticLabel.pack(side='left')
    self.__sub7StatusDynamicLabel.pack(side='right')
    self.__bookFrameSub7Header.pack()
    self.__bookFrameSub7Title.pack()
    self.__bookFrameSub7Author.pack()
    self.__bookFrameSub7Button.pack()
    self.__bookFrameSub7Status.pack()
    self.__bookFrameSub7.pack(side='left')

    # Sub 8 Frames Created ---------------------------------------------------
    self.__bookFrameSub8 = Frame(self.__bookFrame)
    self.__bookFrameSub8Header = Frame(self.__bookFrameSub8)
    self.__bookFrameSub8Title = Frame(self.__bookFrameSub8)
    self.__bookFrameSub8Author = Frame(self.__bookFrameSub8)
    self.__bookFrameSub8Button = Frame(self.__bookFrameSub8)
    self.__bookFrameSub8Status = Frame(self.__bookFrameSub8)

    # Sub 8 Items Created ----------------------------------------------------
    self.__sub8HeaderLabel = Label(self.__bookFrameSub8Header, \
                                   text = 'Remove Books')
    self.__sub8TitleLabel = Label(self.__bookFrameSub8Title, text = \
                                  'Title    ')
    self.__sub8TitleEntryBox = Entry(self.__bookFrameSub8Title, width = 40)
    self.__sub8TitleEntryBox.bind('<Return>', self.removeBookActivation)
    self.__sub8AuthorLabel = Label(self.__bookFrameSub8Author, text = 'Author')
    self.__sub8RemoveButton = Button(self.__bookFrameSub8Button, text =\
                  'Remove Book', command = self.removeBook, state='disabled')
    self.__sub8StatusStaticLabel = Label(self.__bookFrameSub8Status,\
          text = 'Status')
    self.__sub8StrVar = StringVar()
    self.__sub8StrVar.set("No transactions")
    self.__sub8StatusDynamicLabel = Label(self.__bookFrameSub8Status, \
           textvariable = self.__sub8StrVar)

    # Sub 8 Frames Packed ----------------------------------------------------
    self.__sub8HeaderLabel.pack(side='top')
    self.__sub8TitleLabel.pack(side='left')
    self.__sub8TitleEntryBox.pack(side='right')
    self.__sub8AuthorLabel.pack(side='left')
    self.__sub8RemoveButton.pack(side='top')
    self.__sub8StatusStaticLabel.pack(side='left')
    self.__sub8StatusDynamicLabel.pack(side='right')
    self.__bookFrameSub8Header.pack()
    self.__bookFrameSub8Title.pack()
    self.__bookFrameSub8Author.pack()
    self.__bookFrameSub8Button.pack()
    self.__bookFrameSub8Status.pack()
    self.__bookFrameSub8.pack(side='right')
    


    self.__homeFrame.pack(side='top')
    self.__searchFrame.pack()
    self.__membershipFrame.pack()
    self.__bookFrame.pack()
    mainloop()

  # Functions ----------------------------------------------------------------
  def searchBooks(self,event):
    bookTitle = self.__sub3TitleEntryBox.get()
    if bookTitle in self.__bookDict:
      bookInfo = self.__libraryModel.getBook(bookTitle)
      self.__sub3StrVarLib.set(self.__libraryModel.getTransactionStatus())
      self.__sub3StrVarBookStatus.set(self.__bookDict[bookTitle].\
                                getTransactionStatus())
      self.__sub3StrVarBookStr.set(bookInfo)
    else:
      messagebox.showwarning("This book does not exist")
    self.__sub3TitleEntryBox.delete(0,END)
    self.__sub3TitleEntryBox.delete(0,END)
    
  def searchPatrons(self,event):
    patronName = self.__sub4NameEntryBox.get()
    if patronName in self.__patronDict:
      patronInfo = self.__libraryModel.getPatron(patronName)
      self.__sub4StrVarLib.set(self.__libraryModel.getTransactionStatus())
      self.__sub4StrVarPatronStr.set(patronInfo)
    else:
      messagebox.showwarning("This patron does not exist")
    self.__sub4NameEntryBox.delete(0,END)

  def checkoutBookActivation(self,event):
    bookTitle = self.__sub1TitleEntryBox.get()
    patronName = self.__sub1PatronEntryBox.get()
    if (patronName in self.__patronDict) and (bookTitle in self.__bookDict)\
       and (not self.__bookDict[bookTitle].isCheckedOut()):
          self.__sub1CheckoutButton.config(state='normal')
    else:
      messagebox.showwarning("Either the title or patron you've entered\
 doesn't exist")

  def checkoutBook(self):
    bookTitle = self.__sub1TitleEntryBox.get()
    patronName = self.__sub1PatronEntryBox.get()
    self.__bookDict[bookTitle].borrowMe(self.__patronDict[patronName])
    self.__sub1StrVar.set(self.__bookDict[bookTitle].getTransactionStatus())
    self.__sub1TitleEntryBox.delete(0,END)
    self.__sub1PatronEntryBox.delete(0,END)
    self.__sub1CheckoutButton.config(state='disabled')

  def returnBookActivation(self,event):
    bookTitle = self.__sub2TitleEntryBox.get()
    if bookTitle in self.__bookDict:
      self.__sub2ReturnButton.config(state='normal')
    else:
      messagebox.showwarning("The book title you've given doesn't exist")
    
  def returnBook(self):
    bookTitle = self.__sub2TitleEntryBox.get()
    bookObject = self.__bookDict[bookTitle]
    bookObject.returnMe()
    self.__sub2StrVar.set(bookObject.getTransactionStatus())
    self.__sub2TitleEntryBox.delete(0,END)
    self.__sub2ReturnButton.config(state='disabled')

  def removePatron(self,event):
    patronName = self.__sub6NameEntryBox.get()
    if patronName in self.__patronDict:
      self.__libraryModel.removePatron(patronName)
      del self.__patronDict[patronName]
      self.__sub6StrVar.set(self.__libraryModel.getTransactionStatus())
    else:
      messagebox.showwarning("This patron isn't a member of the library")
    self.__sub6NameEntryBox.delete(0,END)

  def addPatron(self,event):
    name = self.__sub5NameEntryBox.get()
    if name not in self.__patronDict:
      newPatron = Patron(name)
      self.__patronDict[name] = newPatron
      self.__libraryModel.addPatron(newPatron)
      self.__sub5StrVar.set(self.__libraryModel.getTransactionStatus())
    else:
      messagebox.showwarning("This patron is already a member of the library")
    self.__sub5NameEntryBox.delete(0,END)

  def addBookActivation(self,event):
    title = self.__sub7TitleEntryBox.get()
    author = self.__sub7AuthorEntryBox.get()
    if (title not in self.__bookDict):
      self.__sub7AddButton.config(state='normal')
    else:
      messagebox.showwarning("This book is already in the library")

  def addBook(self):
    title = self.__sub7TitleEntryBox.get()
    author = self.__sub7AuthorEntryBox.get()
    newBook = Book(title, author)
    self.__libraryModel.addBook(newBook)
    self.__bookDict[title] = newBook
    self.__libraryModel.addBook(newBook)
    self.__sub7StrVar.set(self.__libraryModel.getTransactionStatus())
    self.__sub7TitleEntryBox.delete(0,END)
    self.__sub7AuthorEntryBox.delete(0,END)
    self.__sub7AddButton.config(state='disabled')

  def removeBookActivation(self,event):
    bookTitle = self.__sub8TitleEntryBox.get()
    if bookTitle in self.__bookDict:
      self.__sub8RemoveButton.config(state='normal')
    else:
      messagebox.showwarning("This book isn't in the library")

  def removeBook(self):
    bookTitle = self.__sub8TitleEntryBox.get()
    self.__libraryModel.removeBook(bookTitle)
    del self.__bookDict[bookTitle]
    self.__sub8StrVar.set(self.__libraryModel.getTransactionStatus())
    self.__sub8TitleEntryBox.delete(0,END)
    self.__sub8RemoveButton.config(state='disabled')
    

LibraryGUI()
    
