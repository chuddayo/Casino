package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.*;

public class ThreeCardPokerGame implements MultiplayerGamblingGame {
    private final IOConsole console = new IOConsole(AnsiColor.YELLOW);
    private Deck deck;
    private HashSet<ThreeCardPokerPlayer> playerSet;
    private List<Card> dealerHand;
    private HandRank dealerHandRank;
    private final int ante = 5;
    private final int betAmt = 20;

    public ThreeCardPokerGame(HashSet<ThreeCardPokerPlayer> playerSet) {
        this.deck = new Deck();
        this.playerSet = playerSet;
        this.dealerHand = new ArrayList<>();
    }

    public HashSet<ThreeCardPokerPlayer> getPlayerSet() {
        return playerSet;
    }

    public List<Card> dealHand() {
        List<Card> threeCardHand = new ArrayList<>();
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        return threeCardHand;
    }

    public void discardHand(List<Card> hand) {
        for (Card card : hand) {
            deck.discard(card);
        }
    }

    public StringBuilder flipAllCards() {
        StringBuilder allCards = new StringBuilder(" * * Dealer's Hand * *\n");
        allCards.append(dealerHandRank).append("\n");
        for(Card card : dealerHand) {
            allCards.append(card).append("\n");
        }
        for(ThreeCardPokerPlayer player : playerSet) {
            allCards.append(" * * ").append(player.getPlayerName()).append("'s Hand * *\n");
            allCards.append(player.getPlayerHandRank()).append("\n");
            for(Card card : player.getPlayerHand()) {
                allCards.append(card).append("\n");
            }
        }

        return allCards;
    }

    @Override
    public void beginGame() {
        System.out.println(printInstructions());
        while (true) {
            if (playerSet.size() > 6) {
                System.out.println("Too many players, returning to lobby.");
                break;
            }
            /*  for each player:
                    place ante or return to lobby
                    remove from playerSet if they leave game */
            for (ThreeCardPokerPlayer player : playerSet) {
                int playerInput = console.getIntegerInput(": (1) Place Ante  (2) Return to Lobby");
                if (playerInput == 1) {
                    player.getPlayerAccount().deductBalance(ante);
                } else if (playerInput == 2) {
                    playerSet.remove(player);
                }
            }
            if (playerSet.isEmpty()) break; // exit game

            // deal the dealer in and all players remaining who have anted
            dealerHand = dealHand();
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHand(dealHand());
            }

            // TODO show each player their hand and ask for bet
            //    flag players as folded if they don't place further bet

            // determine rank of all hands still in
            dealerHandRank = determineHandRank(dealerHand);
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHandRank(determineHandRank(player.getPlayerHand()));
            }

            HashSet<ThreeCardPokerPlayer> winners = decideWinners(playerSet);

            System.out.println(flipAllCards()); // TODO more display based on hand results and payouts
            for (ThreeCardPokerPlayer winner : winners) {
                payout(winner.getAccount(), 10);
            }

            // TODO discard all hands
            deck.shuffle();
        }
    }

    public HashSet<ThreeCardPokerPlayer> decideWinners(HashSet<ThreeCardPokerPlayer> potentialWinners) {
        HashSet<ThreeCardPokerPlayer> winners = new HashSet<>();
        for (ThreeCardPokerPlayer potential : potentialWinners) {
            if (potential.getPlayerHandRank().compareTo(dealerHandRank) > 0) {
                winners.add(potential);
            }
        }
        return winners;
    }

    public HandRank determineHandRank(List<Card> hand) {
        HandRank handRank;
        sortHand(hand);
        // is it a straight?
        if ((hand.get(2).getCardValue().compareTo(hand.get(1).getCardValue()) == 1) &&
            (hand.get(1).getCardValue().compareTo(hand.get(0).getCardValue()) == 1)) {
            // if yes, is it a straight flush? -> if yes, STRAIGHTFLUSH
            if (hand.get(0).getSuit().equals(hand.get(1).getSuit()) &&
                hand.get(1).getSuit().equals(hand.get(2).getSuit())) {
                return HandRank.STRAIGHTFLUSH;
            }
            // if no, STRAIGHT
            return HandRank.STRAIGHT;
        }
        // TODO look for A-2-3 straight flush or straight
        // is it three of a kind? -> if yes, THREEOFAKIND
        if (hand.get(0).getCardValue() == hand.get(1).getCardValue() &&
            hand.get(1).getCardValue() == hand.get(2).getCardValue()) {
            return HandRank.THREEOFAKIND;
        }
        // is it a flush? -> if yes, FLUSH
        if (hand.get(0).getSuit().equals(hand.get(1).getSuit()) &&
                hand.get(1).getSuit().equals(hand.get(2).getSuit())) {
            return HandRank.FLUSH;
        }
        // is it one pair? -> if yes
        if (hand.get(0).getCardValue() == hand.get(1).getCardValue() ||
                hand.get(1).getCardValue() == hand.get(2).getCardValue()) {
            return HandRank.ONEPAIR;
        }
        return HandRank.HIGHCARD;
    }

    public void sortHand(List<Card> hand) {
        Comparator<Card> byCardValue = Card::compareTo;
        hand.sort(byCardValue);
    }

    @Override
    public String printInstructions() {
        return "-------------------------------------\n" +
               "---- Welcome to Three Card Poker ----\n" +
               "-------------------------------------\n" +
               "Place an ante of 5 to see if you win \n";
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        // if playerHand <= dealerHand, remove player from players
        // prob need an enum for hand ranks?
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.addBalance(payoutAmount);
        System.out.println("Paid $" + payoutAmount + " to " + account.getUserName() + "'s account!");
    }
}
