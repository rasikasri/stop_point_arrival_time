package com.ra.integration;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static com.github.tomakehurst.wiremock.http.RequestMethod.ANY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


public class ArrivalSearchIT extends IntegrationBase{

    @Test
    public void testSearchPeople(){
        //Given
        mockResponse("/StopPoint/940GZZLUKBY/Arrivals", getResponseResources("response.json"), HttpStatus.OK.value(), ANY);

        //When
        ResponseEntity<String> response = sendRequestToServer("/arrivals/940GZZLUKBY");

        //Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(response.getHeaders().containsKey(HttpHeaders.CONTENT_TYPE));
        assertEquals(APPLICATION_JSON_VALUE, response.getHeaders().get(HttpHeaders.CONTENT_TYPE).get(0));
        assertEquals(2, (Integer) JsonPath.parse(response.getBody()).read("$.length()"));
        assertEquals(7, (Integer) JsonPath.parse(response.getBody()).read("$[0].arrivals.length()"));
        assertEquals(3, (Integer) JsonPath.parse(response.getBody()).read("$[1].arrivals.length()"));
    }

}
