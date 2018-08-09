# Avoid `import xx.xx.*` in file

Sometimes it is annoying to use automatic import in IDE because some checkStyle will ask to import packages one by one and some people wants to import it one by one for readability.   
This is how to settings in IDE to shutdown the auto whole package imports.

File -> Settings -> Code Style -> language(assume Java) -> Class count to use import with '*'  
File -> Settings -> Code Style -> language(assume Java) -> Names count to use import with '*'

modify the number to a large number like 100 in order to avoid IDE to use * in import statements. 
