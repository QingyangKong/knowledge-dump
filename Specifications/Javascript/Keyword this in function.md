# Keyword this in function
Keyword `this` is always used in a method or a function although it can be used in global. When this used in a function, `this` contains the value of object that invokes the function. `this` is used to access the properties and functions whithin the object called the function. See example below:
```
var person = {
  firstName: 'Qingyang',
  lastName: 'Kong',
  printFullName: function() {
    console.log(this.firstName + ' ' + lastName);
  }
}

person.printFullName(); //print "Qingyang Kong"
```
In the example, the object that calls printFullName is person so `this` can be used to access firstName and lastName saved in the object person.   
Please see another example:
```
function printFullName() {
  console.log(this);
}
printFullName();//print all valeu for object window
```
In the example, the object that calls the fnction is window so that all value in window is printed.
