"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
Assignment8Ex1
"""

"""
Analysis:

Write a program that returns a slice when given a list, a starting index
and a limit index. Compare the results with Python's slice operation.

Output to monitor:
  theSlice (list) - Slice created by the program
  pythonsSlice (list) - Slice created using Python operator

Input from keyboard:
  startIndex (int) - Starting slice index
  limitIndex (int) - Limiting slice index

Tasks allocated to functions:
  All functions are located in listFunctionsModule
  integerValidation() - Simple predicate function
  indexValidation() - Simple predicate function
  indexConversion() - Converts from (str) to (int)
  mySliceValidation() - Simple predicate function
  mySlice()
  
"""
# Imports --------------------------------------------------------------------

import listFunctionsModule

# Main ------------------------------------------------------------------------

# Generates the initial list that will be sliced, the starting slice index, and
#   the limiting slice index
# Generates a slice using mySlice() and compares it to Python's slice
#   operation
def main():
  # Generates and prints the initial list, the list's length and the program's
  #   purpose
  aList = listFunctionsModule.listGenerator()
  print("The list is: ", aList, "and its length is", len(aList),"\n")
  print("This program will create a slice of a list without using the\
 slice operator \n")

  # Priming read and repeat
  startIndex = input("Enter the starting index for the slice\nOr press\
 <Enter> to quit: ")
  while startIndex:

    # Gets the limiting index, validates inputted data and converts as
    #  necessary
    limitIndex = input("Enter the limiting index for the slice: ")
    while not listFunctionsModule.\
          indexValidation(startIndex,limitIndex,aList):
      print("One or both of your indices are invalid")
      startIndex = input("Enter the starting index for the slice: ")
      limitIndex = input("Enter the limiting index for the slice: ")
    startIndex = listFunctionsModule.indexConversion(startIndex)
    limitIndex = listFunctionsModule.indexConversion(limitIndex)

    # Prints out the slice generated by mySlice() and the slice generated
    #  by Python
    theSlice = listFunctionsModule.\
               mySlice(aList,startIndex,limitIndex)
    print("My slice is: %-s" % theSlice)
    pythonsSlice = aList[startIndex:limitIndex]
    print("Python's slice is: %-s " % pythonsSlice)

    # Compares the slices generated to see whether they are equal or not
    #  and prints whether mySlice() makes successful slices or not
    if listFunctionsModule.\
       mySliceValidation(theSlice,pythonsSlice):
      print("Success! The two slices are equal.\n")
    else:
      print("The slices weren't equal. Please report this problem.\n")

    # Continuation read
    startIndex = input("Enter the starting index for the slice\nOr press\
 <Enter> to quit: ")
    
main()


    
      
    
  

