package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.Player;

public class ThreeCardPokerGame implements GameInterface {
    @Override
    public void beginGame() {

    }

    @Override
    public String printInstructions() {
        return "Welcome";
    }

    @Override
    public Player decideWinner() {
        return null;
    }
}
