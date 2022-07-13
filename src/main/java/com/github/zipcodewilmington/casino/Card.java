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

    private enum CardValue {
        TWO(2),
        THREE(3),
        FOUR(4),
        FIVE(5),
        SIX(6),
        SEVEN(7),
        EIGHT(8),
        NINE(9),
        TEN(10),
        JACK(11),
        QUEEN(12),
        KING(13),
        ACE(14);

        private final int cardValue;

        CardValue(int value) {
            this.cardValue = value;
        }

        public int getCardValue() {
            return cardValue;
        }
    }

    private enum Suit {
        CLUBS,
        DIAMONDS,
        HEARTS,
        SPADES;
    }
}
