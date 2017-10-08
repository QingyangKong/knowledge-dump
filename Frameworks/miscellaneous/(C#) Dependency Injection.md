# Dependency Injection

In .Net frameowrk, app can be based on abstractions, the pattern that app s based on abstraction rather then concrete class is called dependency injection. Usually dependency injection is used in ASP.Net MVC to add services like log and authentication service, but it can be used in any app.  
## Definition of DI.  Use DI in .Net core console app:  
The process is: Create Inerfaces as services -> Create classes to implement interfaces-> add interface and concrete class in DI container.   
Create Interface to define service:
```
public interface IIntroduction
{
    void PrintIntroduction();
}
```
Implement the interface.
```
public class IntroductionDaeneryTargaryen : IIntroduction
{
    public void PrintIntroduction()
    {
        Console.WriteLine("Daenerys of the House Targaryen" +
            "\nthe first of her name \nthe unburnt, queen of the andals" +
            "\nthe rhoynar and the first men \nqueen of meereen" +
            "\nkhaleesi of the great grass sea \nprotector of the realm" +
            "\nlady regnant of the seven kingdoms \nbreaker of chains and mother of Dragons");
    }
}
```
```
public class IntroductionJonSnow : IIntroduction
{
    public void PrintIntroduction()
    {
        Console.Write("Jon Snow, First of His Name \nThe Bastard of Winterfell" +
                "\nThe White Wolf \nSworn Brother Guarding the Realms of Men" +
                "\nLord of the First Men and the Free Folk\n998th Lord Commander of the Nights Watch" +
                "\nDefender of the Wall \nThe Resurrected" +
                "\nSlayer of White Walkers and the Army of the Dead \nThe King in the North \nProtector of the Realm" +
                "\nRightful King of the Seven Kingdoms \nThe Prince Who Was Promised");
    }
}
```
Add services into service provider:
```
class Program
{
    private static IServiceProvider _serviceProvider;

    static void Main(string[] args)
    {
        var services = new ServiceCollection();
        services.AddSingleton<IIntroduction, IntroductionDaeneryTargaryen>();
        IIntroduction instance = new IntroductionJonSnow();
        //services.AddSingleton(instance);

        _serviceProvider = services.BuildServiceProvider();
        IIntroduction introService = _serviceProvider.GetService<IIntroduction>();
        introService.PrintIntroduction();

        Console.WriteLine("Test End");
        Console.ReadKey();
    }
}
```
## Use DI in ASP.Net

 AddTransient<Interface, class>()
 Transient objects are always different; a new instance is provided to every controller and every service.
 
 AddScoped<Interface, class>()
 Scoped objects are the same within a request, but different across different requests
 
 AddSingleton<Interface, class>()
 Singleton objects are the same for every object and every request (regardless of whether an instance is provided in ConfigureServices)
 Because the singleton is created once started and never changes, 
 the instance of class can directly provided as a parameter.
 eg:
 Interface A = new class();
 services.AddSingleton(A); equals to services.AddSingleton<Interface, class>;
