# Grocery List Api
This is a simple RESTful API for the Grocery List App

![SpringBoot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white) ![Docker](https://img.shields.io/badge/Docker-2496ED?logoColor=white&logo=docker&style=for-the-badge) ![Java](https://img.shields.io/badge/Java-v11-blue?logo=java&style=for-the-badge) ![GitHub Workflow Status (branch)](https://img.shields.io/github/workflow/status/joanpablo/grocery-list-api/grocery-list-api/master?style=for-the-badge)

This is a [Spring Boot](https://spring.io/projects/spring-boot) application that exposes the necessary REST services for the Grocery List App.

The OpenAPI Specification v3 of the services are described in the file:
```
open-api-specs/grocery-list-api.yaml
```

You can copy the content of the file and paste it in the [Swagger Editor](https://swagger.io/tools/swagger-editor/)
to see the description of all services in the project.


This is a screenshoot of the [Swagger Editor](https://swagger.io/tools/swagger-editor/) with the specifications
![Screenshot 2021-11-27 211456](https://user-images.githubusercontent.com/37339180/143719472-a52253c7-9fa1-418c-9db5-02230c735d20.jpg)

## Build project
```shell
./gradlew build
````

## Run tests
```shell
./gradlew test
````

You will find a complete report of the result os the tests in:  
```
build/reports/test/index.html
``` 

The tests were written in [Spock Framework](https://spockframework.org/spock/docs/2.0/all_in_one.html) using the 
language Groovy.  

All test are located in:
```
src/test/groovy/com/rideco/grocery/controllers/ProductsControllerSpec.groovy
```

this is an example of a test:
```groovy
def "create a product with NULL name throws BAD_REQUEST response"() {
    given: "a request body with null name"
    def createProductRequestBody = [
        name       : null,
        description: '1 bottle'
    ]

    when: "create the new product"
    def response = mvc.perform(
            post("/rest/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(toJson(createProductRequestBody))
    )

    then: "the response is BAD_REQUEST"
    response.andExpect(status().isBadRequest())

    and: "the error code is VALIDATION_ERROR"
    ErrorResponse errorResponse = parseErrorResponse(response.andReturn().response.contentAsString)
    errorResponse.errorCode == ErrorResponseCode.VALIDATION_ERROR
}
```
