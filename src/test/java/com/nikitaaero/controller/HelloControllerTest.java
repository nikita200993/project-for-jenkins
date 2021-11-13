package com.nikitaaero.controller;

import com.nikitaaero.config.WebConfig;
import io.qameta.allure.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = WebConfig.class)
@WebAppConfiguration("classpath:webapp")
public class HelloControllerTest {

    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext ctx;

    @BeforeEach
    public void setUpMockMvc() {
        mockMvc = MockMvcBuilders.webAppContextSetup(ctx)
                .build();
    }

    @Test
    @Story("User gets hello world page")
    public void testReturnHelloViewName() throws Exception {
        mockMvc.perform(get("/"))
                .andExpect(status().is(HttpStatus.OK.value()))
                .andExpect(view().name("index"));
    }
}
