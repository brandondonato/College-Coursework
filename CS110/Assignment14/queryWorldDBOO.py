'''
Brandon Donato
'''

'''
This program finds the population of a city via database query
Output:
  query result (str)
Input:
  city (str)
Classes Used:
  BadArgument
  QueryWorldBD
'''

import sqlite3

# ---------------------------------------------------------------------
'''
User defined exception class (subclass of Exception)
Used to signal program that query should not be issued
'''

class BadArgument(Exception):
  
#-- Constructor --------------------------------------------------------
  
  def __init__(self):
    self.__title = 'Missing Argument'
    self.__message = "You have an invalid range or the numbers you have given\
 aren't integers"

#-- Accessors ----------------------------------------------------------
    
  # return title (str)
  def getTitle(self):
    return self.__title
    
#-- to String ----------------------------------------------------------
  
  def __str__(self):
    return self.__message

# -----------------------------------------------------------------------------
# -----------------------------------------------------------------------------

'''
Encapsulates a  population query sent to world database
'''
class QueryWorldDB:
  
  # Connect to database and get cursor
  # param dbName (str)
  def __init__(self, dBName):
    conn = sqlite3.connect(dBName)
    self.__cursor = conn.cursor()
    # Must make city instance variable so that it is accessible to all methods
    self.__currentCity = "" 


# -- Mutators ----------------------------------------------------------------

  # 
  # param cityName (str)
  def setCity(self, cityName):
    self.__currentCity = cityName

  def setUpperBound(self, upperPop):
    self.__upperPop = upperPop

  def setLowerBound(self, lowerPop):
    self.__lowerPop = lowerPop


  # raises BadArgument Exception if city is blank or contains invalid chars
  def popQuery(self):
    if self.isValidRange(self.__upperPop, self.__lowerPop):
      self.__cursor.execute('select name, population from city where population\
> ? and population < ? order by population desc',\
                            (self.__lowerPop,self.__upperPop))
      cities = self.__cursor.fetchall()
      for city in cities:
        print(city)
    else:
      raise BadArgument()

  def isValidRange(self, upperPop, lowerPop):
    return upperPop.isdigit() and lowerPop.isdigit() and\
           int(upperPop) >= 0 and int(lowerPop) >= 0 and\
           int(upperPop) > int(lowerPop)

  # Close connection to db
  def closeConnection(self):
    self.__cursor.close()

# -- toString ----------------------------------------------------------------

  # return result (str)
  def __str__(self):
    # Note that if city isn't in database, then answer will be None
    # If city is in database, answer will be a tuple object
    # Will have to get element[0] of tuple in order to use it
    answer = self.__cursor.fetchone()

    # Note that 4th format specifier denotes a string rather than an int in 
    # order to accommodate possibility that answer is None
    return '%s %s %s %s\n' % (
      ('The population of' if answer else 'There is no city named'),
      self.__currentCity,
      ('is' if answer else 'in the database'),
      ('' if answer == None else str(answer[0])) )
  
#------------------------------------------------------------------------------
#------------------------------------------------------------------------------
  
# Find population of any city stored in world database
# Cities must contain only alphabetic characters with the exeception of mult-
#   word cities, which must be connected with '_' (no spaces allowed)
def main():
  # set up connection and create cursor
  query = QueryWorldDB('worldDB(1)')

  # get input from user (priming read)
  upperPop = input("Enter a maximum population: \nor (Press <Enter> to quit):  ")
  
  
  # let user get as many results as desired
  while upperPop:
    lowerPop = input("Enter a minimum population: ")
    try:
      # set up and issue query
      query.setUpperBound(upperPop)
      query.setLowerBound(lowerPop)
      query.popQuery()
      # show results
      print(query)
    except BadArgument as err:
      # city input empty or malformed
      print('\n%s: %s\n' % (err.getTitle(), str(err) ))
       
    # let user enter another city (continuation read)
    upperPop = input("Enter a maximum population: \nor \
(Press <Enter> to quit):  ")
    
  # close connection to db
  query.closeConnection()

main()
                            
                            
                    
