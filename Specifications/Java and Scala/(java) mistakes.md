```
public void test(int i){
  System.out.println(i);
}

...
int i = 0; 
test(i++);
System.out.println(i);
...
```
result is: 
```
0
1
```
`++` executed after the current statement.
