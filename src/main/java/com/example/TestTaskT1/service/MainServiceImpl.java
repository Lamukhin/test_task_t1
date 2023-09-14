package com.example.TestTaskT1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.stereotype.Service;

import com.example.TestTaskT1.exception_handling.WrongLineException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MainServiceImpl implements MainService {

    @Override
    public Map<Character, Integer> solveTheTask(String line) {
        if (line.isEmpty()) {
            log.error("We got an empty line!");
            throw new WrongLineException("You have sent an empty String line!");
        }
        Map<Character, Integer> answer = getMapWithUniqueChars(line);
        log.info("We got a map with unique chars {}", answer.toString());
        List<Map.Entry<Character, Integer>> entryList = getSortedListOfMapEntries(answer);
        log.info("We sorted list of map {}", entryList.toString());
        answer = getSortedMap(entryList);
        return answer;
    }

    public Map<Character, Integer> getSortedMap(List<Entry<Character, Integer>> entryList) {
        Map<Character, Integer> sortedMap = new LinkedHashMap<>();
        for (Map.Entry<Character, Integer> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }
        return sortedMap;
    }

    public Map<Character, Integer> getMapWithUniqueChars(String line) {
        Map<Character, Integer> newMap = new HashMap<>();
        for (char c : line.toCharArray()) {
            newMap.put(c, newMap.getOrDefault(c, 0) + 1);
        }
        return newMap;
    }

    public List<Map.Entry<Character, Integer>> getSortedListOfMapEntries(Map<Character, Integer> map) {
        List<Map.Entry<Character, Integer>> entryList = new ArrayList<>(map.entrySet());
        Collections.sort(entryList, (a, b) -> b.getValue().compareTo(a.getValue()));
        return entryList;
    }
}
