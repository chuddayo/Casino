package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class HighLowDicePlayerTest {

    Account account = new Account("Linda", "test", 1000);
    HighLowDicePlayer player = new HighLowDicePlayer(account);

    @Test
    public void testAccount() {
        Assert.assertEquals(account, player.getPlayerAccount());
    }

    @Test
    public void testGetPlayerName() {
        Assert.assertEquals("Linda", player.getPlayerName());
    }

    @Test
    public void testBet() {
        player.bet("high");
        Assert.assertEquals("high", player.getBet());
    }

    @Test
    public void testBetBad() {
        player.bet("asdasdasd");
        Assert.assertEquals("invalid", player.getBet());
    }

    @Test
    public void testBetBad2() {
        player.bet("9879123asd");
        Assert.assertEquals("invalid", player.getBet());
    }

    @Test
    public void testBetAmount() {
        player.placeBet(100);
        Assert.assertEquals(100, player.getBetAmount());
    }

    @Test
    public void testBetAmountBad() {
        player.placeBet(100000000);
        Assert.assertEquals(-1, player.getBetAmount());
    }
}
