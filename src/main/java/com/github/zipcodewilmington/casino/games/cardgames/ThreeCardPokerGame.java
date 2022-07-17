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

    public ThreeCardPokerGame(HashSet<ThreeCardPokerPlayer> playerSet) {
        this.deck = new Deck();
        this.playerSet = playerSet;
        this.dealerHand = new ArrayList<>();
    }

    @Override
    public void beginGame() {
        System.out.println(printInstructions());
        while (true) {
            if (playerSet.size() > 6) {
                System.out.println("Too many players, returning to lobby."); // TODO sleep here?
                break;
            }
            /*  for each player:  place ante or return to lobby
                                  remove from playerSet if they leave game */
            HashSet<ThreeCardPokerPlayer> removePlayers = new HashSet<>();
            for (ThreeCardPokerPlayer player : playerSet) {
                System.out.println("\n" + player.getPlayerName() + " has " + player.getAccount().getBalance() + " tokens.");
                int playerInput = console.getIntegerInput(player.getPlayerName() + " : (1) Place Ante  (2) Return to Lobby");
                if (playerInput == 1) {
                    int ante = console.getIntegerInput("\nHow many tokens would you like to ante?");
                    player.placeBet(ante);
                    player.getPlayerAccount().deductBalance(ante);
                } else if (playerInput == 2) {
                    removePlayers.add(player);
                }
            }
            playerSet.removeAll(removePlayers);
            if (playerSet.isEmpty()) break; // exit game

            // deal the dealer in and all players remaining who have anted
            dealerHand = dealHand();
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHand(dealHand());
            }

            // TODO show each player their hand and ask for PLAY bet
            //    flag players as folded if they don't place further bet
            HashSet<ThreeCardPokerPlayer> showdownPlayers = new HashSet<>();
            for (ThreeCardPokerPlayer player : playerSet) {
                // dispay hand
                player.setPlayerHandRank(determineHandRank(player.getPlayerHand()));
                System.out.println("\n * * " + player.getPlayerName() + "'s Hand * *");
                System.out.println(player.getPlayerHand());
                System.out.println(player.getPlayerHandRank() + "\n");
                // ask to add play bet or fold
                String userInput;
                do {
                    userInput = console.getStringInput("(1) Play: betting " + player.getAnte() + " more tokens  (2) Fold");
                    if (userInput.equals("1")) {
                        player.getPlayerAccount().deductBalance(player.getAnte());
                        showdownPlayers.add(player);
                    }
                } while (!userInput.equals("1") && !userInput.equals("2"));
            }

            if (!showdownPlayers.isEmpty()) {
                // determine rank of dealer's hand
                dealerHandRank = determineHandRank(dealerHand);
                // decide who won if they went to showdown with dealer
                HashSet<ThreeCardPokerPlayer> winners = decideWinners(showdownPlayers);

                System.out.println(flipAllCards(showdownPlayers)); // TODO more display based on hand results and payouts
                for (ThreeCardPokerPlayer winner : winners) {
                    payout(winner.getAccount(), winner.getAnte() * 2);
                }
            } else {
                System.out.println("\nAll players folded and conceded their ante bets.\n");
            }

            // CLEANUP : discard hands back into deck, shuffle deck
            discardHand(dealerHand);
            for (ThreeCardPokerPlayer player : playerSet) {
                discardHand(player.getPlayerHand());
            }
            deck.shuffle();
        }
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
        hand.clear();
    }

    public StringBuilder flipAllCards(HashSet<ThreeCardPokerPlayer> showdownPlayers) {
        StringBuilder allCards = new StringBuilder("\n * * Dealer's Hand * *\n");
        allCards.append(dealerHandRank).append("\n");
        for(Card card : dealerHand) {
            allCards.append(card).append("\n");
        }
        for(ThreeCardPokerPlayer player : showdownPlayers) {
            allCards.append(" * * ").append(player.getPlayerName()).append("'s Hand * *\n");
            allCards.append(player.getPlayerHandRank()).append("\n");
            for(Card card : player.getPlayerHand()) {
                allCards.append(card).append("\n");
            }
        }

        return allCards;
    }

    public HashSet<ThreeCardPokerPlayer> decideWinners(HashSet<ThreeCardPokerPlayer> potentialWinners) {
        HashSet<ThreeCardPokerPlayer> winners = new HashSet<>();
        for (ThreeCardPokerPlayer potential : potentialWinners) {
            int relativeHandValue = potential.getPlayerHandRank().compareTo(dealerHandRank);
            // if player handrank is higher than dealer
            if (relativeHandValue > 0) {
                winners.add(potential);
            // if the player and dealer have the same rank, we look closer at the card values
            } else if (relativeHandValue == 0) {

                if (potential.getPlayerHandRank().equals(HandRank.STRAIGHTFLUSH) ||
                        potential.getPlayerHandRank().equals(HandRank.STRAIGHT)) {
                    // re-ordering if player has 2 3 A
                    if (potential.getPlayerHand().get(2).getCardValue().equals(CardValue.ACE) &&
                            potential.getPlayerHand().get(1).getCardValue().equals(CardValue.THREE)) {
                        potential.setPlayerHand(sortAceTwoThreeStraight(potential.getPlayerHand()));
                    }
                    // re-order if dealer has 2 3 A
                    if (getDealerHand().get(2).getCardValue().equals(CardValue.ACE) &&
                            getDealerHand().get(1).getCardValue().equals(CardValue.THREE)) {
                        setDealerHand(sortAceTwoThreeStraight(getDealerHand()));
                    }
                    if (potential.getPlayerHand().get(2).getCardValue().compareTo(dealerHand.get(2).getCardValue()) > 0) {
                        winners.add(potential);
                    }

                } else if (potential.getPlayerHandRank().equals(HandRank.THREEOFAKIND)) {
                    if (potential.getPlayerHand().get(2).getCardValue().compareTo(dealerHand.get(2).getCardValue()) > 0) {
                        winners.add(potential);
                    }
                } else if (potential.getPlayerHandRank().equals(HandRank.FLUSH) ||
                        potential.getPlayerHandRank().equals(HandRank.HIGHCARD) ||
                        potential.getPlayerHandRank().equals(HandRank.ONEPAIR)) {

                    // if we're talking about two ONEPAIR hands, we'll do some special sorting to make comparison easier
                    if (potential.getPlayerHandRank().equals(HandRank.ONEPAIR)) {
                        dealerHand = pushOnePairHand(dealerHand);
                        potential.setPlayerHand(pushOnePairHand(potential.getPlayerHand()));
                    }
                    // compare cards one at a time highest to lowest, or the paired cards then the third card
                    relativeHandValue = potential.getPlayerHand().get(2).getCardValue().compareTo(dealerHand.get(2).getCardValue());
                    if (relativeHandValue > 0) winners.add(potential);
                    else if (relativeHandValue == 0) {
                        relativeHandValue = potential.getPlayerHand().get(1).getCardValue().compareTo(dealerHand.get(1).getCardValue());
                        if (relativeHandValue > 0) winners.add(potential);
                        else if (relativeHandValue == 0) {
                            if (potential.getPlayerHand().get(0).getCardValue().compareTo(dealerHand.get(0).getCardValue()) > 0) {
                                winners.add(potential);
                            }
                        }
                    }
                }
            }
        }
        return winners;
    }

    public HandRank determineHandRank(List<Card> hand) {
        sortHand(hand);
        // is it a straight?
        if (((hand.get(2).getCardValue().compareTo(hand.get(1).getCardValue()) == 1) &&
            (hand.get(1).getCardValue().compareTo(hand.get(0).getCardValue()) == 1)) ||
        ((hand.get(2).getCardValue().equals(CardValue.ACE)) &&
                (hand.get(1).getCardValue().equals(CardValue.THREE)) &&
                (hand.get(0).getCardValue().equals(CardValue.TWO)))){
            // if yes, is it a straight flush? -> if yes, STRAIGHTFLUSH
            if (hand.get(0).getSuit().equals(hand.get(1).getSuit()) &&
                hand.get(1).getSuit().equals(hand.get(2).getSuit())) {
                return HandRank.STRAIGHTFLUSH;
            }
            // if no, STRAIGHT
            return HandRank.STRAIGHT;
        }
        // is it three of a kind? -> if yes, THREEOFAKIND
        if (hand.get(0).getCardValue().equals(hand.get(1).getCardValue()) &&
            hand.get(1).getCardValue().equals(hand.get(2).getCardValue())) {
            return HandRank.THREEOFAKIND;
        }
        // is it a flush? -> if yes, FLUSH
        if (hand.get(0).getSuit().equals(hand.get(1).getSuit()) &&
                hand.get(1).getSuit().equals(hand.get(2).getSuit())) {
            return HandRank.FLUSH;
        }
        // is it one pair? -> if yes
        if (hand.get(0).getCardValue().equals(hand.get(1).getCardValue())  ||
                hand.get(1).getCardValue().equals(hand.get(2).getCardValue())) {
            return HandRank.ONEPAIR;
        }
        return HandRank.HIGHCARD;
    }

    public void sortHand(List<Card> hand) {
        Comparator<Card> byCardValue = Card::compareTo;
        hand.sort(byCardValue);
    }

    public List<Card> sortAceTwoThreeStraight(List<Card> hand) { // TODO write tests
        // assuming hand currently 2 3 A
        List<Card> threeHighStraight = new ArrayList<>();
        threeHighStraight.add(hand.get(2));
        threeHighStraight.add(hand.get(0));
        threeHighStraight.add(hand.get(1));
        return threeHighStraight;
    }

    public List<Card> pushOnePairHand(List<Card> hand) {
        List<Card> sortedPairHand = new ArrayList<>();
        if (hand.get(0).getCardValue().compareTo(hand.get(1).getCardValue()) == 0) {
            sortedPairHand.add(hand.get(2));
            sortedPairHand.add(hand.get(1));
            sortedPairHand.add(hand.get(0));
        } else if (hand.get(0).getCardValue().compareTo(hand.get(2).getCardValue()) == 0) {
            sortedPairHand.add(hand.get(1));
            sortedPairHand.add(hand.get(0));
            sortedPairHand.add(hand.get(2));
        } else if (hand.get(1).getCardValue().compareTo(hand.get(2).getCardValue()) == 0) {
            return hand;
        }
        return sortedPairHand;
    }

    @Override
    public String printInstructions() {
        return "-------------------------------------\n" +
               "---- Welcome to Three Card Poker ----\n" +
               "-------------------------------------";
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        // TODO remove from interface?
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.addBalance(payoutAmount);
        System.out.println("Paid " + payoutAmount + " tokens to " + account.getUserName() + "'s account!");
    }

    public List<Card> getDealerHand() {
        return dealerHand;
    }

    public void setDealerHand(List<Card> dealerHand) {
        this.dealerHand = dealerHand;
    }

    public HandRank getDealerHandRank() {
        return dealerHandRank;
    }

    public void setDealerHandRank(HandRank dealerHandRank) {
        this.dealerHandRank = dealerHandRank;
    }

    public Deck getDeck() {
        return deck;
    }
}
