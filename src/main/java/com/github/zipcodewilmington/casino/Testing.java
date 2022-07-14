package com.github.zipcodewilmington.casino;

import com.github.zipcodewilmington.casino.games.dicegames.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegames.HighLowDicePlayer;

import java.util.HashSet;

public class Testing {

    public static void main(String[] args) {

        Account linda = new Account("linda", "asd", 10000);
        Account steven = new Account("steven", "asd", 10000);

        HighLowDicePlayer p1 = new HighLowDicePlayer(linda);
        HighLowDicePlayer p2 = new HighLowDicePlayer(steven);

        HashSet<HighLowDicePlayer> players = new HashSet<>();

        players.add(p1);
        players.add(p2);

        HighLowDice game = new HighLowDice(players);
        game.beginGame();

        System.out.println(linda.getBalance());
        System.out.println(steven.getBalance());

    }
}