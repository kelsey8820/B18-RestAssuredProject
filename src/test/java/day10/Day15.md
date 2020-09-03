# Day 15 (Day 10 of RestAssured)

Last class ,

* We reviewed data driven test 
* We did some role based access control test (who can do what , who can not do what)
* Learned how to reuse the `RequestSpecification` and `ResponseSpecification`

```java
RequestSpecification requestSpec = given().somespecHere..... ; 

ResponseSpecification responseSpec = responsespecBuilderObject...... build() ; 

given()
        .spec(requestSpecGoesHere)
when()
        .get("/someEndPoint")
then()
        .spec(responseSpecGoesHere)

```
##  How to build ResponseSpecification object easily 

```java 
    ResponseSpecification responseSpec = expect().statusCode(200).contentType(ContentType.JSON) ; 
```
----
## Practice what we learned with LibraryApp 

We will use these 3 endpoints : 
* `GET /dahsboard_status`
* `GET /get_book_categories`
* `GET /get_all_users`

We want to save the Request spec for 
  * setting the `X-LIBRARY-TOKEN` HEADER 
  * `ContentType` Header 
  * logging everything 

We want to save the Response spec for 
  * Status code of `200`
  * `ContentType` Header is `JSON` 
  * `log` every detail


## Setting the Request and Response Specification at global level
 Just like we set baseURI and basePath using the static fields of RestAssured class , 
 we can do the same for requestSpecification and responseSpecification if we have 
 common spec for our entire RestAssured tests. 
 ```java 
RestAssured.baseURI = "some URL here"
RestAssured.basePath = "/some path here" ;
RestAssured.requestSpecification  = given()..somespec ;
RestAssured.responseSpecification = expect()..somespec ; 
 ```

 ### Practice the De-Serialization using the same test 
 1. get the `Map<String,Integer>` object out of the response of **GET /dashboard_stats**

    Solution : 
```java
        Response response =  when().get(" /dashboard_stats").prettyPeek();
        // here getMap method automatically converting String to number
        // for the value data type `Integer` since it can be converted
        Map<String,Integer> statMap = response.jsonPath().getMap("") ;
        System.out.println("statMap = " + statMap);
```
 2. get the `List<Category>` object from the response of **GET /get_book_categories**
 3. get the `List<User>` object from the response of **GET /get_all_users**

>hint : you will need to create 2 POJO class called `Category` , `User`; 
----
## Project Lombok : How to remove boiler plate code
* Getters Setters 
* Constructors 
* toString method 
* and so on.... 

Using Project lombok is 2 step process is IntelliJ Idea
1. Make sure you have latest IntelliJ 
2. Add dependency to your project 
```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>
 ```
3. Add A IntelliJ Plugin called Lombok from the [Plugin website]("https://plugins.jetbrains.com/plugin/6317-lombok") 

or IntelliJ `Preference --search plugins --Marketplace-- Lombok` as below
* Intall it and restart the IDE to take affect. (Say yes to any pop-up) 

![How to Do it](./src/test/resources/gifs/How_To_Install_Lombok_Plugin_in_IntelliJ.gif")

Once you completed previous steps you can use all powerful Lombok annotations to remove boilerplate repetitive codes. 

```Java 
import lombok.*;

@Getter // This annotation will add getters for all fields
@Setter // This annotation will add setters for all fields
@AllArgsConstructor // will add constructor including all fields
@NoArgsConstructor // will add no argument constructor
@ToString // will add toString method for the class
public class Category {

    private String id;
    private String name;

}
```

**Now you can directly use this Class to create object as if you have all required members you specified as below :**
```Java
public class LombokTest {

    @Test
    public void test(){
        // Using 2 args constructor
        Category c1 = new Category("12","abc");
        System.out.println("c1 = " + c1);
        //c1 = Category(id=12, name=abc)
    }

}
```


