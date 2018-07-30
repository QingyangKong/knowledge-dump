```
import java.util.Objects;
import java.util.HashSet;
import java.util.HashMap;
import java.util.Set;

public class HashCodeTest {
    public static void main(String[] args){
        Person p1 = new Person(1, "qingyang");
        Person p2 = new Person(1, "qingyang");

        System.out.println("Test if p1.equals(p2): " +  p1.equals(p2));
        System.out.println("p1.hashCode: " + p1.hashCode());
        System.out.println("p2.hashCode: " + p2.hashCode());  

        HashSet<Person> hs = new HashSet<Person>();
        hs.add(p1);
        hs.add(p2);
        System.out.println(hs.size());
    }
}

class Person {
    int id;
    String name;
    Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    //if method equals not overriden, 2 identical object will be different.
    //usually, if a class is supposed to be compared field by field, you need to override method equals 
    @Override
    public boolean equals(Object other){
        if(other == null) {
            System.out.println("other is null");
            return false;
        }
        if(!(other instanceof Person)) {
            System.out.println("other is not istance of Person");
            return false;
        }
        Person toCompare = (Person) other;
        return this.id == toCompare.id && this.name == toCompare.name;
    }

    //native method hashCode() returns object's address if it is not overriden
    //So by practice, hashCode must be overriden if equals is overriden.
    //or given 2 objects A and B. A equals B, A and B will be 2 objects when they pushed to hashSet.
    @Override
    public int hashCode(){
        return java.util.Objects.hash(id, name);
    }
}
```
