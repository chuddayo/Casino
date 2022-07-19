package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;

public class AccountTest {

    Account account3;
    Account account4;

    HighLowDicePlayer player1;

    HighLowDicePlayer player2;

    HashSet<HighLowDicePlayer> playerSet;

    @Test
    public void testSetup() throws IOException { // TODO
        Account account3;
        Account account4;

        HighLowDicePlayer player1;

        HighLowDicePlayer player2;

        HashSet<HighLowDicePlayer> playerSet;
    }

    @Test
    public void accountManagerTest() throws IOException {
        AccountManager mgr = new AccountManager();
        mgr.createAccount("goose", "omgimagoose");
        mgr.createAccount("duck", "omgimaduck");

    }

    @Test
    public void creatingAccountTest() throws IOException {
        AccountManager mgr = new AccountManager();
        mgr.createAccount("goose", "omgimagoose");
        mgr.createAccount("duck", "omgimaduck");
    }

}
