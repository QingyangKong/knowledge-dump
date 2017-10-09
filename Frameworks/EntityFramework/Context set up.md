# Create DBContext
## Explicitly create context.  
Override `OnModelCreating` and `OnConfiguring`.  
Sample Code:  
```
public class EntityFrameworkTestDBContext: DbContext
    {
        public DbSet<Request> requests { get; set; }

        //method OnModelCreating is only called when models are created not at the time point when the context is instantiated.
        protected override void OnModelCreating(ModelBuilder modelBuilder) {
        
            modelBuilder.Entity<Request>().ToTable("REQUEST");
            modelBuilder.Entity<Request>().HasKey(x => x.requestId);
            modelBuilder.Entity<Request>().Property(x => x.requestId).HasColumnName("REQUEST_ID");
            modelBuilder.Entity<Request>().Property(x => x.clientId).HasColumnName("CLIENT_ID");
            modelBuilder.Entity<Request>().Property(x => x.creatdBy).HasColumnName("CREATED_BY");
            modelBuilder.Entity<Request>().Property(x => x.updateDate).HasColumnName("UPDATE_DATE");

            base.OnModelCreating(modelBuilder);
        }
        
        //method Onconfiguring is called when the context is instantiated.
        protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder) {
            var cxnStr = ConfigurationManager.AppSettings["ConnectionString"];
            optionsBuilder.UseSqlServer(cxnStr);
            base.OnConfiguring(optionsBuilder);
        }
    }
```
Error "Instance failure" happens because the sql server instance is not set up properly.  
After defining OnConfiguring, DbContexxt can be instantiated directly without any parameter.
```
...
var context = new EntityFrameworkTestDBContext();
...
```
## Use Constructor with DbContextOptions
To be more general, the context can be instantiated by the constructor inherited from super class `DbContext` rather then default zero-parameter constructor. What we need to provide is to create DbContext by providing DbContextOptions. Be attention, DbContextOptions is for entityframeworkcore, connection string is used in the EntityFramework 6.
```
public class DataContext: DbContext
{
    public DataContext(DbContextOptions dbContextOptions):base(dbContextOptions){}
    ...
}
```
Make the custom context's constructor inherit from DbContext's constructor and create a context by passing DbContextOptions. In this scenario, OnConfiguring is not necessary because DbContextOptions is provided when create context as below:
```
...
DataContext dataContext = new DataContext(contextOptions);
var results = dataContext.requests.ToList();
...
```
## Use Context in dependency injection pattern
Usually in a ASP.net because context will be used many times, we register it as a service by using Dependency Injection.  Code is lke below:  
```
...
private static IServiceProvider _serviceProvider;
...
...
var contextOptions = new DbContextOptionsBuilder()
                .UseSqlServer(connectionStr)
                .Options;


var services = new ServiceCollection();
services.AddSingleton(contextOptions);
services.AddScoped<DataContext>();

_serviceProvider = services.BuildServiceProvider();
...
```
Use it by getting a singleton from IServiceProvider.
```
...
var context = _serviceProvider.GetService<DataContext>();
...

```
## decouple `IEntityTypeConfiguration`
Create entity.
```
public class Request
{
    public int RequestId { get; set; }
    public int CreatedBy { get; set; }
    public DateTime UpdateDate { get; set; }
    public int ClientId { get; set; }
}
```
Create EntityTypeConfiguration with the entity.
```
public class RequestMappings : IEntityTypeConfiguration<Request>
{
    public void Configure(EntityTypeBuilder<Request> builder)
    {
        builder.ToTable("REQUEST");
        builder.HasKey(x => x.RequestId);
        builder.Property(x => x.RequestId).HasColumnName("REQUEST_ID");
        builder.Property(x => x.ClientId).HasColumnName("CLIENT_ID");
        builder.Property(x => x.CreatedBy).HasColumnName("CREATED_BY");
        builder.Property(x => x.UpdateDate).HasColumnName("UPDATE_DATE");
    }
}
```
At last register this in `OnModelCreating()` in context.
```
protected override void OnModelCreating(ModelBuilder modelBuilder)
{
    modelBuilder.ApplyConfiguration(new RequestMappings());
}
``
