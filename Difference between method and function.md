#Difeerences between functions and methods in Scala

Although terms function and method are mentioned interchangably in most cases, they are not quite the same thing. As first citizen in scala, functions are complete objects implementing different triats such as function0, function1, function2, etc, while a method, like in java, is a part of class or object. In most of situations distinctions between functions and methods can be ignored, but in some case, differences might matter. In practice, there are 3 main differences between functions and methods.

###1. Codes in function wont be executed when the function is called, it returns itself as final value, while method will be executed and return the final value when the method called.

####Example 1: 
Define `m` as a method returning a string "this is a method"  
Define `f` as a function returning a string "this is a function"
```
scala> def m(): String = "this is a method"
m: ()String

scala> val f: ()=> String = () => "this is a function"
f: () => String = <function0>
```
Assign method m and function f to `test_1` and `test_2`.
```
scala> val test_1 = m
test: String = this is a method

scala> val test_2 = f
test_2: () => String = <function0>
```
From results we can see that `test_1` is a string with value "this is a method", while `test_2` is a function with datatype <function0>. That means when I assgin method to a variable, this method is invoked and executed and then return a value to the variable so the assignment is not a true assignment but an invoke of method. But for function, no value returns and variable assigned is funtion itself. The fact `test_2` is not a value returned but a function indicates that the function is not invoked but assigned as an object.  

So what shoould I do force function to be invoked?
```
scala> test_2()
res3: String = this is a function
```
Like Javascript, the way to invoke a function is add parentheses after the function name. In this way, when I input `test_2()`, the function can be invoked and return the string.
####Example 2:
```
scala> def m(i: Int): Int = i + 1
m: (i: Int)Int

scala> val f: Int => Int = i => i + 1
f: Int => Int = <function1>
```
Define `m` as a method with one parameter, `f` as a function with one parameter. Both of them are going to add one and return a new integer.
```
scala> val test_1 = m
<console>:12: error: missing argument list for method m
Unapplied methods are only converted to functions when a function type is expected.
You can make this conversion explicit by writing `m _` or `m(_)` instead of `m`.
       val test_1 = m
                    ^
scala> val test_1 = m(1)
test_1: Int = 2
```
As disscussed above, the method cannot be regarded as an object and it can only be invoked to return a value. If I try to invoke a more-than-zero-argument method given no argument, codes cannot be compiled because logics in the method cannot be executed without parameter. That is also the reason error "missing argument list for method m" poped up. So argument must be provided when the method is invoked.
```
scala> val test_2 = f
test_2: Int => Int = <function1>

scala> test_2(1)
res0: Int = 2
```
Unlike method, fucntion `f`, as an object, can be assgined and is able to return value when arguments provided.

###2. Functions can be passed to a method or function while method not.
Define a function `m` and method `f` to do the same logics.
```
scala> def m(i: Int): Int = i + 1
m: (i: Int)Int

scala> val f: Int => Int = i => i + 1
f: Int => Int = <function1>
```

Define a method `m_test` that is supposed to get a function as an argument, and `m_test` will print type of function and invoke the function with the other parameter received.
```
scala> def m_test(f: Int => Int, i: Int): Unit = { println(f); println(f(i)); }
m_test: (f: Int => Int, i: Int)Unit
```
Pass a function into `m_test`, results shows type of this function is a <function1> and it can be invoked successfully.
```
scala> m_test(f, 1)
<function1>
2
```
Pass a method into `m_test`, method can sill be invoked but be attention that the `m` is a <function1> instead of method before invoke in the method. Scala has already converted a method to a function when passing into a method so that I am able to get the same results as passing a function. This process is called ETA process and it is easier for us to use method as function in Scala.
```
scala> m_test(m, 1)
<function1>
2
```
###3. Parameter list is option for a method but mandatory for a function 
Argument is not required for method but for function, parameter is a mandatory and you need to give a `()` if there is no argument needed for the function.
Define a method `m` and function `f` without parameter:
```
scala> def m{}
m: Unit
scala> val f: () => Unit = () =>{}
f: () => Unit = <function0>
```
If I define a function without parameter in this way, it is not gonna work. It indicates that there must be a paramter list for function. Maybe syntax is too hard to be handled by compiler to revognized so that functions are required a list of parameters even if is is empty.
```
scala> val f:  => Unit = =>{}
<console>:1: error: identifier expected but '=>' found.
val f:  => Unit = =>{}
        ^
```
