## Constructor and inheritance in javascript
Basic features in OOP are implemented in Javascript bu using prototype. There are 2 types of prototypes: function prototype and object prototype.  
Function prototype: A function prototype is an object instance that will become the proptotype of all objects created using the function as a constructor.  
Object prototype: An object prototype is an object instance from which the object is inherited.  
`functionaName.prototype` and `objectName.__proto__` show 2 types of proptotypes above.  
### Constructor
Constructor is nothing but a function but used with keyword `new`.
```
function Person(name, age){
	this.name = name
	this.age = age
}

Person.prototype.isMember = true
var frank = new Person('frank', 18)
console.log(frank.name)       //frank
console.log(frank.age)        //18

//When check isMember, it is not a property of object frank. But it can be printed out. 
//That is because JS will check prototype property if it is not found it in instance properties whitin the object.
console.log(frank.hasOwnProperty('isMember'))   //false
console.log(frank.isMember)                     //true

//Whether the prototype property exists has no influence on instance property.
//Be attention, 'isMember' printed out is an instance property instead of a prototype property now.
frank.isMember = false
console.log(frank.isMember)                     //false
console.log(frank.hasOwnProperty('isMember'))   //true

//Prototype property can be changed after the object is created by prototype property or __proto__.
//Be attention, by changing prototype proeprty in constructor, all object based on the constructor will be changed.
//I guess that is because all prototype proeprty in objects refers to constructor's prototype property.
var mark = new Person('Mark', 20)
console.log(mark.isMember)              //true
Person.prototype.isMemeber = false      
console.log(mark.isMember)              //false
```

### Inheritance
All prototype properties can be inherited by other 
function Employee(){
}
Employee.prototype.companyName = "Frank's company"

function Manager(name){
	this.name = name
}
Manager.prototype = Object.create(Employee.prototype)
Manager.prototype.title = 'Manager'

var manager = new Manager('frank')
console.log(manager.name)             //frank
console.log(manager.title)            //Manager
console.log(manager.companyName)      //Frank's company
