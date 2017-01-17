<h2>Use of Option data type</h2>
<h3>1. Introduction:</h3>
Option is a data type used in Scala to avoid null pointer exception. In Java, this kind of exception happens when the codes rely on a variable that is a unexpected null. There are 2 types of data type in option: `None` and `Some`, when there is no value in option, it is None, otherwise Some.
<h3>2. Use Cases</h3>
<h4>2.1 pattern match</h4>
Because Option is a case class, it can be used in pattern match perfectly. All values fetched from a map is an Option object with 2 possibilities: Some and None.
```
val testMap: Map[String, String] = Map("US" -> "DC", "China" -> "Beijing")
testMap: scala.collection.immutable.Map[String,String] = Map(US -> DC, China -> Beijing)
```
```
val capital1 = testMap.get("US")
capital1: Option[String] = Some(DC)
```
```
val capital2 = testMap.get("Britain")
capital2: Option[String] = None
```
When the key exists in the Map, value is contained in the Some and None would be returned when key does not exist in the Map.  
`Option.get` to get the value of the option but would throw exception when the Option is empty
```
val capital1Str = capital1.get
capital1Str: String = DC
val capital2Str = capital2.get
java.util.NoSuchElementException: None.get
  at scala.None$.get(Option.scala:347)
  at scala.None$.get(Option.scala:345)
  ... 32 elided
 ```
In this way, null pointer exception still exists so method `Option.get` is not recommended. There is another method called `getOrElse` to avoid the exception by defining a default value.
```
val capital3Str = capital1.getOrElse("default")
capital3Str: String = DC
val capital4Str = capital2.getOrElse("default")
capital4Str: String = default
```
`Map.getOrElse()` is to combine `Map.get` and `Option.getOrElse`
```
Map.getOrElse(key, default) to get the values with keys or get default
val capital5Str = testMap.getOrElse("US", "default")
capital5Str: String = DC
val capital6Str = testMap.getOrElse("Britain", "default")
capital6Str: String = default
```
<h4>2.2 use as collection</h4>
Apart from Pattern match, Option can be used as collection. According to some unknown sources, using Option as a collection is the most idiomatic way.
```
val USCapital = testMap.get("US")
val BritainCapital = testMap.get("Britain")

def getCapital(s: String): String = s"capital is $s"
def getDefault = "NA"

println( USCapital.map(getCapital).getOrElse(getDefault) )
println( BritainCapital.map(getCapital).getOrElse(getDefault) )

Results: 
capital is US
NA
```
how above codes works?  
map(f) = some(f(a)) or none, and then getOrElse to get the content or default value. In this example, option can be regarded as a collection that can be applied with multiple methods. Except for map, there are other methods like `foreach`, `list`, `fold`, `filter`, etc. There is a method table for Option at this [blog](http://blog.originate.com/blog/2014/06/15/idiomatic-scala-your-options-do-not-match/).  
Here is an example for `list`:  
