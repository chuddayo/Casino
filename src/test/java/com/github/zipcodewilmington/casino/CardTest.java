package com.github.zipcodewilmington.casino;

import org.junit.Assert;
import org.junit.Test;

public class CardTest {
    @Test
    public void CardConstructorTest() {
        Suit spades = Suit.SPADES;
        CardValue nine = CardValue.NINE;
        Card card = new Card(nine, spades);
        Suit actualSuit = card.getSuit();
        CardValue actualCardValue = card.getCardValue();

        Assert.assertEquals(spades, actualSuit);
        Assert.assertEquals(nine, actualCardValue);
    }

    @Test
    public void toStringCardTest() {
        Suit spades = Suit.SPADES;
        CardValue nine = CardValue.NINE;
        Card card = new Card(nine, spades);
        String expected = "NINE of SPADES";

        String actual = card.toString();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void compareToCardTest() {
        Suit hearts = Suit.HEARTS;
        CardValue king = CardValue.KING;
        Card card1 = new Card(king, hearts);
        Suit spades = Suit.SPADES;
        CardValue nine = CardValue.NINE;
        Card card2 = new Card(nine, spades);

        System.out.println(card1.compareTo(card2));
    }
}
