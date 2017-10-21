## Global context in browser and Node.js

### In Chrome or other browser:  
Variables and functions that declared will be added into glabal object `window`.
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
### In Node js:  
When a variable is defined, it is defined within module rather than global context.
```
var name = frank;
console.log(window.name)
\\result: window is not defined
```
In order to sue global context in Node js, keyword `var` needs to be removed in declration and the global variable is `global` rather than `window`
```
name = 'Frank'
console.log(global.name)
//result: Frank
```
The reason is because the browser execute script in global scope in default, while node js isolate variables into multiple modules in default.
