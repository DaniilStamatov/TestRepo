package com.stamatov.ForTest.controller;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StringControllerTest {
    @InjectMocks
    private StringController stringController;

    @Test
    void getFrequency_validInput_returnFrequencyMap() {
        // Arrange
        String inputString = "aabbc";
        Map<Character, Integer> expectedFrequencyMap = new HashMap<>();
        expectedFrequencyMap.put('a', 2);
        expectedFrequencyMap.put('b', 2);
        expectedFrequencyMap.put('c', 1);

        // Act
        ResponseEntity<Map<Character, Integer>> responseEntity = stringController.getFrequency(inputString);
        Map<Character, Integer> actualFrequencyMap = responseEntity.getBody();

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedFrequencyMap, actualFrequencyMap);
    }

    @Test
    void getFrequency_emptyInput_returnBadRequest() {
        // Arrange
        String inputString = "";

        // Act
        ResponseEntity<Map<Character, Integer>> responseEntity = stringController.getFrequency(inputString);
        HttpStatus actualStatusCode = (HttpStatus) responseEntity.getStatusCode();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, actualStatusCode);
    }

    @Test
    void getFrequency_nullInput_returnBadRequest() {
        // Arrange
        String inputString = null;

        // Act
        ResponseEntity<Map<Character, Integer>> responseEntity = stringController.getFrequency(inputString);
        HttpStatus actualStatusCode = (HttpStatus) responseEntity.getStatusCode();

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, actualStatusCode);
    }
}