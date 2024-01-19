package com.stamatov.ForTest.controller;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestController

public class StringController {
    // обрабатываем запрос с параметом inputString
    @GetMapping("/frequency")
    public ResponseEntity<Map<Character, Integer>> getFrequency(@RequestParam String inputString){
        if(inputString == null ||inputString.isEmpty() ){
            return ResponseEntity.status(400).body(Collections.emptyMap());
        }

        Map<Character, Integer> frequencyMap = new HashMap<>();
        for(char c : inputString.toCharArray()){
            frequencyMap.put(c, frequencyMap.getOrDefault(c, 0) +1);
        }
        Map<Character, Integer> sortedMap = sortFrequencyMap(frequencyMap);
        return ResponseEntity.ok(sortedMap);
    }
    //сортируем мапу по частоте символов в порядке убывания
    private Map<Character, Integer> sortFrequencyMap(Map<Character, Integer> frequencyMap){
        Map<Character, Integer> sortedMap = new HashMap<>();
        frequencyMap.entrySet().stream()
                .sorted(Map.Entry.<Character, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedMap.put(entry.getKey(), entry.getValue()));
        return sortedMap;
    }
}
