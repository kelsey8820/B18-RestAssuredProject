# Day 18 : 
----------
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
  - Meta data about the request, basically providing more information along with the request
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

- Status code 
  - A number to indicate the status of your response , list of all status codes can be found [here](https://httpstatuses.com/) 
- 
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

- Body  (Payload)
  - The actual resource we got from the server 
  - Contentype can be 
    - plain text 
    - xml, html 
    - json 
    - or any other types 
- Header 
  - The meta data about the response to provide more information  
- cookie
- Time (spent to get this response)

----- 

## Soap Request : 
Here is the example provided by postman in the [doc](https://learning.postman.com/docs/sending-requests/supported-api-frameworks/making-soap-requests/) 

You can see more example by looking at this [doc](https://documenter.getpostman.com/view/8854915/Szf26WHn?version=latest&_ga=2.111743751.265151840.1599750425-735093615.1589812348)

Or you can directly import the collection with all the post request by clicking on this button : [![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/3bf2daab4832964def44)




### SOAP : 
    Simple Object Access Protocol 
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

