#Star program

import turtle
wn = turtle.Screen()
draw = turtle.Turtle()

pointNumber = input("How many verticies do you want the star to have? ")
pointNumber = int(pointNumber)


for polygonBase in range(pointNumber):
  draw.forward(50)
  for triangles in range(3):
    draw.left(120)
    draw.forward(50)
  draw.right(360/pointNumber)
  


wn.exitonclick()

  
