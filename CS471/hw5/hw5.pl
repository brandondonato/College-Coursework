/* Problem 1 Answer: */
%Insert base cases
insert(X, T, node(X, T, _)).
insert(X, T, node(X, _, T)).
insert(X, node(X,L,T), node(X,L,T)).

insert(X, node(Y, L, _), node(Z, L2, _)) :- X<Y, Y = Z, insert(X, L, L2).
insert(X, node(Y, _, R), node(Z, _, R2)) :- X>Y, Y = Z, insert(X, R, R2).

/* Problem 1 Test: */
 :- insert(3,node(5,nil,nil),X), X = node(5,node(3,nil,nil),nil). %SUCCEED
 :- insert(7,node(5,nil,nil),X), X = node(5,nil,node(7,nil,nil)).  %SUCCEED
 :- insert(1,node(5,node(3,nil,nil),node(7,nil,nil)),X),
       X = node(5,node(3,node(1,nil,nil),nil),node(7,nil,nil)). %SUCCEED
 :- insert(1,node(5,node(3,node(2,nil,nil),nil),node(7,nil,nil)),X),
       X = node(5,node(3,node(2,node(1,nil,nil),nil),nil),node(7,nil,nil)). %SUCCEED

 :- (insert(3,node(5,node(3,node(2,nil,nil),nil),node(7,nil,nil)),X),
   X = node(5,node(3,node(2,node(3,nil,nil),nil)),node(7,nil,nil))) -> fail ; true.


/* Problem 2:
Using the same encoding for a binary search tree, write a predicate to_list(X,Y)
that succeeds if Y is an in-order list of the elements of all the nodes of tree X
(Y should show an in order traversal of X). If you are rusty and do not remember
what an in order traversal is, give
https://en.wikipedia.org/wiki/Tree_traversal#In-order a quick glance.

For example...
to_list(node(5,node(3,node(1,nil,nil),node(4,nil,nil)),node(7,nil,nil)),X) will
succeed with X = [1,3,4,5,7]. */

/* Problem 2 Answer:  */
to_list(nil, []).
to_list(node(X, L, R), RetList) :- to_list(L, LList), to_list(R, RList),
                                    append(LList, [X|RList], RetList).

/* Problem 2 Tests:  */
:- to_list(node(3,nil,nil),L), L = [3]. %SUCCEED
:- to_list(node(5,node(3,nil,nil),nil),L), L = [3,5].  %SUCCEED
:- to_list(node(5,node(3,node(1,nil,nil),node(4,nil,nil)),node(7,nil,nil)),L),
 L = [1,3,4,5,7]. %SUCCEED

:- (to_list(node(3,nil,nil),L), L = [5]) -> fail ; true.

/* Problem 3:
Write a predicate right_rotate(X,Y) that succeeds if Y is the tree X rotated right at
its root. Read https://en.wikipedia.org/wiki/Tree_rotation to refresh tree rotation in
full. This problem may seem hard at first, but once you "see" the answer it really
demonstrates the elegance of unification/pattern matching. You do not need to handle
error cases.

For example, the following shows a right rotation at the root.

        5                        3
       / \                      / \
      3   7         -->        2   5
     / \                          / \
    2   4                        4   7

*/

/* Problem 3 Answer: */
right_rotate(node(Val,node(Val2,L,R),R2), node(Val2,L,node(Val,R,R2))).


/* Problem 3 Test: */
:- right_rotate(node(5,node(3,node(2,nil,nil),node(4,nil,nil)),node(7,nil,nil)),X), X = node(3, node(2, nil, nil), node(5, node(4, nil, nil), node(7, nil, nil))). %SUCCEED
:- right_rotate(node(5,node(3,nil,node(4,nil,nil)),node(7,nil,nil)),X), X = node(3, nil, node(5, node(4, nil, nil), node(7, nil, nil))). %SUCCEED
:- right_rotate(node(3,node(2,node(1,nil,nil),nil),nil),X), right_rotate(X,Y), Y = node(1,nil,node(2,nil,node(3,nil,nil))). %SUCCEED

:- right_rotate(node(5,nil,node(7,nil,nil)),_) -> fail ; true. %FAIL

/* Problem 4:

 In the assignment 4, you wrote Prolog rules for symbolic differentiation.
 Below is my solutions for this problem.
 Keep in mind, though, that terms such as U+V are still trees with the functor
 at the root, and that evaluation of such terms requires additional processing
 which you will complete.

 Define the predicate, 'evaluate'/3, that uses the result from symbolic
 differentiation  and a list of items with the structure var:value (e.g. [a:5,x:6]
 and computes the resulting value. e.g.

    ?- d(3*(x +2*x*x),x,Result), VarValue = [x:2,y:5], evaluate(Result,Value,VarValue).
    Result = 3* (1+ (2*x*1+x*2))+ (x+2*x*x)*0,
    VarValue = [x:2, y:5],
    Value = 27


    ?- d((3*x) ^ 4,x,Result), VarValue = [x:2,y:5] , evaluate(Result,Value,VarValue).
    Result = 4* (3*x)^3*3,
    VarValue = [x:2, y:5],
    Value = 2592.
 */

/* Problem 4 Answer:  */
d(x,x,1).
d(C,x,0):-number(C).
d(C*x,x,C):-number(C).
d(-U, X, -DU) :- d(U, X, DU).
d( U + V, x, RU + RV ):-d(U,x,RU), d(V,x,RV).
d( U - V, x, RU - RV ):-d(U,x,RU), d(V,x,RV).
d(U * V,x, U * DV + V * DU):- d(U,x,DU), d(V,x,DV).
d(U ^ N, x, N*U ^ N1*DU) :- integer(N), N1 is N-1, d(U, x, DU).

evaluate(X,X,_) :- number(X).
evaluate(A,R,L) :- atom(A), member(A:R,L).
evaluate(U+V,R,L) :- evaluate(U,UR,L), evaluate(V,VR,L), R is UR+VR.
evaluate(U-V,R,L) :- evaluate(U,UR,L), evaluate(V,VR,L), R is UR-VR.
evaluate(U*V,R,L) :- evaluate(U,UR,L), evaluate(V,VR,L), R is UR*VR.
evaluate(U^N,R,L) :- evaluate(U,UR,L), number(N), R is UR**N.

/* Problem 4 Tests:  */
 :- evaluate(x*y, 6, [x:2, y:3]).
 :- evaluate(x*y, 8, [x:2, y:3]) -> fail ; true.
 :- evaluate(x^3, 8, [x:2]).
 :- evaluate(2*8, 16, []).
 :- evaluate(2*8, 0, []) -> fail ; true.
 :- evaluate(2*y, 16, [y:8]).

 :- d(3*(x +2*x*x),x,Result), VarValue = [x:2,y:5], evaluate(Result,Value,VarValue),
     Result = 3* (1+ (2*x*1+x*2))+ (x+2*x*x)*0,
     VarValue = [x:2, y:5],
     Value = 27.

 :- d((3*x) ^ 4,x,Result), VarValue = [x:2,y:5] , evaluate(Result,Value,VarValue),
     Result = 4* (3*x)^3*3,
     VarValue = [x:2, y:5],
     Value = 2592.

/* Problem 5:
We will encode a mini-AST in Prolog using complex data structures. A "node" will
consist of either a nb(Functor,LeftExpr,RightExpr), nu(Functor,Expr) or nn(Number).

nb(Functor,LeftExpr,RightExpr) -- "node binary", Functor is guaranteed to be a
binary arithmetic predicate that can be evaluated with `is`. LeftExpr and RightExpr
are recursively defined "nodes".

nu(Functor,Expr) -- "node unary", Functor is guaranteed to be a unary arithmetic
predicate that can be evaluated with `is`. Expr is a recursively defined "node".

nn(Number) -- "node number", Number is guaranteed to be just that.

Hence, the following AST
      +
     / \
    *   random
   / \       \
  2  3        5
would be encoded as nb(+,nb(*,nn(2),nn(3)),nu(random,nn(5))).

Write a predicate run(X,Y) that succeeds if Y is the result obtained from "running"
(evaluating) X. You will need to use the =.. predicate. It may be helped to view
some of the binary and unary arithmetic predicates --
http://www.swi-prolog.org/man/Ardith.html. If you write your predicate correctly,
you do not need to concern yourself with the actual arithmetic functor supplied
in the nodes. */

/* Problem 5 Answer: */
run(nn(X), Y) :- Y = X.
run(nu(F,E), Y) :- run(E, E2), Temp =..[F,E2], Y is Temp.
run(nb(F,LE,RE), Y) :- run(LE, LE2), run(RE, RE2), Temp=..[F,LE2,RE2], Y is Temp.

/* Problem 5 Tests: */
:- run(nb(+,nb(*,nn(2),nn(3)),nu(random,nn(5))),_).
:- run(nb(+,nb(*,nn(2),nn(3)),nn(3)),E), E=9.
:- run(nb(+,nb(*,nn(2),nn(3)),nb(-,nn(6),nn(3))),E), E=9.
:- run(nn(2),E), E=2.
:- run(nu(abs,nn(-2)),E), E=2.

:- (run(nb(+,nb(*,nn(2),nn(3)),nb(-,nn(6),nn(3))),E), E=8) -> fail ; true.

/* Problem 6:
Using the AST described in problem 5, write a predicate binaryAP/2.
binaryAP(AST, BPlst) succeeds if all the binary arithmetic predicates that occur
in AST are collected into BPlst.  Use an in order traversal of AST.  */

/* Problem 6 Answer: */
binaryAP(nn(N), []) :- number(N).
binaryAP(nu(_, E), L) :- binaryAP(E,L).
binaryAP(nb(F,LE,RE), L) :- binaryAP(LE, R1), binaryAP(RE, R2), append(R1,[F],Temp),
                            append(Temp,R2,L).

/* Problem 6 Tests: */
:- T = nb(+,nb(*,nn(2),nn(3)),nu(random,nn(5))), binaryAP(T,L), L = [*, +].  %SUCCEED
:- T = nb(+, nb(*, nn(2), nn(3)), nb(-,nn(3), nn(5))),  binaryAP(T,L), L = [*, +, -]. %SUCCEED
:- T = nb(+, nb(*, nn(2),  nb(-,nn(3), nb(//, nn(2), nn(5)))),nn(9)) ,  binaryAP(T,L), L = [*, -, //, +]. %SUCCEED

:- (T = nb(+,nb(*,nn(2),nn(3)),nu(random,nn(5))), binaryAP(T,L), L = [+,*]) -> fail ; true.

/* Problem 7:
   Write a predicate unNest/2.  unNest(+Nested, -LstAtoms), where Nested is a list of
   lists of atoms and LstAtoms is a list of atoms (i.e. no items in LstAtoms is a list),
   succeeds if all the atoms from Nested are in LstAtoms and in the same order.
   For example, unNest([a,[b,[c]],d],U) succeeds if U = [a, b, c, d] .


   Think What NOT how. This can be done with only 3 clauses */

/* Problem 7 Answer: */
unNest([],[]).
unNest(X, [X]) :- atom(X).
unNest([H|T], L) :- unNest(H,Temp), unNest(T,Temp2), append(Temp,Temp2,R), R = L.

/* Problem 7 Tests: */

:- unNest([[r,s,[x,y]],[a],[],[s,t]],One), One = [r, s, x, y, a, s, t]. %succeeds
:- unNest([[r,s,[x,y]],[a],[],[s,t]],One), One = [r, s, x, y, a, t] -> fail ; true.
:- unNest([[[r]],[]],[r]). %succeeds

/* Problem 8:

In class we discussed difference lists and how to append two of them in "constant"
time.

Write a predicate, append3DL(A,B,C,D) that succeeds if D is the difference lists
A, B, and C appended.
*/

/* Problem 8 Answer: */
append3DL(A-B, B-C, C-D, A-D).

/* Problem 8 Tests: */
:- append3DL([1,2|A]-A,[3,4|B]-B,[5,6|[]]-[],L), L = [1,2,3,4,5,6]-[]. % SUCCEED
:- append3DL([a,b|A]-A,[b,1,2|B]-B,[3|C]-C,L), L = [a, b, b, 1, 2, 3|C]-C. % SUCCEED


:- (append3DL([1,2|A]-A,[3,4|B]-B,[5,6|[]]-[],L), L = [1,2,3,4,5]-[]) -> fail ; true.   % FAIL

/* Problem 9:
In class we discussed green and red cuts (!). A green cut is a cut that DOES
NOT change correctness (the answer returned) but simply improves efficiency
by preventing unnecessary backtracking. Red cuts change correctness - if a
predicate is correct and contains a cut that, when removed, is no longer
correct, it is a red cut.

Insert cuts into the following 2 predicates. The my_max/3 are already correct,
but using a cut (green) will improve their efficiency.
The last, my_max1/3 is wrong, but inserting a cut (red) will
make it correct.
*/

/* Problem 9 Answer */


my_max(X,Y,Y) :- X =< Y, !.
my_max(X,Y,X) :- X > Y.

my_max1(X,Y,Z) :- X =< Y, !, Y = Z.
my_max1(X,_,X).

/* Problem 9 Test */
% You're own your own for this one :) */
%:- my_max1(3,4,3). %SUCCEED

/* Problem 10:

Write a predicate change/2 that given the change amount, computes the way in which
exact change can be given. Use the following USA's coin facts at your solution. */

coin(dollar, 100).
coin(half, 50).
coin(quarter, 25).
coin(dime,10).
coin(nickel,5).
coin(penny,1).

/* The predicate change(S,CL) succeeds if given a positive integer S, CL is a list
 of tuples that contains the name of the coin and the number of coins needed to
 return the correct change.

The Coin Changing problem is an interesting problem usually studied in Algorithms.
http://condor.depaul.edu/~rjohnson/algorithm/coins.pdf is a nice discussion.
Think about (no need to turn in)
1) How could we generalize this problem to handle coins from other currencies?
2) What are the different techniques to find the change with the fewest number of coins ?
3) What happens if the order of the "coin" facts change?
*/

/* Problem 10 Answer: */
change(0, []).
change(S, List) :- coin(Y,Z), Z =< S, N is div(S, Z), NewS is mod(S, Z), change(NewS, Var),
                    List = [(Y,N)|Var].

/* Problem 10 Tests: */
:- change(168,C), C = [ (dollar, 1), (half, 1), (dime, 1), (nickel, 1), (penny, 3)] .  %SUCCEED
:- change(75,C),  C = [ (half, 1), (quarter, 1)] .                                     %SUCCEED

:- (change(75,C), C = [(half, 2)]) -> fail ; true.             %FAIL
