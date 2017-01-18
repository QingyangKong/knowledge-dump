<h2>Use underscore in SCALA</h2>
<h3>1. In pattern match</h3>
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

result is:1
```
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
result is:
`2`
<h3>2. successive underscores</h3>
Each successive underscores implies another argument of in the anonymous function.
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
