package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.cardutils.Card;
import com.github.zipcodewilmington.casino.games.cardutils.CardValue;
import com.github.zipcodewilmington.casino.games.cardutils.HandRank;
import com.github.zipcodewilmington.casino.games.cardutils.Suit;
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
    Account account1;
    Account account2;
    ThreeCardPokerPlayer player1;
    ThreeCardPokerPlayer player2;
    HashSet<ThreeCardPokerPlayer> playerSet;
    ThreeCardPokerGame newGame;

    @Before
    public void setup(){
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
    @Test
    public void sortAceTwoThreeStraightTest() {
        List<Card> hand = new ArrayList<>();
        Card card1 = new Card(two, diamonds);
        Card card2 = new Card(ace, spades);
        Card card3 = new Card(three, clubs);
        hand.add(card1);
        hand.add(card2);
        hand.add(card3);


        List<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card2);
        expectedHand.add(card1);
        expectedHand.add(card3);

        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        newGame.sortHand(hand);
        List<Card> actualHand = newGame.sortAceTwoThreeStraight(hand);
        Assert.assertEquals(expectedHand, actualHand);
    }
    @Test
    public void discardHandTest1() {
        newGame = new ThreeCardPokerGame(playerSet);
        Assert.assertEquals(52, newGame.getDeck().size());

        Card card1 = new Card(queen, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(queen, clubs);
        List<Card> hand = new ArrayList<>();
        hand.add(card1);
        hand.add(card2);
        hand.add(card3);

        newGame.discardHand(hand);

        Assert.assertEquals(55, newGame.getDeck().size());
    }
    @Test
    public void pushOnePairHandTest1() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(queen, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(queen, clubs);
        hand.add(card2);
        hand.add(card);
        hand.add(card3);

        List<Card> expectedHand = new ArrayList<>();
        expectedHand.add(card2);
        expectedHand.add(card);
        expectedHand.add(card3);

        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        List<Card> actual = newGame.pushOnePairHand(hand);
        Assert.assertEquals(expectedHand, actual);
    }
    @Test
    public void determineHandRankStraightTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(five, diamonds);
        Card card2 = new Card(three, spades);
        Card card3 = new Card(four, clubs);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHT;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotStraightTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(five, diamonds);
        Card card2 = new Card(king, spades);
        Card card3 = new Card(four, clubs);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHT;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankStraightFlushTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(ace, diamonds);
        Card card2 = new Card(king, diamonds);
        Card card3 = new Card(queen, diamonds);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHTFLUSH;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotStraightFlushTest1() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(ace, diamonds);
        Card card2 = new Card(seven, diamonds);
        Card card3 = new Card(queen, diamonds);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHTFLUSH;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotStraightFlushTest2() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(ace, diamonds);
        Card card2 = new Card(king, diamonds);
        Card card3 = new Card(queen, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHTFLUSH;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankFlushTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(king, diamonds);
        Card card3 = new Card(four, diamonds);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.FLUSH;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotFlushTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(king, diamonds);
        Card card3 = new Card(four, clubs);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.FLUSH;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankThreeOfAKindTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(seven, clubs);
        Card card3 = new Card(seven, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.THREEOFAKIND;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotThreeOfAKindTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(seven, diamonds);
        Card card2 = new Card(seven, clubs);
        Card card3 = new Card(five, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.THREEOFAKIND;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankOnePairTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(king, diamonds);
        Card card2 = new Card(king, clubs);
        Card card3 = new Card(five, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.ONEPAIR;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankNotOnePairTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(king, diamonds);
        Card card2 = new Card(three, clubs);
        Card card3 = new Card(five, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.ONEPAIR;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertNotEquals(expected, actual);
    }
    @Test
    public void determineHandRankHighCardTest() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(king, diamonds);
        Card card2 = new Card(four, clubs);
        Card card3 = new Card(five, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.HIGHCARD;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineHandRankA23Test() {
        List<Card> hand = new ArrayList<>();
        Card card = new Card(ace, diamonds);
        Card card2 = new Card(three, clubs);
        Card card3 = new Card(two, hearts);
        hand.add(card);
        hand.add(card2);
        hand.add(card3);

        HandRank expected = HandRank.STRAIGHT;
        playerSet.add(player1);
        playerSet.add(player2);
        newGame = new ThreeCardPokerGame(playerSet);

        HandRank actual = newGame.determineHandRank(hand);
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void determineWinnerTest1() {
        // tie expected
        List<Card> hand1 = new ArrayList<>();
        Card card1 = new Card(ace, diamonds);
        Card card2 = new Card(king, clubs);
        Card card3 = new Card(seven, hearts);
        hand1.add(card1);
        hand1.add(card2);
        hand1.add(card3);

        List<Card> hand2 = new ArrayList<>();
        Card card4 = new Card(king, hearts);
        Card card5 = new Card(ace, clubs);
        Card card6 = new Card(seven, spades);
        hand2.add(card4);
        hand2.add(card5);
        hand2.add(card6);

        playerSet.add(player1);
        newGame = new ThreeCardPokerGame(playerSet);
        newGame.setDealerHand(hand1);
        player1.setPlayerHand(hand2);
        newGame.setDealerHandRank(newGame.determineHandRank(newGame.getDealerHand()));
        player1.setPlayerHandRank(newGame.determineHandRank(player1.getPlayerHand()));

        Assert.assertEquals(1, playerSet.size());
        HashSet<ThreeCardPokerPlayer> expectedWinners = newGame.decideWinners(playerSet);

        Assert.assertTrue(expectedWinners.isEmpty());
    }
    @Test
    public void determineWinnerTest2() {
        // player to lose, dealer has straight > player's flush
        List<Card> dealerHand = new ArrayList<>();
        Card card1 = new Card(four, diamonds);
        Card card2 = new Card(five, clubs);
        Card card3 = new Card(six, hearts);
        dealerHand.add(card1);
        dealerHand.add(card2);
        dealerHand.add(card3);

        List<Card> playerHand = new ArrayList<>();
        Card card4 = new Card(king, hearts);
        Card card5 = new Card(ace, hearts);
        Card card6 = new Card(seven, hearts);
        playerHand.add(card4);
        playerHand.add(card5);
        playerHand.add(card6);

        playerSet.add(player1);
        newGame = new ThreeCardPokerGame(playerSet);
        newGame.setDealerHand(dealerHand);
        player1.setPlayerHand(playerHand);
        newGame.setDealerHandRank(newGame.determineHandRank(newGame.getDealerHand()));
        player1.setPlayerHandRank(newGame.determineHandRank(player1.getPlayerHand()));

        Assert.assertEquals(1, playerSet.size());
        HashSet<ThreeCardPokerPlayer> expectedWinners = newGame.decideWinners(playerSet);

        Assert.assertTrue(expectedWinners.isEmpty());
    }
    @Test
    public void determineWinnerTest3() {
        // player to win with 8-7-2 over 8-6-4
        List<Card> dealerHand = new ArrayList<>();
        Card card1 = new Card(four, diamonds);
        Card card2 = new Card(eight, clubs);
        Card card3 = new Card(six, hearts);
        dealerHand.add(card1);
        dealerHand.add(card2);
        dealerHand.add(card3);

        List<Card> playerHand = new ArrayList<>();
        Card card4 = new Card(two, spades);
        Card card5 = new Card(eight, hearts);
        Card card6 = new Card(seven, hearts);
        playerHand.add(card4);
        playerHand.add(card5);
        playerHand.add(card6);

        playerSet.add(player1);
        newGame = new ThreeCardPokerGame(playerSet);
        newGame.setDealerHand(dealerHand);
        player1.setPlayerHand(playerHand);
        newGame.setDealerHandRank(newGame.determineHandRank(newGame.getDealerHand()));
        player1.setPlayerHandRank(newGame.determineHandRank(player1.getPlayerHand()));

        Assert.assertEquals(1, playerSet.size());
        HashSet<ThreeCardPokerPlayer> expectedWinners = newGame.decideWinners(playerSet);

        Assert.assertEquals(1, expectedWinners.size());
    }
    @Test
    public void determineWinnerTest4() {
        // player to lose sf to sf with 765hhh < T98sss
        List<Card> dealerHand = new ArrayList<>();
        Card card1 = new Card(ten, spades);
        Card card2 = new Card(nine, spades);
        Card card3 = new Card(eight, spades);
        dealerHand.add(card1);
        dealerHand.add(card2);
        dealerHand.add(card3);

        List<Card> playerHand = new ArrayList<>();
        Card card4 = new Card(seven, hearts);
        Card card5 = new Card(five, hearts);
        Card card6 = new Card(six, hearts);
        playerHand.add(card4);
        playerHand.add(card5);
        playerHand.add(card6);

        playerSet.add(player1);
        newGame = new ThreeCardPokerGame(playerSet);
        newGame.setDealerHand(dealerHand);
        player1.setPlayerHand(playerHand);
        newGame.setDealerHandRank(newGame.determineHandRank(newGame.getDealerHand()));
        player1.setPlayerHandRank(newGame.determineHandRank(player1.getPlayerHand()));

        Assert.assertEquals(1, playerSet.size());
        HashSet<ThreeCardPokerPlayer> expectedWinners = newGame.decideWinners(playerSet);

        Assert.assertEquals(0, expectedWinners.size());
    }
    @Test
    public void determineWinnerTest5() {
        // player to win with 876 high straight over dealer's A23
        List<Card> dealerHand = new ArrayList<>();
        Card card1 = new Card(three, spades);
        Card card2 = new Card(two, spades);
        Card card3 = new Card(ace, diamonds);
        dealerHand.add(card1);
        dealerHand.add(card2);
        dealerHand.add(card3);

        List<Card> playerHand = new ArrayList<>();
        Card card4 = new Card(seven, hearts);
        Card card5 = new Card(eight, clubs);
        Card card6 = new Card(six, hearts);
        playerHand.add(card4);
        playerHand.add(card5);
        playerHand.add(card6);

        playerSet.add(player1);
        newGame = new ThreeCardPokerGame(playerSet);
        newGame.setDealerHand(dealerHand);
        player1.setPlayerHand(playerHand);
        newGame.setDealerHandRank(newGame.determineHandRank(newGame.getDealerHand()));
        player1.setPlayerHandRank(newGame.determineHandRank(player1.getPlayerHand()));

        Assert.assertEquals(1, playerSet.size());
        HashSet<ThreeCardPokerPlayer> expectedWinners = newGame.decideWinners(playerSet);

        Assert.assertEquals(1, expectedWinners.size());
    }
}