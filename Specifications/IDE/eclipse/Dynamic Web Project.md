# Create dynamic web project in eclipse
1 Create a maven project  
2 Add dependencies
```
  <dependencies>
  	<dependency>
	    <groupId>com.sun.jersey</groupId>
	    <artifactId>jersey-server</artifactId>
	    <version>1.9.1</version>
	</dependency>
  </dependencies>
  ```
 3 Add plugin to indicate java version  
 4 Add a facet for Dynamic Web Project to the maven project.  
 5 Export project as WAR file and put into the `$SERVER_HOME\webapp` in server directory like Tomcat.  
 6 Modify the deployment assembly and add maven libs to make suew that WAR file exported together.
