package com.rideco.grocery.controllers

import com.rideco.grocery.models.ErrorResponse
import com.rideco.grocery.models.ErrorResponseCode
import com.rideco.grocery.models.Product
import com.rideco.grocery.repositories.ProductsRepository
import groovy.json.JsonBuilder
import groovy.json.JsonSlurper
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductsControllerSpec extends Specification {

    @Autowired
    private MockMvc mvc

    @SpringBean
    ProductsRepository productsRepository = Mock()

    def "delete a not existing Product returns a NOT_FOUND HTTP status code"() {
        given: "the product does not exist in repository"
        int productId = 20
        productsRepository.findById(productId) >> Optional.empty()

        when: "perform a delete request"
        def response = mvc.perform(
                delete("/rest/products/$productId")
                        .contentType(MediaType.APPLICATION_JSON)
        )

        then: "response is NOT_FOUND"
        response.andExpect(status().isNotFound())
    }

    def "update a not existing Product returns a NOT_FOUND HTTP status code"() {
        given: "the product does not exist in repository"
        int productId = 20
        productsRepository.findById(productId) >> Optional.empty()

        when: "perform an update request"
        def response = mvc.perform(
                put("/rest/products/$productId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson([name: 'Bananas']))
        )

        then: "response is NOT_FOUND"
        response.andExpect(status().isNotFound())
    }

    def "update a product save the new values in repository"() {
        given: "a product in database"
        int productId = 20
        def product = new Product(
                id: productId,
                name: "Milk",
                description: "1L"
        )

        productsRepository.findById(productId) >> Optional.of(product)

        when: "perform an update request"
        def requestBody = [
                name       : 'Bottle of Milk',
                description: '1 bottle'
        ]

        def response = mvc.perform(
                put("/rest/products/$productId")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(requestBody))
        )

        then: "response is OK"
        response.andExpect(status().isOk())

        and: "repository is called with correct arguments value"
        1 * productsRepository.save({
            verifyAll(it, Product) {
                name == 'Bottle of Milk'
                description == '1 bottle'
                id == productId
            }
        }) >> { Product p -> p }
    }

    def "get the collection of products returns a valid response"() {
        given: "A list of products in the repository"

        def firstProductId = 1
        def firstProductName = 'Bananas'
        def secondProductId = 2
        def secondProductName = 'Milk'

        def products = ArrayList.of(
                new Product(id: firstProductId, name: firstProductName),
                new Product(id: secondProductId, name: secondProductName),
        )

        productsRepository.findAllByOrderByIdDesc() >> products

        when: "request the list of products"
        def response = mvc.perform(
                get("/rest/products")
                        .contentType(MediaType.APPLICATION_JSON)
        )

        then: "the response is OK"
        response.andExpect(status().isOk())

        and: "the response has the collection of products"
        def productCollection = parseJsonArray(response.andReturn().response.contentAsString)
        productCollection.size() == 2

        productCollection.get(0).id == firstProductId
        productCollection.get(0).name == firstProductName

        productCollection.get(1).id == secondProductId
        productCollection.get(1).name == secondProductName
    }

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

    def "create a product with EMPTY name throws BAD_REQUEST response"() {
        given: "a request body with empty name"
        def createProductRequestBody = [
                name       : '',
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

    def "update a product with NULL name throws BAD_REQUEST response"() {
        given: "a request body with null name"
        def createProductRequestBody = [
                name       : null,
                description: '1 bottle'
        ]

        when: "update the product"
        def response = mvc.perform(
                put("/rest/products/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(createProductRequestBody))
        )

        then: "the response is BAD_REQUEST"
        response.andExpect(status().isBadRequest())

        and: "the error code is VALIDATION_ERROR"
        ErrorResponse errorResponse = parseErrorResponse(response.andReturn().response.contentAsString)
        errorResponse.errorCode == ErrorResponseCode.VALIDATION_ERROR
    }

    def "update a product with EMPTY name throws BAD_REQUEST response"() {
        given: "a request body with empty name"
        def createProductRequestBody = [
                name       : '',
                description: '1 bottle'
        ]

        when: "update the product"
        def response = mvc.perform(
                put("/rest/products/20")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(toJson(createProductRequestBody))
        )

        then: "the response is BAD_REQUEST"
        response.andExpect(status().isBadRequest())

        and: "the error code is VALIDATION_ERROR"
        ErrorResponse errorResponse = parseErrorResponse(response.andReturn().response.contentAsString)
        errorResponse.errorCode == ErrorResponseCode.VALIDATION_ERROR
    }

    private static String toJson(Map<String, Object> map) {
        return new JsonBuilder(map).toString()
    }

    private static List<Product> parseJsonArray(String json) {
        return new JsonSlurper().parseText(json) as List<Product>
    }

    private static ErrorResponse parseErrorResponse(String json) {
        return new JsonSlurper().parseText(json) as ErrorResponse
    }
}
