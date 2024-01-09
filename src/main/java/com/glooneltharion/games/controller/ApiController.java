package com.glooneltharion.games.controller;

import com.glooneltharion.games.dto.PlayerDto;
import com.glooneltharion.games.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ApiController {

    private final PlayerService playerService;

    @GetMapping
    public List<PlayerDto> getPlayers(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "above", required = false, defaultValue = "0") Integer above
    ) {
        return playerService.search(name, above);
    }

    @PostMapping
    public ResponseEntity<PlayerDto> addPlayer(@RequestBody PlayerDto player) {

        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(playerService.addPlayer(player));
        } catch (NoSuchElementException exc) {
            return ResponseEntity.badRequest().build();
        }
    }
}
