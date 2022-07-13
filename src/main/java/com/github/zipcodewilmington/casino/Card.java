package com.github.zipcodewilmington.casino;

public class Card implements Comparable<Card> {
    private Suit suit;
    private CardValue cardValue;
    public Card(CardValue cardValue, Suit suit) {
        this.cardValue = cardValue;
        this.suit = suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public void setCardValue(CardValue cardValue) {
        this.cardValue = cardValue;
    }

    public int getRank() {
        return 0;
    }

    public String getSuit() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }

    @Override
    public int compareTo(Card card) {
        return 0;
    }
}
