package com.github.zipcodewilmington.casino.games;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.Card;
import com.github.zipcodewilmington.casino.CardValue;
import com.github.zipcodewilmington.casino.Suit;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ThreeCardPokerGameTest {
    Suit spades;
    Suit hearts;
    Suit diamonds;
    Suit clubs;
    CardValue three;
    CardValue four;
    CardValue seven;
    CardValue king;
    CardValue ace;
    Account account1;
    Account account2;
    ThreeCardPokerPlayer player1;
    ThreeCardPokerPlayer player2;
    HashSet<ThreeCardPokerPlayer> playerSet;
    ThreeCardPokerGame newGame;

    @Before
    public void setup(){
        three = CardValue.THREE;
        four = CardValue.FOUR;
        seven = CardValue.SEVEN;
        king = CardValue.KING;
        ace = CardValue.ACE;
        hearts = Suit.HEARTS;
        diamonds = Suit.DIAMONDS;
        clubs = Suit.CLUBS;
        spades = Suit.SPADES;

        account1 = new Account("Kyle", "p", 5000);
        account2 = new Account("Thina", "q", 8000);
        player1 = new ThreeCardPokerPlayer(account1);
        player2 = new ThreeCardPokerPlayer(account2);
        playerSet = new HashSet<>();
    }
    @Test
    public void sortHandTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(three, spades);
        Card card3 = new Card(ace, diamonds);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        List<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card2);
        expectedHand.add(card);
        expectedHand.add(card3);

        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        newGame.sortHand(hand);
        Assert.assertEquals(expectedHand, hand);
    }
    @Test
    public void sortHandTest2() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(three, spades);
        Card card3 = new Card(seven, clubs);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        List<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card2);
        expectedHand.add(card);
        expectedHand.add(card3);

        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        newGame.sortHand(hand);
        Assert.assertEquals(expectedHand, hand);
    }

    @Test
    public void sortHandTest3() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(ace, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(seven, clubs);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        List<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card3);
        expectedHand.add(card2);
        expectedHand.add(card);

        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        newGame.sortHand(hand);
        Assert.assertEquals(expectedHand, hand);
    }

    //    @Test
//    public void flipAllCardsTest() {
//        Account account1 = new Account("Kyle", "p", 5000);
//        Account account2 = new Account("Thina", "q", 8000);
//        ThreeCardPokerPlayer player1 = new ThreeCardPokerPlayer(account1);
//        ThreeCardPokerPlayer player2 = new ThreeCardPokerPlayer(account2);
//        HashSet<ThreeCardPokerPlayer> playerSet = new HashSet<>();
//        playerSet.add(player1);
//        playerSet.add(player2);
//        ThreeCardPokerGame newGame = new ThreeCardPokerGame(playerSet);
//
//        System.out.println(newGame.flipAllCards());
//    }
}