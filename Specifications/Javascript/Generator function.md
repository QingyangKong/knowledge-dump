## How to use generator function
Generator function stops at the point of yield and wait for the next call.  
`next` can get the an object generate by `yield` {value: {value} ; done: {bool}}
```
function* generateEvent(start){
    var count = 0;
    while(true){
        console.log("counter:" + count); 
        var tmp = yield start;          //this will be returned in function next()
        console.log("end");             //start from here when next() is called again.
        start++;
        count++;
    }
}

var gen = generateEvent(6);
console.log(gen.next());
console.log(gen.next());

//results:
//counter:0
//{ value: 6, done: false }
///end
//counter:1
//{ value: 7, done: false }
```
yield can also take parameter from next. Please note that first `next()` just start the generator function and cannot pass any argument.
```
function* generateEvent(){
  while(true){
    console.log(yield);
  }
}
var gen = generateEvent();
gen.next();
gen.next(1);
gen.next(2);
gen.next(3);

//results:
//1
//2
//3
```
