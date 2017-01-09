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
result is:
`1`  
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
