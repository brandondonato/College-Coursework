Brandon Donato

> module Hw12
>     where

-------------------------------------------------------------------------------------
LAB PART 1:

> prodT (a,b,c) = a * b * c
> prodC a b c = a * b * c
> prodCx :: Num a => a -> (a -> (a -> a))
> prodCx a b c = a * b * c

> prodC1 = prodC 1
> prodC2 = prodC1 2
> prodC3 = prodC2 3
> prodC12 = prodC 1 2

> data Point a = Pt a a deriving (Show, Eq)

    A) Based on the function as its written, the inferred type of prodT is a
        function that takes in a tuple of 3 Nums, and returning a single Num

    B) The inferred type of prodC is a fucntion that takes a Num, returns a Num
        after performing the multiplication, doing that 3 times, and then returns
        the final Num. It does the multiplication of each variable from left to
        right, as opposed to all at once.

    C) According to Haskell, the type inference of prodCx and prodC are exactly
        the same.

    D) I believe this is legal since the language will recursively call functions.

    E) You cannot write the same sort of recursive function calls since prodT needs
        to recieve all of the arguments all at once. This works for prodC because
        prodC just needs 3 Nums, not necessarily a tuple with 3 Nums.

    F) Function applications are left associative. (F G H == ( (F G) H) )

LAB PART 2:

    A) If h = (f o g), then the result of h(x) = 3 - ( (x-1 * 2) * 5).
    B) h2(x) = (g o f) = ( (3 - (x*5)) - 1) * 2
    C) h(2) = 3 - ( ((2)-1 * 2) * 5) = -7
    D) h2(2) = ( (3 - ((2)*5)) - 1) * 2 = -16

> f(x) = 3 - (x * 5)
> g(x) = (x - 1) * 2
> h(x) = (f.g)
> h2(x) = (g.f)

LAB PART 3:

    A)
        1: Num a => a -> a -> a
        Function that takes two Num arguments and returns a single Num

        2: Num a => a -> (a -> a)
        Function that takes a single Num argument, returning a function which takes
        a single Num argument, returning a single Num

        3: Eq a => a -> a -> Bool
        Function that takes two Eq arguments and returns a single Bool

        4: (a -> b) -> [a] -> [b]
        A polymorphic function that takes a function which takes a single value, returning
        another value, and takes a list, and then returns a list

        5: (a -> b -> a) -> a -> [b] -> a
        A polymorphic function which takes: a function which takes 2 arguments, and returns
        an value of the same type as argument one, a value, a list and then returns a value

        6: (a -> a) -> a -> a
        A polymorphic function which takes: a function which takes 1 argument and returns a
        value of the same type, and another value of the same type, returning a value of the same
        type

        7: (b -> c) -> (a -> b) -> a -> c
        A polymorphic function which takes: a function which takes 1 argument of type a, returning
        a argument of type c, a function which takes 1 argument of type a and returning a value of
        type b, a value of type a and then returns a value of type c

        8: (a -> Bool) -> [a] -> [a]
        A polymorphic function which takes: a function which takes 1 argument of type a, returning
        a Bool, a list of type a and returns a list of type a

    B)

        (1) (+) 1
        Guess: Num a => (a)->a->a
        Actual: Num a => a->a

        (2) (+) 1 2
        Guess: Num a
        Actual: Num a => a

        (3) (.) (\x -> x * 2)
        Guess: Eq a => (a->b) -> a
        Actual: (.) (\x -> x * 2) :: Num c => (a -> c) -> a -> c

        (4) (.) (\x -> x * 2) abs
        Guess: (.) (\x -> x * 2) abs :: Num c => (a->c)->a
        Actual: (.) (\x -> x * 2) abs :: Num c => c -> c

        (5) (.) (+)
        Guess: (.) (+) :: Num a => (a -> b) -> a -> a
        Actual: (.) (+) :: Num a1 => (a2 -> a1) -> a2 -> a1 -> a1

        (6) (.) (+) (\m -> 10 * m)
        Guess: (.) (+) (\m -> 10 * m) :: Num a => (a -> a) -> a
        Actual: (.) (+) (\m -> 10 * m) :: Num a => a -> a -> a

        (7) (.) (+) (\m -> 10 * m) 3
        Guess:  (.) (+) (\m -> 10 * m) 3 :: Num a => a -> a
        Actual: (.) (+) (\m -> 10 * m) 3 :: Num a => a -> a

    C)

        What is the data type name: Point
        What is the constructor name: Pt
        What happens if you do not use deriving in the type declaration when you try to find
        if two points are equal: You would not be able to find equality and you would not be able
        to print the Point type
-------------------------------------------------------------------------------------------
        
Rename this file to "Hw12.lhs".
Add your code to this file in the positions indicated with "Problem N Answer:".
You will need to remove the spaces before ">" for the types to become part of
your code.

The names of the functions SHOULD NOT BE CHANGED. I will be grading these with
a script - if your code doesn't define the functions with the exact names
specified, they will be assumed skipped.

Once you've finished editing this file, submit it in a tar.gz directory
as usual.


Problem 1:

Write a function "prodAll" that takes a list of numbers and returns their product.
This must be a recursive solution.
Example:
...> prodALL [2,7,4]
56

Problem 1 Answer:

> prodALL :: Num a => [a] -> a
> prodALL [a] = a
> prodALL (a:b) = a * prodALL(b)



Problem 2:

Write a function "binOpALL op lst" that takes an arbitrary binary operator and
a list of numbers and returns the result of applying the 'op' to the values in
list. To make things easy use the prefix notation for the operators,
e.g. (*) 3 2 evaluates to 6.
Example:
...> binOpALL (+) [2,7,4]
13

Problem 2 Answer:

> binOpALL :: Num a => (a -> a -> a) -> [a] -> a
> binOpALL op [a] = a
> binOpALL op (a:b) = op a (binOpALL op b)

Problem 3:  Write a function prodALLC that behaves like prodALL (problem 1)
            Your definition must use a partial evaluation of binOpALL .


Example:
...> prodALLC [2,7,4]
56

Problem 3 Answer:

> prodALLC [a] = a
> prodALLC (a:b) = a * (prodALL b)

Problem 4:
Write a recursive function "insertAt v p lst" that inserts a value v at
position p in list lst. If p is greater than the length of lst, simply
insert it at the end.
Example:
...> insertAt 12 1 [1,2,3]
[1,12,2,3]
...> insertAt 12 10 [1,2,3]
[1,2,3,12]

Remember, Haskell is "pure", so you can not modify lst. Instead, you should
recurse and build a new list from lst with v inserted.

Problem 4 Answer:

> insertAt :: (Eq a, Num a) => b -> a -> [b] -> [b]
> insertAt val i [] = [val]
> insertAt val 0 (a:b) = [val] ++ [a] ++ b
> insertAt val i (a:b) = [a] ++ insertAt val (i-1) b


Problem 5. Thompson 10.3
Define a function composeList which composes a list of unary functions into a
single function. You may assume the list of functions is not empty.
Define composeList using primitive recursion.
Example:
...> composeList [ (*) 2, (*) 2] 2
8
...> composeList [ (-) 3 , (*) 2, (+)5 ] 7
-21
Notice how in the above example, the output of composeList [ (-) 3, (*) 2, (+) 5]
is the function f(x) = (3 - (2 * (5 + x))).


Problem 5 Answer:

> composeList [a] = a
> composeList (a:b) = a . composeList b

Problem 6:
In homework 1 you were introduced to the Ackermann Numbers.
The definition we used in the assignment is:

	ack( m,n ) =	n + 1			    if m = 0
	ack( m,n ) =	ack(m - 1, 1)		    if n = 0 and m > 0
	ack( m,n ) =	ack( m-1, ack( m, n-1 ) )   if n >0 and m > 0

Define the ack function in Haskell.
Example:
...> ack 0 3
4
...> ack 3 2
29

Problem 6 Answer:

> ack m n
>   | (m == 0)          =   n+1
>   | (n == 0 && m > 0) =   ack (m-1) 1
>   | (n > 0 && m > 0)  =   ack (m-1) (ack m (n-1))

Problem 7:
Implement Newton's method for calculating the square root of N.
Your definition should use primitive recursive style.
See (http://bingweb.binghamton.edu/~head/CS471/HW/Haskell2F18.html) webpage for
a definition of Newton method for the approximation of roots.
Your definition should include a user defined (input) "guess" value and a user
defined "nearEnough" value.
"nearEnough" is use to determine when the guess is close enough to the square root.
You should use locally defined helper functions to make your code more readable.

Example:
...> newtonAppr 144 1 0.1
12.000545730742438
...> newtonAppr 144 1 0.0001
12.0000000124087
...> newtonAppr 144 1 0.000000000000001
12.0
...> newtonAppr 5e+30 1 1000000000000000000000000000000
2.317148867384728e15
...> newtonAppr 5e+30 1 100000000000000000000000000
2.2360684271923805e15

Problem 7 Answer:
Note: again, you may have a slightly different type depending on your
solution.

> newtonAppr :: (Fractional a, Ord a) => a -> a -> a -> a
> newtonAppr value guess nearEnough =
>  if ( (guess * guess) >= value - nearEnough  && (guess * guess) <= value + nearEnough)
>  then guess
>  else newtonAppr value ( ((value/guess) + guess) /2 ) nearEnough
