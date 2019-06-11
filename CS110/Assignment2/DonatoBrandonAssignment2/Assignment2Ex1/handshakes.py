#Handshakes Program


classNumber = input("How many people are there in your class? ")
classNumber = int(classNumber)

handshakes = (classNumber * (classNumber - 1)) // 2

print("There would be",handshakes,"handshakes in this class")
