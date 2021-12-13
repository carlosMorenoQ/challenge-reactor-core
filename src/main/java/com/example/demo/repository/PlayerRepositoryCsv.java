package com.example.demo.repository;

import com.example.demo.model.Player;
import com.example.demo.utilities.CsvUtilFile;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.*;

public class PlayerRepositoryCsv {

    private Flux<Player> fluxFromPlayers(){
        return Flux.fromIterable(CsvUtilFile.getPlayers());
    }

    public Flux<String> getPlayersOver34() {
        return fluxFromPlayers().filter(player -> player.getAge() > 34)
                .map(Player::getName);
    }

    //Jugador de un club en especifico
    public Flux<Player> getPlayersByClub(String club) {
        return  fluxFromPlayers()
                .filter(player -> Objects.equals(player.getClub(), club));
    }

    //nacionalidades de los jugadores
    public Flux<String> getPlayersNacionalitie(){
        return fluxFromPlayers()
                .map(Player::getNational)
                .distinct();
    }

    //Ranquin de los jugadores
    public Mono<Map<String, Collection<Player>>> getRankinPlayers(){
        return paises()
                .flatMap(pais -> fluxFromPlayers()
                        .filter(player -> pais.equals(player.getNational()))
                        .sort(Comparator.comparingInt(Player::getWinners)))
                .collectMultimap(Player::getNational,player -> player);
    }

    static Flux<String> paises (){
        return Flux.fromStream(CsvUtilFile.getPlayers().stream()
                .map(Player::getNational).distinct());
    }


}
