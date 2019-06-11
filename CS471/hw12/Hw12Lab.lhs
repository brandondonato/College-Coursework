> module Hw12Lab
> where

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
