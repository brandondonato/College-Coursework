'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment4Ex1
'''


#The getX and getSinX functions are fruitful functions.

#They need return statements because they have numeric values.

#Having global constants are important because if they occur multiple \
#times, they are easy to adjust.

#The values are used when creating the screen, axis and \
#when plotting the graph.


import math
import turtle
MAX_X = 2*math.pi
MAX_Y = 1
CONSTANT_0 = 0
CONSTANT_1 = 1
CONSTANT_2 = 2
CONSTANT_360 = 360
CONSTANT_PI = math.pi

def setUpWindow(screenObject):
  screenObject.setworldcoordinates(-CONSTANT_1,-CONSTANT_2,MAX_X,MAX_Y)
  screenObject.bgcolor('lightblue')

def getX(degrees):
  radianAngle = math.radians(degrees)
  return radianAngle

def getSinX(degrees):
  return math.sin(getX(degrees))

def setUpTurtle(turtle):
  turtle.goto(-CONSTANT_PI,CONSTANT_0)
  turtle.goto(MAX_X,CONSTANT_0)
  turtle.penup()
  turtle.goto(CONSTANT_0,CONSTANT_2)
  turtle.pendown()
  turtle.goto(CONSTANT_0,-CONSTANT_2)
  turtle.goto(CONSTANT_0,CONSTANT_0)

def drawSinCurve(turtleObject):
  for angle in range(CONSTANT_0,(CONSTANT_360 + CONSTANT_1)):
      x = math.radians(angle)
      y = math.sin(getX(angle))
      turtleObject.goto(x,y)
      

def main():
  sn = turtle.Screen()
  fred = turtle.Turtle()
  setUpWindow(sn)
  setUpTurtle(fred)
  drawSinCurve(fred)
  
main()


