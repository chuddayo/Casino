package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class RoulettePlayer extends Player implements GamblingPlayer {


    public RoulettePlayer(Account account) {
        super(account);
    }

    @Override
    public void placeBet(int amount) {

    }

    @Override
    public String getPlayerName() {
        return account.getUserName();
    }

    @Override
    public Account getPlayerAccount() {
        return account;
    }
}
