# CURD in Entity Framework
## Create
Create an entity and then add it by context.
```
private static void AddItem()
{
    Request request = new Request { requestId = 1 };
    request.clientId = 1;
    request.updateDate = DateTime.Now;
    request.creatdBy = 1;
    using (var context = new EntityFrameworkTestDBContext())
    {
        context.Add(request);
        context.SaveChanges();
    }
}
```
Basically, states of entry in context are detached, added, detached. See the sample codes below 
```
private static void AddItemByEntry()
{
    Request request = new Request { requestId = 1 };
    request.clientId = 1;
    request.updateDate = DateTime.Now;
    request.creatdBy = 1;
    using (var context = new EntityFrameworkTestDBContext())
    {                
        var entry = context.Entry(request);
        Console.WriteLine(entry.State);
        entry.State = Microsoft.EntityFrameworkCore.EntityState.Added;
        Console.WriteLine(entry.State);
        context.SaveChanges();
        Console.WriteLine(entry.State);
    }
}
```
Result is:
```
Detached
Added
Unchanged
```

## Update
```
private static void UpdateByAttatch()
{
    using (var context = new EntityFrameworkTestDBContext())
    {
        var request = new Request {
            requestId = 1
        };
        context.requests.Attach(request);
        request.updateDate = DateTime.Now;
        request.creatdBy = 1;
        context.SaveChanges();
    }
}
```
`Attach` means context will strat tracking this entity. State for update is detached, modified and unchanged. 
```
private static void UpdateByEntry()
{
    using (var context = new EntityFrameworkTestDBContext())
    {
        var request = new Request
        {
            requestId = 1
        };
        var entry = context.Entry(request);
        entry.State = Microsoft.EntityFrameworkCore.EntityState.Unchanged;
        request.updateDate = DateTime.Now;
        entry.Property(x => x.updateDate).IsModified = true;
        context.SaveChanges();
    }
}
```
The reason se EntityState for the entry as `Unchanged` is to make the entry tracked by entityframework. State of the entry is `Detached` if they are not tracked and there is no way changing state of property when the whole entity's state is `Detached`.   

To set the whole entity as `Unchanged` and set the property as `Modified` when there is only one or two property updated. If there are a lot of properties changed, the best practice should be setting entry as `Changed` and then set the column that is not supposed to be updated as false.  

## Read
Nothing special.
```
private static void ReadRequest()
{
    using (var context = new EntityFrameworkTestDBContext())
    {
        var requests = context.requests;
        foreach (var request in requests)
        {
            Console.WriteLine(request.requestId);
        }
    }
}
```
## Delete
```
private static void RemoveRequest()
{
    Request request = new Request
    {
        requestId = 1
    };
    using (var context = new EntityFrameworkTestDBContext())
    {
        var entry = context.Entry(request);
        context.Remove(request);
        context.SaveChanges();
    }
}
```
State of entry is detached, deleted and detached.
```
private static void RemoveRequestByEntry()
{
    Request request = new Request
    {
        requestId = 1
    };

    using (var context = new EntityFrameworkTestDBContext())
    {
        var entry = context.Entry(request);
        Console.WriteLine(entry.State);
        entry.State = Microsoft.EntityFrameworkCore.EntityState.Deleted;
        Console.WriteLine(entry.State);
        context.SaveChanges();
        Console.WriteLine(entry.State);
    }
}
```
result is:
```
Detached
Deleted
Detached
```

## conclusion
### State 
For whatever create, update, delete, the very first thing is to make the property supposed to be changed be tracked by context. This can be done by `context.add(entity)`, `context.attch(entity)` and `context.delete(entity)`, and it can also be accomplished by changing state directly. States of tracked and to be changed: `added`, `deleted` and `attached`. State of tracked `unchanged`. State of not tracked `detached`.    

### PK  
PK must exist when update and delete record in DB otherwise error `Database operation expected to affect 1 row(s) but actually affected 0 row(s). Data may have been modified or deleted since entities were loaded` will be thrown. PK is not required when add an entry in a table with PK auto increment.  
PK cannot be modified, or `The property 'xxx' on entity type 'xx' is part of a key and so cannot be modified or marked as modified.` I think that is because entityframework is searching entry by primary key. The best practice to use it to write a stored procedure and let EF execute the stored procedure.
