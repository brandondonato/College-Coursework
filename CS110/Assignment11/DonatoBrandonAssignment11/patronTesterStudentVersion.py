from patronStudentVersion import *
from libraryModule import DIVIDER

# This is a tester for the Patron class
# Note that it uses the Patron class as well as the global constant 
#   from the libraryModule file
def main():
#-----------------------------------------------------------------------------
  # Create patron and exercise 'to string', accessors and predicates

  print(DIVIDER + 'Patron: \n')
  
  patron = Patron('first')
  print(patron)
  print('\nName: ' + patron.getName() + 'Status: ' + patron.getStatus() + \
        '\nBooks Out: ' + str(patron.getNumBooksOut()))
  print('\nCan Take out Books? ' + str(patron.canCheckOutBooks()) + \
        '\nHas Books Checked Out? ' + str(patron.hasCheckedOutBooks()))

  print()
  
  # Take out books until max amount have been taken out
  # Exercise 'to String', accessors, predicates, and increment() mutator
  # updateStatus() will be exercised automatically
  
  # Note:  Book controls whether or not this should be done
  for i in range(Patron.MAX_BOOKS_OUT):
    print('\nHave patron take book out:  ' + str(i + 1))
    patron.increment()
    print(patron)
    print('\nName: ' + patron.getName() + '\nStatus: ' + patron.getStatus() + \
          '\nBooks Out: ' + str(patron.getNumBooksOut()))
    print('\nCan Take out Books? ' + str(patron.canCheckOutBooks()) + \
          '\nHas Books Checked Out? ' + str(patron.hasCheckedOutBooks()))

  print()
  
  # Return books until all have been returned
  # Exercise 'to String', accessors, and decrement() mutator
  # updateStatus() will be exercised automatically
  
  for i in range(Patron.MAX_BOOKS_OUT):
    print('\nHave patron return books:  ' + str(i + 1))
    patron.decrement()
    print(patron)
    print('\nName: ' + patron.getName() + '\nStatus: ' + patron.getStatus() + \
        '\nBooks Out: ' + str(patron.getNumBooksOut()))
    print('\nCan Take out Books? ' + str(patron.canCheckOutBooks()) + \
          '\nHas Books Checked Out? ' + str(patron.hasCheckedOutBooks()))

main()  
