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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import pl.sobczak.sptest.exceptions.SwapiRestExceptions;
import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

/**
 *
 * @author Lukasz Sobczak
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
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof SwapiRestExceptions.AkeitaException));

    }

    @Test
    void testHappyPath() throws Exception {
        long first = 1L;
        String firstQuery = "{\"query_criteria_character_phrase\":\"a\",\"query_criteria_planet_name\": \"Alderaan\"}";

        MockHttpServletRequestBuilder builder
                = MockMvcRequestBuilders.put("/report/" + first)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstQuery);

        mvc.perform(builder)
                .andDo(print())
                .andExpect(status().isNoContent());

        var result = mvc.perform(get("/report/{first}", first))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();

        var JSONresponse = result.getResponse().getContentAsString();

        assertThat(JSONresponse).as("json body")
                .matches(JSON -> JSON.split("Leia Organa").length > 3)
                .matches(JSON -> JSON.split("Alderaan").length > 8);

        mvc.perform(delete("/report/{first}", first))
                .andDo(print())
                .andExpect(status().isNoContent());

        mvc.perform(get("/report/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("[]")))
                .andReturn();

    }

}
