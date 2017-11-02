## Global context in browser and Node.js

### host object:
Since browser execute script in global context by default and usually needs to interact with front-end, host objects are provided. In addtion, variables and functions that declared will be by default added into glabal object `window`. In nodejs, scrpits are executed in every modules, the mechanism to define global variables and no host object provided.
#### global variables in broswer and node js:
In a browser environment, the global scope is controlled by the window object while in Node.js, it’s controlled by the global object. The reason, I guess, is because the browser execute script in global scope by default, while Node.js isolates variables into multiple modules.
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
In Node.js, when a variable is defined, it is defined within module rather than global context.
```
var name = frank;
console.log(window.name)
\\result: window is not defined
```
In order to sue global context in Node.js, keyword `var` needs to be removed in declration and the global variable is `global` rather than `window`
```
name = 'Frank'
console.log(global.name)
//result: Frank
```

### exports functions in modules
Node js has a module loading system, so variables and functions need to be exported in order to be used y other modules.
Example
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
There is one thing to be attention, keyword `exports` is not always working. For example, if I exports properties within a single object in this way:
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
The values are not exported at all, and the reason is that keyword
Be attention to the keyword exports, that actually is module.exports. Use `exports` is acutally `module.exposts`, so when reference passed, `exports` is equal to `module.exports`. But if there is a new function or variable created and assigned to `exports`, `exports` will become a new value and lost reference to `module.reference`. The logics is shown below:
```
var module = { exports: {} };
var exports = module.exports;
...
// all codes
...
return module.exports;
```
Always use `module.exports` to avoid this problem:
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
