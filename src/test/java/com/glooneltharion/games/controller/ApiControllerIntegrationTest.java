package com.glooneltharion.games.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.glooneltharion.games.dto.PlayerDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ApiControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc; //simulate HTTP req to the controller dont forget to set it for every controller

    ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testGetPlayersOK() throws Exception {
        mockMvc.perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void testGetPlayersOKWithAbove() throws Exception {
        mockMvc.perform(get("/api")
                        .param("above", "700"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$.[0].name").value("Honey Badger"));
    }

    @Test
    void testAddPlayerOK() throws Exception {
        PlayerDto playerDto = new PlayerDto("Amethyst", 200,
                false, "Snake");

        mockMvc.perform(post("/api").content(objectMapper.writeValueAsString(playerDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Amethyst"));

    }
}