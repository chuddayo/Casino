package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.cardutils.Card;
import com.github.zipcodewilmington.casino.games.cardutils.CardValue;
import com.github.zipcodewilmington.casino.games.cardutils.HandRank;
import com.github.zipcodewilmington.casino.games.cardutils.Suit;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ThreeCardPokerPlayerTest {
    Account account;
    ThreeCardPokerPlayer player;
    Suit spades;
    Suit hearts;
    Suit diamonds;
    Suit clubs;
    CardValue two;
    CardValue three;
    CardValue four;
    CardValue five;
    CardValue six;
    CardValue seven;
    CardValue eight;
    CardValue nine;
    CardValue ten;
    CardValue jack;
    CardValue queen;
    CardValue king;
    CardValue ace;
    @Before
    public void init() {
        account = new Account("ryan", "ok", 2000);
        player = new ThreeCardPokerPlayer(account);

        two = CardValue.TWO;
        three = CardValue.THREE;
        four = CardValue.FOUR;
        five = CardValue.FIVE;
        six = CardValue.SIX;
        seven = CardValue.SEVEN;
        eight = CardValue.EIGHT;
        nine = CardValue.NINE;
        ten = CardValue.TEN;
        jack = CardValue.JACK;
        queen = CardValue.QUEEN;
        king = CardValue.KING;
        ace = CardValue.ACE;
        hearts = Suit.HEARTS;
        diamonds = Suit.DIAMONDS;
        clubs = Suit.CLUBS;
        spades = Suit.SPADES;
    }
    @Test
    public void setPlayerHandTest() {
        List<Card> hand = new ArrayList<>();
        Card card1 = new Card(queen, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(queen, clubs);
        hand.add(card1);
        hand.add(card2);
        hand.add(card3);

        player.setPlayerHand(hand);
        List<Card> actual = player.getPlayerHand();

        Assert.assertEquals(hand, actual);
    }
    @Test
    public void setPlayerHandTest2() {
        List<Card> hand1 = new ArrayList<>();
        Card card1 = new Card(queen, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(queen, clubs);
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);

        List<Card> hand2 = new ArrayList<>();
        Card card4 = new Card(four, hearts);
        Card card5 = new Card(two, spades);
        Card card6 = new Card(three, clubs);
        hand2.add(card4);
        hand2.add(card5);
        hand2.add(card6);

        player.setPlayerHand(hand1);
        player.setPlayerHand(hand2);
        List<Card> actual = player.getPlayerHand();

        Assert.assertNotEquals(hand1, actual);
    }
    @Test
    public void getPlayerHandNullTest() {
        List<Card> actual = player.getPlayerHand();
        Assert.assertNull(actual);
    }
    @Test
    public void setPlayerHandRankTest() {
        HandRank handRank = HandRank.STRAIGHT;

        player.setPlayerHandRank(handRank);
        HandRank actual = player.getPlayerHandRank();

        Assert.assertEquals(handRank, actual);
    }
    @Test
    public void setPlayerHandRankNotEqualsTest() {
        HandRank handRank = HandRank.STRAIGHT;

        player.setPlayerHandRank(HandRank.HIGHCARD);
        HandRank actual = player.getPlayerHandRank();

        Assert.assertNotEquals(handRank, actual);
    }
    @Test
    public void placeBetTest() {
        int expected = 317;
        player.placeBet(expected);
        int actual = player.getAnte();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void placeBetNotSetTest() {
        Integer actual = player.getAnte();
        Assert.assertNull(actual);
    }
    @Test
    public void setPairPlusBetTest() {
        int expected = 455;
        player.setPairPlusBet(expected);
        int actual = player.getPairPlusBet();

        Assert.assertEquals(expected, actual);
    }
    @Test
    public void setPairPlusBetNotSetTest() {
        Integer actual = player.getPairPlusBet();
        Assert.assertNull(actual);
    }
}
