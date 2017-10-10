# Save a file by Entity Framework

## Save as binary
To save file as binary, the datatype should be `varbinary(max)` in database and `byte[]` in entity.   
Sample code:  
```
private static void SaveFile(string filePath)
{
    byte[] fileBytes = System.IO.File.ReadAllBytes(filePath);
    
    RequestFile requestFile = new RequestFile
    {
        FileId = 1,
        RequestId = 1,
        File = fileBytes
    };
    
    using (var context = new EntityFrameworkTestDBContext())
    {
        context.Add(requestFile);
        context.SaveChanges();
    }
}
```
If encryption is required, usually it will encrypt byte array. eg AES Encryption.  
This pair of keys can be saved in config of the project and used to encrypt when upload and decrypt when download.
