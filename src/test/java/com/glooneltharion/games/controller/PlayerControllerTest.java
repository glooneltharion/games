package com.glooneltharion.games.controller;

import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;
import com.glooneltharion.games.service.GameService;
import com.glooneltharion.games.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(controllers = PlayerController.class)
class PlayerControllerTest {

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
    void testPlayOK() throws Exception {
        mockMvc.perform(post("/players/play"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("success", "Success: Game successful! You win!"));
    }

    @Test
    void testPlayThrows() throws Exception {
        when(playerService.play(1l, 0))
                .thenThrow(Exception.class);

        mockMvc.perform(post("/players/play")
                        .param("id", "1")
                        .param("score", "0"))
                .andDo(print())
                .andExpect(status().is3xxRedirection())
                .andExpect(flash().attribute("error", "Error: Play failed! You lose!"));
    }
}