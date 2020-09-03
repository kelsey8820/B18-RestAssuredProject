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
```java
         Response response = when()
                 .get("/get_book_categories");
         List<Category> categoryList = response.jsonPath().getList("", Category.class) ;
        System.out.println("categoryList = " + categoryList);
```
 3. get the `List<User>` object from the response of **GET /get_all_users**
```java 
        Response response =  when().get(" /get_all_users");
        JsonPath jp = response.jsonPath();
        List<User> allUserLst = jp.getList("", User.class) ;
        System.out.println("allUserLst = " + allUserLst);
```
 4. get `List<Map<Integer,String> >` object from the response of **GET /get_book_categories**
```java
       // Each category is being saved into Map object 
       // And the JsonArray is being saved into the List
       // Jackson data-bind take care of all the conversion where it can 
       List< Map<Integer,String> > categoryMapList = response.jsonPath().getList("");
        System.out.println("categoryMapList = " + categoryMapList);
```

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

![How install plugin](../../resources/gifs/How_To_Install_Lombok_Plugin_in_IntelliJ.gif "How to do install plugin")

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

## How do you handle data issue in your automation ? 

### The challenges :

* Dynamic data --
   a data that change often and can not be reused for next test. 
* Data is not sync between environment 
    data that exists in one environment does not exists in another and test fail because of it. 

### What's your approach to handle them ? 
* If the data change often and you have access to DB
  * Query the database to get up to date data and feed the data to your test. 
* If you do not have access to DB but have access to API
  * Send the request to the endpoint that return your data and get the fields that you want using jsonPath. 
* If you do not have data : **CREATE IT FIRST** then use that new data for your test. 
  * You may create it from the UI Directly if the flow is available. 
  * If UI is not ready, but you have access to the API endpoint to create data, you may send `POST` request to add randomized data and use it for the test.
  * last option(probably you will never have access to) is , inject the data directly from the database, you may ask for DBA help if that's the option. 

---- 
#### Example of getting the data from database to feed our API Test

Problem Set : 
> Assuming we just got several Spartan server IP addresses that we have DB acccess to.
>  
> We want to run a test on `GET /spartans/{id}`. 
> Data is so dynamic and different by environment to envieonment. 
> We are constantly getting `404` error because the data does not exists

Approach :
> Since we know there are lots of data already exists, we just needed to pick the correct id, we decided to make a conneciton to the database and get the latest available data to run the test (also thought about getting random data each time.)

Steps : 
1. Using utility to make a connection to `Spartan Database` and ran below query :  
```SQL 
    SELECT * FROM SPARTANS ORDER BY SPARTAN_ID DESC ;
```
2. Using the utility method `getRowMap(1)` to get the first row as `Map<String,String>` and saved it as appropriate data type: 
 ```java
    Map<String, String> firstRowMap = DB_Utility.getRowMap(1);
    int id = Integer.parseInt(firstRowMap.get("SPARTAN_ID"));
    String name = firstRowMap.get("NAME");
    String gender = firstRowMap.get("GENDER");
    long phone =Long.parseLong(firstRowMap.get("PHONE"));
 ```
 3. Use the id to send the ```GET /spartans/{id}``` request using RestAssured. 
```java 
    when().get("/spartans/{id}" , idFromDB )... ; 
```
 4. use the `name`,`gender`,`phone` we got from the DB as expected result to validate the json response we got from the request. 
```java 
    ...then()
                .log().all()
                .statusCode(200)
                .body("id",  is(idFromDB) )
                .body("name",  is(nameFromDB) )
                .body("gender", is(genderFromDB))
                // converting the phoen to long to avoid error
                .body("phone.toLong()", is(phoneFromDB) ) ;
```
 5. Switched the different IP Addresses to see if it still work
 6. _Pending_ : Some IPs did not return any data so planning to create data any time  ```SELECT count(*) FROM SPARTANS``` return 0; 
 7. As time went by , we realized when we read the latest data, it's always reading same data since no new data has been created for a while. so we decided to randomize the data we got by doint below 
   ```java
     Map<String, String> firstRowMap = DB_Utility.getRowMap(RANDOM_ID_WITHIN_THE_RANGE);
   ```
Our range is from row 1 till the last row count. 
In order to get the last row count , we can call the utility method as below: 
   ```java
     int rowCount = DB_Utility.getRowCount();
   ```
In order to get random row number between 1 to row count, 
we can use faker: 
   ```java
     int randomRowNum = new Faker().number().numberBetween(1, rowCount) ;
   ```
Now when we call the method we are passing random row number each time. 
Our test is more robust and practical. 
```java 
Map<String, String> randomRowMap = DB_Utility.getRowMap(randomRowNum);
        System.out.println("CURRENT ROW IS "+ randomRowNum);
        System.out.println("CURRENT ROW DATA IS "+ randomRowMap);
```
Saving the data from Database for the test use: 
```java
        int idFromDB        = Integer.parseInt( randomRowMap.get("SPARTAN_ID") );
        String nameFromDB   = randomRowMap.get("NAME") ;
        String genderFromDB = randomRowMap.get("GENDER") ;
        long phoneFromDB    = Long.parseLong(randomRowMap.get("PHONE"));
```
Now we can take same rest assured action to make our test data and assertion dynamic in any environment that has data. 
```java
       given()
                .log().uri().
        when()
                .get("/spartans/{id}" , idFromDB ).
                then()
                .log().all()
                .statusCode(200)
                .body("id",  is(idFromDB) )
                .body("name",  is(nameFromDB) )
                .body("gender", is(genderFromDB))
                .body("phone.toLong()", is(phoneFromDB) ) ;
```

