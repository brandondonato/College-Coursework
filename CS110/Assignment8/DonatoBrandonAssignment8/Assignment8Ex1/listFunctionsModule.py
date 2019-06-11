"""
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
Assignment8Ex1
"""
# Imports --------------------------------------------------------------------

import random

# Functions -------------------------------------------------------------------

# Creates list of random integers
# returns the list of random integers (list)
def listGenerator():
  newList = []
  for a in range(random.randint(1,25)):
    newList.append(random.randint(1,25))
  return newList                
  
# Check that the starting and limiting indicies are positive integers
# param startIndex (str) - The beginning index
# param limitIndex (str) - The limiting index
# invoke str.isdigit()
# returns True when valid, False otherwise (bool)
def integerValidation(startIndex,limitIndex):
  return startIndex.isdigit() and limitIndex.isdigit()

# Checks index inputs are valid inputs within the range of the list's
#   index
# param startIndex (str) - The beginning index
# param limitIndex (str) - The limiting index
# param aList (list) - The inital list
# invoke len()
# invoke int()
# invoke integerValidation()
# returns True when valid, False otherwise (bool)
def indexValidation(startIndex,limitIndex,aList):
  return (integerValidation(startIndex,limitIndex)) and\
         (int(limitIndex) > int(startIndex)) and\
         (int(limitIndex) <= (len(aList) - 1))

# Converts index inputs from (str) to (int)
# param indexStr (str) - The index string
# invoke int()
# returns indexInt (int) - The index integer
def indexConversion(indexStr):
  indexInt = int(indexStr)
  return indexInt

# Checks the mySlice() output against Python's slice operation
# param theSlice (list) - The slice computed from mySlice()
# param pythonsSlice (list) - The slice computed from Python's slice
#   operator
# returns True when valid, False otherwise (bool)
def mySliceValidation(theSlice, pythonsSlice):
  return theSlice == pythonsSlice

# Creates a sublist when given a list, starting index and limiting index
# param aList (list) - The given list
# param startIndex (int) - The starting index
# param limitIndex (int) - The limiting index
# returns the new sublist (list)
def mySlice(aList,startIndex,limitIndex):
  newList = []
  for i in range(startIndex, limitIndex):
    newList.append(aList[i])
    ##print(newList)
  return newList


