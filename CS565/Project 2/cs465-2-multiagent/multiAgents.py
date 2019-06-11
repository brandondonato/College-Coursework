# multiAgents.py
# --------------
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


from util import manhattanDistance
from game import Directions
import random, util

from game import Agent
import sys

class ReflexAgent(Agent):
    """
      A reflex agent chooses an action at each choice point by examining
      its alternatives via a state evaluation function.

      The code below is provided as a guide.  You are welcome to change
      it in any way you see fit, so long as you don't touch our method
      headers.
    """


    def getAction(self, gameState):
        """
        You do not need to change this method, but you're welcome to.

        getAction chooses among the best options according to the evaluation function.

        Just like in the previous project, getAction takes a GameState and returns
        some Directions.X for some X in the set {North, South, West, East, Stop}
        """
        # Collect legal moves and successor states
        legalMoves = gameState.getLegalActions()

        # Choose one of the best actions
        scores = [self.evaluationFunction(gameState, action) for action in legalMoves]
        bestScore = max(scores)
        bestIndices = [index for index in range(len(scores)) if scores[index] == bestScore]
        chosenIndex = random.choice(bestIndices) # Pick randomly among the best

        "Add more of your code here if you want to"

        return legalMoves[chosenIndex]

    def evaluationFunction(self, currentGameState, action):
        """
        Design a better evaluation function here.

        The evaluation function takes in the current and proposed successor
        GameStates (pacman.py) and returns a number, where higher numbers are better.

        The code below extracts some useful information from the state, like the
        remaining food (newFood) and Pacman position after moving (newPos).
        newScaredTimes holds the number of moves that each ghost will remain
        scared because of Pacman having eaten a power pellet.

        Print out these variables to see what you're getting, then combine them
        to create a masterful evaluation function.
        """
        # Useful information you can extract from a GameState (pacman.py)
        successorGameState = currentGameState.generatePacmanSuccessor(action)
        newPos = successorGameState.getPacmanPosition()
        newFood = successorGameState.getFood()
        newGhostStates = successorGameState.getGhostStates()
        newScaredTimes = [ghostState.scaredTimer for ghostState in newGhostStates]

        #Get the position of the ghost
        ghostPositionList = successorGameState.getGhostPositions()
        ghostPos = ghostPositionList[0]
        ghostDist = util.manhattanDistance(ghostPos, newPos)

        #Determine the closest food
        closestFood = sys.maxint
        foodList = newFood.asList()
        for foodPellet in foodList:
            #Get the distance from the new position to the next food
            foodDist = util.manhattanDistance(foodPellet, newPos)
            if(foodDist < closestFood):
                closestFood = foodDist

        #Determine if the ghost is too close
        if(ghostDist <= 1):
            #Run away
            return -1 * sys.maxint
        else:
            #Return the score + reward(arbitrary positive #) / distance(time loss)
            return successorGameState.getScore()+5/closestFood

def scoreEvaluationFunction(currentGameState):
    """
      This default evaluation function just returns the score of the state.
      The score is the same one displayed in the Pacman GUI.

      This evaluation function is meant for use with adversarial search agents
      (not reflex agents).
    """
    return currentGameState.getScore()

class MultiAgentSearchAgent(Agent):
    """
      This class provides some common elements to all of your
      multi-agent searchers.  Any methods defined here will be available
      to the MinimaxPacmanAgent, AlphaBetaPacmanAgent & ExpectimaxPacmanAgent.

      You *do not* need to make any changes here, but you can if you want to
      add functionality to all your adversarial search agents.  Please do not
      remove anything, however.

      Note: this is an abstract class: one that should not be instantiated.  It's
      only partially specified, and designed to be extended.  Agent (game.py)
      is another abstract class.
    """

    def __init__(self, evalFn = 'scoreEvaluationFunction', depth = '2'):
        self.index = 0 # Pacman is always agent index 0
        self.evaluationFunction = util.lookup(evalFn, globals())
        self.depth = int(depth)

class MinimaxAgent(MultiAgentSearchAgent):
    """
      Your minimax agent (question 2)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action from the current gameState using self.depth
          and self.evaluationFunction.

          Here are some method calls that might be useful when implementing minimax.

          gameState.getLegalActions(agentIndex):
            Returns a list of legal actions for an agent
            agentIndex=0 means Pacman, ghosts are >= 1

          gameState.generateSuccessor(agentIndex, action):
            Returns the successor game state after an agent takes an action

          gameState.getNumAgents():
            Returns the total number of agents in the game
        """
        "*** YOUR CODE HERE ***"
        finalMoveList = self.maxValue(gameState, 0, 0)
        #print finalMove
        return finalMoveList[0]
        #for ghost in range(2):
        #    print "num: %d" % ghost
        #print self.evaluationFunction(gameState)
        #return Directions.STOP
        #util.raiseNotDefined()

    #Create a max-value function for the given states
    def maxValue(self,gameState,depth,agentIndex):

        #If we are at the end, stop
        if(self.depth == depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value associated with the next move
        bestValue = -1 * sys.maxint
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine the next state for all ghosts
            newStateValue = self.minValue(newState,depth,agentIndex+1)

            #print "newStateValue: ", newStateValue
            #If the new state is better, save it
            if(bestValue < newStateValue[1]):
                #print "New state value: ", newStateValue
                nextAction = action
                bestValue = newStateValue[1]

        #If there was no better state, just stop to evaluate
        if(bestValue == -1*sys.maxint):
            return (Directions.STOP, self.evaluationFunction(gameState))
        else:
            return (nextAction, bestValue)

    #Create a min-value function for the given states
    def minValue(self,gameState,depth,agentIndex):

        #If we are at the end, stop
        if(self.depth <= depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value and action associated with the next move
        bestValue = sys.maxint
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine if you need to find the next ghost node or pacman
            nextAgentIndex = (agentIndex+1) % (gameState.getNumAgents())

            #If the index == 0, then it's pacman, else its a ghost
            if(nextAgentIndex == 0):
                #Increase the depth of the minimax tree
                newStateValue = self.maxValue(newState, depth+1, 0)
            else:
                #Maintain the same level of the minimax tree
                newStateValue = self.minValue(newState, depth, nextAgentIndex)

            #If the new state is better, save it
            if(bestValue > newStateValue[1]):
                nextAction = action
                bestValue = newStateValue[1]

        #If there was no better state, just stop to evaluate
        if(bestValue == sys.maxint):
            return (Directions.STOP, self.evaluationFunction(gameState))
        else:
            return (nextAction, bestValue)

class AlphaBetaAgent(MultiAgentSearchAgent):
    """
      Your minimax agent with alpha-beta pruning (question 3)
    """

    def getAction(self, gameState):
        """
          Returns the minimax action using self.depth and self.evaluationFunction
        """
        "*** YOUR CODE HERE ***"
        #util.raiseNotDefined()
        finalMoveList = self.maxValuePruning(gameState,0,0,-1*sys.maxint, sys.maxint)
        return finalMoveList[0]

    #Create a max-value function for the given states
    def maxValuePruning(self,gameState,depth,agentIndex,alpha,beta):

        #If we are at the end, stop
        if(self.depth == depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value associated with the next move
        bestValue = -1 * sys.maxint
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine the next state for all ghosts
            newStateValue = self.minValuePruning(newState,depth,agentIndex+1,alpha,beta)

            #print "newStateValue: ", newStateValue
            #If the new state is better, save it
            if(bestValue < newStateValue[1]):
                #print "New state value: ", newStateValue
                nextAction = action
                bestValue = newStateValue[1]

            #Evaluate against the Beta value
            if(bestValue > beta):
                return (nextAction, bestValue)

            #Set the alpha value
            if(alpha < bestValue):
                alpha = bestValue

        #If there was no better state, just stop to evaluate
        if(bestValue == -1*sys.maxint):
            return (Directions.STOP, self.evaluationFunction(gameState))
        else:
            return (nextAction, bestValue)

    #Create a min-value function for the given states
    def minValuePruning(self,gameState,depth,agentIndex,alpha,beta):

        #If we are at the end, stop
        if(self.depth <= depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value and action associated with the next move
        bestValue = sys.maxint
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine if you need to find the next ghost node or pacman
            nextAgentIndex = (agentIndex+1) % (gameState.getNumAgents())

            #If the index == 0, then it's pacman, else its a ghost
            if(nextAgentIndex == 0):
                #Increase the depth of the minimax tree
                newStateValue = self.maxValuePruning(newState,depth+1,0,alpha,beta)
            else:
                #Maintain the same level of the minimax tree
                newStateValue = self.minValuePruning(newState,depth,nextAgentIndex,alpha,beta)

            #If the new state is better, save it
            if(bestValue > newStateValue[1]):
                nextAction = action
                bestValue = newStateValue[1]

            #Check against the alpha value
            if(bestValue < alpha):
                return (nextAction, bestValue)
            #Set the beta value
            if(beta > bestValue):
                beta = bestValue

        #If there was no better state, just stop to evaluate
        if(bestValue == sys.maxint):
            return (Directions.STOP, self.evaluationFunction(gameState))
        else:
            return (nextAction, bestValue)

class ExpectimaxAgent(MultiAgentSearchAgent):
    """
      Your expectimax agent (question 4)
    """

    def getAction(self, gameState):
        """
          Returns the expectimax action using self.depth and self.evaluationFunction

          All ghosts should be modeled as choosing uniformly at random from their
          legal moves.
        """
        "*** YOUR CODE HERE ***"
        #util.raiseNotDefined()
        finalMoveList = self.maxValue(gameState, 0, 0)
        #print finalMove
        return finalMoveList[0]

    #Create a max-value function for the given states
    def maxValue(self,gameState,depth,agentIndex):

        #If we are at the end, stop
        if(self.depth == depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value associated with the next move
        bestValue = -1 * sys.maxint
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine the next state for all ghosts
            newStateValue = self.expectedValue(newState,depth,agentIndex+1)

            #print "newStateValue: ", newStateValue
            #If the new state is better, save it
            if(bestValue < newStateValue[1]):
                #print "New state value: ", newStateValue
                nextAction = action
                bestValue = newStateValue[1]

        #If there was no better state, just stop to evaluate
        if(bestValue == -1*sys.maxint):
            return (Directions.STOP, self.evaluationFunction(gameState))
        else:
            return (nextAction, bestValue)

    #Create a min-value function for the given states
    def expectedValue(self,gameState,depth,agentIndex):

        #If we are at the end, stop
        if(self.depth <= depth or gameState.isWin() or gameState.isLose()):
            #Returning a list because you need the direction and total cost
            return (Directions.STOP, self.evaluationFunction(gameState))

        #Get all of the possible actions from the current state
        legalActions = gameState.getLegalActions(agentIndex)

        #Initialize the value and action associated with the next move
        bestValue = sys.maxint
        totalScoresSum = 0.0
        for action in legalActions:
            #Find the value of all possible states
            newState = gameState.generateSuccessor(agentIndex, action)

            #Determine if you need to find the next ghost node or pacman
            nextAgentIndex = (agentIndex+1) % (gameState.getNumAgents())

            #If the index == 0, then it's pacman, else its a ghost
            if(nextAgentIndex == 0):
                #Increase the depth of the minimax tree
                newStateValue = self.maxValue(newState, depth+1, 0)
            else:
                #Maintain the same level of the minimax tree
                newStateValue = self.expectedValue(newState, depth, nextAgentIndex)

            #Update the score
            totalScoresSum += newStateValue[1]

            #If the new state is better, save it
            if(bestValue > newStateValue[1]):
                nextAction = action
                bestValue = newStateValue[1]

        #If there was no better state, just stop to evaluate
        if(bestValue == sys.maxint):
            return (Directions.STOP, totalScoresSum/float(len(legalActions)) )
        else:
            return (nextAction, totalScoresSum/float(len(legalActions)) )

def betterEvaluationFunction(currentGameState):
    """
      Your extreme ghost-hunting, pellet-nabbing, food-gobbling, unstoppable
      evaluation function (question 5).

      DESCRIPTION: <write something here so we know what you did>
    """
    "*** YOUR CODE HERE ***"
    #util.raiseNotDefined()
    #Create some linear combination of: ghosts, food, pellets
    #   Specifically distance to ghosts and food and pellets
    ghostLocations = currentGameState.getGhostPositions()
    pacLocation = currentGameState.getPacmanPosition()
    foodLocations = currentGameState.getFood().asList()

    #Start the basis of our return value
    finalNum = currentGameState.getScore()

    #Keep track of the closest ghost
    minGhostDist = sys.maxint
    for ghosts in ghostLocations:
        ghostDist = util.manhattanDistance(ghosts, pacLocation)
        #print "Ghost dist: ", ghostDist
        if(ghostDist < minGhostDist):
            minGhostDist = ghostDist

    #Run away from close ghosts
    if(minGhostDist == 1):
        return -1 * sys.maxint
    else:
        #Distance to ghosts make a difference
        if(minGhostDist == 0):
            finalNum += 1
        else:
            finalNum += 0.1/(minGhostDist)

    #Now explore the distance between pacman and the closestFood
    minFoodDist = sys.maxint
    for foodSpot in foodLocations:
        foodDist = util.manhattanDistance(foodSpot, pacLocation)
        if(foodDist < minFoodDist):
            minFoodDist = foodDist

    #distance to food makes a difference
    if(minFoodDist != sys.maxint):
        if(minFoodDist == 0):
            finalNum += 1
        else:
            finalNum += (.5/minFoodDist)
    else:
        finalNum += 10

    #Determine how much total food exists
    #Negative value because more food is bad
    totalFood = len(foodLocations)
    finalNum += -200*totalFood

    #Determine how many total power pellets exist |
    #Negative value because you should eat more pellets
    totalPower = len(currentGameState.getCapsules() )
    finalNum += -100*totalPower

    #Return Arbitrary linear combination
    return finalNum
# Abbreviation
better = betterEvaluationFunction
