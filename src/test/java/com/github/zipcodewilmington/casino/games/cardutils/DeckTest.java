package com.github.zipcodewilmington.casino.games.cardutils;

import com.github.zipcodewilmington.casino.games.cardutils.Card;
import com.github.zipcodewilmington.casino.games.cardutils.Deck;
import org.junit.Assert;
import org.junit.Test;

public class DeckTest {
    @Test
    public void DeckConstructorTest() {
        Deck deck = new Deck();
        Assert.assertEquals(52, deck.size());

        Card actual = deck.dealTop();
        Assert.assertFalse(deck.contains(actual));
        deck.discard(actual);
        Assert.assertTrue(deck.contains(actual));
    }

    @Test
    public void dealTopTest() {
        Deck deck = new Deck();
        Assert.assertEquals(52, deck.size());

        Card actual = deck.dealTop();
        Assert.assertFalse(deck.contains(actual));
        Assert.assertEquals(51, deck.size());
    }

    @Test
    public void discardTest() {
        Deck deck = new Deck();
        Assert.assertEquals(52, deck.size());

        Card actual = deck.dealTop();
        Assert.assertNotEquals(52, deck.size());
        deck.discard(actual);
        Assert.assertEquals(52, deck.size());
    }
}
