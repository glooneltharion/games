package com.glooneltharion.games;

import com.glooneltharion.games.model.Game;
import com.glooneltharion.games.model.Player;
import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class GamesApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(GamesApplication.class, args);
    }

    private final GameRepository gameRepository;
    private final PlayerRepository playerRepository;

    @Override
    public void run(String... args) throws Exception {
        Game game1 = gameRepository.save(new Game("Space Invaders", 1000));
        Game game2 = gameRepository.save(new Game("Snake", 300));

        playerRepository.save(new Player("Honey Badger",  game1, 900, false));
        playerRepository.save(new Player("Cat",  game1, 100, false));
        playerRepository.save(new Player("Fox",  game1, 650, true));
        playerRepository.save(new Player("Hippo",  game2, 190, false));

    }
}
