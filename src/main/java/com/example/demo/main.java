package com.example.demo;

import com.example.demo.model.Player;
import com.example.demo.utilities.CsvUtilFile;

import java.util.List;
import java.util.stream.Collectors;

public class main {

    public static void main(String[] args) {

/*
        //Jugadores con edad mayor a 34
        System.out.println("Jugadores con edad mayor a 34");
        CsvUtilFile.getPlayers().stream()
                .filter(player -> player.getAge()>34)
                .map(Player::getName)
                .forEach(System.out::println);

        //Jugador de un club en especifico
        System.out.println("Jugador de un club en especifico");
        String club = "Juventus";
        CsvUtilFile.getPlayers().stream()
                .filter(player -> Objects.equals(player.getClub(), club))
                .map(Player::getName)
                .forEach(System.out::println);
*/

        //nacionalidades de los jugadores
        CsvUtilFile.getPlayers().stream().map(Player::getNational).distinct().forEach(System.out::println);

/*

        //Ranquin de los jugadores
        paises().forEach(pais-> CsvUtilFile.getPlayers().stream()
       .filter(player -> Objects.equals(player.getNational(),pais))
       .sorted(Comparator.comparingInt(Player::getWinners))
       .forEach(player -> System.out.println(pais + ": Jugador, " + player.getName() +
               ", Victorias, " + player.getWinners() )));
*/


    }

    static List<String> paises (){
        return CsvUtilFile.getPlayers().stream()
                .map(Player::getNational).distinct().collect(Collectors.toList());
    }





}
