package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;

import java.util.List;

public class ThreeCardPokerPlayer {
    private Account account;
    private List<Card> playerHand;

    public ThreeCardPokerPlayer(Account account) {
        this.account = account;
    }

    public int playOrFold() {
        return 0; // TODO do we need this method? or should user input call play and fold methods separately
    }
}
