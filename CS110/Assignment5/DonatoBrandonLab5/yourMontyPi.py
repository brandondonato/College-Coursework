'''
Donato, Brandon
bdonato1@binghamton.edu
B57
Jia Yang
Lab#5
'''

'''
Estimates pi using Monte Carlo simulation

Virtual Dartboard has area 2 X 2 to accommodate unit circle
Total area is 4
Therefore, since area of unit circle = pi * radius^2 (and radius of 1 squared
  is 1), ratio of area of unit circle to area of board should be pi/4
  Theoretically, if you fill the entire board with darts, counting
  the number of darts that fall within the circle divided by the
  total number of darts thrown should give us that ratio (i.e., 1/4 * pi)
  Therefore, multiplying that result by 4 should give us an approx. of pi

Output to monitor:
  approximation of pi (float)
Output to window:
  colored dots that simulate unit circle on 2x2 square
Input from keyboard:
  numberDarts (int)
Tasks allocated to functions:
  setUpDartboard() - invokes setworldcoordinates(), drawSquare(), and
                     drawLine()
  drawSquare() - to outline dartboard                     
  drawLine() - to draw axes
  inCircle() - determine if dot is in circle
  montePi() - simulation algorithm  
'''

# List imports here:
import math
import turtle
import random

# List constants here (NO MAGIC NUMBERS!):
#I'll start you off with these (there are more . . .)
BATCH_OF_DARTS = 5000
BOARD_WIDTH = 2
DOT_SIZE = 2
METHOD = "a Monte Carlo Simulation"
RIGHT_ANGLE = 90
BACKWARDS_ANGLE = 180
SQUARE_SIDES = 4
PI_MULTIPLIER = 4

# This is a non-fruitful or void function
# Draws square given turtle, width and top left XY position
# Use the parameter names given
# param grapher (Turtle)
# param width (int)
# param topLeftX (int)
# param topLeftY (int)
def drawSquare( grapher, width, topLeftX, topLeftY ):
  # Don't forget to put pen up and down as appropriate  
  grapher.penup()
  grapher.goto(topLeftX, topLeftY) 
  #grapher.left(BACKWARDS_ANGLE)
  grapher.pendown()
  for square in range(SQUARE_SIDES):
    grapher.forward(width)
    grapher.right(RIGHT_ANGLE)
    


# This is a non-fruitful or void function
# Draws axis-lines given turtle and endpoints
# Use the parameter names given
# param grapher (Turtle)
# param xStart (int)
# param yStart (int)
# param xEnd (int)
# param yEnd (int)
def drawLine( grapher, xStart, yStart, xEnd, yEnd ):
  # Don't forget to put pen up and down as appropriate
  grapher.penup()
  grapher.goto(xStart,yStart)
  grapher.pendown()
  grapher.goto(xEnd,yEnd)


# This is a non-fruitful or void function
# Sets up 2X2 area with x- and y-axis to simulate dartboard
# Use the parameter names given
# param board - window that will simulate board
# param grapher - turtle that will do drawing
def setUpDartboard( board, grapher):
  # Hint:  you will be using BOARD_WIDTH here!
  # Remember that the order, number, and type of arguments sent to an
  #   invoked function must match the order, number, and type of that
  #   function's parameters
  # However, matching the names of the arguments to the names of the
  #   parameters is not only irrelevant, but in many cases will be impossible
  #   (say when the argument is a named constant or when an argument doesn't
  #   have a name, like when it's 0, 1 or -1)
  
  # set up 2X2 area and set pensize  
  board.setworldcoordinates \
    (-1, -1, 1, 1) # make a 2X2 area
  grapher.pensize(2)

  # Draw board
  drawSquare(grapher, BOARD_WIDTH, -1, 1)

  # Draw x- and y-axes
  drawLine(grapher, -1, 0, 1, 0)
  drawLine(grapher, 0, -1, 0, 1)


# This is a fruitful function
# (Predicate Function)
# Determines whether or not dart falls inside unit circle with center at 0,0
# Use the parameter name given
# param dart (Turtle)
# return True if in circle, False if not
def inCircle( dart ):
  # Look up distance() method on a turtle object
  # You'll want to determine if the distance of the dart from the center is
  #   in the circle (i.e., <= the circle's radius of 1)
  # This is a one-line function:  DON'T use an if
  return dart.distance(0,0) < 1

# This is a fruitful function
# Algorithm for Monte Carlo simulation
# Use the parameter names given
# param numberDarts (int)
# param dart (Turtle)
# return approximation of pi (float)
def montePi( numberDarts, dart ):
  # Initialize inCircleCount
  inCircleCount = 0
  # Loop for numberDart
  for counter in range(numberDarts):
    # This will help you keep track of how many darts have already been thrown,
    # and, therefore, how many still to go!
    if counter % BATCH_OF_DARTS == 0: #Debug
      print(counter)

    # Get random x and y coordinates and then position dart
    # Hint:  Use random.random() and random.choice()
    dartboardRange = [-1,1]
    randX = random.random()
    randY = random.random()
    x = randX * random.choice(dartboardRange)
    y = randY * random.choice(dartboardRange)
    dart.penup()
    dart.goto(x,y)
    dart.pendown()
    # Draw red dot if in circle, blue dot if not
    if inCircle(dart):
      # Increment inCircleCount
      # Set dart color to red
      inCircleCount += 1
      dart.color('red')
    else:
      # Set dart color to blue
      dart.color('blue')
    
    # Draw dot
    dart.stamp()

  # ratio of inCircleCount to numberDarts is pi/4, therefore
  #   ratio * 4 will be approximation of pi
  # return approximation of pi after 'throwing' all the darts
  ratio = (inCircleCount / numberDarts)
  return ratio * PI_MULTIPLIER


# This is a non-fruitful or void function
#Performs Monte Carlo simulation to generate approximation of Pi
def main():
  # Get number of darts for simulation from user
  # Note continuation character <\> so we don't go over 78 columns:
  print("This is a program that simulates throwing darts at a dartboard\n" \
        "in order to approximate pi: The ratio of darts in a unit circle\n"\
        "to the total number of darts in a 2X2 square should be\n"\
        "approximately equal to pi/4")

  # When developing and testing this program, start with a very small number
  #   to see that it's working
  # When you think everything is good, then give it a huge number and let it cook
  # You may want to leave it running overnight - it's going to take a while
  # Make sure your computer isn't set to go to sleep
  # Take screen shots of both windows when its done so that you can upload them to
  # Blackboard
  numberDarts = int(input(
    "\nPlease input the number of darts to be thrown in the simulation:  ")) 

  #Create window, turtle, set up window as dartboard
  window = turtle.Screen()  
  darty = turtle.Turtle()
  darty.speed(0) # as fast as it will go!
  setUpDartboard(window, darty)

  # Include the following code in order to update animation periodically
  # instead of for each throw (saves LOTS of time):
  window.tracer(BATCH_OF_DARTS)

  # Conduct simulation and print result
  # Note use of format strings and format specifiers!!
  # We will talk about these on Monday
  print("\nThe estimation of pi from %s using %d virtual darts is %.9f" %
    (METHOD, numberDarts,montePi(numberDarts, darty)))
  
  # Keep the window up until dismissed (Hopefully this will work!)
  # Don't hide or mess with window while it's 'working'
  window.exitonclick()

main()
