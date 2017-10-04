```
string str = "assign 1";
var i = str == "assign 1" ? 1 :"2";
```
Error `type of conditional expression cannot be determined` will be thrown because operation expression must have a return type so that 2 options after question mark have to be able to be the same or convertable.  

This is the correct one:  
```
string str = "assign 1";
var i = str == "assign 1" ? 1 : (object) "2";
```
