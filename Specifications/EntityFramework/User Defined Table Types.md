# User Defined Table
## Why use User Defined Table
In a project, data transfer object(DTO) is usually used to aggregate data to pass around in calls. The reason to use DTO is to reduce number of calls.  
Entityframework serves as a bridge between app(may be a service, web backend, console app, etc) and database. In some case, there might be a requriement that a bunch of data is better to be bundled together and transferred in one call. DTO can be used to agrregate data in this way, and User_Defined_table is like DTO in database side to get information in DTO passed by entityframework.  
## Architecture
<b>Object</b>(DTO) &nbsp;&nbsp; -> &nbsp;&nbsp; <b>DataTable</b>(a data type can be recgonized by db) &nbsp;&nbsp; -> &nbsp;&nbsp; <b>SqlParameter</b>(datatable with other properties)&nbsp; -> &nbsp;&nbsp;<b>user defined table</b>(DTO in db side) &nbsp;&nbsp;-> &nbsp;&nbsp;&nbsp;<b>stored procedure</b>(function in db side) &nbsp;&nbsp;->&nbsp;&nbsp; <b>table</b>(data saved in db)
## Example
### In DB side, Create User Defined Table and SP in DB
Create a user table called RequestTable which contains 3 properties:
```
USE [EntityFrameworkTest]
GO

/****** Object:  UserDefinedTableType [dbo].[RequestTable]    Script Date: 10/8/2017 9:59:14 AM ******/
CREATE TYPE [dbo].[RequestTable] AS TABLE(
	[CREATED_BY] [int] NOT NULL,
	[UPDATE_DATE] [datetime] NULL,
	[CLIENT_ID] [int] NULL
)
GO
```
Create a stored procedure to save this user defined table in table. Save the user defined table into table [dbo].[request] and return the requestId. RequestId is primary key in table request with `identity(1,1)`. 
```
...
CREATE PROCEDURE [dbo].[SaveRequest]( 
	@p_REQUEST [dbo].[RequestTable] READONLY, 
	@p_REQUEST_ID int out
	)
AS
BEGIN
	SET NOCOUNT ON;

	DECLARE @v_REQUEST_ID int

	INSERT INTO [dbo].[REQUEST] 
	(
		CREATED_BY,
		UPDATE_DATE,
		CLIENT_ID
	)
	SELECT 
		CREATED_BY,
		UPDATE_DATE,
		CLIENT_ID
	FROM @p_REQUEST

	SELECT @v_REQUEST_ID = SCOPE_IDENTITY();
	SELECT @p_REQUEST_ID = @v_REQUEST_ID;
END
GO
```
### In App side, convert object info to datatable
Write a method to convert class to datatable
```
...
var requestTable = new System.Data.DataTable();

requestTable.Columns.Add(new System.Data.DataColumn
{
    ColumnName = "CREATED_BY",
    AllowDBNull = false,
    DataType = typeof(int),
});
requestTable.Columns.Add(new System.Data.DataColumn
{
    ColumnName = "UPDATED_DATE",
    AllowDBNull = true,
    DataType = typeof(DateTime)
});
requestTable.Columns.Add(new System.Data.DataColumn
{
    ColumnName = "CLIENT_ID",
    AllowDBNull = false,
    DataType = typeof(int)
});

var row = requestTable.NewRow();
row["CREATED_BY"] = requestToSave.CreatedBy != null ? requestToSave.CreatedBy : (object) DBNull.Value;
row["UPDATED_DATE"] = requestToSave.UpdateDate != null ? requestToSave.UpdateDate : (object) DBNull.Value;
row["CLIENT_ID"] = requestToSave.ClientId;
requestTable.Rows.Add(row);
...
```

### In Entity frameowrk, create a method to execute stored procedure
```
public int SaveRequestAndReturnRequestId(DataTable request)
{
    var requestParam = new SqlParameter
    {
        ParameterName = "p_REQUEST",
        Direction = ParameterDirection.Input,
        IsNullable = false,
        SqlDbType = SqlDbType.Structured,
        Value = request,
        TypeName = "dbo.RequestTable"
    };

    var requestIdParam = new SqlParameter
    {
        ParameterName = "p_REQUEST_ID",
        Direction = ParameterDirection.Output,
        IsNullable = false,
        SqlDbType = SqlDbType.Int,
    };
    Database.ExecuteSqlCommand("dbo.SaveRequest @p_REQUEST, @p_REQUEST_ID out", requestParam, requestIdParam);
    return (int)requestIdParam.Value;
}
```
## Attention
1. Keep nullablity consistent. When create SqlParamter, make sure that nullablilty of the column in datatable the same as column in user defined table. Same for table and user-defined-table. 
eg:  
In user defined table, if `[CREATED_BY] [int] NOT NULL` cannot be null, this column in data table cannot be null(`AllowDBNull = false`).
```
CREATE TYPE [dbo].[RequestTable] AS TABLE(
	[CREATED_BY] [int] NOT NULL,
	[UPDATE_DATE] [datetime] NULL,
	[CLIENT_ID] [int] NULL
)
```
```
requestTable.Columns.Add(new System.Data.DataColumn
{
    ColumnName = "CREATED_BY",
    AllowDBNull = false,
    DataType = typeof(int),
});
```
2. Make sure datatype passed aroung can be recgonized by each part. When assign variable to column in data table column, make sure the datatype is DBNull instead of null. Type null is not able to be used by database.
```
row["UPDATED_DATE"] = requestToSave.UpdateDate != null ? requestToSave.UpdateDate : (object) DBNull.Value;
```
The reason that `UpdateDate` cannot be assigned directly is because `null` is not able to be used by database and must be convert to `DBNull` first.  
## bad thing
When use user defined table, code could be very clean but it is relatively hard to maintain becasue to keep consistency is trivial and when table changed, a new user defined table must be changed also. So just use this machnism when the data type is very stable.  
