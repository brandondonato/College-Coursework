# shopSmart.py
# ------------
# Licensing Information:  You are free to use or extend these projects for
# educational purposes provided that (1) you do not distribute or publish
# solutions, (2) you retain this notice, and (3) you provide clear
# attribution to UC Berkeley, including a link to http://ai.berkeley.edu.
#
# Attribution Information: The Pacman AI projects were developed at UC Berkeley.
# The core projects and autograders were primarily created by John DeNero
# (denero@cs.berkeley.edu) and Dan Klein (klein@cs.berkeley.edu).
# Student side autograding was added by Brad Miller, Nick Hay, and
# Pieter Abbeel (pabbeel@cs.berkeley.edu).


"""
Here's the intended output of this script, once you fill it in:

Welcome to shop1 fruit shop
Welcome to shop2 fruit shop
For orders:  [('apples', 1.0), ('oranges', 3.0)] best shop is shop1
For orders:  [('apples', 3.0)] best shop is shop2
"""

import shop

def shopSmart(orderList, fruitShops):
    """
        orderList: List of (fruit, numPound) tuples
        fruitShops: List of FruitShops
    """
    #Find the price at each fruit shop, then find the cheapest shop
    count = 0
    currentMin = 0
    currentMinShop = fruitShops[0]
    for shop in fruitShops:
        #print "Order: %s, current shop: %s" % (orderList, shop.getName() )

        #Get the total cost for the order at this shop
        totalCost = shop.getPriceOfOrder(orderList)
        #print "Cost for %s: %s\n" % (shop.getName(), totalCost)

        #Compare it with the minimum cost
        if(count == 0):
            #print "First shop is the min."
            currentMin = totalCost
            currentMinShop = shop
        else:
            #Update the minimum cost and the associated store
            if(totalCost < currentMin):
                #print "The totalCost:%s is less than the currentMin:%s"\
                #% (totalCost, currentMin)
                currentMin = totalCost
                currentMinShop = shop

        #Update the current count of the loop since it is only
        #   used to establish the first minimum
        count += 1

    #Now return the min shop
    return currentMinShop

if __name__ == '__main__':
  "This code runs when you invoke the script from the command line"
  orders = [('apples',1.0), ('oranges',3.0)]
  dir1 = {'apples': 2.0, 'oranges':1.0}
  shop1 =  shop.FruitShop('shop1',dir1)
  dir2 = {'apples': 1.0, 'oranges': 5.0}
  shop2 = shop.FruitShop('shop2',dir2)
  shops = [shop1, shop2]
  print "For orders ", orders, ", the best shop is", shopSmart(orders, shops).getName()
  orders = [('apples',3.0)]
  print "For orders: ", orders, ", the best shop is", shopSmart(orders, shops).getName()
