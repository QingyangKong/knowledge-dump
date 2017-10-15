# Asynchronous in JS programing
In order to avoid program to be blocked by some time consuming manipulations(eg: read a big file, wait http response), it is necessary to use asynchronous programing.  
## Synchrounous Programing
```
var fs = require('fs');
var data = fs.readFileSync('input.txt');
console.log(data);
console.log('program end');
```
Result:
```
This is an input file
program end
```
## Aynschronous Program with asynchronous function
```
var fs = require('fs');
var data = fs.readFile('input.txt', (err, data) => {
  if(err) return err;
  return data.toString();
});
console.log(data);
console.log('program end');
```
Result:
```
program end
This is an input file
```
## Asynchronous Program with Promise
The advantage of promise over asynchoronous program is that result of the promise can be returned as a handler can be used anywhere.
```
var fs = requiree('fs');
var myPromise = new Promise(function(resolve, reject) => {
  var data = fs.readFile('input.txt', (err, data) => {
    if(err) reject(err);
    resolve(data.toString());
  });
});
myPromise
  .then(data => console.log(data))
  .catch(err => console.log(data));
```
Result:
```
program end
This is an input file
```
## Asynchoronous Program with RxJs observable
The advantage of the observable over promise is that multiple events rather than single event can be handled in observable.
```
var fs = require(fs');
var rx = require('rxjs/Rx');
var myObservable = new rx.observable.create(observable => {
  fs.readFile('input.txt', (err, data) => {
    if(err) observer.next(err);
    observer.next(data.toString());
  })
});
myObservable.subscribe(
  (data) => {console.log(data)},
  (err) => {console.log(err)},
  () => {console.log('complete')}
);
console.log('program end')
```
Result：
```
program end
This is an input file
```
