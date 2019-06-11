#Hello, Little Turtles! Exercise 6
def main():

  import turtle
  wn = turtle.Screen()
  brandon = turtle.Turtle()

  polygonSides = input("How many sides does the polygon have? ")
  polygonSides = int(polygonSides)
  sideLength = input("How long is each side of the polygon? ")
  sideLength = int(sideLength)
  sideColor = input("What color are the sides of the polygon? ")
  fillColor = input("What color is the inside of the polygon? ")

  brandon.color(sideColor)
  brandon.fillcolor(fillColor)
  brandon.begin_fill()

  for i in range(polygonSides):
    brandon.forward(sideLength)
    brandon.left(360/polygonSides)
  
  brandon.end_fill()

  wn.exitonclick()

main()
