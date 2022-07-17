package com.github.zipcodewilmington.casino.DiceTests;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Test;

import java.util.HashSet;

public class HighLowDicePlayerTest {

    @Test
    public void test() {
        Account acc1 = new Account("linda", "asd", 10000);
        Account acc2 = new Account("adnil", "dsa", 10000);

        HighLowDicePlayer p1 = new HighLowDicePlayer(acc1);
        HighLowDicePlayer p2 = new HighLowDicePlayer(acc2);

        HashSet<HighLowDicePlayer> players = new HashSet<>();

        players.add(p1);
        players.add(p2);

        HighLowDice game = new HighLowDice(players);
        game.beginGame();

        System.out.println(acc1.getBalance());
        System.out.println(acc2.getBalance());
    }

}
