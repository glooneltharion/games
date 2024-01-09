package com.glooneltharion.games.model;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    public Player(String name, Game game, Integer score, Boolean isDead) {
        this.name = name;
        this.game = game;
        this.score = score;
        this.isDead = isDead;
    }

    @ManyToOne
    private Game game;
    private Integer score;
    @Getter(AccessLevel.NONE)
    private Boolean isDead;

    public Boolean isDead() {
        return isDead;
    }
}
