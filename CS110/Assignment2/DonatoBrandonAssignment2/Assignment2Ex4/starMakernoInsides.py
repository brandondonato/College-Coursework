#Star with no insides program

import turtle
wn = turtle.Screen()
draw = turtle.Turtle()

pointNumber = input("How many verticies do you want the star to have? ")
pointNumber = int(pointNumber)


for polygonBase in range(pointNumber):
  draw.penup()
  draw.forward(50)
  draw.pendown()
  for triangles in range(2):
    draw.left(120)
    draw.forward(50)
  draw.penup()
  draw.left(120)
  draw.forward(50)
  draw.pendown()
  draw.right(360/pointNumber)
  


wn.exitonclick()

  
