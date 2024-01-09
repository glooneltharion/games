package com.glooneltharion.games.service;

import com.glooneltharion.games.model.Player;
import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PlayerServiceImplTest {

    PlayerService playerService;

    @Mock
    PlayerRepository playerRepository;

    @Mock
    GameRepository gameRepository;

    AutoCloseable autoCloseable;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        playerService = new PlayerServiceImpl(playerRepository, gameRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testSavePlayerOK() throws Exception {
        Player player = new Player();
        player.setIsDead(true);
        player.setScore(5000);
        player.setId(2L);

        when(playerRepository.findById(any())) // here I set that for any() value of id I will return player
                .thenReturn(Optional.of(player));

        playerService.savePlayer(3l); // here I can put value I want for ID becuese I set any()

        assertFalse(player.isDead());
        assertEquals(0, player.getScore());

        verify(playerRepository, times(1)).save(player);
    }

    @Test
    void testSavePlayerThrows() {
        Player player = new Player();
        player.setIsDead(false);

        when(playerRepository.findById(any()))
                .thenReturn(Optional.of(player));


        assertThrows(Exception.class, () -> playerService.savePlayer(1l));
    }


}