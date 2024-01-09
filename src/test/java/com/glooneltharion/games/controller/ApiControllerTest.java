package com.glooneltharion.games.controller;

import com.glooneltharion.games.dto.PlayerDto;
import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;
import com.glooneltharion.games.service.GameService;
import com.glooneltharion.games.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import java.util.List;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ApiController.class)
class ApiControllerTest {

    @Autowired
    MockMvc mockMvc; //simulate HTTP req to the controller dont forget to set it for every controller

    @MockBean
    PlayerService playerService;

    @MockBean
    GameService gameService;

    @MockBean
    GameRepository gameRepository;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void testGetPlayersOKEmpty() throws Exception {
        mockMvc.perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    void testGetPlayersOK() throws Exception {
        when(playerService.search(null, 0))
                .thenReturn(List.of(
                        new PlayerDto("Fox", 200,
                                false, "Space Invaders"),
                        new PlayerDto("Amethyst", 300, true, "Snake")));

        mockMvc.perform(get("/api"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].name").value("Fox"))
                .andExpect(jsonPath("$.[0].score").value(200))
                .andExpect(jsonPath("$.[1].isDead").value(true))
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    void testAddPlayer() throws Exception {
        PlayerDto playerDto = new PlayerDto("Fox", 200,
                false, "Wanderer");

        when(playerService.addPlayer(any()))
                .thenReturn(playerDto);

        mockMvc.perform(post("/api").content("{}")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Fox"));
    }
}