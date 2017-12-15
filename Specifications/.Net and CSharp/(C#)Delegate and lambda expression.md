# Delegate and lambda expression
## Delegate
Delegate can defines paramters and return type and serve as a pointer to the function passed. Basic usage is shown below:  
```
public class Program
{
	public static string combineStrings(string str1, string str2)
	{
		return str1 + str2;
	}

	public static void Main(string[] args)
	{

		SampleDelegate myDelegate  = new SampleDelegate(combineStrings);
		Console.WriteLine(myDelegate("qingyang", "kong"));
	}
}

//result: qingyangkong
```
Because C# is not marked as a functional programming language, it is ususally thought of there is not feature of high-order funtion or curry(or whatever its name). Actually there is a feature called delegate provided in C#. By using delegate, it is possible to pass a defined function or lamda expression(anonymous function) into another function.  
