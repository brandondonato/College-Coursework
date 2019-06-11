#function that performs + operation
def addThem (num1, num2):
  return num1 + num2

def main():
  firstStr = input("Please enter a whole number:  ") #This is prompting the user to enter a whole number
  secondStr = input("Please enter another whole number:  ") #This is prompting the user to enter another whole number
  first = int(firstStr)#This converts the user's first input from a string to a integer
  second = int(secondStr)#This convers the user's second imput from a string to a integer
  theSum = addThem(first, second) #This adds the two integers together and stores the sum as a single variable
  print("The sum of your numbers is",theSum) #This returns the variable that stored the sum of the two integers
  print ("The sum of your numbers is", addThem(first, second))#This returns the sum of the two integers

main()



