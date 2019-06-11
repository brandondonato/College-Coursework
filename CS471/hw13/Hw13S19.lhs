> module Hw13S19
>     where

Problem 1: Define a data type Point that represents a point in a plane and the
coordinate values should be polymorphic. The constructor for Point should be Pt.
You should include "deriving (Show, Eq)" in your data type definition.   What is
the purpose of  including "deriving"?

Answer 1:

> data Point a = Pt a a deriving (Show, Eq)

The purpose of including "deriving" is to ensure the data type Point is able to
print its values to the screen, and so that the Points can be compared for equality.


Problem 2: Write a Haskell function "inside point r " which returns true
if and only if the point lies inside a circle of radius r and false otherwise.

 "inside" can be defined as:
     inside r point (x, y) is true  if and only if x^2 + y^2 < r^2
  where x and y are the coordinates of the point. For example:

   ....> inside 10.0 (Pt 3.0 2.5)
   True
What type is inferred of inside?

Answer 2:

> inside :: (Ord a, Num a) => a -> Point a -> Bool
> inside num (Pt a b)
>   | a*a + b*b < num*num = True
>   | otherwise = False


Problem 3.
This problem are related to tail recursion.  power is a function which raises a
number n  to the power p. Here is a very simple implementation.

> power :: Integer -> Integer -> Integer
> power n 0 = 1
> power n p = n * power n (p-1)

a) Show the steps in the evaluation of the expression power 2 5,  making sure
   not to reduce any subexpression prematurely. How do the time and maximum
   space required by the evaluation of power n k depend on n and p?  What is
   the time complexity of power?

b) Because of lazy evaluation, the definition of power accumulates of pending
   multiplications. It is inherent in the way power is defined each
   multiplication has to wait until its right argument has been evaluated.
   One way to eliminate the multiplication delay is to convert the function's
   definition to a tail-recursive implementation:

> powerT :: Integer -> Integer -> Integer
> powerT n p = trp p 1
>    where
>    trp p' acc = if (p' == 0) then acc else trp (p'-1) (n*acc)

  Show the steps in the evaluation of the expression powerT 2 5, making sure not
  to reduce any subexpression prematurely. What is the time complexity of powerT?

c) Here is an algorithm that improves time and space

> turboPower a 0 = 1
> turboPower a b
>     | even b = turboPower (a*a) (b `div` 2)
>     | otherwise = a * turboPower a (b-1)

   Instead of merely decrementing its first argument, this algorithm halves it
   whenever it is even, thereby reaching termination much more quickly. What is
   the complexity of this algorithm?

d) Convert turboPower to a tail recursive version -- call you new version
   turboPowerT.   You can follow the pattern used in part b. What is the
   complexity of this algorithm?


Answer 3:

    a)
    power 2 5 -> n * (power 2 (4))
    n * (power 2 (4)) -> n * (n * (power 2 (3)))
    n * (n * (power 2 (3))) -> n * (n * (n * (power 2 (2))))
    n * (n * (n * (power 2 (2)))) -> n * (n* (n * (n * (power 2 (1)))))
    n * (n * (n * (n * (power 2 (1))))) -> n * (n * (n * (n * (n * (power 2 0)))))
    n * (n * (n * (n * (n * (power 2 0))))) -> n * (n * (n * (n * (n * (1)))))

    where n = 2.


    The time and space complexity is O(n * p).

    b)
    powerT 2 5 -> trp 5 1
    trp 5 1 -> trp 4 2
    trp 4 2 -> trp 3 4
    trp 3 4 -> trp 2 8
    trp 2 8 -> trp 1 16
    trp 1 16 -> trp 0 32
    trp 0 32 -> 32

    Here, the time complexity becomes O(p).

    c)
    The time complexity becomes O(log(b)).

    d)

> turboPowerT n p = trp n p 1
>   where
>   trp n p' acc = if(p' == 0) then acc else if(even p') then trp (n*n) (p' `div` 2) (acc) else trp (n) (p'-1) (acc*n)

    This new time complexity becomes O(log(p)).

Problem 4:

The Tree data type defined below is used to build a binary tree.
tree1, tree2 and tree3 are examples of binary tree.


> data Tree a =  Nil
>              | Node a (Tree a) (Tree a) deriving Eq


> instance Show a => Show (Tree a) where
>  show t = show' t 0
>    where
>      show' Nil ind = replicate ind ' ' ++ "Nil"
>      show' (Node v l r) ind =
>        replicate ind ' ' ++ "(Node " ++ show v ++ "\n" ++
>        show' l (ind+1) ++ "\n" ++
>        show' r (ind+1) ++ "\n" ++
>        replicate ind ' ' ++ ")"

What are Tree, Nil and Node?

I have also provided a few default binary trees so you do not need to
keep entering them into ghci. You may simply type tree1 at the prompt and you
will get a pretty print of tree1. Note these 3 examples are in fact binary
search trees.

> tree1 = Node 5 (Node 10 Nil (Node 12 Nil Nil) ) Nil
> tree2 = (Node 5 (Node 2 (Node 1 Nil Nil) (Node 3 Nil Nil)) (Node 7 Nil (Node 9 Nil Nil)))
> tree3 = (Node 5 (Node 2 (Node 1 Nil Nil) Nil) (Node 7 Nil (Node 9 Nil Nil)))


Define "flatten t" which converts a tree to a list.  You should do an
inorder traversal of the tree. You may use "++" operator.
What are your assumptions?  Can you predict the most general type of flatten?

    ...> flatten tree1
    [10,12,5]

Answer 4:

    - Tree is the name of the Class, and Nil and Node are both constructors for the Tree class
    - Here I assume that flatten will evaluate the Tree in-order traversal.

> flatten :: Tree t -> [t]
> flatten Nil = []
> flatten (Node val leftT rightT) = flatten leftT ++ [val] ++ flatten rightT


Problem 5:
The harmonic series is the following infinite series:
                1   1   1   1             1
            1 + - + - + - + - + ... + ... - ...
                2   3   4   5             i
(http://en.wikipedia.org/wiki/Harmonic_series_(mathematics))

Write a function sumHarmonic such that sumHarmonic i is the sum of the first in
terms of this series. For example, sumHarmonic 4 ~> 1 + 1 + 1 + 1 ~> 2.08333...
                                                        2   3   4
Your answer should be in a simple recursive style.

Example:
...> sumHarmonic 4
2.08333...


Answer 5:

> sumHarmonic :: (Eq a, Fractional a) => a -> a
> sumHarmonic 1 = 1
> sumHarmonic i = (1/i) + sumHarmonic (i-1)


Problem 6: (from http://en.wikipedia.org/wiki/Thue%E2%80%93Morse_sequence )
"In mathematics, the Thue-Morse sequence, or Prouhet-Thue-Morse sequence, is a
binary sequence that begins:

  0 01 0110 01101001 0110100110010110 01101001100101101001011001101001 ...

(or if the sequence started with 1...)
    1 10 1001 10010110 1001011001101001 ...

"Characterization using bitwise negation

The Thue-Morse sequence in the form given above, as a sequence of bits,
can be defined recursively using the operation of bitwise negation. So, the
first element is 0. Then once the first 2n elements have been specified,
forming a string s, then the next 2n elements must form the bitwise negation of
s. Now we have defined the first 2n+1 elements, and we recurse.

Spelling out the first few steps in detail:

    * We start with 0.
    * The bitwise negation of 0 is 1.
    * Combining these, the first 2 elements are 01.
    * The bitwise negation of 01 is 10.
    * Combining these, the first 4 elements are 0110.
    * The bitwise negation of 0110 is 1001.
    * Combining these, the first 8 elements are 01101001.
    * And so on.
So the sequence is
    * T0 = 0
    * T1 = 01
    * T2 = 0110
	...

Define a primitive recursive function 'thue' given the nth thue element returns
the next thue element.  The elements will be represented as a list of 0s and 1s.
Example:
...> thue [0,1,1,0]
[0,1,1,0,1,0,0,1]


Problem 6 Answer:

> thue list = list ++ thue2 list
> thue2 [] = []
> thue2 (1:b) = (0:thue2 b)
> thue2 (0:b) = (1:thue2 b)



Problem 7:
Define a function replicate' which given a list of numbers returns a
list with each number duplicated its value. The ' is not a typo - this is to
avoid the existing replicate.
Use primitive recursion in your definition.
You may use a nested helper definition.
Example:
...> replicate' [2, 4, 1]
[2,2,4,4,4,4,1]

Problem 7 Answer:

> replicate' list = [x | x <-list, reps <- [1..x]]
