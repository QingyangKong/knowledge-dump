# Javascript function call pattern
Since javascipt is a dynamic language and js code is not evaluated until it is executed, it could be quite different using js code in some scenairos. There are 2 main differences when call functions in JS code.
### Difference 1:
In languge like java or C#, keyword this is always point to the object in which your method defined, while in java or C# function must be in a class, Because in javascript it is not required to have a class for a function, you can use keyword this wherver you want.

For example.
```
function printInfo(){
    console.log(this.name);
}
printInfo();
//output: undefined.
```

This kind of code is definitely illegal in OOP lanaguage, while in it is legal in js since default context(consider it as class) is window. The reason `undefined` printed is because default context of the function `printInfo` is window in which there is no attribute `name` defined.  

### Difference 2:
In javascript, keyword this always points to current context rather than the object where the function defined.
```
var obj = {
    name: 'qingyangkong',
    printInfo: function(){
        console.log(this.name);
    }
}
obj.printInfo();
var func = obj.printInfo;
func();
//output:
//qingangkong
//undefined
```

The result for the first one is 'qingyangkong', while the second one is undefined. This is because that `func()`'s context is window and it is trying find window.name that does not exist at all. 

### pattern to use context correctly.
#### constructor pattern 
Function are defined to be used with syntax new is called constructor. 
```
function printInfoConstructorPattern() {
    this.func = function() {return 'qingyangkong';}
}
var test = new printInfoConstructorPattern();
console.log(test.func());
//output: qingyangkong
```
func.call(obj, args1, args2);  
func.apply(obj, [args1, args2 ]);

```
function printInfoApplyPattern(a, b){
    console.log(a + b);
    console.log(this.name);
}
var objApplyPattern = {
    name: 'apply & call invocation pattern qingyangkong'
};
printInfoApplyPattern.call(objApplyPattern, 1, 2);
printInfoApplyPattern.apply(objApplyPattern, [1, 2]);
```
var boundFunc = func.bind(object)
```
//bind invocation pattern
function printInfoBindPattern(a, b){
    console.log(a + b);
    console.log(this.name);
}
var objBindPattern = {
    name: 'bind invocation pattern '
}

var boundFunc = printInfoBindPattern.bind(objBindPattern);
boundFunc(1, 2);
```
