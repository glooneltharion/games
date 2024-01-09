package com.glooneltharion.games.service;


import com.glooneltharion.games.model.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    List<Game> findAll();

    Optional<Game> findGameById(Long id);
}
