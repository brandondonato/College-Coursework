'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment6Ex3
'''
'''
Analysis:

Create a program that counts the amount of money a person would earn over
a period of time if his or her salary is one penny the first day and
doubles each day.

Output to monitor:
  dayCount (int) - The number day it is in the computation
  daySalary (int) - The amount of money earned that day
  finalDayCount (int) - The last day the person earned money
  salaryTotal (int) - The total amount of money made

Input from keyboard:
  days (int) - The amount of days the program runs for

Tasks allocated to functions:
  daysValidation()
  salaryComputation()

'''
# Constants
DOUBLES = 2
ONE_CENT = .01



# Validates the day value
# param days (str)
# return True when the str is composed of only numbers and is greater
#   than 1
def daysValidation(days):
  return days.isdigit() and int(days) > 0

# Computes the salary
# param days (int)
# returns each day the computation occurs for, the daily salary on a
#  given day and the total number of days the computation goes on for
#  and the final total pay
def salaryComputation(days):
  dayCount = 1
  daySalary = ONE_CENT
  salaryTotal = 0
  while dayCount <= days:
    print(dayCount,'\t', '\t','\t', '\t', '\t','\t', daySalary)
    salaryTotal = salaryTotal + daySalary
    daySalary = (daySalary * DOUBLES)
    dayCount = dayCount + 1
  finalDayCount = dayCount - 1
  print("In", finalDayCount,"days, a penny grows to $",salaryTotal)

# The program takes in the number of days a person would calculate for
#  and then determines the amount of money the person would make if his
#  or her salary was one cent the first day and then doubled each day
def main():
  # Priming input
  days = input("Enter the number of days for which the salary is to be \
computed. Or press <Enter> to quit: ")

  while days:

    #Validates the users inital day input
    while not daysValidation(days):
      print("Invalid number of days. Please enter a valid number of days \
to calculate.")
      days = input("Enter the number of days for which the salary is to be \
computed. Or press <Enter> to quit: ")

    # Converts the day value into int
    days = int(days)

    # Starts setting up the table
    print("Day", '\t', '\t','\t', '\t', '\t','\t', "Pay")
    print("-----------------------------------------------------")

    # Determines the total amount of money made and the money made each
    #   day
    salaryComputation(days)

    # Continuation of the loop
    days = input("Enter the number of days for which the salary is to be \
computed. Or press <Enter> to quit: ")


main()

      
