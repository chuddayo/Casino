package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

public class ThreeCardPokerGame implements MultiplayerGamblingGame {

    private Deck deck;
    private HashSet<ThreeCardPokerPlayer> playerSet;
    private List<Card> dealerHand;

    public ThreeCardPokerGame(HashSet<ThreeCardPokerPlayer> playerSet) {
        this.deck = new Deck();
        this.playerSet = playerSet;
        this.dealerHand = new ArrayList<>();
    }

    public List<Card> dealHand() {
        List<Card> threeCardHand = new ArrayList<>();
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        threeCardHand.add(deck.dealTop());
        //dealerHand = threeCardHand;
        return threeCardHand;
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
        int playerInput;
        Scanner in = new Scanner(System.in);
        System.out.println(printInstructions());
        while (true) {
            // place ante or return to lobby
            //    for each player
            //    remove from playerSet if they leave game
//            for (ThreeCardPokerPlayer player : playerSet) {
//                System.out.println(player.getPlayerName() +
//                        ": (1) Place Ante  (2) Return to Lobby");
//                playerInput = in.nextInt();
//                if (playerInput == 1) {
//                    player...
//                } else if (playerInput == 2) {
//                    playerSet.remove(player);
//                }
//            }
            //

            dealerHand = dealHand();
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHand(dealHand());
            }
            // ask for bet
            //    flag players as folded if they don't place further bet
            System.out.println(flipAllCards());
            // determine winner for each player and add to balance with payout()
            // for each winning 3cpokerplayer payout(player.getAccount(), $$)
            //     display payouts
            break;
        }
    }

    @Override
    public String printInstructions() {
        return "----------------------------------\n" +
               "---Welcome to Three Card Poker----\n" +
               "----------------------------------\n" +
               "I don't know the rules, good luck.";
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.setBalance(account.getBalance() + payoutAmount);
    }
}
