package com.github.zipcodewilmington.casino.games.dicegames;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;

import java.io.IOException;
import java.util.HashSet;

public class HighLowDiceTest {
    public static void main(String[] args) throws IOException {

        AccountManager mgr = new AccountManager();

        HashSet<HighLowDicePlayer> players = new HashSet<>();

        HighLowDicePlayer linda = new HighLowDicePlayer(mgr.getAccount("Linda", "password"));
        HighLowDicePlayer steven = new HighLowDicePlayer(mgr.getAccount("Steven", "password"));

        players.add(linda);
        players.add(steven);

        HighLowDice game = new HighLowDice(players);
        game.beginGame();

        mgr.updateAccounts();
    }
}
