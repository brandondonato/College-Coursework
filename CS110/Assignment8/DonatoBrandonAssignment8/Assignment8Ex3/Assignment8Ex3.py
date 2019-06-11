'''
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
Assignment8Ex3
'''

"""
Analysis:

Create a program that outputs a L-System through a certain amount of
iterations and uses the turtle module in Python to draw the L-System.

Output to window:
  The L-System is draw after a predetermined number of iterations

Tasks allocated to functions:
  createLSystem() - This makes the actual L-System through so many iterations
  processedString() - This function creates the single iteration of the
    L-System
  applyRules() - This function modifies the string copy to obey the given
    rules
  drawLSystem() - This function actually draws the L-System
"""

# Imports --------------------------------------------------------------------

import turtle

# Constants ------------------------------------------------------------------

ITERATIONS = 5
ANGLE = 25
TURTLE_SIZE = 5

# Functions ------------------------------------------------------------------

# Iterates the L-System through a given number of times
# param iterations (int) - The number of iterations the program is executed
# param beginning (str) - The beginning string of the L-System
# invokes range()
# invokes processedString()
# returns endStr (str) - The final L-System after being iterated through
def createLSystem(iterations, beginning):
  startStr = beginning
  endStr = ""
  for i in range(iterations):
    endStr = processedString(startStr)
    startStr = endStr
  return endStr

# Given a string, copies the string, applies the rules of the L-System
#   and outputs the new string
# param oldStr (str) - The initial string to be copied and modified
# invokes in()
# invokes applyRules()
# returns newStr (str) - The modified initial string which the rules have
#   been applied to
def processedString(oldStr):
  newStr = ""
  for character in oldStr:
    newStr += applyRules(character)
  return newStr

# Based upon a character, modifies the character in a new string based upon
#   given rules
# param character (str) - A single character of a string
# returns newStr (str) - The modified character in a copy that's been put
#   through the rules
def applyRules(character):
  newStr = ""
  if character == 'F':
    newStr = 'F[-F]F[+F]F'
  else:
    newStr = character
  return newStr

# Draws the L-System given the inputted L-System
# param turtle (turtle) - The turtle that draws the system
# param instructions (str) - The L-System that is to be drawn
# param angle (int) - The angle that the turtle will turn
# param distance (int) - The distance the turtle goes before processing the
#   next step
# invokes turtle.heading()
# invokes turtle.forward()
# invokes turtle.right()
# invokes turtle.left()
# invokes turtle.xcor()
# invokes turtle.ycor()
# invokes turtle.setheading()
# invokes turtle.setposition()
# invokes list.pop()
# invokes list.append()
def drawLSystem(turtle, instructions, angle, distance):
  infoList = []
  for cmd in instructions:
    if cmd == 'F':
      turtle.forward(distance)
    elif cmd == '+':
      turtle.right(angle)
    elif cmd == '-':
      turtle.left(angle)
    elif cmd == '[':
      infoList.append([turtle.heading(),turtle.xcor(),turtle.ycor()])
    elif cmd ==']':
      newInfo = infoList.pop()
      turtle.setheading(newInfo[0])
      turtle.setposition(newInfo[1],newInfo[2])

# Main -----------------------------------------------------------------------

# Generates the list of instructions that will be used in drawing the L-System
#   as well as sets up of the turtle and window to draw on.
def main():
  # Generates instructions
  inst = createLSystem(ITERATIONS, "F")
  print(inst)

  # Creates the turtle and window and sets the turtle in place before drawing
  turt = turtle.Turtle()           
  wn = turtle.Screen()
  turt.speed(0)
  turt.penup()
  turt.goto(-200,0)
  turt.pendown()

  # Draws the actual L-System
  drawLSystem(turt, inst, ANGLE, TURTLE_SIZE)   

main()
