package com.glooneltharion.games.controller;

import com.glooneltharion.games.service.GameService;
import com.glooneltharion.games.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class HomeController {
    private final GameService gameService;
    private final PlayerService playerService;
    @GetMapping
    public String getIndex(Model model) {
        model.addAttribute("players", playerService.findAll());
        model.addAttribute("games", gameService.findAll());

        return "index";
    }
}
