# Try with resource
It is somtimes annoying to remember to close resources we used. See example:
```java
public class TryTest {
    public static final String PATH = "<path>";
    public static void main(String[] args) {
        try {
            readFromFile(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFile(String path) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            System.out.println(br.readLine());
        } finally {
            br.close();
        }
    }
}
```
In the end of `readFromFile`, BufferedReader is required to be closed. Well, I use java because I would like to let GC to help me to handle resources, which means I don't want to close any resource by myself. In java 7, there is a new interface `java.lang.AutoCloseable` introduced, any classes implementing the interface will be closed on completion.  
See example below:
```java
public class TryTest {
    public static final String PATH = "<path>";
    public static void main(String[] args) {
        try {
            readFromFileTryWithResource(PATH);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readFromFileTryWithResource(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            System.out.println(br.readLine());
        }
    }
}
```
