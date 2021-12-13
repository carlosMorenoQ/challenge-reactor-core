package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Map;

@Service
public class PlayerService {

    @Autowired
    PlayerRepository playerRepository;

    public Flux<Player> getPlayers(){
        return  playerRepository.findAll()
                .buffer(150)
                .flatMap(Flux::fromIterable);
    }

    public Flux<Player> getPlayersOver34(){
        return getPlayers()
                .filter(player -> player.getAge()>34);
    }

    public Flux<Player> getPlayersByClub(String club){
        return getPlayers()
                .filter(player -> player.getClub().equals(club));
    }


    public Flux<String>getNacionalities(){
        return getPlayers()
                .map(Player::getNational)
                .distinct();
    }

    public Flux<List<Player>> getRankingPlayers(){
        return getPlayers()
                .groupBy(Player::getNational)
                .flatMap(Flux::collectList)
                .sort();
    }

}
