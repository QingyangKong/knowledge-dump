# Future vs CompletableFuture
Before java 8, Future is used for concurrent programming, and CompletableFuture is introduced to help to do cocurrent future. There are a lot of improvements from Future to CompletableFure, and the article is gonna take about basic usage of Future and CompletableFuture and one of CompletableFuture advantages, callback.  

Future sample codes:  
For short, instantiate from a pool, `execute` for `Runnable` and `submit` for `Callable`. `get()` will block the program and hard to add a callback.  
```java
public class FutureTest {
    public static void main(String[] args) {
        Future<String> fut = submitAndGetFutureString();
        comsumeFutureWithGet(fut);
        execute();
        cancelFutureWithCancel(fut);
    }

    public static void cancelFutureWithCancel(Future<String> fut) {
        fut.cancel(true);
        System.out.println("Is future canceled: "+ fut.isCancelled());
        try {
            fut.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Failed to get future");
            e.printStackTrace();
        }
    }

    public static void comsumeFutureWithGet(Future<String> fut) {
        while(!fut.isDone()) {
            System.out.println("Future not completed yet, wait 1 second...");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            String result = fut.get();
            System.out.println(result);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static Future<String> submitAndGetFutureString() {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<String> fut = es.submit(() -> {
            TimeUnit.SECONDS.sleep(5);
            return "String returned";
        });
        es.shutdown();
        return fut;
    }

    public static void execute() {
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.exit(1);
            }
            System.out.println("running");
        });
        es.shutdown();
    }
}

//output:
/*
* Future not completed yet, wait 1 second...
* Future not completed yet, wait 1 second...
* Future not completed yet, wait 1 second...
* Future not completed yet, wait 1 second...
* Future not completed yet, wait 1 second...
* String returned
* Is future canceled: false
* running
*/

```

Sample codes fir CompletableFuture:  
For short, instantiate from `CompletableFuture`, `runAsync` for `Runnable`, `supplyAsSync` for `Callable`. `WhenComplete` will not block the program and easy to add a callback.  
```java
public class CompletableFutureTest {

    public static void main(String[] args) {
        CompletableFuture<String> cf = supplyAsyncAndGetCompletableFutureString();
        cf.whenComplete((data, exception) -> {
            if(exception != null) {
                exception.printStackTrace();
                System.exit(1);
            }
            System.out.println("no exception");
            System.out.println(data);
        });
        runAsync();
    }

    public static void runAsync() {
        ExecutorService pool = Executors.newCachedThreadPool();
        CompletableFuture.runAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("running in runAsyncAndGetCompletableFuture");
        }, pool);
        pool.shutdown();
    }

    public static CompletableFuture<String> supplyAsyncAndGetCompletableFutureString() {
        System.out.println("entering supplyAsync");
        ExecutorService pool = Executors.newSingleThreadExecutor();
        CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "res";
        }, pool);

        pool.shutdown();
        return cf;
    }
}

//output
/*
* entering supplyAsync
* running in runAsyncAndGetCompletableFuture
* no exception
* res
*/
```
