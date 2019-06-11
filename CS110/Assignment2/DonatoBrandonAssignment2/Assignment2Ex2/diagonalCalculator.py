#Diagonal Calculator


polygonSides = input("How many sides does this polygon have? ")
polygonSides = int(polygonSides)

diagonals = ((polygonSides * (polygonSides - 3)) // 2)

print("There are",diagonals,"diagonals in this polygon.")
