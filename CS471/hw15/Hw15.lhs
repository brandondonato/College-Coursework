> module Hw15
>   where

> hLen :: (Num u, Eq t) => ([t] -> u) -> [t] -> u
> hLen = (\f x -> if x == [] then 0 else 1 + (f (tail x)))
> myLength ls = if ls == [] then 0 else 1 + myLength (tail ls)

> factorial :: Integral a => a -> a
> factorial n = if n ==0 then 1 else n * (factorial (n - 1))

> hFact = (\f x -> if x == 0 then 1 else x * (f (x-1)))

> factorial' = hFact factorial'

> fix f = f (fix f)

Brandon Donato
CS 471 Homework 15

PART A:

1)
	The type of (\x  -> x) is: p  -> p

2)
	a. (\x -> x) 3 = 3
	b. (\x -> x) (+) 5 3 = 8
	c. (\x -> x) (+) = Error
	d. A descriptive name for the abstraction in 2.b would be: Given any value, return the input value
3)
	a. (\x -> x+x) 3 = 6
	b. Here, E could be noted as x+x

4)
	a. (\x -> 3) 6 = 3
	b. A descriptive name for the abstraction would be: Given any value, return the number 3

PART B:

1)
	a. hLen is not recursive by definition because it does not call itself in any capacity
	b. hLen is a higher order function because it takes a function as input and returns a function as output
	c. hLen sum [4,5,6] = 12
	d. hLen head [4,5,6] = 6

	hLen removes the first item from the list, applied the given function to the tail of the input list, and then adds 1 to the answer.

2)
	hLen myLength [4,5,6] = 3

3)


PART C:

1)
	The type of hFact is: (Integer -> Integer) -> Integer -> Integer

2)
	The value of hFact (^2) 4 is: 4 * (3^2) = 36

3)
	The value of hFact factorial 5 = 120, which is the same value as factorial 5

PART D:

1)
	The type of factorial' is: Integer -> Integer

2)
	a. The type of fix is: (t -> t) -> t
	b. The difference is that the code below will work in left associative order, thus doing: ((f fix) f), which will return the wrong result.

3)
	The answer for all of the questions is 720.
