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

println(triangularNum(100))
println(triangularNum(19999))
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
println(triangularNumRec(100))
println(triangularNumRec(19999))
```
Results:
```
5050
Exception in thread "main" java.lang.StackOverflowError
```
Well, in the recursive function, if a very big number passed in, exception stack overflow would be thrown out because memory used up.  

<b>Tail Recursion:</b>  
This is a tail recursion, because there is nothing needs to be maintained in the current stack layer and recursive call is the very last thing in the function. So the old stack space can be freed when new space allocated if tail recursion is optimized by the language.

```
def triangularNumTailRec(x: Long, result: Long): Long = x match{
  case 1 => result + 1
  case _ => triangularNumTailRec(x - 1, result + x)
}
println(triangularNumTailRec(100, 0))
println(triangularNumTailRec(19999, 0))
```
Results:
```
5150
200009999
```
No stack overflow! That means scala does have the feature TRO. If there is nothing needs maintained, stack memory would be freed.
####Example 2
Fibonacci: every number after the first two is the sum of the two preceding one.  
eg: Fibonacci sequence: 1, 1, 2, 3, 5, 8, 13, ..... fibonacci(3) = 2 fibonacci(6) = 8  
<b>For Loop</b>
```
def fibo(fib:Long): Long = {
  var num_1 = 0L
  var num_2 = 1L
  var result = 0L
  for(i <- 2L to fib){
    result = num_1 + num_2
    num_1 = num_2
    num_2 = result
  }
  return result 
}
println(fibo(6))
println(fibo(10000))
```
Results:
```
8
-2872092127636481573
```
The result for fib(10000) is not correct because the number is too large to show.  
<b>Recursion</b>
```
def fiboRec(fib: Long): Long = fib match {
  case 0 => 0
  case 1 => 1
  case _ => fiboRec(fib - 1) + fiboRec(fib - 2)
}
println(fiboRec(6))
println(fiboRec(10000))
```
Results:
```
8
Exception in thread "main" java.lang.StackOverflowError
```
As expected, the too much space in stack is allocated so stack overflow exception happens.  
<b>Tail Recursion</b>
```
def fiboTailRec(fib: Long, num_1: Long, num_2: Long): Long = fib match {
  case 0 => num_1
  case _ => fiboTailRec(fib - 1, num_2, num_1 + num_2)
}
println(fiboTailRec(6, 0, 1))
println(fiboTailRec(10000, 0, 1))
```
Result:
```
8
-2872092127636481573
```
Although the result is not correct, this function can be executed as planed without stackoverflow exception. As last example, the fact that no stack overflow in tail recursion indicated that scala supports TRO.

###CPS in Scala  
In my understanding, CPS is a way that pass mutable state through an expression that is a lamda expression with only one parameter. When the CPS function is completed it returns by calling continuation function with value as an argument in the continuation function. 
So in theory, the CPS function does not required much space in stack because space for old stack layer would be freed as soon as possible. But in order to reducing space allocated in stack, language has to support Tail Call Optimization. Let's see if scala has TCO feature.  
Example 3 and 4 are CPS way to write Triangular Number and FiboNacci function.

Example 3:  
<b>Triangular Number in CPS</b>
```
def triangularNumCPS(x: Long, cont: Long => Long): Long = x match{
  case 1 => cont(1)
  case _ => triangularNumCPS(x - 1, tmp => cont(x + tmp))
}
println(triangularNumCPS(100, x=> x))
println(triangularNumCPS(19999, x => x))
```
Result:
```
5050
Exception in thread "main" java.lang.StackOverflowError
```
Stack overflow happens, that means the TCO is not supported in Scala. So in the Fibonacci function in CPS, the same problem should happen. Let's see the result in example 4.

Example 4:  
<b>Fibonacci in CPS</b>
```
def fiboCPS(fib: Long, f: Long => Long): Long = fib match {
  case 1 => f(1)
  case 2 => f(1)
  case _ => fiboCPS(fib - 1, x1 => fiboCPS(fib - 2, x2 => f(x1 + x2)))
}
println(fiboCPS(8, x => x))
println(fiboCPS(10000, x => x))
```
Results:
```
34
Exception in thread "main" java.lang.StackOverflowError
```
Result is as expected, stack overflows again and the result indicates that TCO is not supported in Scala. Although it is possible to write CPS function in scala, it is not safe to use CPS function beucause actually space in stack is still used in this way.

###Tail Recursion Annotation
There is a easy way in scala to verify the recursion is safe or not: `@tailrec`. This annotation to test if the function is a tail recursion and throw compile error if it is not.  
Be attention, `@tailrec` only verify but would not help to transfer a recursion to tail recursion.
Example 5:
`import scala.annotation.tailrec`
import lib first
```
@tailrec
def triangularNumRec(x: Long): Long = x match{
  case 1 => 1
  case _ => triangularNumRec(x - 1) + x
}
```
When use the annotation to a non-tail-recursion function, there would be an error "could not optimize @tailrec annotated method trinagularNumRec: it contains a recursive call not in the tail position".

```
@tailrec
def triangularNumTailRec(x: Long, result: Long): Long = x match{
  case 1 => result + 1
  case _ => triangularNumTailRec(x - 1, result + x)
}
```
When used to annotated a tail-recursion function, there is no error. So the annotation is a siren to warn the danger but is not able to fix the bug.
