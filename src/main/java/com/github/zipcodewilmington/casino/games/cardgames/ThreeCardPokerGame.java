package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.*;

public class ThreeCardPokerGame implements MultiplayerGamblingGame {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);
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
        for(Card card : dealerHand) {
            allCards.append(card).append("\n");
        }
        for(ThreeCardPokerPlayer player : playerSet) {
            allCards.append(" * * ").append(player.getPlayerName()).append("'s Hand * *\n");
            for(Card card : player.getPlayerHand()) {
                allCards.append(card).append("\n");
            }
        }

        return allCards;
    }

    @Override
    public void beginGame() {
        System.out.println(printInstructions());

        // check to make sure # of players < 7
        // check

        while (true) {
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

            // show each player their hand and ask for bet
            //    flag players as folded if they don't place further bet

            // determine rank of all hands still in
            dealerHandRank = determineHandRank(dealerHand);
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHandRank(determineHandRank(player.getPlayerHand()));
            }

            // determine winner for each player and add to balance with payout()
            HashSet<ThreeCardPokerPlayer> winners = decideWinners(playerSet);

            // for each winning 3cpokerplayer payout(player.getAccount(), $$)
            for (ThreeCardPokerPlayer winner : winners) {
                payout(winner.getAccount(), 10);
            }
            //     display payouts

            System.out.println(flipAllCards()); // TODO more display based on hand results and payouts
        }
    }

    public HashSet<ThreeCardPokerPlayer> decideWinners(HashSet<ThreeCardPokerPlayer> potentialWinners) {
        // compare each potentialWinner HandRank to the dealer HandRank
        return potentialWinners;
    }

    public HandRank determineHandRank(List<Card> hand) {
        HandRank handRank;
        hand = sortHand(hand);
        // is it a straight?
            // if yes, is it a straight flush? -> if yes, STRAIGHTFLUSH
                // if no, STRAIGHT
        // is it three of a kind? -> if yes, THREEOFAKIND
        // is it a flush? -> if yes, FLUSH
        // is it one pair? -> if yes
        return HandRank.HIGHCARD;
    }

    public List<Card> sortHand(List<Card> hand) {
        Comparator<Card> byCardValue = Card::compareTo;
        hand.sort(byCardValue);
        return hand;
    }

    @Override
    public String printInstructions() {
        return "----------------------------------\n" +
               "---Welcome to Three Card Poker----\n" +
               "----------------------------------\n";
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
