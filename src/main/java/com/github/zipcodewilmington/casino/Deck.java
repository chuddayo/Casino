package com.github.zipcodewilmington.casino;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    Stack<Card> deck;

    public Deck() {
        this.deck = new Stack<>();

        for (int i = 0; i < 13; i++) {
            CardValue value = CardValue.values()[i];
            for (int j = 0; j < 4; j++) {
                Card card = new Card(value, Suit.values()[j]);
                this.deck.add(card);
            }
        }

        this.shuffle();
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealTop() {
        return deck.pop();
    }

    public void discard(Card card) {
        deck.add(card);
    }
}
