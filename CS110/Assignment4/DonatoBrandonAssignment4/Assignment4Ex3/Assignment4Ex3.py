'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment4Ex3
'''


#Using a setUpTurtle function is the same as the first two programs.\
#The fact that the program outputs a graph is also the same.

#I can reuse the body of the drawSinCurve function and modify it to \
#accomodate to the logarithmic function.I can also use the setUpWindow \
#function and the main function accomodated to fit the logarithmic curve.

#My final y value is 11 because the log2(2048) = 11.


import math
import turtle
MAX_X = 2048
MAX_Y = 12
CONSTANT_0 = 0
CONSTANT_2 = 2
CONSTANT_1 = 1
def setUpWindow(screenObject):
  screenObject.setworldcoordinates(CONSTANT_0,-CONSTANT_1,MAX_X,MAX_Y)
  screenObject.bgcolor('lightblue')

def setUpTurtle(turtle):
  turtle.goto(CONSTANT_0,CONSTANT_0)
  turtle.goto(MAX_X,CONSTANT_0)
  turtle.penup()
  turtle.goto(CONSTANT_0,CONSTANT_2)
  turtle.pendown()
  turtle.goto(CONSTANT_0,-CONSTANT_2)
  turtle.goto(CONSTANT_0,CONSTANT_0)


def drawLogCurve(turtleObject):
  for angle in range(CONSTANT_1,(MAX_X+CONSTANT_1)):
      x = angle
      y = math.log(angle,CONSTANT_2)
      turtleObject.goto(x,y)

def log(xValue):
 return math.log(xValue,CONSTANT_2)

def main():
  sn = turtle.Screen()
  fred = turtle.Turtle()
  setUpWindow(sn)
  setUpTurtle(fred)
  drawLogCurve(fred)

main()

