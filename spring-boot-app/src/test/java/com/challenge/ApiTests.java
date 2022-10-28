package com.challenge;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.hamcrest.CoreMatchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK, classes = App.class)
@AutoConfigureMockMvc
public class ApiTests {

    @Autowired
    private MockMvc mvc;

    @Test
    public void SimpleRouteShouldBeCorrect()
            throws Exception {

        mvc.perform(get("/api/routing/poL/iTa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string(containsString("[\"POL\",\"DEU\",\"CHE\",\"ITA\"]")));
    }

    @Test
    public void ArubaShouldReturn418()
            throws Exception {

        mvc.perform(get("/api/routing/ABW/iTa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isIAmATeapot())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string(containsString("teapot")));
    }

    @Test
    public void RussiaShouldNotChangeBorders()
            throws Exception {

        mvc.perform(get("/api/countries")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(
                        content().string(containsString(
                                "{\"cca3\":\"RUS\",\"borders\":[\"AZE\",\"BLR\",\"CHN\",\"EST\",\"FIN\",\"GEO\",\"KAZ\",\"PRK\",\"LVA\",\"LTU\",\"MNG\",\"NOR\",\"POL\",\"UKR\"]}")));
    }
}
