<h1>Java version related configuration in eclipse</h1>
<h3>1. What is JRE System Library?</h3>
In eclipse, in every project, there is a JRE System Library attached. By change the JRE System Library it is allowed to change version of JRE used for the project. But actually, eclipse would not use the version indicated by JRE System Libarary unless the specific version is installed in environment. If the version is not installed, installed JDK of closest version would be used. For example, if I change property of JRE System Library and change it from 1.7 to 1.5 in an environment where only JDK 1.7 installed, the project still use lib of 1.7 rather than 1.5. But one instresting thing is the eclipse regards 1.5 is used in the project and may shoot the error `Java compiler level does not match the version installed java project facet` if the version of compiler of eclipse is not align with version of DEFINED JRE System Library.

<h3>2. What is java compiler level?</h3>
Java compiler compliance level can be set in the `window->preference->java->compiler`, and it is used to compile source codes into java classes. Eclipse has its own java compilers and do not use libs in JDK that is defined in JRE System Library for any project. The compiliance set here is equal to command "java -source xx". Be attention that version of JRE System Library for a project is the JRE used to run classes, while Java compiler level is to indicate version of Java compiler to compile source codes. When the version of JRE System Library set in project is not compatible with Java compiler level, there would be an error in eclipse problem perspective.

<h3>3. What is installed JRE?</h3>
In the window->preference->java->installed JRE, we are able to choose JRE/JDK version to run the java classes. All versions of JREs here should be installed in the environment and eclipse does have it in its own. java compiler mentioned in last question is used to compile codes and JRE here is to run the compiled classes. Be attention that JRE needs to be same or newer than the java compiler compliance level. 

<h3>4. What is java project facet for java?</h3>
Right click project and property->project facets, we can set versions in multiple facets in a project. Facet is a concept related to eclipse. In eclipse, there are multiple facets for a project and java is one of them. If you change the version of java here, version of JRE System Library would be change too. Right click JRE System Library and update version is just another way to change java facets of a project in eclipse.

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
