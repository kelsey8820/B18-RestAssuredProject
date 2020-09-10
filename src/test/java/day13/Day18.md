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
- #### Body 
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

- Body 
- Header 
- cookie