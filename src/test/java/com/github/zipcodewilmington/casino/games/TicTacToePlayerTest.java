package com.github.zipcodewilmington.casino.games;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import org.junit.Test;

import java.util.HashSet;

public class TicTacToePlayerTest {

    @Test
    public void test() {
        Account acct1 = new Account("tenae", "6", 1000);

        TicTacToePlayer p1 = new TicTacToePlayer(acct1);

        HashSet<TicTacToePlayer> players = new HashSet<>();
        players.add(p1);

        TicTacToeTest game = new TicTacToeTest();

        System.out.println(acct1.getBalance());
    }
}
