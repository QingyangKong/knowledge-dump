Avoid to create a table with default values, because the default value would be saved as a constraint. The constraint must be removed first if use wants to remove the table. To remove constraint, must know its name but this name is dynamic. It is not easy to write the script somethimes especially for Prod db.  

If the constraint is already there, they can be removed with script:
```
declare @constraint_name varchar(50)
SELECT @constraint_name = df.name
FROM sys.default_constraints df
INNER JOIN sys.tables t ON df.parent_object_id = t.object_id
INNER JOIN sys.columns c ON df.parent_object_id = c.object_id AND df.parent_column_id = c.column_id 
where t.name='Table_Name' and c.name='Column_Name'

Exec ('ALTER TABLE Table_Name ' + @constraint_name);
ALTER TABLE Table_Name DROP COLUMN Column_Name;
```
Be attention `ALTER TABLE Table_Name @constraint_name` won't work because `@constraint_name` will be interpreted as a string with value "@constraint_name".
