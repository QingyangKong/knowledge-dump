# Prototype Basics

First let's see what is the problem of constructor.
```
function Human(firstName, lastName) {
	this.firstName = firstName;
	this.lastName = lastName;
	this.fullName = function() {
		return this.firstName + this.lastName;
	}
}

var h1 = new Human("Qingyang", "Kong");
var h2 = new Human("XiaoOu", "Chen");

console.log(h1.fullName());
console.log(h2.fullName());
```
For h1 and h2, they hold copies of fullName in their own. It is waste of resource because the funtion does the same thing.


In order to make multiple objects share the same memory, prototype can be used to write the common part(methods and attributes).
```
function Human() {
}

Human.prototype.firstName = "default firstName";
Human.prototype.lastName = "deafult lastName";
Human.prototype.fullName = function() {
	return this.firstName + " " + this.lastName;
}

var h1 = new Human();
var h2 = new Human();
h1.firstName = "Qingyang"
h1.lastName = "Kong"
console.log(h1.fullName());
console.log(h2.fullName());
```

But problem comes for prototype when there is a property of reference type. In the scenario, when one object change the reference, all other objs get changed.
```
function Person() {
}

Person.prototype.friends = ['frend_1', 'friend_2'];
var p1 = new Person();
var p2 = new Person();
console.log(p2.friends)
p1.friends.push('friend_3');
console.log(p2.friends)
```

So the best practice is to define properties inside the constructor and allsahred properties and methods in the prototype.
```
function Human(firstName, lastName){
	this.firstName = firstName;
	this.lastName = lastName;
	this.friends = ['friend_1', 'friend_2'];
}
Human.prototype.fullName = function() {
	return this.firstName + " " +  this.lastName;
}
var h1 = new Human('Qingyang', 'Kong');
var h2 = new Human('XiaoOu', 'Chen');
console.log(h1.fullName());
console.log(h2.fullName());
console.log(h2.friends);
h1.friends.push('friend_3');
console.log(h2.friends);
```
