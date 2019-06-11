'''
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
preLab7
'''


OPERATIONS = 'ed'
KEY_PREFIX = '-'
ENCRYPT = 1
DECRYPT = -1

# Check that requested operation is valid
# param opStr (str) - operation requested
# return  True when valid, False otherwise (bool)
def validateOperation(opStr):
  newOperationsE = 'E'+ OPERATIONS[1:]
  newOperationsD = OPERATIONS[0] + 'D'
  return opStr == OPERATIONS[0] or opStr == newOperationsE[0]\
         or opStr == OPERATIONS[1] or opStr == newOperationsD[1]

# Check that rotation key is of the form <digits> or -<digits>
# param rotationKeyStr (str)
# invokes ________ ?
# returns:  True when valid, False otherwise (bool)
def validateRotationKey(rotationKeyStr):
  if rotationKeyStr[0] == KEY_PREFIX:
    rotationKeyStr = rotationKeyStr[1:]
  return rotationKeyStr.isdigit() and\
         int(rotationKeyStr) > 0

# Convert rotation key to value usable for requested operation
# param  opStr (str) - operation requested
# param  rotationKeyStr (str) - the original encryption key
# invoke _____?
# return encryption or decryption rotation key (int)
def convertRotationKey(opStr, rotationKeyStr):
  while not validateOperation(opStr):
    print("You need a valid input. Please try again.")
    opStr = input("Please either enter <e> for encrytion or <d>\
 for decyrption: ")
  while not validateRotationKey(rotationKeyStr):
    print("You need a valid input. Please try again.")
    rotationKeyStr = input("Please enter a positive or negative integer\
 for the rotation key.")
  if opStr == 'e' or opStr == 'E':
    rotationKeyInt = ENCRYPT * int(rotationKeyStr)
  else:
    rotationKeyInt = DECRYPT * int(rotationKeyStr)
  return rotationKeyInt

print(convertRotationKey('a','w2'))

 
