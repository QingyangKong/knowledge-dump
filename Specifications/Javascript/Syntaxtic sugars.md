### triple dots:
It can include all arguments into an array.
```
"use strict"

function countLength (... args){
    return args.length;
}

var len = countLength(["apple", "pear"]);
console.log(len);
len = countLength("apple", "pear")
console.log(len);
//results
//1
//2
//first output is 1 because there is only one arguemnt althogh it is an array 
//in this scenario, the ... args for it is [["apple", "pear"]], so the result is 1
```
Actually, `...` is not to include anything, it spread a variable into multiple parameters. In example above, it `...` could be thought of as to spread array `args` across multiple parameters passed. See the example below:
```
"use strict"
var arr = [1, 2, 3];
var arr_new = [arr, 4, 5, 6]
console.log(arr_new)
//result:
//[ [ 1, 2, 3 ], 4, 5, 6 ]
```
Elements in array `arr` is still in an array.
```
"use strict"
var arr = [1, 2, 3];
var arr_new = [...arr, 4, 5, 6]
console.log(arr_new)
//result:
//[ 1, 2, 3, 4, 5, 6 ]
```
This time elements in array `arr` are spread out.
### arguments
```
function getElements(){
    var len = arguments.length;
    console.log(arguments);
}

getElements("apple", "pear");
getElements();
//results:
//{ '0': 'apple', '1': 'pear' }
//{}
```
There is no need to define a function with arguments(I think this feature is very bad). All arguments passed to a function in javascript is zipped into a map.
