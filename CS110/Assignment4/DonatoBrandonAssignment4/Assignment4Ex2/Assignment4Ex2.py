'''
Donato,Brandon
bdonato1@binghamton.edu
CS 110 - B57
Jia Yang
assignment4Ex2
'''


#The getX function is the same in both programs because it converts angles \
#to radians.The setUpTurtle function and the global constants are also \
#the same. It's easier to re-use a function I've created than make \
#a new one.

#I'm re-using the getX function, the setUpTurtle function as well as \
#nearly the same body to the drawSinCurve function with minor \
#modifications to accomodate to the cosine function.This inherently \
#means the main function will be extremely similar.

#The difference between this program and the other will that this function \
#will return the cosine of some inputted value. The graph will also be \
#drawn differently because its a different graph.


import math
import turtle
MAX_X = 2*math.pi
MAX_Y = 1
CONSTANT_0 = 0
CONSTANT_1 = 1
CONSTANT_2 = 2
CONSTANT_360 = 360
CONSTANT_PI = math.pi


def getX(degrees):
  radianAngle = math.radians(degrees)
  return radianAngle

def setUpWindow(screenObject):
  screenObject.setworldcoordinates(-CONSTANT_1,-CONSTANT_2,MAX_X,MAX_Y)
  screenObject.bgcolor('lightblue')

def setUpTurtle(turtle):
  turtle.goto(-CONSTANT_1*CONSTANT_PI,CONSTANT_0)
  turtle.goto(MAX_X,CONSTANT_0)
  turtle.penup()
  turtle.goto(CONSTANT_0,CONSTANT_2)
  turtle.pendown()
  turtle.goto(CONSTANT_0,-CONSTANT_2)
  turtle.goto(CONSTANT_0,CONSTANT_0)

def getCosX(degrees):
  return math.cos(getX(degrees))

def drawCosCurve(turtleObject):
  for angle in range(CONSTANT_0,(CONSTANT_360 + CONSTANT_1)):
      x = math.radians(angle)
      y = math.cos(getX(angle))
      turtleObject.goto(x,y)

def main():
  sn = turtle.Screen()
  fred = turtle.Turtle()
  setUpWindow(sn)
  setUpTurtle(fred)
  drawCosCurve(fred)

main()

