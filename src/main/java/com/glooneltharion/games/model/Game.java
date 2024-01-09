package com.glooneltharion.games.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer maxScore;
    private Date firstCompleted;

    public Game(String name, Integer maxScore) {
        this.name = name;
        this.maxScore = maxScore;
    }

    @OneToMany(mappedBy = "game")
    private List<Player> players = new ArrayList<>();

    public String getTitle() {
        return String.format(
                "%s - Max Score: %d (difficulty level of the game: %d)",
                getName(), getMaxScore(), getDifficulty());
    }

    public int getDifficulty() {
        return (getMaxScore() / 100);
    }
}
