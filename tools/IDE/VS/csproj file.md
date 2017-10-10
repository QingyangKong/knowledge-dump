
In projects based on .net core 2. Project.json is not recommended, csproj is supposed to use.  
Right click the project and select edit `{project}.csproj` to add or remove dependencies.  
eg:
```
<Project Sdk="Microsoft.NET.Sdk">

  <PropertyGroup>
    <TargetFramework>netcoreapp2.0</TargetFramework>
  </PropertyGroup>

  <ItemGroup>
    <PackageReference Include="Microsoft.EntityFrameworkCore" Version="2.0.0" />
    <PackageReference Include="Microsoft.EntityFrameworkCore.SqlServer" Version="2.0.0" />
  </ItemGroup>

</Project>
```
