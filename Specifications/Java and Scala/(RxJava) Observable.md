# RxJava Observable
Rx means ReactiveX which is a project that provides implementations for multiple languages to allow developer to write reactive prorgamming pattern. In short, in reactive you can create observable object standing for data source and attach observer on the observable. In this way, it is possible to propagate events to registered observers.  

## Create an observable from a existing collection:
```java
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Action2;

public class TestObservableFrom {
    public static void main(String[] args) {
        testObservableFrom("Frank", "Kong");
        testObservalbeFromNew("Frank", "Kong");
    }

    public static void testObservableFrom(String... names) {
        Observable observable = Observable.<String>from(names);
        observable.subscribe(new Action1<String>() {
            @Override
            public void call(String str) {
                System.out.println(str);
            }
        });
    }

    //can be written like this since java 8
    public static void testObservalbeFromNew(String... names) {
        Observable observable = Observable.from(names);
        observable.subscribe((str) -> {
            System.out.println(str);
        });
    }
}

```
## Create an observable by `create`
```java
import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;

import java.util.concurrent.TimeUnit;

public class TestObservableCreate {
    public static void main(String[] args) {
        Observable<String> ob = createObservable();
        ob.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println(s);
            }
        });

        Observable<String> obn = createObservableNew();
        obn.subscribe((s) -> System.out.println(s));
    }

    public static Observable<String> createObservable() {
        Observable observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber){
                doSomething(subscriber);
            }
        });
        return observable;
    }
    
    //can be written like this in java 8
    public static Observable<String> createObservableNew() {
        return Observable.create(
                (Subscriber<? super String> subscriber) -> {
                    doSomething(subscriber);
                });
    }

    public static void doSomething(Subscriber subscriber) {
        try {
            TimeUnit.SECONDS.sleep(5);
            subscriber.onNext("something is done");
        } catch (InterruptedException e) {
            subscriber.onError(e);
            e.printStackTrace();
            System.exit(1);
        }
    }
}

```
