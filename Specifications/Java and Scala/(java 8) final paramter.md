## Final parameter for inner class
In Java sometimes you need to use an inner class within an outer class.  
Sample codes:  
```java
    public static Future<String> getStringFromFuture(final String temp) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> fut = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "sss" + temp;
            }
        });
        return fut;
    }
```
because the varaible `temp` is used by inner anonymous class, it has to be declared as `final` in order to keep the inner class instantiated by the variable honesty.

In java 8, `final` is not required because the grammer add a `final` automatically for `variable`.  
Sample codes:
```java
//temp is not required declared with final
    public static Future<String> getStringFromFuture(String temp) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> fut = pool.submit(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "sss" + temp;
            }
        });
        return fut;
    }
```
or using lambda expression:
```java
    public static Future<String> getStringFromFuture(String temp) {
        ExecutorService pool = Executors.newCachedThreadPool();
        Future<String> fut = pool.submit(() -> {
            return "ssss" + temp;
        });
        return fut;
    }
```
