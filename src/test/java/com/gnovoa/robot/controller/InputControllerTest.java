
//Namespace
package com.gnovoa.robot.controller;

//Imports
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

/**
 * Integration Test Class
 */
@ActiveProfiles("integrationTest")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class InputControllerTest {

    //Fields
    private static final String BASE_URL = "/api/v1/input";

    @Autowired
    private transient TestRestTemplate restTemplate;

    private transient  String inputText =
            "5 3\n" +
            "1 1 E\n" +
            "RFRFRFRF\n" +
            "3 2 N\n" +
            "FRRFLLFFRRFLL\n" +
            "0 3 W\n" +
            "LLFFFLFLFL";

    private transient String myOutput =
            "1 1 E\n" +
            "3 3 N LOST\n" +
            "0 3 E LOST";

    private transient String redBadgerOutput =
            "1 1 E\n" +
            "3 3 N LOST\n" +
            "2 3 S";


    @Test
    void processInputOK() {

        //Create payload
        String inputPayload = inputText;

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(inputPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<String> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
        Assertions.assertEquals(myOutput, response.getBody());
    }


    @Test
    void prcoessInputWithInvalidInstruction() {
        //Create payload
        String inputPayload = inputText.replaceAll("F","B");

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(inputPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<String> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void prcoessInputWithInvalidOrientation() {
        //Create payload
        String inputPayload = inputText.replaceAll("N","B");

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(inputPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<String> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }


    @Test
    void prcoessInputWithCoordinateException() {
        //Create payload
        String inputPayload = inputText.replaceAll("5","77");

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(inputPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<String> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    void prcoessInputWithParseException() {
        //Create payload
        String inputPayload = "This is a random text";

        //Set the headers
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.setContentType(MediaType.APPLICATION_JSON);

        //Create the http request
        HttpEntity<?> request = new HttpEntity<>(inputPayload, requestHeaders);

        //Invoke the API service
        ResponseEntity<String> response = restTemplate
                .exchange(BASE_URL, HttpMethod.POST, request,  new ParameterizedTypeReference<>() { });

        //Verify
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
