package com.github.zipcodewilmington.casino.games.dicegames;
import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class HighLowDicePlayer extends Player implements GamblingPlayer {

    Account account;

    public HighLowDicePlayer(Account account) {
        super(account);
    }

    @Override
    public void placeBet(int amount) {

    }
}
