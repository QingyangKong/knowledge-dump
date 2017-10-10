# Use underscore in SCALA
### 1. In pattern match
In pattetn matching, underscore serves as a wildcard to match everything <b>includes</b> `null`.  
proof:
```
object underscoreTest {
    def main(args: Array[String]): Unit = {
    val s = null
    val ss = s match {
    case null => 1  
    case _    => 2
    }
    println(ss)
  }
}
```
result is 1

And modify the program to this:
```
object underscoreTest {
    def main(args: Array[String]): Unit = {
    val s = null
    val ss = s match {
      case _    => 2
    }
    println(ss)
  }
}
```
result is 2
### Underscores in anonymous function
The underscore acts as a placeholder in anonymous function and first one is a short form of second one. If there is only on parameter for the function, underscore can even be skipped.
```
val list = List(1, 2, 3, 4)
val newList_1 = list.map ( print(_) )
println
val newList_2 = list.map ( x => print(x) )
println
val newList_3 = list.map ( print )
println
Result is:
1234
1234
1234
```
Underscores can be used for 2 parameters, and each successive underscores implies another argument of in the anonymous function.
```
val list = List((1, 2), (3, 4), (5, 6), (7, 8))
val newList = list.map(x => x._1 + x._2)
println(newList)

result is: List(3, 7, 11, 15)
```
It cannot be written like this.
```
val list = List((1, 2), (3, 4), (5, 6), (7, 8))
val newList = list.map(_._1 + _._2)
println(newList)
```
Because second underscore implies second parameter in current tuple, there gonna be an error `missing parameter type for expanded function`.  
If there are multiple element in each tuple of a collection, better way is to use case class.
```
val list = List((1, 2), (3, 4), (5, 6), (7, 8))
val newList = list.map{case(x, y) => x + y}
println(newList)
result is: List(3, 7, 11, 15)
```
Small tip: case class can only used in an anonymous function with curly brackets.
