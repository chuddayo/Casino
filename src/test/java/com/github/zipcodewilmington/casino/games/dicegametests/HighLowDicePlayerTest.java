package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Test;

import java.util.HashSet;

public class HighLowDicePlayerTest {
    Account account3;
    Account account4;

    HighLowDicePlayer player1;

    HighLowDicePlayer player2;

    HashSet<HighLowDicePlayer> playerSet;

    @Test
    public void setupTest() {
        account3 = new Account("Ryan", "C", 5000);
        account4 = new Account("Tristan", "T", 8000);
        player1 = new HighLowDicePlayer(account3);
        player2 = new HighLowDicePlayer(account4);
        playerSet = new HashSet<>();
    }



}
