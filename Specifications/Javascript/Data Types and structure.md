### datatypes and data structures in Javascript  
#### 5 primitive datatypes:  
*  number
*  string
*  boolean
*  undefined
*  null  
#### 1 complex datattype:
* Object
#### data structure
* Array
* Json
* Set
* Map

### undefined vs null:
Unlike some language where undefined and null are the same datatype. In javascript, undefined means a variable declared but not assgined.
```
var a;
console.log(a);
//result: undefined
```
null is a value that can be assigned.
```
var a = null;
console.log(a);
//result: null
```
### undefined vs varaible is not defined
Please be attention that undefined variable is declared without assignment, but if the variable is not even declared, there would an error thrown that "variable is not defined". In this case, the datatype is not undefined, but does not exist beacuse the variable itself does not exist at all.
```
var a;
console.log(b)
//result: ReferenceError: b is not defined
//   at Object.<anonymous> (C:\webdev\projects\tests\nodeTest\testObj.js:21:13)
//   at Module._compile (module.js:570:32)
//   at Object.Module._extensions..js (module.js:579:10)
//   at Module.load (module.js:487:32)
//   at tryModuleLoad (module.js:446:12)
//   at Function.Module._load (module.js:438:3)
//   at Module.runMain (module.js:604:10)
//   at run (bootstrap_node.js:394:7)
//   at startup (bootstrap_node.js:149:9)
//   at bootstrap_node.js:509:3
```
### function and property
Data within an object is called property and function within an object is called method, so an object consists of properties and methods. Properties can be accessed by squqre brackets like this: obj['property_name'], the reason to access property in this way is because sometimes, in development phase, property name is not fixed and we can set property name in a config file. In Js file, string in config file can be fetched and used otherwise every single reference needs to be modified.  
Example:
```
\\in config file
...
config{
  property_1: "property_name_1",
  property_2: "property_name_2",
}
...
\\in js file
var property_name_1 = config.property_1;
obj[property_name_1]
```
Like all other functional proraming language, function is a first citizen and can be used as variable, so functions can be saved in the object and used just like property. The only difference is to add a pair of parentheses to force it to be executed. 
```
objTest{
  print: function(something){
    cosole.log(somthing)
  }
}
objTest.print('first line');
var func = objTest.print;
func('second line')
//result:
//first line
//second line
```
### object constructor 
In Js, constructor for an object is defiend as a function and, as example shown above, there is no access modifier.
```
function person(namem age){
  this.name = name
  this.age = age
}
var p = new person('frank', 18)
console.log(p.name)
console.log(p.age)
//result:
//frank
//18
```
