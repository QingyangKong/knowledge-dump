# CURD in Entity Framework
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
`Attach` means context will strat tracking this entity.  
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
Create an entry and then decide which property needs to be updated by marking it as modified.   
The reason se EntityState for the entry as `Unchanged` is to make the entry tracked by entityframework. State of the entry is `Detached` if they are not tracked and there is no way changing state of property when the whole entity's state is `Detached`.  

To set the whole entity as `Unchanged` and set the property as `Modified` when there is only one or two property updated. If there are a lot of properties changed, the best practice is to set entry as `Changed` and then set the column that is not supposed to be updated as false.  

Tips:  
When using attach or entry to update data in db, primary key must be provided and the PK must exist or error `Database operation expected to affect 1 row(s) but actually affected 0 row(s). Data may have been modified or deleted since entities were loaded` will be thrown.  
And primary cannot be modified, or `The property 'xxx' on entity type 'xx' is part of a key and so cannot be modified or marked as modified.` I think that is because entityframework is searching entry by primary key. The best practice to use it to write a stored procedure and let EF execute the stored procedure.
