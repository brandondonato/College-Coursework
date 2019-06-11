'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment6Ex1
'''

'''
Analysis:

Generate income tax and net income given the gross income and marital
status of the user.

Output to monitor:
  maritalStatus (str) - The marital status of the user.
  income (int) - The taxable income of the user (in dollars).
  taxes (float) - The amount of tax the person owes (in dollars).
  total (float) - The net income of the user (in dollars).

Input from keyboard:
  maritalStatus (str) - The marital status of the user.
  income (int) - The taxable income of the user (in dollars).

Tasks allocated to Functions:
  computeTax() 
  netIncome() 
  incomeValidation()
  maritalStatusValidation()

'''

#Constants
INCOME_9075 = 9075
INCOME_18150 = 18150
INCOME_36900 = 36900
INCOME_73800 = 73800
INCOME_89350 = 89350
INCOME_148850 = 148850
INCOME_186350 = 186350
INCOME_226850 = 226850
INCOME_405100 = 405100
INCOME_406750 = 406750
INCOME_457600 = 457600
TAXED_INCOME_907_5 = 907.5
TAXED_INCOME_1815 = 1815
TAXED_INCOME_5081_25 = 5081.25
TAXED_INCOME_10162_5 = 10162.5
TAXED_INCOME_18193_75 = 18193.75
TAXED_INCOME_28925 = 28925
TAXED_INCOME_45353_75 = 45353.75
TAXED_INCOME_50765 = 50765
TAXED_INCOME_109587_5 = 109587.5
TAXED_INCOME_117541_25 = 117541.25
TAXED_INCOME_118118_75 = 118118.75
TAXED_INCOME_127962_5 = 127962.5
TAX_PERCENT_10 = .10
TAX_PERCENT_15 = .15
TAX_PERCENT_25 = .25
TAX_PERCENT_28 = .28
TAX_PERCENT_33 = .33
TAX_PERCENT_35 = .35
TAX_PERCENT_396 = .396

# Computes income tax
# param maritalStatus (str)
# param income (int)
# returns tax owed (float)
def computeTax(maritalStatus, income):
  if (maritalStatus == "single" or maritalStatus == "Single"):
    if (income >= 0 and income <= INCOME_9075):
      tax = (TAX_PERCENT_10 * income)
    elif income <= INCOME_36900:
      tax = (TAXED_INCOME_907_5 + (TAX_PERCENT_15 * income))
    elif income <= INCOME_89350:
      tax = (TAXED_INCOME_5081_25 + (TAX_PERCENT_25 * income))
    elif income <= INCOME_186350:
      tax = (TAXED_INCOME_18193_75 + (TAX_PERCENT_28 * income))
    elif income <= INCOME_405100:
      tax = (TAXED_INCOME_45353_75 + (TAX_PERCENT_33 * income))
    elif income <= INCOME_406750:
      tax = (TAXED_INCOME_117541_25 + (TAX_PERCENT_35 * income))
    else:
      tax = (TAXED_INCOME_118118_75 + (TAX_PERCENT_396 * income))
  else:
    if (income >= 0 and income <= INCOME_18150):
      tax = (TAX_PERCENT_10 * income)
    elif income <= INCOME_73800:
      tax = (TAXED_INCOME_1815 + (TAX_PERCENT_15 * income))
    elif income <= INCOME_148850:
      tax = (TAXED_INCOME_10162_5 + (TAX_PERCENT_25 * income))
    elif income <= INCOME_226850:
      tax = (TAXED_INCOME_28925 + (TAX_PERCENT_28 * income))
    elif income <= INCOME_405100:
      tax = (TAXED_INCOME_50765 + (TAX_PERCENT_33 * income))
    elif income <= INCOME_457600:
      tax = (TAXED_INCOME_109587_5 + (TAX_PERCENT_35 * income))
    else:
      tax = (TAXED_INCOME_127962_5 + (TAX_PERCENT_396 * income))
  return tax

# Computes net income
# param income (int)
# param tax (float)
# returns the net income (float)
def netIncome(income, tax):
  total = (income - tax)
  return total

# Validates income
# param income (str)
# return True when income is only made of digits and greater than 0
def incomeValidation(income):
  return income.isdigit() and int(income) > 0

# Validates marital status
# param maritalStatus (str)
# return True when marital status is single or married
def maritalStatusValidation(maritalStatus):
  return maritalStatus == 'single' or maritalStatus == 'Single'\
         or maritalStatus == 'married' or maritalStatus == 'Married'

# The program calculates the income tax and the net income of the user
#   given marital status and gross income
def main():
  # Priming read
  name = input("Please enter your name or press <Enter> to quit:")

  while name:
    # Takes in the users marital status 
    maritalStatus = input("Are you single or married? ")

    # Validates the marital status
    while not maritalStatusValidation(maritalStatus):
      print("You did not enter a valid marital status. \
Please enter either single or married.")
      maritalStatus = input("Are you single or married? ")

    # Takes in the users income
    income = input("What is your taxable income? (in dollars) ")

    # Validates the income
    while not incomeValidation(income):
      print("You did not enter a valid income. Please enter your income.")
      income = (input("What is your taxable income? (in dollars) "))

    # Coverts income into an int
    income = int(income)

    # Computes income tax
    tax = computeTax(maritalStatus, income)

    # Computes net income
    total = netIncome(income, tax)

    # Displays marital status, gross income, and taxes owed
    print("You've entered that you're",maritalStatus,"and that you make $", \
    income,"a year. Therefore your income tax is: $", tax)

    # Displays net income 
    print("Your net income would be:$", total)

    # Continuation of the loop
    name = input("Please enter your name or press <Enter> to quit:")
    
  '''
# tester
# Test values should show 'smooth' transition from one tax bracket to the \
  next

  states = ['single', 'married']
  incomes = [[0,9075, 9076, 36900, 36901, 89350, 89351,
             186350, 186351, 405100, 405101, 406750, 406751],
            [0, 18150, 18151, 73800, 73801, 148850, 148851,
            226850, 226851,  405100, 405101, 457600, 457601]]

 

  for i in range(len(states)):
    for j in range(len(incomes[0])):
      print("%s, $%.2f = $%.2f" % \
          (states[i], incomes[i][j], computeTax(states[i], incomes[i][j])))

  '''
main()