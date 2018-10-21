## variable scope and declaration
### 2 scopes in javascript:
In javascript, in general, there are 2 scopes: global and local.
Variables declared within functions are in local scope while variables decared outside functions are in global scope.
Variables in global scope are accessible anywhere, while local variables are only accessable within the function.  
eg:  
```javascript
var varOutside = 1;
function printVariables(){
	var varInside = 2;
	console.log(varOutside)
	console.log(varInside)
}
console.log(varOutside)
console.log(varInside)
//results:
//ReferenceError: varInside is not defined
```

### Problem: Missing block scope in ECMAScript5
In ECMAScript5, due to the lack of block scope, variables defined in blocks are consiered in global scope, and they are accessible anywhere.
eg:
```javascript
for(var i = 0; i < 5; i++){
	console.log(i);
}
console.log(i)
//result: 0, 1,2,3,4,5
```
For developers from other languages, there should be an error thrown out becasuse variable `i` shouldn't be found outside the block structure `for`. The reason the variable still keep instead of being released is that Javascript lacks block scope and variable `i` is considered as a global variable.  

To avoid this, what people usually do in ECMAScript 5 is to use Immediately-invoked function expression which is to call a function directly when script started to be executed. That is why we always see script like `(function () { /* ... */ })();`, it is used to avoid global variables messed up by codes and keep all global variables (including variables within a bloack structure) accessable in current context. In Node.js, this is done for every single module. 

### Solution: Keyword `let` ECMAScript 6
As we talked above variables declared with `var` is not restricted in a block scope, but the feature of `var` cannot be changed by ECMAScript in consideration of backward-compatibility. As a result, ECMAScript 6 introduced a new keyword `let`. In a block structure, variables declared with the keyword will be restriced in the block scope.  
eg:
```javascript 
for (let i = 0; i < 5; i++) {
	console.log(i);
}
console.log(i)
//result:
//ReferenceError: i is not defined
```

### Declaration

Declare with curly brackets and use `const` in order to avoid to mess up scope. 
```js
const { rpcUrl, bypassHooks,  noConflict } = options
```
It can be written in a more verbose way:
```js
const rpcUrl = options.rpcUrl;
const bypassHooks = options.bypassHooks;
const noConflict = options.noConflict;
```

In js, string inerpolation requires backtick:
```js
let var = 'var string';
console.log(`${var}`)
//output: var string
```
```js
console.log('show string', var);
//output: show string var string
```