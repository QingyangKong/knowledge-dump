# Flow Control
## keyword GO
  
GO signals the end of a batch of Transact-SQL statements to the SQL Server utilities.  
eg:
```
ALTER TABLE [dbo].[REQUEST] ADD [REQUSET_STATUS] varchar(30)
UPDATE TABLE [dbo].[REQUEST] SET [REQUSET_STATUS] = 'ENDED'
```
When execute these 2 commands in one batch, they won't work but throw the error "Invalid column name 'REQUSET_STATUS'." because the column is not recgonized by SQL server.
use it in this way:
```
ALTER TABLE [dbo].[REQUEST] ADD [REQUSET_STATUS] varchar(30)
GO
UPDATE TABLE [dbo].[REQUEST] SET [REQUSET_STATUS] = 'ENDED'
GO
```
After the first 'GO', the batch is ended and then the column could be recgonized when second command runs.  
Please be attention, `GO` is not a T-SQL syntax but used by SSMS to submit scripts batch by batch.  

## Keyword BEGIN END
BEGIN and END are like "{}" in programing language
BEGIN and END are like a pair of {} in other programing language, and can be added for a execution but not really used in most cases. It is necessary only after the IF loop and some flow-control clause.
eg:
Create a table if not exists, and insert 1 record. 
```
IF NOT EXISTS (
  SELECT * 
  FROM sysobjects 
  WHERE name='table_name' 
		AND xtype='U'
)
BEGIN
	CREATE TABLE table_name(
		[ID] [int] NOT NULL,
		PRIMARY KEY CLUSTERED 
		(
  			[FILE_TYPE_ID] ASC
  		)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
  	) ON [PRIMARY]
  
  	INSERT INTO table_name VALUES 1
  END
```  
Table created and record inserted successfully by script above but if I remove BEGIN and END and run that again. 
```
IF NOT EXISTS (
  SELECT * 
  FROM sysobjects 
  WHERE name='table_name' 
		AND xtype='U'
)
CREATE TABLE table_name(
	[ID] [int] NOT NULL,
	PRIMARY KEY CLUSTERED 
	(
		[FILE_TYPE_ID] ASC
	)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]

INSERT INTO table_name VALUES 1
```
It will throw error "cannot insert duplicate key in object", and it is because there is only one statement after if clause is in scope and the second statemetn is not controled by if clause.  

GO cannot be used in BEGIN END because GO is not a syntax in T-SQL and it is only used by ssms ot other tool.
It cannot be compiled, and it just tell compiler to execute batch by batch.
