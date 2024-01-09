package com.glooneltharion.games.service;

import com.glooneltharion.games.dto.PlayerDto;
import com.glooneltharion.games.model.Game;
import com.glooneltharion.games.model.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerService {
    List<Player> findAll();

    Player addPlayer(String name, Game game);

    Optional<Player> findById(Long id);

    Player play(Long id, Integer score) throws Exception;

    Player savePlayer(Long id) throws Exception;

    List<PlayerDto> search(String name, int above);

    PlayerDto addPlayer(PlayerDto playerInput);
}
