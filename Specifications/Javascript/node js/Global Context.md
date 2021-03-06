## Global context in browser and Node.js

#### global variables in broswer and node js:
In a browser environment, the global scope is controlled by the window object, while in Node.js it’s controlled by the global object. 
eg:
```
var name = 'frank';
console.log(window.name);
//result: frank
```
```
function helloWorld(){
  console.log('hello world');
}
window.helloWorld();
//result: hello world
```
In Node.js, when a variable is defined, it is defined within module rather than global context. The reason, I guess, is because the browser execute script in global scope by default, while Node.js isolates variables into multiple modules.  
```
var name = frank;
console.log(window.name)
\\result: window is not defined
```
In order to use global context in Node.js, keyword `var` needs to be removed in variable declration and use `global` as global variable is rather than `window`.
```
name = 'Frank'
console.log(global.name)
//result: Frank
```

### exports functions in modules
Node.js has a module loading system, so variables and functions need to be exported if they are supposed to be used by other modules.  
Example:
```
//Let's say I want to write infor about my info in the file frank.js
exports.name = 'Frank';
exports.age = 18
```
```
//in foo.js
var frank = require('./frank.js');
console.log(frank.name);
console.log(frank.age);
//result: 
//Frank
//18
```
There is one thing that needs a little attention, keyword `exports` is not working as expected if it is assigned with new value. For example, if I exports properties within a single object in this way:
```
//in frank.js
exports = {
  name: 'frank',
  age: 18
}
```
```
var frank = require('./frank.js')
console.log(frank.age)
console.log(frank.name)
//result:
//undefined
//undefined
```
In this example, values are not exported at all, and the reason is that keyword `exports` is module.exports. Use `exports` is acutally equal to use `module.exports`. But if there is a new function or variable created and assigned to `exports`, `exports` will become a new value and lost reference to `module.reference`. The logics is shown below:
```
var module = { exports: {} };
var exports = module.exports;
...
// all codes
...
return module.exports;
```
To solve this problem, use `module.exports`. Use `module.exports` is always correct.  
eg:
```
//in frank.js
module.exports = {
  name: 'frank',
  age: 18
}
```
```
var frank = require('./frank.js')
console.log(frank.age);
console.log(frank.name);
```
