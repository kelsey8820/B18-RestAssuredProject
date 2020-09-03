# RestAssuredProject
B18 Rest Assured Project

![How to Do it](./src/test/resources/gifs/HowToAddResourceFolder.gif "How to Do it")


```java
public class TestClass{
    
    @ParameterizedTest
    @CsvFileSource("/data.csv")
    public void testAddition(int a, int b){
        System.out.println(a + b );
    }    

}
```
