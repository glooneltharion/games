package com.glooneltharion.games.controller;

import com.glooneltharion.games.service.GameService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("games")
@RequiredArgsConstructor
public class GameController {

    private final GameService gameService;

    @GetMapping("/{id}")
    public String getGame(
            @PathVariable("id") Long id,
            Model model) {

        var game = gameService.findGameById(id);

        if (game.isPresent()) {
            model.addAttribute("game", game.get());
        } else {
            model.addAttribute("error", "Error: Game not found!");
        }

        return "game";
    }
}
