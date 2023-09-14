package com.example.TestTaskT1.service;

import com.example.TestTaskT1.exception_handling.WrongLineException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;


@WebMvcTest(MainServiceImpl.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MainServiceImplTest {

    @Autowired
    private MainServiceImpl mainServiceImpl;

    private Map<Character, Integer> finalAnswer;
    private String inputLine;
    private String emptyLine;
    private Map<Character, Integer> inputMap;
    private List<Map.Entry<Character, Integer>> sortedListOfEntries;

    @BeforeEach
    public void init() {
        inputLine = "aaaaabcccc";
        finalAnswer = new HashMap<>() {
            {
                put('a', 5);
                put('c', 4);
                put('b', 1);
            }
        };

        inputMap = new HashMap<>() {
            {
                put('a', 5);
                put('b', 1);
                put('c', 4);
            }
        };

        sortedListOfEntries = new ArrayList<>();
        sortedListOfEntries.add(new AbstractMap.SimpleEntry<>('a', 5));
        sortedListOfEntries.add(new AbstractMap.SimpleEntry<>('c', 4));
        sortedListOfEntries.add(new AbstractMap.SimpleEntry<>('b', 1));
        
        emptyLine = "";
    }

    @Test
    public void shouldSolveTheTask() {
    	Map<Character, Integer> result = mainServiceImpl.solveTheTask(inputLine);
    	assertEquals(finalAnswer, result);
    }

    @Test
    public void shouldNotSolveTheTask_throwException() {
        Exception exception = Assertions.assertThrows(WrongLineException.class, () -> {
            mainServiceImpl.solveTheTask(emptyLine);
        });

        String expectedMessage = "You have sent an empty String line!";
        String actualMessage = exception.getMessage();

        Assertions.assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void shouldGetSortedMap() {
        Map<Character, Integer> result = mainServiceImpl.getSortedMap(sortedListOfEntries);
        assertEquals(finalAnswer, result);
    }

    @Test
    public void shouldGetSortedListOfMapEntries() {
        List<Map.Entry<Character, Integer>> result = mainServiceImpl.getSortedListOfMapEntries(inputMap);
        assertEquals(sortedListOfEntries, result);

    }
}
