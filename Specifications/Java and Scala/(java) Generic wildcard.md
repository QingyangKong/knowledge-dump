# Wildcard in generic
Question mark (?) is the wildcard in generics and represents an unknown type. The wildcard can be used as the type of a parameter, field, or local variable and sometimes as a return type. We can’t use wildcards while invoking a generic method or instantiating a generic class. 
## Lower bounded wildcard
Retrict that only class as super class of a specific class.
```
public class WildcardTest {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        printInfoIntegerSuperClass(intList);
        System.out.println();
    }

    public static void printInfoIntegerSuperClass(List<? super Integer> list) {
        list.forEach(e -> System.out.print(e + " "));
    }
}
```
## Upper bounded wildcard
Retrict that only class as subclass of a specific class.
```
public class WildcardTest {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        printInfoNumberSubclass(intList);
        System.out.println();
    }

    public static void printInfoNumberSubclass(List<? extends Number> list) {
        list.forEach(e -> System.out.print(e + " "));
    }
}
```
## Unbounded wildcard
No restriction at all. 
```
public class WildcardTest {
    public static void main(String[] args) {
        List<Integer> intList = Arrays.asList(1, 2, 3, 4);
        printInfoAnything(intList);
        System.out.println();
    }

    public static void printInfoAnything(List<?> list) {
        list.forEach(e -> System.out.print(e + " "));
    }
}
```
