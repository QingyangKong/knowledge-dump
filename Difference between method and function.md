Although terms function and method are mentioned interchangably in most cases, they are not quite the same thing. As first citizen in scala, functions are complete objects implementing different triats such as function0, function1, function2, etc, while a method, like in java, is a part of class or object. In most of situations distinctions between functions and methods can be ignored, but in some case, differences might matter. In general, there are 4 main differences between functions and methods.

###1. Codes in function wont be executed when the function is called, it returns itself as final value, while method will be executed and return the final value when the method called.

```
scala> def m(): String = "this is a method"
m: ()String

scala> val f: ()=> String = () => "this is a function"
f: () => String = <function0>
```
Define `m` as a method returning a string "this is a method"  
Define `f` as a function returning a string "this is a function"
```
scala> val test_1 = m
test: String = this is a method

scala> val test_2 = f
test_2: () => String = <function0>
```
Assign method m and function f to `test_1` and `test_2`. `test_1` is a string with value "this is a method", while `test_2` is a function with datatype <function0>. That means when I assgin method to a variable, this method is invoked and executed and then return a value to the variable so the assignment is not a true assignment but an invoke of method. But for function, no value returns and variable assigned is funtion itself. The fact `test_2` is not a value returned but a function indicates that the function is not invoked but assigned as an object.
```
scala> test_2()
res3: String = this is a function
```
Like Javascript, the way to invoke a function is add parentheses after the function name. In this way, when I input `test_2()`, the function can be invoked and return the string.

###2. Functions can be assigned to a variable while method not.

###3. Functions can be passed to a method or function while method not.

###4. Parameter list is option for a method but mandatory for a function 
