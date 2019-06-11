from patronStudentVersion import *
from bookStudentVersion import *
from libraryStudentVersion import *
from libraryModule import *

# This is a tester for the Library class
# Note that it uses the Patron, Book, and Library classes as well as
#   the classes from the libraryModule file
def main():

  patrons = ['Able', 'Baker', 'Charlie', 'Delta', 'Echo']
  bookTitles = ['Absolut Python', 'Dummies for Python', \
                'Evolving Python', 'Python for Profit', \
                'Python for Reptiles']
  authors = ['Anya', 'Dullest', 'Everest', 'Prospero', 'Nagini']
    
  print(DIVIDER + 'Library: \n')

  
  #  Create named library and display its initial state
  #  Exercises constructor and 'to string'
  library = Library('The Python Library')
  
  print(DIVIDER + 'Initial state:  \n' + str(library))
  
#-----------------------------------------------------------------------    
  #  Add patrons to library and exercise 'to String', getPatron() and
  #  getTransactionStatus() accessors, and addPatron() mutator, as well
  #  as the private methods they call

  print(DIVIDER + 'Adding patrons:')
  for name in patrons:
    library.addPatron(Patron(name))
    print(library.getTransactionStatus())
    print(str(library.getPatron(name)))
  print('\nAfter adding patrons:  \n' + str(library))

#-----------------------------------------------------------------------

  # Add books to library and exercise 'to String', getBook() and
  #  getTransactionStatus() accessors, and addBook() mutator, as well
  #  as the private methods they call
  
  print(DIVIDER + 'Adding books:')
  for titleIndex in range(len(bookTitles)):                     
    library.addBook(Book(bookTitles[titleIndex], authors[titleIndex]))          
    print(library.getTransactionStatus())
    print(str(library.getBook(bookTitles[titleIndex])))
  print('\nAfter adding books:  \n' + str(library))
  
#-----------------------------------------------------------------------    

  #  Access and take out books from library to exercise 'to String',
  #  getBook(), getPatron(), and getTransactionStatus() accessors, and
  #  addBook() mutator from Book, as well as the private methods called
  print(DIVIDER + 'Taking out books:')
  
  for i in range(Patron.MAX_BOOKS_OUT):
    print()
    book = library.getBook(bookTitles[i])
    print(library.getTransactionStatus())
    book.borrowMe(library.getPatron(patrons[0]))
    print(library.getTransactionStatus())
    print(book.getTransactionStatus())
    print()                                            
    print(library)
#-----------------------------------------------------------------------

  #  Access/take out books from library again, to exercise 'to String',
  #  getBook(), getPatron(), and getTransactionStatus() accessors, and
  #  addBook() mutator from Book, as well as the private methods called
  
  print(DIVIDER + 'Taking out books again:')
  
  for i in range(len(bookTitles)):
    print()
    book = library.getBook(bookTitles[i])
    print(library.getTransactionStatus())
    book.borrowMe(library.getPatron(patrons[i]))
    print(library.getTransactionStatus())
    print(book.getTransactionStatus())
    print()                                            
    print(library)
#-----------------------------------------------------------------------

  # Try taking out book that is not in library
  
  print(DIVIDER + 'Accessing non-existent book:')
  
  book = Book('Unknowable Python', 'The Unknown Coder')
  print(book)
  book = library.getBook('Unknowable Python')
  print(library.getTransactionStatus())
#-----------------------------------------------------------------------

  # Try accessing patron who is not member of library
 
  print(DIVIDER + 'Accessing non-existent patron:')

  patron = Patron('The Unknown Library Member')
  print(patron)
  patron = library.getPatron('The Unknown Library Member')
  print(library.getTransactionStatus())
  
#-----------------------------------------------------------------------

  # Show current state of library
  
  print(DIVIDER + 'Current state of library:')
  
  print(library)
#-----------------------------------------------------------------------

  # Remove patron
  
  print(DIVIDER + 'Remove patron:')    

  print(library.getPatron(patrons[-1]))
  library.removePatron(patrons[-1])
  print(library.getTransactionStatus())
  library.getBook(bookTitles[-1]).getTransactionStatus()
  print(library.getPatron(patrons[-1]))
  print(library.getTransactionStatus())          
  print(library)
#-----------------------------------------------------------------------

  # Remove book
  
  print(DIVIDER + 'Remove book:')

  book = library.getBook(bookTitles[-2])
  print(book)
  library.removeBook(bookTitles[-2])
  print(library.getTransactionStatus())
  print(library.getBook(bookTitles[-2]))
  print(library.getTransactionStatus())
  book.getTransactionStatus()
  print(library)
#-----------------------------------------------------------------------

  # Return books that are in library and have been taken out
  
  print(DIVIDER + 'Return books:')

  for title in bookTitles:
    print()
    book = library.getBook(title)
    print(library.getTransactionStatus())
    if book:
      book.returnMe()
      print(library.getTransactionStatus())
      print(book.getTransactionStatus())
      print()                                            
  print(library)

#-----------------------------------------------------------------------

  # Save library to file
  
  print(DIVIDER + 'Save library:')    

  libraryFileName = input('Input the file name that will be ' +\
                           'used to store library records:  ')
  libraryRecords = LibraryRecords(libraryFileName)
  libraryRecords.save(library)
#-----------------------------------------------------------------------    

  # Load library from file

  print(DIVIDER + 'Load library:')    

  libraryFileName = input('Input the file name that was ' +\
                           'used to store library records:  ')
  libraryRecords = LibraryRecords(libraryFileName)
  library = libraryRecords.load()
  print('\nLibrary loaded:  \n' + str(library))
#-----------------------------------------------------------------------

  # Load data and create new library
  # Add book to new library

  print(DIVIDER + 'Can we maintain more than one?')

  print('Start with original: ')
  library2 = libraryRecords.load()
  print('\nLibrary2 loaded from library:  \n' + str(library2))
  anotherBook = Book('Cloud Atlas', 'Mitchell')
  library2.addBook(anotherBook)
  print('\nLibrary2 after book added:  \n' + str(library2))

#-----------------------------------------------------------------------

  # Save new library
  
  print(DIVIDER + 'Save library2:')    

  library2FileName = input('Input the file name that will be ' +\
                           'used to store library records:  ')
  library2Records = LibraryRecords(library2FileName)
  library2Records.save(library2)
#-----------------------------------------------------------------------    

  #  Load new library (show display all records including new book)

  print(DIVIDER + 'Load library2:')    

  library2FileName = input('Input the file name that was ' +\
                           'used to store library records:  ')
  library2Records = LibraryRecords(library2FileName)
  library2 = library2Records.load()
  print('\nLibrary 2loaded:  \n' + str(library2))

#-----------------------------------------------------------------------    

  #  Load old library and compare records (should display old records)

  print(DIVIDER + 'Compare to original:')    

  libraryFileName = input('Input the file name that was ' +\
                           'used to store original library records:  ')
  libraryRecords = LibraryRecords(libraryFileName)
  library = libraryRecords.load()
  print('\nOriginal Library loaded:  \n' + str(library))

       
main()
