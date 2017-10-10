In the Visual studio, click `tool` -> `Nuget Package Manager` -> `package manager console` to open the console and execute commands.  
Be attention:  
Working directory in console is opened at solution folder rather than the project folder. In this condition, there are some commands depending on project conf cannot be run.  

eg: `npm install`, this command requires `package.json` but this file is under the project folder.  
You need use `cd` to change to project directory manually.  
