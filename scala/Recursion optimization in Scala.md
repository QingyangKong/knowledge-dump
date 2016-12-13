#Recursion Optimization in Scala
###Why recursion popular in FP?
As an important feature, recursion is widely used in the functional programming. I think the reason is there is no mutable state in FP and
recursion allows for the construction of code that doesn’t require setting and maintaining state with local variables.
For example, it is impossible to maintain a mutable state like a counter for a loop in FP but it is allowed to pass values through parameter without storing state in member varialbe.  
Another feature of recursion is that side-effect can be avoided easily by writing recursive function. (That does not mean side-effect is a bad thing, but in some scenario we might want to avoid it especially in concurrent computing.) Why easier? Because by using recursion to return a consistent value based on given inputs, no state is needed to recurse except for read-only parameter and write-only return value. In this way no external variable would be changed. 

###Dangers of Recursion: Stack Overflow
However, recursive solution for problem is not always safe. When a function is called, a size of memory in stack needs to be allocated. If the function is called too many times, memory in stack will be used up and at this point of time, the most famous exception "stackoverflow" will happen.
###Deal with Exception Stack Overflow
There are 2 ways solving the problem "stackoverflow": one is to write a recursion as a tail recursion and the other is to write function in continuation-passing-style(CPS). In order to take advantage of tail recursion and CPS, the language has to support tail recursion optimization(TRO) and tail call optimization(TCO). As TRO is included in TCO, TRO is supported if the language supports TCO. For functional programming language, TCO is supported but for most of procedural programming language, there is only TRO but no TCO.  

###Tail Recurion in Scala
Scala is a combination of functional programming and procedural programming, so I am wondering if Scala does recursion optimization for recursion. In examples below I am going to write imperative way, recursion and tail recursion for Triangular Number and Fibonacci Number to see if Scala supports TRO. 
####Example 1:
Triangular Number: any of the series of numbers (1, 3, 6, 10, 15, etc.) obtained by continued summation of the natural numbers 1, 2, 3, 4, 5, etc. Eg: triangularNum(3) = 1 + 2 + 3 = 6; triangularNum(4) = 1 + 2 + 3 + 4 = 10.  
<b>For-loop:</b>

```
def triangularNum(x: Long): Long = {
  var result = 0L
  for(i <- 1L to x){
    result += i
  }
  result
}

triangularNum(100)
triangularNum(19999)
```
Results:
```
5050
199990000
```
<b>Recursion:</b>  
Obviously, the function is not a Tail Recursion because `x` needs to be maintained in current stack layer and recursive call is not very last thing in the function. So it is possible to throw the exception stackoverflow.
```
def triangularNumRec(x: Long): Long = x match{
  case 1 => 1
  case _ => triangularNumRec(x - 1) + x
}
triangularNumRec(100)
triangularNumRec(19999)
```
Results:
```
5050
Exception in thread "main" java.lang.StackOverflowError
```
Well, in the recursive function, if a very big number passed in, exception stack overflow would be thrown out because memory used up.  

<b>Tail Recustion:</b>  
This is a tail recursion, because there is nothing needs to be maintained in the current stack layer and recursive call is the very last thing in the function. So the old stack space can be freed when new space allocated if tail recursion is optimized by the language.

```
def triangularNumTailRec(x: Long, result: Long): Long = x match{
  case 1 => result + 1
  case _ => triangularNumTailRec(x - 1, result + x)
}
triangularNumTailRec(100, 0)
triangularNumTailRec(19999, 0)
```
Results:
```
5150
200009999
```
No stack overflow! That means scala does have the feature TRO. If there is nothing needs maintained, stack memory would be freed.
####Example 2
Fibonacci: every number after the first two is the sum of the two preceding one.
