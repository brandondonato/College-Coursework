import turtle

def seq3np1(n):
  """ Print the 3n+1 sequence from n, terminating when it reaches 1."""
  count = 0
  while n != 1:
    if n % 2 == 0:        # n is even
     n = n // 2
    else:                 # n is odd
     n = n * 3 + 1
    count += 1               
  return count

def sequenceLoop(a,b):
  maxSoFar = 0
  t1 = turtle.Turtle()
  w = turtle.Screen()
  for start in range(a,b):
    print(start,'\t','\t',  seq3np1(start))
    if maxSoFar < (seq3np1(start)):
      maxSoFar = (seq3np1(start))
    graph(t1,w, start, seq3np1(start))
  return maxSoFar

def graph(t,w, x, y):
  w.setworldcoordinates(0,0,50,100)
  t.goto(x,y)
  t.stamp()

def main():
  print("Start Value", "\t" , "Number of Iterations")
  print("--", "\t", "\t" , "---")
  print("The longest number of iterations was: ",sequenceLoop(1, 51),\
        "iterations.")
  
main() 
