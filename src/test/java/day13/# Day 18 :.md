# Day 18 : 

## Quick Recap : 

What do we have in the request and response : 

### Request : 
- Request URL 
  - `YourDomain:Port/Endpoints`
  - `http://yourIp:8000/api/spartans`
  - `http://awesomesite.com/api/someEndpointHere`
  - If a port is not specified below 2 ports are assumed 
  - port `80` for url started with `http`
  - port `443` for url started with `https`

- HTTP Method|Verb  
  - GET  
    - Used to retrive resources from the server. 
    - Usually we get 200OK back for successful response
  - POST 
    - Used to send|add resource to the server
    - It must have the body 
    - It must specify the content type of the body
    - Usually you can only add one resource per request
    - Usually 201 Created for successful response , it can also be 200 if the api dev decided to do so (just like we saw in library login endpoint)
  
  - PUT 
    - Used to update the resouces in the server
    - It must have the body 
    - It must specify the content type of the body
    - The request url point to single resource being updated. `/spartans/{id}`
    - usually we get `204` for successful response
  - PATCH 
    - Usually Used to partially update the resource
    - It must specify the content type of the body
    - It must have the body 
  
  - DELETE
    - Used to delete the resouce in the server 
    - It must point to single resource
  - HEAD 
    - Used to just get the header from the response , not the body. 
- Header 
  - Meta data about the request, basically providing more information along with the request
  - Common headers : 
    - `Content Type` header : to specify what kind of data you are sending to the server 
    - `Accept` header : to specify what kind of data format you want to get in the response like `json` or `xml`
    - `Authorization` header : used to provide the `Bearer Token` in many APIs
    - Some custom headers provided in the API according to the doc like `X-LIBRARY-Token`
  
- Query Parameter
  - A key value pair Usually used to filter the result
  - It's easily recognizable in the url because it always come at the end of URL right after `?`
    - for example 
    http://google.com/?q=Cybertek&source=hp
  - It can also be used for providing the api keys in some api 
    - for example http://www.omdbapi.com/?t=Boss%20Baby&r=xml&`apikey=yourKeyGoesHere`
  - It can be used for specifying the response content type if the api doc says so
    - http://www.omdbapi.com/?t=Boss%20Baby&`r=xml`&apikey=yourKeyGoesHere
- Path Variable 
- Body 
- cookie


### Response 
- Status code 
- Body 
- Header 
- cookie