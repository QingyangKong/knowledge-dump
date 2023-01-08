`async` means the task is going to be executed asynchronously with main thread.
Any method prefixed with keyword async is regarded as an asynchronous task. For an asynchronous task, it is supposed to have keyword `await`. For a method with `async` and `await`, it will be executed in the main thread till keyword `await`. When `await` comes, the method will return the handler to the main thread and wait for the result.   
Data type of the handler could be `Task`, `Task<T>` and `void`.   
+ When there is nothing to be returned, and you don't care if it is successful, use `void` as return type.  
+ When there is nothing to be returned but you want to the result of the async method, use `Task` as a return type. Use keyword `wait` to wait the task to be done and get status to know if it is successful.   
+ If there is something supposed to be returned, use `Task<T>` to return the handler. To see the value, use `wait` to wait to the end of task and get result from the returned task. Pleas be attention that call result for a task will enforce a wait.  

Example:
```C#
public static async Task<int> GetWebsiteLength()
{
	var httpClient = new HttpClient();
	int exampleInt = (await httpClient.GetStringAsync("http://msdn.microsoft.com")).Length;
	Console.WriteLine("running in the method getWebsiteLength");
	return exampleInt;
}

public static void Main(string[] args)
{
	Console.WriteLine("Test start");
	var s = GetWebsiteLength();
	Console.WriteLine("Processing");
	Console.WriteLine("Test end with result {0}", s.Status);
	s.Wait();
	Console.WriteLine("Test end with result {0}", s.Status);
	Console.WriteLine("Test end with result {0}", s.Result);
}
```
result:
```
Test start
Processing
Test end with result WaitingForActivation
running in the method getWebsiteLength
Test end with result RanToCompletion
Test end with result 41097
```
