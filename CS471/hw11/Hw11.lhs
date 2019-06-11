> module Hw11
> where

Define factorial. Let Haskell infer the type of factorial.

> factorial n = if n == 0 then 1 else n * factorial(n-1)
> fact1 :: Int -> Int
> fact1 n = if n == 0 then 1 else n * fact1(n-1)
> fact2 :: Integer -> Integer
> fact2 n = if n == 0 then 1 else n * fact2(n-1)
> factP :: Integer -> Integer
> factP 0 = 1
> factP n = n * factP(n-1)
> factG x
>   | x < 0     = error "neg x"
>   | x == 0    = 1
>   | otherwise = x * factG(x-1)
> factG2 :: Integer -> Integer
> factG2 n
>   | n < 0     = error "neg n"
>   | n == 0    = 1
>   | otherwise = n * factG2 (n-1)
> factE :: Integer -> Integer
> factE n
>   | n < 0     = error "neg n"
>   | n == 0    = 1
>   | otherwise = n * factE n-1

Question 4:

When running fact1 500, the prompt returns 0. I believe this is because
of the type fact1 is using when performing computations (Int vs. Integer). Integers
have arbitrary precision, meaning it will hold any number, no matter how large. However,
Ints are limited by their max size (32 or 64 bits).

Question 5:

The command "factorial (-2)" gets the response "*** Exception: stack
overflow," which indicates that the program ran, but used too much memory before
getting an answer. This is due to the fact -2 will never reach the base case, and will
continuously decrease until overflow.

The command "factorial -2" gets the response "<interactive>:47:1: error:
    • Non type-variable argument in the constraint: Num (p -> p)
      (Use FlexibleContexts to permit this)
    • When checking the inferred type
        it :: forall p. (Eq p, Num p, Num (p -> p)) => p -> p"
because giving -2 to the program does not match the type Num, so the program does not run.

Question 8:

The command "factorial 5.1" gives "*** Exception: stack overflow" because it will never
reach the base case of n == 0

The command "factG 5.1" gives the following: "*** Exception: neg x" because the program
will subtract 1, eventually getting to -.9, which is a negative number, thus throwing
the error.

The command "factG2 5.1" gives "<interactive>:62:1: error:
    • Variable not in scope: factG2 :: Double -> t
    • Perhaps you meant one of these:
        ‘fact2’ (line 10), ‘factG’ (line 14), ‘fact1’ (line 8)"
because it recognizes that the number given to the program does not match the Integer
type.

The command "factG2 5" gives 120, which means it operates as intended
The command "factE 5" gives "<interactive>:66:1: error:
    • Variable not in scope: factE :: Integer -> t
    • Perhaps you meant one of these:
        ‘fact1’ (line 8), ‘fact2’ (line 10), ‘factP’ (line 12)"
because the 3rd clause (line 27) does not evaluate "n-1" before performing the left
associativity native to Haskell. 
