package com.glooneltharion.games.repository;

import com.glooneltharion.games.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlayerRepository extends JpaRepository<Player, Long> {
    List<Player> findAllByNameContainsIgnoreCaseAndScoreGreaterThanOrderByScoreDesc(
            @Param("name") String nationality, @Param("above") Integer above);

    List<Player> findAllByScoreGreaterThanOrderByScoreDesc(@Param("above") Integer above);
}
