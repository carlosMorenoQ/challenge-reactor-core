package com.example.demo;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;

import java.util.*;

public class Main2 {

    public static void main(String[] args) {

        //Ranquin de los jugadores
        PlayerRepository playerRepository = new PlayerRepository();
        Map<String, Collection<Player>> lista = playerRepository.getRankinPlayers().block();
        assert lista != null;
        lista.forEach((k,v) -> {
            System.out.println("Pais: " + k + " **********************************************");
            v.forEach(player ->
                System.out.println(player.getName() + " --> victorias: "+player.getWinners()));
        } );

    }


}
