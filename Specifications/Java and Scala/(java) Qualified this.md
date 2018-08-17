# Qualified This

In an inner class, if you want to use method in the outer class, you need to use outClassName.this.methodName.  

outerClassName.this is called qualified this, and please check sample codes below:  

```java
public class QualifiedThisTest {

    public static void main(String[] args) {
        QualifiedThisTest qtt = new QualifiedThisTest();
        qtt.printInfo();
    }

    void printInfo() {
        TestInnerClass tic = new TestInnerClass();
        tic.printInfoForOuter();
        tic.printInfoForInner();
    }

    void printSomething() {
        System.out.println("something from outer");
    }

    private class TestInnerClass {
        void printInfoForOuter() {
            QualifiedThisTest.this.printSomething();
        }

        void printInfoForInner() {
            this.printSomething();
        }

        void printSomething() {
            System.out.println("something from inner");
        }
    }
}
```
output is :
```
something from outer
something from inner
```
