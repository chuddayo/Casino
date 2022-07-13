package com.github.zipcodewilmington.casino;

public class Card implements Comparable<Card> {
    private final Suit suit;
    private final CardValue cardValue;
    public Card(CardValue cardValue, Suit suit) {
        this.cardValue = cardValue;
        this.suit = suit;
    }

    public CardValue getCardValue() {
        return cardValue;
    }

    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return getCardValue() + " of " + getSuit();
    }

    @Override
    public int compareTo(Card card) {
        return this.getCardValue().compareTo(card.getCardValue());
    }
}
