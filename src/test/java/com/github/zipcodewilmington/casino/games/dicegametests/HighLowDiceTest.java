package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

public class HighLowDiceTest {
    Account account5;
    Account account6;

    HighLowDicePlayer player3;

    HighLowDicePlayer player4;

    HashSet<HighLowDicePlayer> playerSet;

    @Test
    public void testSetup() { // TODO
        account5 = new Account("Kris", "hi", 5000);
        account6 = new Account("Dolio", "hi", 8000);
        player3 = new HighLowDicePlayer(account5);
        player4 = new HighLowDicePlayer(account6);
        playerSet = new HashSet<>();
    }

    @Test
    public void highLowDiceGame() throws IOException {
        AccountManager mgr = new AccountManager();
        mgr.createAccount("goose", "omgimagoose");
        mgr.createAccount("duck", "omgimaduck");

        HashSet<HighLowDicePlayer> players = new HashSet<>();

        HighLowDicePlayer goose = new HighLowDicePlayer(mgr.getAccount("goose", "omgimagoose"));
        HighLowDicePlayer duck = new HighLowDicePlayer(mgr.getAccount("duck", "omgimaduck"));

        players.add(goose);
        players.add(duck);

        HighLowDice game = new HighLowDice(players);
    }
}
