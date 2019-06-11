'''
Donato, Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
Lab7Ex17
'''
import turtle

#Constants
ITERATIONS = 5
ANGLE = 60
TURTLE_SIZE = 5

def createLSystem(iterations, beginning):
  startStr = beginning
  endStr = ""
  for i in range(iterations):
    endStr = processedString(startStr)
    startStr = endStr
  return endStr

def processedString(oldStr):
  newStr = ""
  for character in oldStr:
    newStr += applyRules(character)
  return newStr

def applyRules(character):
  newStr = ""
  if character == 'F':
    newStr = 'FF'
  elif character == 'X':
    newStr = '--FXF++FXF++FXF--'
  else:
    newStr = character
  return newStr

def drawLSystem(turtle, instructions, angle, distance):
  for cmd in instructions:
    if cmd == 'F':
      turtle.forward(distance)
    elif cmd == '+':
      turtle.right(angle)
    elif cmd == '-':
      turtle.left(angle)

def main():
  inst = createLSystem(ITERATIONS, "FXF--FF--FF")
  print(inst)
  turt = turtle.Turtle()          
  wn = turtle.Screen()
  turt.speed(10)

  drawLSystem(turt, inst, ANGLE, TURTLE_SIZE)

main()
