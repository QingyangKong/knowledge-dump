## Lambda Expression
Sample code:

```java
Runnable runnableTask = () -> System.out.println("runnable task complete.");
LinkedBlockingQueue<Runnable> runnableList = new LinkedBlockingQueue<>();
runnableList.add(runnableTask);
runnableList.add(runnableTask);
runnableList.add(runnableTask);
runnableList.add(runnableTask);
```
In codes above, I use lambda expression to create a class Runnable class. In java 8, compiler will extend a lambda expression to a class that implements an interface, and the method(lambda expression) is the implementation of the only method defined in the interface.

## Method reference
`::` is used to get a method using method reference.   
Sample code:
```java
//defined in IntPipeline.java
@Override
public final OptionalInt reduce(IntBinaryOperator op) {
    return evaluate(ReduceOps.makeInt(op));
}

@Override
public final OptionalInt max() {
    return reduce(Math::max); //this is the gotcha line
}

//defined in Math.java
public static int max(int a, int b) {
    return (a >= b) ? a : b;
}
```
How it works:  
`Reduce` is supposed to receive a instance implementing interface `IntBinaryOperator`. What we need to do is to construct a new class implementing the method.
```java
reduce(new IntBinaryOperator() {
    int applyAsInt(int left, int right) {
        return Math.max(left, right);
    }
});
```
This is verbose so we can minify the codes to just pass a lambda expression, and the lambda expression will be considered as the method to be implemented. 
```java
reduce((int left, int right) -> Math.max(left, right));
```
If there is a method matching the arguments and return data type, it can be passed using method reference like below:
```java
reduce(Math::max);

```
Check more details here. https://stackoverflow.com/questions/20001427/double-colon-operator-in-java-8
