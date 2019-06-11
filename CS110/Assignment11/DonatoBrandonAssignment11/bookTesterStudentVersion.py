from bookStudentVersion import *
from patronStudentVersion import *
from libraryModule import DIVIDER

# This is a tester for the Book class
# Note that it uses the Patron and Book classes as well as
#   a global constant from the libraryModule file
def main():

  #  Create a couple of patrons:
  patron = Patron('first')
  patron2 = Patron('second')
  
#-----------------------------------------------------------------------

  # Create a book and exercise 'to string', accessors, and predicates
  print(DIVIDER + 'Book: \n')
  
  book = Book('Guide to CS110', 'Williams')
  print(book)
  print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
        '\nPatron: \n' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
        '\n' + book.getWaitListStr() + '\n' +
        'Transaction Status: ' + book.getTransactionStatus())
  print('\nChecked Out? ' + str(book.isCheckedOut()) + \
        '\nReserved? ' + str(book.isReserved()) + \
        '\nhasBook? ' + str(book.hasBook(patron)) + \
        '\nisInWaitList? '+ str(book.isInWaitList(patron2)))

  # Have patrons take max number of books out,
  # and then have patrons try to borrow this book (exercises borrowMe()
  #  as well as the private methods that it calls)
  # Exercise 'to string', accessors, and predicates after each
  print('\nMax out patrons')
  twoPatrons = [patron, patron2]
  for p in twoPatrons:  # max out
    for i in range(Patron.MAX_BOOKS_OUT):
      p.increment()

  print('\nTry to borrow')
  for patron in twoPatrons: # try to take out book
    book.borrowMe(patron)
    print()
    print(book)
    print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
          '\nPatron: ' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
          '\n' + book.getWaitListStr() +
          'Transaction Status: ' + book.getTransactionStatus())
    print('\nChecked Out? ' + str(book.isCheckedOut()) + \
          '\nReserved? ' + str(book.isReserved()) + \
          '\nhasBook? ' + str(book.hasBook(patron)) + \
          '\nisInWaitList? '+ str(book.isInWaitList(patron2)))

  # Have patrons retrun all books out,
  # and then have patrons try to borrow this book (exercises borrowMe()
  #  as well as the private methods that it calls)
  # Exercise 'to string', accessors, and predicates after each   
  print('\nGo back down')
  # go back down
  for p in twoPatrons:
    for i in range(Patron.MAX_BOOKS_OUT):
      p.decrement()

  print('\nTry to borrow')
  for patron in twoPatrons: # try again to take out book
    book.borrowMe(patron)
    print()
    print(book)
    print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
          '\nPatron: ' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
          '\n' + book.getWaitListStr() +
          'Transaction Status: ' + book.getTransactionStatus())
    print('\nChecked Out? ' + str(book.isCheckedOut()) + \
          '\nReserved? ' + str(book.isReserved()) + \
          '\nhasBook? ' + str(book.hasBook(patron)) + \
          '\nisInWaitList? '+ str(book.isInWaitList(patron2)))

  # Have patrons try to borrow book again (exercises borrowMe()
  #  as well as the private methods that it calls)
  # Exercise 'to string', accessors, and predicates after each   
  print('\nTry to borrow again')
  for patron in twoPatrons: # try again to take out book
    book.borrowMe(patron)
    print()
    print(book)
    print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
          '\nPatron: ' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
          '\n' + book.getWaitListStr() +
          'Transaction Status: ' + book.getTransactionStatus())
    print('\nChecked Out? ' + str(book.isCheckedOut()) + \
          '\nReserved? ' + str(book.isReserved()) + \
          '\nhasBook? ' + str(book.hasBook(patron)) + \
          '\nisInWaitList? '+ str(book.isInWaitList(patron2)))

  # Have patrons try to return book (exercises returnMe()
  #  as well as the private methods that it calls)
  # Exercise 'to string', accessors, and predicates after each  
  print('\nReturn book:')
  for patron in twoPatrons: # return book
    book.returnMe()
    print(book)
    print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
          '\nPatron: ' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
          '\n' + book.getWaitListStr() +
          'Transaction Status: ' + book.getTransactionStatus())
    print('\nChecked Out? ' + str(book.isCheckedOut()) + \
          '\nReserved? ' + str(book.isReserved()) + \
          '\nhasBook? ' + str(book.hasBook(patron)) + \
          '\nisInWaitList? '+ str(book.isInWaitList(patron2)))    


  # Have patrons try to return book again(exercises returnMe()
  #  as well as the private methods that it calls)
  # Exercise 'to string', accessors, and predicates after each  
  print('\nTry to return book again')
  for patron in twoPatrons: # try again to return book
    book.returnMe()
    print(book)
    print('\nTitle: ' + book.getTitle() + '\nAuthor: ' + book.getAuthor() + \
          '\nPatron: ' + (str(book.getPatron()) if book.isCheckedOut() else 'None') +
          '\n' + book.getWaitListStr() +
          'Transaction Status: ' + book.getTransactionStatus())
    print('\nChecked Out? ' + str(book.isCheckedOut()) + \
          '\nReserved? ' + str(book.isReserved()) + \
          '\nhasBook? ' + str(book.hasBook(patron)) + \
          '\nisInWaitList? '+ str(book.isInWaitList(patron2)))


  # Create list of patrons and have each one first try to take out this book,
  # and then have each one return it (exercises borrowMe(),and returnMe()
  #  as well as the private methods that they call)
  # Exercise 'to string', accessors, and predicates after each
  # Shows whether or not waiting list is being properly maintained
  print('\nShow that wait list is manageged properly')
  morePatrons = []
  morePatronNames = ['first','second','third', 'fourth', 'fifth', 'sixth']
  print('\nTry to lend out book:')
  for name in morePatronNames:
    morePatrons.append(Patron(name))
  for patron in morePatrons:
    book.borrowMe(patron)
    print(book.getTransactionStatus())
    print(book)
  print('\nReturn book:')
  for patron in morePatrons:
    book.returnMe()
    print(book.getTransactionStatus())
    print(book)

main()
