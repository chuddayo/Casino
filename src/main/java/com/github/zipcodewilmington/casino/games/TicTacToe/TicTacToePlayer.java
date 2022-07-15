package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.Player;

/**
 * Created by leon on 7/21/2020.
 */
public class TicTacToePlayer extends Player {

    String userInput;
    int attempts;
    String[][] userInterface;

    public TicTacToePlayer(Account account) {
        super(account);
    }

    @Override
    public String getPlayerName() {
        return null;
    }

    @Override
    public Account getPlayerAccount() {
        return null;
    }
}