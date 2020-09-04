# Day 16 (Day 11 of RestAssured)

**Warm Up Task :**

Getting the dymanic data from different request for the test.(No DB Access situation)
We want to test
1. 




## JSON SCHEMA VALIDATION 

```json
{
    "id": 1,
    "name": "Florrie",
    "gender": "Male",
    "phone": 3031618834
}
```
We have tested the body , status code , headers 
But now , How do I make sure the response json : 
    have the correct structure as defined? 

for example : 
    it only has the 4 fields as displayed above 
    the data type for the value match
    and the value match certain criteria like (2-15 chars..)

JsonSchema is a documentation in json format to specify the structure of a json string match certian rules or criteria. 

This is one of the minimalist schema for above json string 
```json
{
  "$schema": "http://json-schema.org/draft-04/schema#",
  "type": "object",
  "properties": {
    "id": {
      "type": "integer"
    },
    "name": {
      "type": "string"
    },
    "gender": {
      "type": "string"
    },
    "phone": {
      "type": "integer"
    }
  },
  "required": [
    "id",
    "name",
    "gender",
    "phone"
  ]
}
```
This document is setting the rule for the structure of the json as below
1. it has to be an object `"type": "object"`
2. it has to have 4 fields|properties `id` , `name`,`gender`,`phone`
3. all properties must have the defined data type as value 

Any of above rule fail , it means it's not a valid json we are looking for. else , it's a valid json. 
That's exactly what Json Schema Validaiton is. 

This is free [online site](https://www.liquid-technologies.com/online-json-schema-validator)  we used to validate our json. 

This is another schema document with little bit more rules : 
```json
{
  "definitions": {},
  "$schema": "http://json-schema.org/draft-07/schema#",
  "$id": "https://example.com/object1590716273.json",
  "title": "Root",
  "type": "object",
  "required": [
    "id",
    "name",
    "gender",
    "phone"
  ],
  "properties": {
    "id": {
      "$id": "#root/id",
      "title": "Id",
      "type": "integer",
      "examples": [
        33
      ],
      "default": 0
    },
    "name": {
      "$id": "#root/name",
      "title": "Name",
      "type": "string",
      "default": "",
      "examples": [
        "Wilek"
      ],
      "pattern": "^.*$"
    },
    "gender": {
      "$id": "#root/gender",
      "title": "Gender",
      "type": "string",
      "default": "",
      "examples": [
        "Male"
      ],
      "pattern": "(Male|Female)"
    },
    "phone": {
      "$id": "#root/phone",
      "title": "Phone",
      "type": "integer",
      "examples": [
        2877865902
      ],
      "default": 0
    }
  }
}

```

From our end , we will usually get the schema from the developer.
`src/test/resources` 

Steps : 
1. Add the file called `singleSpartan.json` under `src/test/resources` folder. 
2. Add RestAssured JsonSchema validation dependency to your pom file 
```xml
<dependency>
      <groupId>io.rest-assured</groupId>
      <artifactId>json-schema-validator</artifactId>
      <version>4.3.1</version>
      <scope>test</scope>
</dependency>
```
3. Add below static import 
```java
import static io.restassured.module.jsv.JsonSchemaValidator.*;
```
4. Write your regular restassured assertion under then section 
   add below part.. 
```java
then().body( matchesJsonSchemaInClasspath("singleSpartanSchema.json") )
```

---- 
## How to do Json Schema Validation in Postman

![Tiny Validator](../../resources/gifs/How_to_do_Json_Schema_Validation_in_Postman.gif)

