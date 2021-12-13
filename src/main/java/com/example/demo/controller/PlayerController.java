package com.example.demo.controller;


import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@CrossOrigin("*")
public class PlayerController {


    @Autowired
    PlayerService playerService;

    @GetMapping(value = "/pasarRegistros")
    public Flux<Player> saveAllCsc (){
        return playerService.saveAllFromCSV();
    }




}
