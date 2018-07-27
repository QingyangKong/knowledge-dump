# Asynchronous in JS programing
In order to avoid program to be blocked by some time-consuming manipulations(eg: read a big file, wait http response), it is necessary to use asynchronous programing style.  

1. Read file synchrounously 
2. Read file asynchrounously
3. Read file asynchrounously with Promise
4. Read file asynchrounously with RxJs Observable

## Synchrounous Programing
```
var fs = require('fs')
var data = fs.readFileSync('input.txt')
console.log(data.toString())
console.log('program end')
```
Result:
```
This is an input file
program end
```
## Aynschronous Program with asynchronous function
```
var fs = require('fs')
fs.readFile('inpt.txt', (err, data) => {
	if(err) console.log(err); return
	return console.log(data.toString())
})
console.log('program end')
```
Result:
```
program end
This is an input file
```
## Asynchronous Program with Promise
The advantage of promise over asynchoronous program is that result of the promise can be returned as a handler that provides one more logic level to provide flexibility.
```
var fs = require('fs')
var myPromise = new Promise((resolve, reject) => {
	fs.readFile('input.txt', (err, data) => {
		if(err) reject(err)
		resolve(data.toString())
	})
})
myPromise
  .then(data => console.log(data))
  .catch(err => console.log(err))
console.log('program end')
```
Result:
```
program end
This is an input file
```
## Asynchoronous Program with RxJs observable
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
