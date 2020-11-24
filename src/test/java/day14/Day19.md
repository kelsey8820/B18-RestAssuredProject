# Day 18 : 

## Quick Recap : 

What do we have in the request and response : 

### Request : 
- #### Request URL 
  - `YourDomain:Port/Endpoints`
  - `http://yourIp:8000/api/spartans`
  - `http://awesomesite.com/api/someEndpointHere`
  - If a port is not specified below 2 ports are assumed 
    - port `80` for url started with `http`
    - port `443` for url started with `https`

- #### HTTP Method|Verb  
  - **GET**
    - Used to retrive resources from the server. 
    - Usually we get `200 OK` back for successful response
  - **POST**
    - Used to send|add resource to the server
    - It must have the body 
    - It must specify the content type of the body
    - Usually you can only add one resource per request
    - Usually `201` Created for successful response , it can also be `200` if the api dev decided to do so (just like we saw in library /login endpoint)
  
  - **PUT**
    - Used to update the resouces in the server
    - It must have the body 
    - It must specify the content type of the body
    - The request url point to single resource being updated. `/spartans/{id}`
    - usually we get `204` for successful response
  - **PATCH** 
    - Usually Used to partially update the resource
    - It must specify the content type of the body
    - It must have the body 
  
  - **DELETE**
    - Used to delete the resouce in the server 
    - It must point to single resource
  - **HEAD**
    - Used to just get the header from the response , not the body. 
- #### Header 
  - Metadata about the request, basically providing more information along with the request
  - Common headers : 
    - `Content Type` header : to specify what kind of data you are sending to the server 
    - `Accept` header : to specify what kind of data format you want to get in the response like `json` or `xml`
    - `Authorization` header : used to provide the `Bearer Token` in many APIs
    - Some custom headers provided in the API according to the doc like `X-LIBRARY-Token`
  
- #### Query Parameter
  - A key value pair Usually used to filter the result
  - It's easily recognizable in the url because it always come at the end of URL right after `?`
    - for example 
    http://google.com/?q=Cybertek&source=hp
  - It can also be used for providing the api keys in some api 
    - for example http://www.omdbapi.com/?t=Boss%20Baby&r=xml&`apikey=yourKeyGoesHere`
  - It can be used for specifying the response content type if the api doc says so
    - http://www.omdbapi.com/?t=Boss%20Baby&`r=xml`&apikey=yourKeyGoesHere
- #### Path Variable or Parameter
  - Used to identify single resource among list of resources 
  - It's part or url directly `/spartans/{id}`
    - `https://yourip:8000/spartans/10`
  - Usually cleary defined in the doc either use `:name` or `{name}`
- #### Body (Payload)
  - Exists in `POST` , `PUT` , `Patch`
  - The contentype can be anything according to the doc.
  - Common contentype we have seen so far: 
    - plain text
    - Json 
    - xml , html 
    - URL encoded form data
    - form data
    - file 
    * `Json` is Popular Option!
- #### cookie


### Response 

- #### Status code 
  - A number to indicate the status of your response , list of all status codes can be found [here](https://httpstatuses.com/) 
 
  - `2xx` for success 
    - `200 OK`
    - `201 CREATED`
    - `204 No Content`
  - `4xx` for client side error 
    - `400 Bad Request`
      - Sending bad data to the server
    - `401 Unauthorized`
      - Did not provide correct credentials, we do not know who you are.
    - `403 Forbidden`
      - Do not have permission to take this action, we know who you are but you do not have perission. 
    - `404 Not Found`
      - The resource you are looking for does not exist
    - `405 Method Not Allowed`
      - Can not perform this http method on the endpoint
      - For example : 
        - `POST /api/spartans/15` is not allowd!!!
        - Most of the public api like `movieDB`, `Breaking Bad` ,`Star War` apis only support `GET` 
    - `406 Not Acceptable`
        - Endpoint only support getting the response in certain format and we asked for a format that not acceptable
        - For example : 
          - `/spartans/{id}` only support json and if we put `accept` header value to `xml` We get this status code. 
  
    - `415 Unsupported`
      - Server is expecting to get the body in certain content type , but the client sent unsupported content type
      - For example : `POST /api/spartans` only accept `json` as content type. if you forget to add the content type, it will automatically assume you are sending `plain/text` and we get this error.
      - Or if you specify the content type incorrectly , this status code will be returned. 
  - `5xx` for server side error
    - `500 Internal server error`
      - The server encountered an unexpected condition that prevented it from fulfilling the request. 
      - For example : 
        - in `PUT /api/spartans/{id}` request, unlike the `POST `, backend code does not have any error handling for incorrect body, so server does not know what to do with it and throw `500` error. 
    - `503 Service Unavailable`
      - The server is currently unable to handle the request due to a temporary overload or scheduled maintenance, which will likely be alleviated after some delay.

- #### Body  (Payload)
  - The actual resource we got from the server 
  - Content Type can be 
    - plain text 
    - xml, html 
    - json 
    - or any other types 
- #### Header 
  - The metadata about the response to provide more information  
- #### cookie
- #### Time (spent to get this response)


## Authentication and Authorization 

* Authentication :
  * Telling the system who you are , proving the identity against who you say you are.  
* Authorization : 
  * Things you can do according to who you are, The permission attached to your identity.

### Few ways of making authorized request
  - Basic Auth 
    - providing username and password along with each request.
    - For example 
      - The spartan app with basic auth 
      - if username and password not provided we will get 401
      - Github API also support basic auth 
      - It also supports providing token instead of password by generating the personal token by logging into your account and clicking on this [link](https://github.com/settings/tokens)
  
  - API Keys
    - A token provided by the api vendor , to identify who you are and track your usage and authorize the requests 
    - Usually , it's required to provide the token either in query parameter or header (Strictly according to the doc) 4
  
  - Bearer token
    - A token either provided or generated by making a request to certain endpoint and added to a header called `Authorization` 
    - The value start with `Bearer `+ your long token. 
    - The common token type for this known as `JWT(Json Web Token)`
    - It has information about the identity and authority which can be decoded. The token usually start with `ey......long token`
    - Example :  `Authorization` : `Bearer ey......long token`
  - oAuth2 
    - A much more secure way of authorizing your request 
    - The flow is similar to `Login with facabook,google`
    - Eventually the token still get added to the `Authorization` header.

>How did you authorize your request in your application ? and what happen if you don't?

* One of the internal project I worked on use basic auth , and we just provide the username , password for each request to make authorize request
* Another project I worked on have 2 options 
    * provide the api-key into the query parameter 
    * provide the api-key in custom header 
      * For example : New API you did , [docs here](https://newsapi.org/docs/authentication)
* Most of the projects I worked on use `Bearer ` token with `JWT` in `Authorizaiton` header.  I have endpoint that I can use to generate this token and pass it to the each requests in my test. 

* When the credentails or the token is not provided --> `401`
* When the crenentials or the token does not have permission --> `403`

### BookIT API oAuth2 example 
BookIT api use oAuth2 for authorization , generating the access token is much simplified and the token does not expire. 

#### Task 
   1. Send a request to `GET https://cybertek-reservation-api-qa.herokuapp.com/sign` Endpoint with below 2 query parameters 
      * email=jalabaster7f@drupal.org
      * password=terimapam 
      * Save the token that generated 
   2. Send a request to `GET https://cybertek-reservation-api-qa.herokuapp.com/api/rooms` 
      * provide the `Authorization` header as `Bearer yourTokenHere`
      * or use the RestAssured method `auth().oauth2("Token here) 
> Solution : 

```java 
// Code for getting the token
String accessToken =
  given()
          .queryParam("email","jalabaster7f@drupal.org")
          .queryParam("password", "terimapam").
  when()
          .get("/sign").prettyPeek()
          .path("accessToken") ;
```

```java
// code for using the token in 2 different ways : 
  given()
//                .header("Authorization", "Bearer "+ accessToken)
            .auth().oauth2(accessToken)
            .log().all().
  when()
          .get("/api/rooms").
  then()
          .log().all()
          .statusCode(200)
          .contentType(ContentType.JSON)
```
### How to transfer data between requests in Postman

  Here is the collection : [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/a371e0cecef83d59af15)

  We can use the variable (`Env variable` , `Global variable`)
  #### Steps : 
 * Create a global variable called `my_secret_token` with empty value
 * save the entire response as json 
 * print out the accessToken field from response
 * set the value of global variable to json field
  
```javaScript
var responseJson = pm.response.json(); 
console.log( responseJson.accessToken  );
pm.globals.set("my_secret_token",  responseJson.accessToken );
```   
![Saving_Response_Json_as_Object_and_Print_Its_Field](https://user-images.githubusercontent.com/59104509/92794984-098ebe00-f37e-11ea-9930-d1954be28959.png)

![Setting_the_value_of_global_variable_from_json_field](https://user-images.githubusercontent.com/59104509/92795069-190e0700-f37e-11ea-8ab8-d84d462ae4bf.png)

* Use this variable for next request
![Using_it_in_next_Request](https://user-images.githubusercontent.com/59104509/92795908-c08b3980-f37e-11ea-9cf3-65ca0c91f56c.png))


### Handling Cookie in Postman 
Here is the collection : [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/07efb5040d033f07788f)


Cookie in rest request , just like on the website , used to store some common information about where the request originated. for example , while you shop online , once you view certain items , next time to go to the website it will always suggest similar item beacuse it can access the cookie that contain those information. 

#### Postman 
* We have a button called cookie to give us a way to add cookie to certain domain. by providing the key-value pair (optionally expration date)
* Once added , any request sent to the same domain , can access that cookie 
* You can also programmitically access the cookie in the test tab
  
> This code is checking we have 2 cookies as below 
>   B18 : AWESOME   AND  Motto : Hold your horse

```javaScript
pm.test('the "B18" cookie has correct value', function () {
    pm.expect(pm.cookies.toObject()).to.have.property('B18', 'AWESOME');
});

pm.test('the "Motto" cookie has correct value', function () {
    pm.expect(pm.cookies.toObject()).to.have.property('Motto', 'Hold your horse');
});
```

### RestAssured adding Cookie 
  More information can be found in the doc [here](https://github.com/rest-assured/rest-assured/wiki/Usage#cookies).

  > Try it out with this request : https://postman-echo.com/cookies
  > Try to add 2 cookies in the requests by following the doc. 













----- 

## Soap Request : 
Here is the example provided by postman in the [doc](https://learning.postman.com/docs/sending-requests/supported-api-frameworks/making-soap-requests/) 

You can see more example by looking at this [doc](https://documenter.getpostman.com/view/8854915/Szf26WHn?version=latest&_ga=2.111743751.265151840.1599750425-735093615.1589812348)

Or you can directly import the collection with all the post request by clicking on this button : [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/3bf2daab4832964def44)




    SOAP :  Simple Object Access Protocol 
* It has strict rule on how to send request and response. 

* Soap Request use `XML` format to send and receive response 

* Only method allowed in SOAP is `POST`
* The content type is always xml 
* Body is enclosed in envelop like structure for example :

Sample Post request to turn number into word 
* `POST https://www.dataaccess.com/webservicesserver/NumberConversion.wso` 

* `Header` : `text/xml; charset=utf-8`
* Body : 
```xml 
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
  <soap:Body>
    <NumberToWords xmlns="http://www.dataaccess.com/webservicesserver/">
      <ubiNum>123456789</ubiNum>
    </NumberToWords>
  </soap:Body>
</soap:Envelope>
```
> Response 
```xml
<?xml version="1.0" encoding="utf-8"?>
<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
    <soap:Body>
        <m:NumberToWordsResponse xmlns:m="http://www.dataaccess.com/webservicesserver/">
            <m:NumberToWordsResult>one hundred and twenty three million four hundred and fifty six thousand seven hundred and eighty nine </m:NumberToWordsResult>
        </m:NumberToWordsResponse>
    </soap:Body>
</soap:Envelope>
```
----
## Tools to send API request 
* Postman       -- `Desktop client tool`
* Curl          -- `Command line client tool`
* RestAssured   -- `Java client library`
* SoapUI        -- `Desktop client tool`

A lot of API documentation provide curl command example run the request and Postman can easily import curl command without you manually typing the whole part of the request. 

### How to easily import curl command as Postman Request
![How_to_import_curl_command_to_postman_request](https://user-images.githubusercontent.com/59104509/92767551-3b475b00-f365-11ea-916a-c0de1e24d0c5.gif)

### How to generate curl command from Postman Request

![How_to_generate_curl_command_from_Postman_Request](https://user-images.githubusercontent.com/59104509/92767785-6fbb1700-f365-11ea-9177-710142aa0f04.gif)


### Data Driven Test in Postman 

Postman support data driven test with external file in csv, json format. 
What if I have excel file ? 

Convert to csv and use the csv file that supported.

> **As long as the column name in csv , or the field name in json match your variable name in Postman , it will just use that from the external file.** 

* Sample csv file for the Spartan Data driven test
```
name,gender,phone
Zuura,Female,1231231231
Yucel,Male,2131231231
Rahman,Male,4312412342
Anna,Female,1123431231
Madina,Female,7657654321
```
* Sample json file for the Spartan Data driven test
```json
[
  {"name":"Joanne","gender":"Female","phone":5379812785},
  {"name":"Ely","gender":"Male","phone":5017720233},
  {"name":"Callie","gender":"Female","phone":6035065771},
  {"name":"Ferguson","gender":"Male","phone":5162744587},
  {"name":"Shirley","gender":"Female","phone":7861730884}
]
```

* We can easily generate randomized mock data from https://www.mockaroo.com/
* It support generating CSV, JSON, SQL, and Excel formats.


#### Postman is not just a manual testing tool 
- You can do full automation of your api test directly within the postman. 
- Create collection and folders to organize your requests. 
- Add environment variables to run the collection against different env
- Add assertions in the test tab (lots of sinippets are available)
- Run your collections using Collection Runner of Postman 
  -  select repetition count 
  -  add external files 
-  You can also run your collection from the command line by the help of command line tool from postman called `newman` , You can get more information about `newman` from [here](https://learning.postman.com/docs/running-collections/using-newman-cli/command-line-integration-with-newman/) 


### How to use Chrome developer -> Netwrok tab to see the requests being made. 
 ![capturing_request_from_chrome_developer_tab](https://user-images.githubusercontent.com/59104509/100050552-a8f11800-2de7-11eb-89ce-709984891f05.png)


## RestAssured : 

### Maven dependencies : 
  - This include all basic component of RestAssured 
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>rest-assured</artifactId>
    <version>4.3.1</version>
    <scope>test</scope>
</dependency>
```
  - If we want to use serialization , de-serialization we will need Jackson-Databind
```xml
<dependency>
    <groupId>com.fasterxml.jackson.core</groupId>
    <artifactId>jackson-databind</artifactId>
    <version>2.11.2</version>
</dependency>
```
  - If we need Json schema validation 
```xml
<dependency>
    <groupId>io.rest-assured</groupId>
    <artifactId>json-schema-validator</artifactId>
    <version>4.3.1</version>
    <scope>test</scope>
</dependency>
```
  - JsonPath and XmlPath is already included in the RestAssured dependeny, If you want to work with JsonPath and XmlPath outside context of RestAssured , you may also add below 2 dependencies. 
  It will enable you to directly work with json file or xml file and use JsonPath and XmlPath.
```xml
<dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>json-path</artifactId>
      <version>4.3.1</version>
      <scope>test</scope>
</dependency>

<dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>xml-path</artifactId>
      <version>4.3.1</version>
      <scope>test</scope>
</dependency>
```

## API Documentation 
There different type of API documentations 
  - Swagger doc 
  - Postman doc
  - Custom documenting site 
  - just file contain the doc

> **Swagger is the most popular one.**



## The Structure of RestAssured Method Chaining 
``` 
given() ....   --> RequestSpecification 
     .header  accept() contentType()
     .queryParam
     .pathParam
     .body
     .log
     .auth..
when()       --->> RequestSender
   .get() ------>> Response Object 
   .post()
   .put()
   .delete()
then()      -----> ValidatableResponse  
        This is where assertion happen 
    .statusCode
    .header   accept() contentType()
    .body( matchers goes here)
    .log

```

### Hamcrest Matcher library
* Hamcrest is library aim to created readbale assertions. 
* It can be used separately by itself by importing the dependency
* RestAssured already include Hamcrest library so no separate depenceny requied. 
* Few common matchers we used : 
  * `is(.. )`
  * `not(..)`
  * `equalTo(..)`
  * `containString(...)`
  * `greaterThan(..)`
  * `lessThan(..)`
  * `hasSize(..)`
  * `hasItem(..)` and `hasItems(...)`
  * Any many more.....
