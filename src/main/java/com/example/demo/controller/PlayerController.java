package com.example.demo.controller;


import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PlayerController {


    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/saveAllPlayersFromCsv")
    public Flux<Player> saveAllPlayersFromCsv(){
        return playerService.saveAllFromCSV()
                .buffer(150)
                .flatMap(Flux::fromIterable);
    }

    @GetMapping(value = "/playersOver34")
    public Flux<Player> getPlayersOver34(){
        return playerService.getPlayersOver34();
    }

    @GetMapping(value = "/playersNacionalities")
    public Mono<List<String>> playersNacionalities(){
        return playerService.getNacionalities();
    }


    @GetMapping(value = "/playersFromClub/{club}")
    public Flux<Player> playersFromClub(@PathVariable("club") String club){
        return playerService.getPlayersByClub(club);
    }


    @GetMapping(value = "/rankingPlayers")
    public Flux<List<Player>> rankingPlayers(){
        return playerService.getRankingPlayers();
    }



}
