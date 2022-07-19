package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class HighLowDiceTest {

    Account account1 = new Account("Linda", "test", 5000);
    HighLowDicePlayer player1 = new HighLowDicePlayer(account1);

    Account account2 = new Account("Steven", "test", 5000);
    HighLowDicePlayer player2 = new HighLowDicePlayer(account2);

    HashSet<HighLowDicePlayer> set = new HashSet<>();

    @Test
    public void testGetPlayer() {
        set.add(player1);
        set.add(player2);
        HighLowDice game = new HighLowDice(set);

        Assert.assertEquals(set, game.getPlayers());
    }

    @Test
    public void testPayout() {
        set.add(player1);
        set.add(player2);
        HighLowDice game = new HighLowDice(set);
        game.payout(player1.getAccount(), 1000);
        game.payout(player2.getAccount(), 10000);

        Assert.assertEquals(6000, player1.getAccount().getBalance());
        Assert.assertEquals(15000, player2.getAccount().getBalance());
    }

    @Test
    public void testGetWinningBet() {
        set.add(player1);
        set.add(player2);
        HighLowDice game = new HighLowDice(set);

        game.winningBet();

        Assert.assertTrue(game.getWinningBet().equals("high") || game.getWinningBet().equals("low") || game.getWinningBet().equals("seven"));
    }

    @Test
    public void testCheckPlayerBalance() {
        set.add(player1);
        set.add(player2);
        HighLowDice game = new HighLowDice(set);

        player2.getAccount().setBalance(0);
        game.checkPlayersBalance();

        Assert.assertEquals(1, game.getPlayers().size());
    }

    @Test
    public void testGetPayout() {
        set.add(player1);
        set.add(player2);
        HighLowDice game = new HighLowDice(set);

        player1.placeBet(1000);
        player1.bet("high");
        player2.placeBet(1000);
        player2.bet("low");

        game.winningBet();
        game.payout();

        if (game.getWinningBet().equals("high")) {
            Assert.assertEquals(6000, player1.getAccount().getBalance());
            Assert.assertEquals(4000, player2.getAccount().getBalance());
        } else if (game.getWinningBet().equals("low")) {
            Assert.assertEquals(4000, player1.getAccount().getBalance());
            Assert.assertEquals(6000, player2.getAccount().getBalance());
        } else {
            Assert.assertEquals(4000, player1.getAccount().getBalance());
            Assert.assertEquals(4000, player2.getAccount().getBalance());
        }
    }

}
