package com.glooneltharion.games.service;

import com.glooneltharion.games.dto.PlayerDto;
import com.glooneltharion.games.model.Game;
import com.glooneltharion.games.model.Player;
import com.glooneltharion.games.repository.GameRepository;
import com.glooneltharion.games.repository.PlayerRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;
    private final GameRepository gameRepository;

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll(
                Sort.by("id")
        );
    }

    @Override
    public Player addPlayer(String name, Game game) {
        return playerRepository.save(new Player(
                name, game, 0, false
        ));
    }

    @Override
    public Optional<Player> findById(Long id) {
        return playerRepository.findById(id);
    }

    @Override
    public Player play(Long id, Integer score) throws Exception {
        if (Objects.isNull(id) || Objects.isNull(score) || (score < 0)) {
            throw new Exception();
        }

        var playerOptional = findById(id);

        if (playerOptional.isEmpty()) {
            throw new Exception();
        }

        Player player = playerOptional.get();

        Random random = new Random();

        boolean isDead = random.nextBoolean();

        if (player.isDead() || isDead ||
                player.getGame().getMaxScore() <= player.getScore()) {

            if (!player.isDead() && isDead) {
                player.setIsDead(true);
                playerRepository.saveAndFlush(player);
            }

            throw new Exception();
        } else {
            var game = player.getGame();

            player.setScore(Math.min(
                    player.getScore() + score,
                    game.getMaxScore()));

            if (player.getScore() >= game.getMaxScore()) {
                if (Objects.isNull(game.getFirstCompleted())) {
                    game.setFirstCompleted(Date.valueOf(LocalDate.now()));
                    gameRepository.save(game);
                }
            }

            return playerRepository.save(player);
        }
    }

    @Override
    public Player savePlayer(Long id) throws Exception {
        if (Objects.isNull(id)) {
            throw new Exception();
        }

        var player = playerRepository.findById(id);


        if (player.isEmpty() || !player.get().isDead()) {
            throw new Exception();
        }

        player.get().setScore(0);
        player.get().setIsDead(false);

        playerRepository.save(player.get());
        return player.get();
    }

    @Override
    public List<PlayerDto> search(String name, int above) {
        List<Player> players;

        if (Objects.nonNull(name)) {
            players = playerRepository.findAllByNameContainsIgnoreCaseAndScoreGreaterThanOrderByScoreDesc(
                    name, above
            );
        } else {
            players = playerRepository.findAllByScoreGreaterThanOrderByScoreDesc(
                    above
            );
        }

        return players.stream()
                .map(x -> new PlayerDto(x.getName(),
                        x.getScore(), x.isDead(),
                        x.getGame().getName()))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto addPlayer(PlayerDto playerInput) {
        var game = gameRepository.findGameByName(playerInput.getGame())
                .get();

        var player = new Player();

        player.setName(playerInput.getName());
        player.setScore(playerInput.getScore());
        player.setIsDead(playerInput.getIsDead());
        player.setGame(game);

        return fromPlayer(playerRepository.save(player));
    }

    private PlayerDto fromPlayer(Player player) {
        return new PlayerDto(
                player.getName(),
                player.getScore(), player.isDead(),
                player.getGame().getName());
    }
}

