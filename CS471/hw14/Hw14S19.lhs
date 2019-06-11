> module Hw14S19
>    where

Due Wed. May 8 at midnight

In Lab problems a-c:

Problem a:
Consider the following definition:

> mm f g = (.) (map f) (map g)

What is the type of
   mm ((-) 20) ((+) 9) ?
   - The type is: Num a => [a] -> [a] since all you need to give to this function
        is a single list considering the functions are already defined.
What are the values of
   mm ((-) 20) ((+) 9) [3,-2, -9, 50] = [8,13,20,-39]
   mm ((-) 20) ((+) 9) [-3,2, -9, 50] = [14,9,20,-39]

Describe in word what mm does.
    - mm takes a list and 2 functions, f and g, and produces a list which contains the
        results of f(g(x)), where x is each element within the given list.

Define mm2 to be equivalent to mm but
only using only one 'map' application, and compose, (.).

> mm2 f g = map(f.g)

Problem b:
Consider the following definition:

> lc f p  = \lst -> [f x | x<-lst, p x]

What is the type of
   lc (\n -> n -1) odd ?
   - The type is: Integral b => [b] -> [b] because the two functions needed are already
        provided
What are the values of :
      lc (\n -> n -1) odd [2,33,50,11,23,4] = [32,10,22]
      lc (\n -> n -1) ((.) not odd) [2,33,50,11,23,4] = [1,49,3]

Define hf to be equivalent to lc but uses map and filter instead of list
comprehension.

> hf f p = map(f) . filter(p)

Problem c:
'prodPRL nums', defined below, produces a list of partial products
  of the numbers in nums. The first value in the resulting list
  will be the prod of all the items, the second will be the prod
  of all the items except the first, etc.  For example:

   ...> prodPRL [3,-2,10]
   [-60,-20,10]

> prodPRL (l:ls) = prods [l] ls
>   where
>    prods (a:as) (n:ns) = prods ((a*n):a:as) ns
>    prods as []  = as


Define prodPRLHOF to be equivalent to prodPRL but using a fold.
I have started the prodPRLHOF definition, you only need to
complete the definition for f.

 > prodPRLHOF :: (Num t1, Foldable t) => t t1 -> [t1]

> prodPRLHOF [] = []
> prodPRLHOF (x:xs) = [foldr (*) x (xs)] ++ prodPRLHOF xs


Remember foldr can be defined as follows:

    > foldr :: (a -> b -> b) -> b -> [a] -> b
    > foldr f accumulator [] = accumulator
    > foldr f accumulator (x:xs) = f x  (foldr1 f accumulator xs)


----------------------  HW ----------------------
Problem 1: Find operators (that can be partially applied), sec1 and sec2, so that
  map sec1 . filter sec2
has the same effect as
  filter (>0) . map (+1)


Problem 1 Answer:

> s4 = map (+1) . filter(> (-1))

Problem 2: Remember in the second Haskell assignment problem 6
you used primitive recursion to define a function composeList.
composeList took a list of functions and composed them into a single function.

   ... >composeList [ (-) 3 , (*) 2, (+)5 ] 7
       -21


One possible solution is

> composeList :: [(a -> a) -> a -> a] -> a -> a
> composeList [] = id
> composeList (h:t) = (.) h  composeList t

Define composeHOF to be equivalent to composeList but use a 'fold?'  You will
need to specify the type composeHof

> composeHOF :: Foldable t => t (b -> b) -> b -> b

Problem 2 Answer:

> composeHOF = (\list -> foldr1 (.) list)

Problem 3: The harmonic series is the following infinite series:
                            1   1   1   1               1
                      1 +   - + - + - + - + ...   + ... - ..
                            2   3   4   5               i
(http://en.wikipedia.org/wiki/Harmonic_series_(mathematics))
Write a function sumHarmonic such that sumHarmonic i is the sum of the first in
terms of this series. For example, sumHarmonic 4 ~> 1 + 1 + 1 + 1 ~> 2.08333...
                                                        2   3   4

In the last assignment you defined sumHarmonic using a simple recursive style.
One possible solution is

> sumHarmonic :: (Eq a, Fractional a) => a -> a
> sumHarmonic 1 = 1
> sumHarmonic n = 1 / n + sumHarmonic (n-1)


Rewrite the above definition using either foldr or foldl. ??? should be a lamdba
expression that accumulates the values
  sumH n = foldr ??? 0 [1..n]
or
  sumH n = foldl ??? 0 [1..n]

   ...> sumHarmonic 20
   3.59773965714368


Problem 3 Answer:

> sumH n = foldr (\x y -> y + 1/x) 0 [1..n]


Problem 4: In the last homework, problem 6, you wrote a function to create the
next thue value.  Below is one possible solution.

> thue (s:sx) = s : (mod (s+1)  2): thue sx
> thue [ ] = [ ]

Using the circular list idea demostrated in fibSeq
  (http://bingweb.binghamton.edu/%7Ehead/CS471/NOTES/HASKELL/4hF02.html)
Define thueSeq to be a sequence of thue values. You should use list
comprehension. You may use the 'thue' definition from your homework or the
solution provided above.  You may assume the sequence starts with "0".

One possible solution for

Problem 4 Answer:

> thueSeq = [0]:[0,1]:[x | x <- map thue (tail thueSeq)]

Problem 5: thueSeqMap is another definition of thueSeq using map instead of list
comprehension.


Problem 5 Answer:


Problem 6.
Using an  HOF (map, fold and/or filter ) define flattenT that returns a list of
value in each tuple.

   > flattenT :: Foldable t => t (a, a) -> [a]


 6a) The output should be in the same order as the values appear in the
     original list.
e.g.

   ...> flattenT  [(1,2), (3,4), (11,21),(-5,45)]
   [1,2,3,4,11,21,-5,45]

 6b) flattenR is the same as 7a, however, the values appear in the reverse order
   from the original list. Do not use the builtin reverse.

e.g.
   ...> flattenR  [(1,2), (3,4), (11,21),(-5,45)]
   [45,-5,21,11,4,3,2,1]

Problem 6 Answer:

    > flattenT :: Foldable t => t (a, a) -> [a]

> flattenT listT = foldl1 (++) (map (\ (x,y) -> [x,y] ) listT)

    > flattenR :: Foldable t => t (a, a) -> [a]

> flattenR list = foldl (\x y -> y:x) [] (flattenT list)



Problem 7: An inductive definition of a binary tree and a pretty print function
was provided in the last assignment (repeated below).

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


If 'Tree a' is a BST (binary search tree) then 'tree_search' returns True
if v (value) is a member of the "Tree a"

> tree_search :: Ord a => Tree a -> a -> Bool
> tree_search Nil v = False
> tree_search (Node e l r) v
>  | v == e    = True
>  | v < e     = tree_search l v
>  | v > e     = tree_search r v

'tree_insert', defined below, inserts a value into a BST and
maintances the BST invariant.  Below you see a new operator, "@".
Using "@", pronounced 'as', it is possible to label all or part of an argument.
So we can use 'nd' on line 226 (5 lines below) instead of 'Node e l r'.

> tree_insert :: Ord a => Tree a -> a -> Tree a
> tree_insert Nil v = (Node v Nil Nil)
> tree_insert nd@(Node e l r) v
>  | v == e    = nd
>  | v < e     = (Node e (tree_insert l v) r)
>  | v > e     = (Node e l (tree_insert r v))


Provided below are a few default binary search trees so you do not need to keep
entering them into ghci.

> tree1 = (Node 5 (Node 2 (Node 1 Nil Nil) (Node 3 Nil Nil)) (Node 7 Nil (Node 9 Nil Nil)))
> tree2 = (Node 5 (Node 2 (Node 1 Nil Nil) Nil) (Node 7 Nil (Node 9 Nil Nil)))

 > tree3 = (Node 5 (Node 2 (Node -1 (Node -3 Nil Nil) Nil) Nil) (Node 7 Nil (Node 9 Nil Nil)) )

Using our inductive definition of a Tree and define a function called
  'tree_fold f acc tree" that combines all the values of a tree inorder.
In reality, this is no different than performing an inorder traversal of the
tree, except that you are accumulating (combining) values along the way.

   Here is a rough sketch of the algorithm to help you get started:
    (0) tree_fold f acc Nil = acc (returns the combination thus far)
    (1) tree_fold f acc (Node v l r) =
       (a) Recurse on the left, getting a new accumulated value
       (b) Perform the accumulating operation (f) on the current value (v)
           and the returned value from (a)
       (c) Recurse on the right, passing the new accumulated value from (b)


Problem 7 Answer:

> tree_fold :: (b -> a -> a) -> a -> Tree b -> a
> tree_fold f acc Nil = acc
> tree_fold f acc (Node v l r) = tree_fold f acc2 l
>   where
>   acc2 = f v acc1
>   acc1 = tree_fold f acc r

Problem 8: To demonstrate the computational power of tree_fold, write a function
'inorder_list tree' that uses tree_fold to build a list of all the elements
of tree in order. Since the tree_fold recurses inorder, all you simply need to
think about is the lambda that should build the list

Problem 8 Answer:

> inorder_list :: Tree a -> [a]
> inorder_list tree = tree_fold (:) [] tree


Problem 9: Given two lists where all the elements are unique and are in
increasing order.   Define the function 'merge' that returns a list where
all the elements are unique and are in increasing order.

Problem 9 Answer:

> merge (x:xs) (y:ys)
>   | x > y = y : merge (x:xs) ys
>   | x < y = x : merge xs (y:ys)
>   | x == y = x : merge xs ys

> merge listA [] = listA
> merge [] listB = listB


Problem 10:  (Thompson 17.24/30 )
Define the list of numbers whose only prime factors are 2, 3, and 5, the
so-called Hamming numbers:

   ...> hamming
   1,2,3,4,5,6,8,9,10,12,15,16,18,20,24 ...


 You may consider using any combinition of the following techiques
       to express your solution  list comprehension notation,
       and/or explicit recursion, and/or local definitions .
       You may 'merge' defined in the last homework useful.

(Hint: Apply the circular list idea demostrated in fibSeq
  (http://bingweb.binghamton.edu/%7Ehead/CS471/NOTES/HASKELL/4hF02.html))

Problem 10 Answer:

> hamming = 1 : merge (map (2*) hamming) (merge (map (3*) hamming) (map (5*) hamming))
