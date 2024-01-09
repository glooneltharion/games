package com.glooneltharion.games.repository;

import com.glooneltharion.games.model.Player;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
class PlayerRepositoryTest {

    // for test we are using h2 database dont forget to change application.properties and add dependencies
    @Autowired
    PlayerRepository playerRepository;

    @Test
    void testGetPlayer() {
        Optional<Player> player = playerRepository.findById(1L);

        assertTrue(player.isPresent());
        assertEquals( "Honey Badger", player.get().getName());
    }
}