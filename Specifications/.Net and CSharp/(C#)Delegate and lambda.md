# Delegate and lambda expression
## Delegate
Delegate can defines paramters and return type and serve as a pointer to the function passed.  
Basic usage is shown below:  
```
public class Person
{
	public string FirstName { get; set; }
	public string LastName { get; set; }
	public bool IsMember { get; set; }
}

public delegate void PrintInfoDelegate(Person person);
public delegate string GetInformationDelegate(Person person);

public class Program
{
	public static void Main(string[] args)
	{
		Console.WriteLine("Test start");
		Person person = new Person
		{
			FirstName = "Qingyang",
			LastName = "Kong",
			IsMember = false
		};

		PrintInfoDelegate printDelegate = new PrintInfoDelegate(PrintPersonInformation);
		printDelegate(person);
		
		GetInformationDelegate getInformationDelegate = new GetInformationDelegate(GetJsonInfoPerson);
		string result = getInformationDelegate(person);
		Console.WriteLine(result);
		
		Console.ReadKey();
	}

	public static void PrintPersonInformation(Person p)
	{
		Console.WriteLine($"FirstName: {p.FirstName} \nLastName: {p.LastName}\nIsMember: {p.IsMember}");
	}

	public static string GetJsonInfoPerson(Person p)
	{
		return Newtonsoft.Json.JsonConvert.SerializeObject(p);
	}
}
```
In above codes, there are 2 delegates defined and assigned with 2 user defined method. User defined function can also be passed as anonymous function like lambda expression.
```
public delegate void PrintInfoDelegate(Person person);
public delegate string GetInformationDelegate(Person person);
public class Program
{
	public static void Main(string[] args)
	{
		Console.WriteLine("Test start");
		Person person = new Person
		{
			FirstName = "Qingyang",
			LastName = "Kong",
			IsMember = false
		};

		PrintInfoDelegate printDelegate = 
			new PrintInfoDelegate(x => 
				Console.WriteLine($"FirstName: {x.FirstName} \nLastName: {x.LastName}\nIsMember: {x.IsMember}"));
		printDelegate(person);

		GetInformationDelegate getInformationDelegate = 
			new GetInformationDelegate(x => Newtonsoft.Json.JsonConvert.SerializeObject(x));
		string result = getInformationDelegate(person);
		Console.WriteLine(result);

		Console.ReadKey();
	}
}
```
In C#, there are 2 keywords provided to faciliate delegate. Func is for the delegate that returns a value and Action is for void delegate. See codes below:
```
public class Program
{
public static void Main(string[] args)
{
    Console.WriteLine("Test start");
    Person person = new Person
    {
	FirstName = "Qingyang",
	LastName = "Kong",
	IsMember = false
    };

    Action<Person> printAction = 
    	new Action<Person>(x => 
		Console.WriteLine($"FirstName: {x.FirstName} \nLastName: {x.LastName}\nIsMember: {x.IsMember}"));
    printAction(person);

    Func<Person, string> getFunc = 
    	new Func<Person, string>(x => Newtonsoft.Json.JsonConvert.SerializeObject(x));
    string result = getFunc(person);
    Console.WriteLine(result);

    Console.ReadKey();
}
}
```
For all above codes, it is also possible to use function to do it without delegate, and using traditional function seems even easier than delegate. So what is the use case for delegate? The biggest advantage, as talked at first, is that delegate can be considered as function pointer, which means it can be listed as a prameter in another function. By doing this, user can pass function to another function.  
Because C# is not marked as a functional programming language, it is ususally thought of there is not feature of high-order funtion or curry(or whatever its name). Actually there is a feature called delegate provided in C#. By using delegate, it is possible to pass a defined function or lamda expression(anonymous function) into another function.  
See Example below:
```
//First define functions in Extenstions
public static class PersonExtensions
{
	public static void DoSomething(this Person person, Action<Person> action)
	{
		action(person);
	}
	public static string DoSomething(this Person person, Func<Person, string> func)
	{
		return func(person);
	}
}

public class Program
{
	public static void Main(string[] args)
	{
		Console.WriteLine("Test start");
		Person person = new Person
		{
			FirstName = "Qingyang",
			LastName = "Kong",
			IsMember = false
		};


		person.DoSomething(
			x => Console.WriteLine($"FirstName: {x.FirstName} \nLastName: {x.LastName}\nIsMember: {x.IsMember}"));
		var result = person.DoSomething(x => Newtonsoft.Json.JsonConvert.SerializeObject(x));

		Console.WriteLine(result);

		Console.ReadKey();
	}
}
```

LINQ in C# is implemented by this mechanism: delegate and partial class(somthing similar as extensions).
See source codes below:
```
public static IEnumerable<TSource> Where<TSource>(this IEnumerable<TSource> source, Func<TSource, bool> predicate) {
	if (source == null) throw Error.ArgumentNull("source");
	if (predicate == null) throw Error.ArgumentNull("predicate");
	if (source is Iterator<TSource>) return ((Iterator<TSource>)source).Where(predicate);
	if (source is TSource[]) return new WhereArrayIterator<TSource>((TSource[])source, predicate);
	if (source is List<TSource>) return new WhereListIterator<TSource>((List<TSource>)source, predicate);
	return new WhereEnumerableIterator<TSource>(source, predicate);
}
```
This is how Where defined in Enumerable, it will finally returns an instance of class WhereEnumrableIterator where a method MoveNext override.
predicate is passed in constructor and it is used in MoveNext to check every element in enumerator and return the element when predicate is true. By using the Func, enumerator can verfiy each element by using lambda expression passed in.
```
public override bool MoveNext() {
	switch (state) {
		case 1:
			enumerator = source.GetEnumerator();
			state = 2;
			goto case 2;
		case 2:
			while (enumerator.MoveNext()) {
				TSource item = enumerator.Current;
				if (predicate(item)) {
					current = item;//if the element in verfied by predicate, asign element to current and return true
					return true;
				}
			}
			Dispose();
			break;
	}
	return false;
}
```
Select use the same thing:
```
public static IEnumerable<TResult> Select<TSource, TResult>(this IEnumerable<TSource> source, Func<TSource, TResult> selector) {
	if (source == null) throw Error.ArgumentNull("source");
	if (selector == null) throw Error.ArgumentNull("selector");
	if (source is Iterator<TSource>) return ((Iterator<TSource>)source).Select(selector);
	if (source is TSource[]) return new WhereSelectArrayIterator<TSource, TResult>((TSource[])source, null, selector);
	if (source is List<TSource>) return new WhereSelectListIterator<TSource, TResult>((List<TSource>)source, null, selector);
	return new WhereSelectEnumerableIterator<TSource, TResult>(source, null, selector);
}


public override bool MoveNext() {
	switch (state) {
		case 1:
			enumerator = source.GetEnumerator();
			state = 2;
			goto case 2;
		case 2:
			while (enumerator.MoveNext()) {
				TSource item = enumerator.Current;
				if (predicate == null || predicate(item)) {
					current = selector(item);//assign the result of selector(current element) to current and return true
					return true;
				}
			}
			Dispose();
			break;
	}
	return false;
}
```
Test Question:
```
public static void Main(string[] args)
{
	Console.WriteLine("Test start");
	IEnumerable<string> strs = new List<string>() { "a", "b", "c" };
	var results = strs.Select(x => x == "a");
	foreach (var result in results)
	{
		Console.WriteLine(result);
	}

	Console.ReadKey();
}
```
result is {true, false, false}, because the select return result of selector(element), and the passed selector `x => x == "a"` only return bool.
