package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.games.cardutils.Card;
import com.github.zipcodewilmington.casino.games.cardutils.HandRank;

import java.util.List;

public class ThreeCardPokerPlayer extends Player implements GamblingPlayer{
    private List<Card> playerHand;
    private HandRank playerHandRank;
    private Integer ante;
    private Integer pairPlusBet;

    public ThreeCardPokerPlayer(Account account) {
        super(account);
    }

    @Override
    public String getPlayerName() {
        return account.getUserName();
    }

    @Override
    public Account getPlayerAccount() {
        return account;
    }

    public HandRank getPlayerHandRank() {
        return playerHandRank;
    }

    public void setPlayerHandRank(HandRank playerHandRank) {
        this.playerHandRank = playerHandRank;
    }

    public List<Card> getPlayerHand() {
        return playerHand;
    }

    public void setPlayerHand(List<Card> playerHand) {
        this.playerHand = playerHand;
    }

    public Integer getAnte() {
        return ante;
    }

    @Override
    public void placeBet(int amount) {
        ante = amount;
    }

    public Integer getPairPlusBet() {
        return pairPlusBet;
    }

    public void setPairPlusBet(Integer pairPlusBet) {
        this.pairPlusBet = pairPlusBet;
    }
}
