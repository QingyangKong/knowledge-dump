# Asynchronous in JS programing
In order to avoid program to be blocked by some time-consuming manipulations(eg: read a big file, wait http response), it is necessary to use asynchronous programing style. The article will talk about the history in javascript to hadle asynchronous programming.  

- callback 
- callback hell
- Promise
- async await in ES7
- Read file asynchrounously with RxJs Observable

## callback
In very beginning, javascript implement callbacks by add a callback function after in a time consuming function like data fetch from DB, REST call, reading a large file or any other expensive I/O operations.  
So first let's see how to write a function will callbacks.
```js
function doSomething(callbackFn) {
    var error = undefined;
    var data = "success";
    callbackFn(error, data);
}

function cbFn(error, data) {
    if(error) {
        console.error("sth wrong: " + error)
    } else {
        console.log(data);
    }
}

doSomething(cbFn);
```
Result:
```
success
```
By attaching callback in the end of function doSomething, we can make sure the `cbFn` is executed definitely at the end of `doSomething` and this is called callback.
## callback hell
Sometimes we have multiple callbacks and want to have all of them executed in sequencce. Example down below is to show how to do this and what is the callback hell.
```javascript
function doSomething(toPrint, cb) {
    setTimeout(() => {
        var error = undefined;
        var success = "success";
        cb(error, toPrint + " " + success);
    }, 3000);
}


doSomething("first call", (error, data) => {
    if(error){
        console.error(error)
    } else {
        console.log(data);
        doSomething("second call", (error, data) => {
            if(error) {
                console.error(error);
            } else {
                console.log(data);
                doSomething("third call", (error, data) => {
                    if(error) {
                        console.error(error);
                    } else {
                        console.log(data);
                    }
                })
            }
        })
    }
})
```
result
```
//3s
first call success
//3s
second call success
//3s
third call success
```
Through the codes above, we can achieve what we want but this does not look good. 2 reasons why the code is bad: 1. the codes are nested one inside the other. That means you have to indent for every line, for the example, there are only 3 callbacks for the example, think about if you have 20 callbacks, how many space indent is required. 2. if you just want to add a new step in the middle, you need to add indent to everything below it.  

## Promise
Promise is a new feature to avoid callback hells. Promise uses resolve and reject to wrap result from a function and then return a handler so that codes do not need to be nested in another. See example below:
```javascript
function doSomething(toPrint, cb) {
    setTimeout(() => {
        var error = undefined;
        var data = toPrint + " success";
        cb(error, data);
    }, 3000)
}

function doSomethingInPromise(toPrint) {
    return new Promise((resolve, reject) => {
        doSomething(toPrint, (error, data) => {
            if(error) {
                reject(error);
            } else {
                resolve(data)
            }
        })
    })
}

doSomethingInPromise("first call")
.then((data) => {
    console.log(data);
    return doSomethingInPromise("second call");
}).then((data) => {
    console.log(data);
    return doSomethingInPromise("third call");
}).then((data) => {
    console.log(data);
    console.log("finished");
})
```
result:
```
first call success
second call success
third call success
finished
```
Through the example, it not hard to recognize that promise, basically, wraps callbacks with a resolve and reject and then provide a uniform handler indicating status of the callback result. Theoratically, all function with callback function can be and should be written as promise style. For this reason, javascript even provides a function `promisify` to help you to do it. See example below:
```javascript
function doSomething(toPrint, cb) {
    setTimeout(() => {
        var error = undefined;
        var data = toPrint + " success";
        cb(error, data);
    }, 3000);
}

const { promisify } = require('util');
const doSomethingInPromise = promisify(doSomething);

doSomethingInPromise("first call").then((data)=> {
    console.log(data)
    return doSomethingInPromise("second call");
}).then((data) => {
    console.log(data);
    return doSomethingInPromise("thrid call");
}).then((data) => {
    console.log(data)
    console.log("finish");
}).catch((error)=>{
    console.error(error);
})
```

## async and await
`async` and `await` are new pair of keywords introduced since ES7, and it does the totally the same thing as callbacks and promise, which is handling the asynchronous programming. The reason for `async` and `await` is, IMO, to help programmer from other languages to get familiar with async mechanism in javascript quickly, because whatever for callback or promise, it is unique thing for js and very unfriendly for coders from other field, like me C# refugee. Let's see the how to write it in codes.    
```javascript
function doSomething(toPrint, cb) {
    setTimeout(() => {
        var error = undefined;
        var data = "success " + toPrint;
        cb(error, data);
    }, 3000);
}

function doSomethingInPromise(toPrint) {
    return new Promise((resolve, reject) => {
        doSomething(toPrint, (error, data) => {
            if(error) {
                reject(error)
            } else {
                resolve(data)
            }
        })
    })
}

(async () => {
    let firstResult = await doSomethingInPromise("first call");
    console.log(firstResult);
    let secondResult = await doSomethingInPromise("second call");
    console.log(secondResult);
    let thridResult = await doSomethingInPromise("thrid call");
    console.log(thridResult);
    console.log("finish")
})();
```
result
```
success first call
success second call
success thrid call
finish
```
Please be attention that `await` can only be applied in a function marked by `async`.
## Rx Observable
The advantage of the observable over promise is that multiple events rather than single event can be handled in observable and the user can also return error and complete information.
```
var fs = require('fs')
var rx = require('rxjs/Rx')

var myObservable = new rx.Observable.create((observer) => {
	fs.readFile('input.txt', (err, data) => {
		if(err) return observer.error('file read error: ' + err)
		return observer.next(data.toString())
	})
})

myObservable.subscribe(
	(data) => {console.log(data)},
	(err) => {console.log(err)},
	() => {console.log('complete')}
)
console.log('program end')
```
Result：
```
program end
This is an input file
```
