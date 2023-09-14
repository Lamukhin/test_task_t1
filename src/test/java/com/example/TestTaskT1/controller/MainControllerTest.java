package com.example.TestTaskT1.controller;

import com.example.TestTaskT1.dto.StringLineDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.TestTaskT1.service.MainService;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@WebMvcTest(MainController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class MainControllerTest {

    @Autowired
    private MainController mainController;

    @MockBean
    private MainService mainService;

    private StringLineDto stringLineDto;

    private String a;

    Map<Character, Integer> answer;

    @BeforeEach
    public void init() {
        stringLineDto = new StringLineDto("aaaaabcccc");
        a = "aaaaabcccc";
        answer = new HashMap<>() {
            {
                put('a', 5);
                put('c', 4);
                put('b', 1);
            }
        };
    }

    @Test
    public void shouldSolveTheTask() throws Exception {
        when(mainService.solveTheTask(a)).thenReturn(answer);

        ResponseEntity<Map<Character, Integer>> response = mainController.solveTheTask(stringLineDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(answer, response.getBody());
    }
}
