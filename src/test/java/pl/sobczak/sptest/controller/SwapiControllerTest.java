/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.sobczak.sptest.controller;

import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pl.sobczak.sptest.exceptions.RestExceptions;

/**
 *
 * @author piko
 */
@SpringBootTest
@AutoConfigureMockMvc
class SwapiControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    void testAkeita() throws Exception {

        mvc.perform(get("/report/akeita")                
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isIAmATeapot())
                .andExpect(result -> assertEquals("No Cofee Today!", result.getResolvedException().getMessage()))
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof RestExceptions.AkeitaException));

    }

//    @Test
//    void testGetOne() throws Exception{
//        
//        long id = 1;
//        
//        mvc.perform(get("/report/fake/{id}", id))
//                .andDo(print())
//                .andExpect(content().string(containsString("Fake Report with id 1")))
//                .andExpect(status().isOk());
//    }
//    
}
