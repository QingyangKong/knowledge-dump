## Project Structure
When downgrade java version in Intellij, there is quite a lot of changes.  
First `file -> project structure -> project -> project SDK`, and this setting decides which java version your IDE is going to use to compile.  
When you downgrade from java 8 to java 7 for example, you are also required to change module's settings too because it seems that IDE will not do it for you automatically.  
`file -> project structure -> Modules -> sub project name -> source and dependency tabs`.  
## Settings
And also go to `file -> settings -> Build, Execution, Deployment -> Java Compiler` to change the setting version. 

If you just want to check grammer in IDE without compiling it, change the setting in `file -> project structure -> Modules -> sub project name -> source` is enough.  
