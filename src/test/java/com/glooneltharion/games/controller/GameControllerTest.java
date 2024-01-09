package com.glooneltharion.games.controller;

import com.glooneltharion.games.model.Game;
import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;
import com.glooneltharion.games.service.GameService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = GameController.class)
class GameControllerTest {

    @Autowired
    MockMvc mockMvc; //simulate HTTP req to the controller dont forget to set it for every controller

    @MockBean
    GameService gameService;

    @MockBean
    GameRepository gameRepository;

    @MockBean
    PlayerRepository playerRepository;

    @Test
    void testGetGameOKWithError() throws Exception {
        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("error", "Error: Game not found!"));
    }

    @Test
    void testGetGameOKWithGAme() throws Exception {
        Game game = new Game();
        game.setName("Space Invaders");
        game.setMaxScore(1000);
        game.setId(1l);

        when(gameService.findGameById(any()))
                .thenReturn(Optional.of(game));

        mockMvc.perform(get("/games/1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("game"))
                .andExpect(model().attribute("game", game));
    }
}