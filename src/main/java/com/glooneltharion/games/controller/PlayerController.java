package com.glooneltharion.games.controller;


import com.glooneltharion.games.service.GameService;
import com.glooneltharion.games.service.PlayerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequestMapping("/players")
@RequiredArgsConstructor
public class PlayerController {
    private final PlayerService playerService;
    private final GameService gameService;

    @PostMapping
    public String addPlayer(
            @RequestParam(required = false) String name,
            @RequestParam(value = "game-id", required = false) Long gameId,
            RedirectAttributes redirectAttributes) {

        if (
                Objects.isNull(name) || name.isEmpty() ||
                        Objects.isNull(gameId)
        ) {
            redirectAttributes.addFlashAttribute("error", "Error: Missing data!");
        } else {
            playerService.addPlayer(name, gameService.findGameById(gameId).get());
        }
        return "redirect:/";
    }

    @PostMapping("/play")
    public String play(RedirectAttributes redirectAttributes,
                        @RequestParam(value = "id", required = false) Long id,
                        @RequestParam(value = "score", required = false) Integer score) {

        try {
            playerService.play(id, score);
            redirectAttributes.addFlashAttribute("success", "Success: Game successful! You win!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: Play failed! You lose!");
        }
        return "redirect:/";
    }

    @GetMapping("/{id}/save")
    public String save(
            RedirectAttributes redirectAttributes,
            @PathVariable("id") Long id) {

        try {
            playerService.savePlayer(id);
            redirectAttributes.addFlashAttribute("success", "Success: Player saved!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Error: Saving failed!");
        }
        return "redirect:/";

    }

}
