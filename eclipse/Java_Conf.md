<h1>Java version related configuration in eclipse</h1>
For any java project, 2 things need to be set: java runtime environment and java compiler. I am going to illustrate how to configure these 2 setting in the eclipse IDE in this article.

<h3>1. What is java compiler level?</h3>
It is version of compiler to compile source codes. Java compiler compliance level can be set in the `window->preference->java->compiler`, and it is used to compile source codes into java classes. Eclipse has its own java compilers. The compiliance level is equal to command "java -source xx".

<h3>2. What is installed JRE?</h3>
In the `window->preference->java->installed JRE`, we are able to choose JRE version to run the java classes. All versions of JREs here should be installed in the environment and eclipse doesn't have it in its own. Java compiler mentioned in last question is used to compile source codes and JRE here is to run the compiled classes. Be attention that version of installed JRE is used to run classes, while Java compiler level is to indicate version of Java compiler to compile source codes. 
Installed JREs are basically paths to JRE home used by eclipse, and installed JREs cannot be set to a specific version unless the version installed.  

<h3>3. What is JRE System Library?</h3>
It is version of Java runtime for a specific project. In eclipse, there is a JRE System Library attached for each project. By changing the JRE System Library it is allowed to change version of JRE used for this project. But actually, eclipse would not use the version indicated by JRE System Libarary unless the specific version is set in the `installed JRE` mentioned in Q2. If the version is not installed, closest installed version would be chosen. For example, if I open property of JRE System Library and change it from 1.7 to 1.5 in an environment where only JRE 1.7 set, the project still use 1.7 rather than 1.5.  
But one instresting thing is the eclipse regards 1.5 is used in the project and may shoot the error `Java compiler level does not match the version installed java project facet`.  
In addition, JRE attached to a project needs to be same or newer than the java compiler compliance level. When the version of JRE System Library set in project is not compatible with Java compiler level, there would be an error in eclipse problem perspective.

<h3>4. What is java project facet for java?</h3>
Facet is a pure eclipse concept and it is equal to JRE System Library. Right click project and property->project facets, we can set versions in multiple facets in a project. In eclipse, there are multiple facets for a project and java is one of them. If we change the version of java here, version of JRE System Library would be changed too. Right click JRE System Library and update version is just another way to change java facets of a project in eclipse.

<h3>5. Set version globally</h3>
Set default installed JRE at `window->preference->java->installed JRE` to set the Java runtime version globally.  
Set Java compiler compliance level at `window->preference->java->compiler` to set Java compiler version globally.  
By doing this, all new created projects would use these versions.  

<h3>6. Set version for a project</h3>
Instead of using global settings, sometimes we need to set specific runtime and compiler versions for specific project.  
Change JRE System Library or change java in project facet to change Java runtime version for a particular project.  
Change Java compiler level in the `properties` of project to set compiler level for a particular project.  

<h3>Attention</h3>
For every project, no matter global or specific version of runtime and compiler used, runtime version must be same or newer thant compiler version.  

<h3>Small Tip for maven:</h3>
Default version of java in Maven.
In maven, there is a plugin called maven-compiler-plugin defining compiler source and target. Default version of this plugin is 1.5 so there is nothing set for the plugin, version of JRE System Library would be set as 1.5 when update project through maven. And then problem like "Java compiler version mismatch".Way to set is add 
```
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-compiler-plugin</artifactId>
	<version>${versionNumber}</version>
	<configuration>
		<source>${sourceVersion}</source>
		<target>${targetVersion}</target>
	</configuration>
</plugin>
```
${versionNumber} can be found in maven available plugins: https://maven.apache.org/plugins/ 
