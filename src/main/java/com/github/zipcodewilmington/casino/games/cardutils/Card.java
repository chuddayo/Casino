package com.github.zipcodewilmington.casino.games.cardutils;

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
        char charValue = '?';
        int cardValue = getCardValue().getCardValue();
        if (cardValue == 2) charValue = '2';
        else if (cardValue == 3) charValue = '3';
        else if (cardValue == 4) charValue = '4';
        else if (cardValue == 5) charValue = '5';
        else if (cardValue == 6) charValue = '6';
        else if (cardValue == 7) charValue = '7';
        else if (cardValue == 8) charValue = '8';
        else if (cardValue == 9) charValue = '9';
        else if (cardValue == 10) charValue = 'T';
        else if (cardValue == 11) charValue = 'J';
        else if (cardValue == 12) charValue = 'Q';
        else if (cardValue == 13) charValue = 'K';
        else if (cardValue == 14) charValue = 'A';

        char charSuit = '%';
        if (getSuit().equals(Suit.SPADES)) charSuit = '♠';
        if (getSuit().equals(Suit.HEARTS)) charSuit = '♥';
        if (getSuit().equals(Suit.DIAMONDS)) charSuit = '♦';
        if (getSuit().equals(Suit.CLUBS)) charSuit = '♣';

        StringBuilder cardRep = new StringBuilder("┌─────────────┐\n");
        cardRep.append("│ ").append(charValue).append("           │\n");
        cardRep.append("│             │\n");
        cardRep.append("│             │\n");
        cardRep.append("│      ").append(charSuit).append("      │\n");
        cardRep.append("│             │\n");
        cardRep.append("│             │\n");
        cardRep.append("│           ").append(charValue).append(" │\n");
        cardRep.append("└─────────────┘\n");

        return String.valueOf(cardRep);

        //return getCardValue() + " of " + getSuit();
        /*
    ['┌─────────────┐'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['│░░░░░░░░░░░░░│'],
    ['└─────────────┘']
    ['┌─────────────┐'],
    ['│ A           │'],
    ['│             │'],
    ['│             │'],
    ['│      ♦      │'],
    ['│             │'],
    ['│             │'],
    ['│           A │'],
    ['└─────────────┘']
         */
    }

    @Override
    public int compareTo(Card card) {
        return this.getCardValue().compareTo(card.getCardValue());
    }
}
