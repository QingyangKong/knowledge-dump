# Exclude jars in dependency
If there is jar file that does not matching the version of jar that is expected to use, it can be removed by this block:
```
<exclusions>
  <exclusion>
    <groupId>org.apache.kafka</groupId>
    <artifactId>kafka_${kafka.version}</artifactId>
  </exclusion>
</exclusions>
```
In order to see which dependecy this jar belongs to, there is a perspective for POM file called `Dependency Hierarchy` that is third tab in POM file.
