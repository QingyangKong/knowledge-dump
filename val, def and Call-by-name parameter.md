##Difference between val and def and introduction to call-by-name parameter
In this file, I am going to talk about the difference between keyword val and def in scala and introduce the use of call-by-name parameter.
##Context:
1. what is the difference between keyword "val" and "def"?
2. what is the use of the call-by-name parameter in method?
3. what is the use of keyword "lazy"?

###Difference between keyword "val" and "def":
In short, body of val will be evaluated once when the val defined while def is evaluated evvery time it is used.
####example 1
In this example, I will create `v` and `d` by keyword `val` and `def` and see difference between them. I instantiate 2 vals with an expression to print out a string and then return an integer.
```
scala> val v = { print("val initiated\t"); 1 }
val initiated   v: Int = 1

scala> def d = { print("def initiated\t"); 1 }
d: Int
```
From results, we can see when `v` is defined, contexts are evaluated immediately, and the "val initiated" is printed out. When `d` is defined, contexts are <b>NOT</b> evaluated, so string "def initiated" is not printed out.  
If I use `v` and `d` as below:
```
scala> v
res1: Int = 1

scala> d
def initiated   res2: Int = 1
```
Expression `print("val initiated\t")` in `v` is not evaluated while expression of `d` is evalauted and that is why string "def initiated" is printed out. This example indicates that def is not evaluated unless it is used while val is evaluated once it is created.  
####example 2
In this example I will assagin `util.Random.nextInt(10)` to val and def and to see if def will be evaluated every time.
```
scala> val randomVal = util.Random.nextInt(10)
randomVal: Int = 7
scala> val listVal = List(randomVal, randomVal, randomVal, randomVal, randomVal, randomVal, randomVal, randomVal)
listVal: List[Int] = List(7, 7, 7, 7, 7, 7, 7, 7)
```
The reason all `ramdomVal`s in list is the same number is because expression is evaluated when `randomVal` is defined and `util.Random.nextInt(10)` is not invoked again when `randomVal` is used in future.

If I assagin `util.Random.nextInt(10)` to a def as below, element in the list is not the same number.
```
scala> def randomDef = util.Random.nextInt(10)
randomDef: Int
scala> val listDef = List(randomDef, randomDef, randomDef, randomDef, randomDef, randomDef, randomDef, randomDef)
listDef: List[Int] = List(2, 5, 8, 9, 1, 3, 3, 8)
```
Reason that elements are different is because expression is evaluated every time the randomDef is used.  
In conclusion, val is evalauted once when it is created but def is evaluated every time it is used.  

###Use of call-by-name parameter:
Somtimes we want pass expressions to a method, but expressions(like variable created with keyword def) are always evaluated even though not used in the method at all. Expression misuse will make program do extra work or cause unexpected side-effect.
####example 3
```
scala> def sayHi(name: String, exp_1: Unit, exp_2: Unit, exp_3: Unit){
     |  name match{
     |    case "Frank" => exp_1
     |    case "Tom" => exp_2
     |    case _ => println("s")
     |  }
     | }
sayHi: (name: String, exp_1: Unit, exp_2: Unit, exp_3: Unit)Unit
```
Define a method to do different operation when different `name` passed in.
```
scala> def helloFrank = {println("Hello Frank")}
helloFrank: Unit
scala> def helloTom = {println("Hello Tom")}
helloTom: Unit
scala> def helloAnn = {println("Hello Ann")}
helloAnn: Unit
```
Create 3 "def' to pass to the method.
```
scala> sayHi("Frank", helloFrank, helloTom, helloAnn)
Hello Frank
Hello Tom
Hello Ann
```
After I call the method `sayHi` all of 3 "def"s are evalauted even though they are not used in the method yet. After evaluation, 3 of them are converted into Unit and cannot be evaluated when they are used in the method.  
Because expected result is "Hello Frank", I need to ensure the other 2 are only evalauted when used. How to do this? Scala provides a way called call-by-name parameter. In order to use the feature, I need to define method as below:
```
scala> def sayHi(name: String, exp_1: => Unit, exp_2: => Unit, exp_3: => Unit){
     |   behavior match{
     |     case "Frank" => exp_1
     |     case "Tom" => exp_2
     |     case _ => println("s")
     |   }
     | }
sayHi: (name: String, exp_1: => Unit, exp_2: => Unit, exp_3: => Unit)Unit

scala> sayHi("Frank", helloFrank, helloTom, helloAnn)
Hello Frank
```
This time I get the right answer. By defining expressions with type `: => Unit`, it can be promised that expressions are only evaluated when they are used. In this example `exp_1`, `exp_2` and `exp_3` are called call-by-name parameter.  
In conclusion, I can make sure "def"s are not evaluated unless they are used.

###Keyword "lazy"
keyword lazy is used before "val". If "lazy" is used, the val is evaluated at time when used rather than created, but it is still evaluated only once. That means "lazy val" only evaluated when used and after that this "lazy val" will keep the same and not evaluated again.  
####example 4
```
scala> lazy val rand = util.Random.nextInt(10)
rand: Int = <lazy>

scala> List(rand, rand, rand, rand)
res3: List[Int] = List(1, 1, 1, 1)
```
Data type for rand after creation is <lazy>, so `rand` is not evalauted yet at this time point. When I create List with multiple `rand`, all elements in the list are the same number, that certify that `rand` is only evaluated once.  
From 4 examples above in a very short conclusion, ranking of laziness is `def` > `lazy val` > `val`.
