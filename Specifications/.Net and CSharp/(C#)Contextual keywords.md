## Property and field
Developers from other programing language may not be familiar with definitions of property and field in C#. In nutshell, field is a variable that should always keep private within a class, and property is an abstraction to allow users to set and retrieve a field by accessors `get` and `set`.  
Example:  
```
class Person
{
   private string _firstName; 
   public string FirstName
   {
       get
       {
           return _firstName;
       }
       set
       {
           _firstName = value;
       }
   }
}
```
`_firstName` is a field and `FirstName` is a property with 2 accessros `get` and `set`.  
Usually it is not nececssary to define a field explicitly if there is no other manipulations except for simple set and get value, because property can be auto-implemented.  
So the short hand for codes above is like:
```
class Person
{
   public string FirstName{ get; set; }
}
```
## get set and value
These are 3 contextual key words.
`get` and `set` are accessors that can return and set private field. `value` references code that client code is attempting to assign to a property.  
Example:
```
class Person
{
    private string _firstName;
    public string FirstName
    {
        get
        {
            return string.Format("First name is :{0}", _firstName);
        }
        set
        {
            _firstName = value != null ? 
                char.ToUpper(value[0]) + value.Substring(1): string.Empty;
        }
    }

    public string LastName
    {
        get;set;
    }
}
 ```
 test with codes:
 ```
 Person p = new Person();
 p.FirstName = "qingyang";
 Console.WriteLine(p.FirstName);
 Console.ReadKey();
 ```
 result:
 ```
 First name is :Qingyang
 ```
More can be checked here: https://docs.microsoft.com/en-us/dotnet/csharp/language-reference/keywords/contextual-keywords
