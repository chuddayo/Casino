package com.github.zipcodewilmington.casino;

import java.util.Collections;
import java.util.Stack;

public class Deck {
    Stack<Card> deck;

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card dealTop() {
        return null;
    }

    public void discard(Card card) {

    }
}
