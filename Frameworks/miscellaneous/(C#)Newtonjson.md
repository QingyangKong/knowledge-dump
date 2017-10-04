## Newtonsoft.json 
It is a dependency that helps to convert from json string to objects.   
It is imported in .Net by this statement: `<package id="Newtonsoft.Json" version="10.0.3" targetFramework="net462" />`  


Sample code:  
### Convert the single JSON object:
```
public class Person
{
    public string FirstName { get; set; }
    public string LastName { get; set; }
    public int age { get; set; }
}

...
var jsonObj = "{\"FirstName\": \"fname_1\", \"LastName\": \"lname_1\", \"age\": \"30\", \"telPhone\":\"123\"}";
var obj = Newtonsoft.Json.JsonConvert.DeserializeObject<Person>(jsonObj);
Console.WriteLine("FirstName: {0}, LastName: {1}, Age: {2}", obj.FirstName, obj.LastName, obj.age);
...
```
output:
```
FirstName: fname_1, LastName: lname_1, Age: 30
```  


### Convert a JSON string:
```            
var json = "[{\"FirstName\": \"fname_1\", \"LastName\": \"lname_1\", \"age\": \"30\", \"telPhone\":\"123\"}, {\"FirstName\": \"fname_2\", \"LastName\": \"lname_2\", \"age\": \"28\", \"telPhone\":\"456\"}]";
var objs = Newtonsoft.Json.JsonConvert.DeserializeObject<IEnumerable<Person>>(json);
foreach (var ob in objs)
{
    Console.WriteLine("FirstName: {0}, LastName: {1}, Age: {2}", ob.FirstName, ob.LastName, ob.age);
}
```
output:
```
FirstName: fname_1, LastName: lname_1, Age: 30
FirstName: fname_2, LastName: lname_2, Age: 28
```
