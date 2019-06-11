 # search.py
# ---------
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
In search.py, you will implement generic search algorithms which are called by
Pacman agents (in searchAgents.py).
"""

import util

class SearchProblem:
    """
    This class outlines the structure of a search problem, but doesn't implement
    any of the methods (in object-oriented terminology: an abstract class).

    You do not need to change anything in this class, ever.
    """

    def getStartState(self):
        """
        Returns the start state for the search problem.
        """
        util.raiseNotDefined()

    def isGoalState(self, state):
        """
          state: Search state

        Returns True if and only if the state is a valid goal state.
        """
        util.raiseNotDefined()

    def getSuccessors(self, state):
        """
          state: Search state

        For a given state, this should return a list of triples, (successor,
        action, stepCost), where 'successor' is a successor to the current
        state, 'action' is the action required to get there, and 'stepCost' is
        the incremental cost of expanding to that successor.
        """
        util.raiseNotDefined()

    def getCostOfActions(self, actions):
        """
         actions: A list of actions to take

        This method returns the total cost of a particular sequence of actions.
        The sequence must be composed of legal moves.
        """
        util.raiseNotDefined()


def tinyMazeSearch(problem):
    """
    Returns a sequence of moves that solves tinyMaze.  For any other maze, the
    sequence of moves will be incorrect, so only use this for tinyMaze.
    """
    from game import Directions
    s = Directions.SOUTH
    w = Directions.WEST
    return  [s, s, w, s, w, w, s, w]

def generalSearch(problem, container, heuristic=None):
    """
    This is a general search function that takes in some sort of container and
    the problem. This allows the function to be modular for the respective search
    algorithm implemented.

    The following search algorithms can be used here without any heuristic:
        depthFirstSearch - container = stack
        breadthFirstSearch - container = queue

    The following search algorithms can be used here with some form of heuristic:
        aStarSearch - PriorityQueue with the nullHeuristic
        uniformCostSearch - PriorityQueue with nullHeuristic

        The difference between aStarSearch and uniformCostSearch is that
        aStarSearch is uses both the actual cost and the heuristic, while
        uniformCostSearch uses only the heuristic.
    """
    #Debug stuff
    #print "\nStart:", problem.getStartState()
    visitedList = [] #This indicates the states we have already visited

    #Perform a check for the heuristic
    if(not heuristic == None):
        #Put the initial state and the returnList into the container
        #   Also, add the nextCost into the tuple, and give the PriorityQueue
        #   the cost with the heuristic as the priority
        container.push( (problem.getStartState(), [], 0), 0)
    else:
        #Put the initial state and the returnList into the container
        container.push( (problem.getStartState(), []) )

    #Iterate through the container(fringe) until there are no elements left
    while(not container.isEmpty()):
        #Perform the heuristic check
        if(heuristic):
            poppedItem = container.pop()
            #print poppedItem
            newState = poppedItem[0] #Get the current state
            returnDirections = poppedItem[1] #These are the movement actions
            stateCost = poppedItem[2] #This is the current cost
            #print "State cost: ", stateCost

        else:
            poppedItem = container.pop()
            #print poppedItem
            newState = poppedItem[0] #Get the current state
            returnDirections = poppedItem[1] #These are the movement actions
            #print returnDirections

        #If we have seen a node before, do not visit it a second time
        if(newState not in visitedList):
            #print "Have not visited: ", newState
            #Now add the visited node to the visitedList
            visitedList.append(newState)

            #Verify whether or not the current state is the goal state
            if(problem.isGoalState(newState)):
                #print type(returnDirections)
                #print "\nFinal transitions: ", returnDirections
                #print "Goal state: ", newState
                return returnDirections

            #If not the goal state, enque the adjacent nodes based on
            #   the given algorithm or heuristic
            if(heuristic):
                nextStateList = problem.getSuccessors(newState)

                #Iterate through and add all of the adjacent states to the container
                for states in nextStateList:
                    nextState = states[0]
                    nextDirection = states[1]
                    nextCost = states[2]
                    #print "nextDirection: ", nextDirection
                    #print "nextState: ", nextState
                    #print "nextCost: ", nextCost


                    #Add the new information.
                    #heuristic(nextState, problem) evaluates the potential cost of
                    #   going to nextState. That is pushed with the updated state
                    #   information
                    container.push( (nextState, returnDirections + [nextDirection], \
                                        nextCost + stateCost), nextCost + stateCost \
                                        + heuristic(nextState, problem)  )
            else:
                nextStateList = problem.getSuccessors(newState)

                #Iterate through and add all of the adjacent states to the container
                for states in nextStateList:
                    nextState = states[0]
                    nextDirection = states[1]
                    #print "nextDirection: ", nextDirection
                    #print "nextState: ", nextState

                    #Add the new information back into the container
                    container.push( (nextState, returnDirections + [nextDirection]) )

                    #break #Breaks out of the while loop

    #util.raiseNotDefined()

def depthFirstSearch(problem):
    """
    Search the deepest nodes in the search tree first.

    Your search algorithm needs to return a list of actions that reaches the
    goal. Make sure to implement a graph search algorithm.

    To get started, you might want to try some of these simple commands to
    understand the search problem that is being passed in:

    print "Start:", problem.getStartState()
    print "Is the start a goal?", problem.isGoalState(problem.getStartState())
    print "Start's successors:", problem.getSuccessors(problem.getStartState())
    """
    "*** YOUR CODE HERE ***"
    # Start state returns some letter that means nothing to me at this moment
    #print "Start:", problem.getStartState()

    # This returns a boolean on whether or not the state is a goal state
    #print "Is the start a goal?", problem.isGoalState(problem.getStartState())

    # This retuns the successors of the current state(node). The successors are
    #   written in the form of a list
    #print "Start's successors:", problem.getSuccessors(problem.getStartState())

    #Goal of DFS should be to: 1) Enque all successor states in a stack
    #                          2) Check to see if the current state is the goal
    #                          3a) If it is the goal state, return!
    #                          3b) If not, enque all of the successor states
    #                          4) Choose the top of the stack and repeat 2)

    # What I need to keep track of while searching: Active path to the goal
    return generalSearch(problem, util.Stack())

def breadthFirstSearch(problem):
    """Search the shallowest nodes in the search tree first."""
    "*** YOUR CODE HERE ***"
    return generalSearch(problem, util.Queue())
    #util.raiseNotDefined()

def uniformCostSearch(problem):
    """Search the node of least total cost first."""
    "*** YOUR CODE HERE ***"
    return generalSearch(problem, util.PriorityQueue(), nullHeuristic)
    #util.raiseNotDefined()

def nullHeuristic(state, problem=None):
    """
    A heuristic function estimates the cost from the current state to the nearest
    goal in the provided SearchProblem.  This heuristic is trivial.
    """
    return 0

def aStarSearch(problem, heuristic=nullHeuristic):
    """Search the node that has the lowest combined cost and heuristic first."""
    "*** YOUR CODE HERE ***"
    return generalSearch(problem, util.PriorityQueue(), heuristic)
    #util.raiseNotDefined()


# Abbreviations
bfs = breadthFirstSearch
dfs = depthFirstSearch
astar = aStarSearch
ucs = uniformCostSearch
