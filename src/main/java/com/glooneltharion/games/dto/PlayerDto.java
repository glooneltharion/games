package com.glooneltharion.games.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class PlayerDto {
    private String name;
    private Integer score;
    private Boolean isDead;
    private String game;
}
