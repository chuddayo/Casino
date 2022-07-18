package com.github.zipcodewilmington.casino.games.cardgames;

import com.github.zipcodewilmington.casino.*;
import com.github.zipcodewilmington.casino.games.cardutils.Card;
import com.github.zipcodewilmington.casino.games.cardutils.CardValue;
import com.github.zipcodewilmington.casino.games.cardutils.Deck;
import com.github.zipcodewilmington.casino.games.cardutils.HandRank;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.util.*;

public class ThreeCardPokerGame implements MultiplayerGamblingGame {
    private final AnsiColor color = AnsiColor.YELLOW;
    private final IOConsole console = new IOConsole(color);
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
        System.out.format(color.getColor() + printInstructions());
        while (true) {
            if (playerSet.size() > 6) {
                System.out.println("Too many players, returning to lobby."); // TODO sleep here?
                break;
            }
            // TODO betting amounts limit based on balance
            // *************************
            //     ANTE & PAIR PLUS
            // *************************
            HashSet<ThreeCardPokerPlayer> removePlayers = new HashSet<>();
            HashSet<ThreeCardPokerPlayer> pairPlusPlayers = new HashSet<>();
            for (ThreeCardPokerPlayer player : playerSet) {
                int playerInput;
                do {
                    System.out.println("\n" + player.getPlayerName() + " has " + player.getAccount().getBalance() + " tokens.");
                    playerInput = console.getIntegerInput(player.getPlayerName() + " : (1) Place Ante  (2) Return to Lobby");
                    if (playerInput == 1) {
                        int ante = console.getIntegerInput("\nHow many tokens would you like to ante?");
                        player.placeBet(ante);
                        player.getPlayerAccount().deductBalance(ante);

                        String input = console.getStringInput("Would you like to place a Pair Plus bet?\n" +
                                "(1) Yes  (2) No");
                        if (input.equals("1") || input.equalsIgnoreCase("YES") || input.equalsIgnoreCase("Y")) {
                            // Pair Plus betting
                            int pairPlusInput = console.getIntegerInput("How many tokens to bet on Pair Plus?");
                            player.setPairPlusBet(pairPlusInput);
                            player.getPlayerAccount().deductBalance(pairPlusInput);
                            pairPlusPlayers.add(player);
                        }
                    } else if (playerInput == 2) {
                        removePlayers.add(player);
                    }
                } while (playerInput != 1 && (playerInput != 2));
            }
            playerSet.removeAll(removePlayers);
            if (playerSet.isEmpty()) break; // exit game

            // *************************
            //           DEAL
            // *************************
            dealerHand = dealHand();
            for (ThreeCardPokerPlayer player : playerSet) {
                player.setPlayerHand(dealHand());
            }

            // *************************
            //         DECISION
            // *************************
            HashSet<ThreeCardPokerPlayer> showdownPlayers = new HashSet<>();
            for (ThreeCardPokerPlayer player : playerSet) {
                // dispay hand
                player.setPlayerHandRank(determineHandRank(player.getPlayerHand()));
                System.out.println("\n * * " + player.getPlayerName() + "'s Hand * *");
                System.out.println(player.getPlayerHand()); // TODO better card display
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

            // *************************
            //         SHOWDOWN
            // *************************
            if (!showdownPlayers.isEmpty()) {
                // determine rank of dealer's hand
                dealerHandRank = determineHandRank(dealerHand);
                // show all showdown hands
                System.out.println(flipAllCards(showdownPlayers)); // TODO better card display

                if (HandRank.HIGHCARD.compareTo(dealerHandRank) < 0 ||
                dealerHand.get(2).getCardValue().compareTo(CardValue.JACK) > 0) {
                    // decide who won if they went to showdown with dealer
                    HashSet<ThreeCardPokerPlayer> winners = decideWinners(showdownPlayers);
                    if (winners.isEmpty()) {
                        System.out.println("Dealer's hand wins. All bets were lost.");
                    }
                    // payout winners 4 * ante for winning against dealer
                    for (ThreeCardPokerPlayer winner : winners) {
                        payout(winner.getAccount(), winner.getAnte() * 4);
                    }
                } else { // dealer's hand does not qualify
                    System.out.println("The dealer's hand does not qualify.");
                    // payout 3 * ante for all showdown players
                    for (ThreeCardPokerPlayer winner : showdownPlayers) {
                        payout(winner.getAccount(), winner.getAnte() * 3);
                    }
                }
                // Payout Pair Plus Bets
                for (ThreeCardPokerPlayer pairPlusPlayer : pairPlusPlayers) {
                    if (pairPlusPlayer.getPlayerHandRank().compareTo(HandRank.HIGHCARD) > 0) {
                        System.out.println(pairPlusPlayer.getPlayerName() + " has a " + pairPlusPlayer.getPlayerHandRank() + " and the Pair Plus bet pays out.");
                        if (pairPlusPlayer.getPlayerHandRank().equals(HandRank.ONEPAIR)) {
                            payout(pairPlusPlayer.getAccount(), pairPlusPlayer.getPairPlusBet() * 2);
                        } else if (pairPlusPlayer.getPlayerHandRank().equals(HandRank.FLUSH)) {
                            payout(pairPlusPlayer.getAccount(), pairPlusPlayer.getPairPlusBet() * 4);
                        } else if (pairPlusPlayer.getPlayerHandRank().equals(HandRank.STRAIGHT)) {
                            payout(pairPlusPlayer.getAccount(), pairPlusPlayer.getPairPlusBet() * 7);
                        } else if (pairPlusPlayer.getPlayerHandRank().equals(HandRank.THREEOFAKIND)) {
                            payout(pairPlusPlayer.getAccount(), pairPlusPlayer.getPairPlusBet() * 31);
                        } else if (pairPlusPlayer.getPlayerHandRank().equals(HandRank.STRAIGHTFLUSH)) {
                            payout(pairPlusPlayer.getAccount(), pairPlusPlayer.getPairPlusBet() * 41);
                        }
                    }
                }
            } else {
                System.out.println("\nAll players folded and conceded their ante bets.\n");
            }

            // *************************
            //         CLEANUP
            // *************************
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

    public List<Card> sortAceTwoThreeStraight(List<Card> hand) {
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
        return  "\n\n\n\n\n\n\n\n" +
                " _________   ___   ___   ______    ______   ______       ______   ________   ______    ______     \n" +
                "/________/\\ /__/\\ /__/\\ /_____/\\  /_____/\\ /_____/\\     /_____/\\ /_______/\\ /_____/\\  /_____/\\    \n" +
                "\\__.::.__\\/ \\::\\ \\\\  \\ \\\\:::_ \\ \\ \\::::_\\/_\\::::_\\/_    \\:::__\\/ \\::: _  \\ \\\\:::_ \\ \\ \\:::_ \\ \\   \n" +
                "   \\::\\ \\    \\::\\/_\\ .\\ \\\\:(_) ) )_\\:\\/___/\\\\:\\/___/\\    \\:\\ \\  __\\::(_)  \\ \\\\:(_) ) )_\\:\\ \\ \\ \\  \n" +
                "    \\::\\ \\    \\:: ___::\\ \\\\: __ `\\ \\\\::___\\/_\\::___\\/_    \\:\\ \\/_/\\\\:: __  \\ \\\\: __ `\\ \\\\:\\ \\ \\ \\ \n" +
                "     \\::\\ \\    \\: \\ \\\\::\\ \\\\ \\ `\\ \\ \\\\:\\___/ \\\\:\\___/ \\    \\:\\_\\ \\ \\\\:.\\ \\  \\ \\\\ \\ `\\ \\ \\\\:\\/ :| |\n" +
                "      \\__\\/     \\__\\/ \\::\\/ \\_\\/ \\_\\/ \\_____\\/ \\_____\\/     \\_____\\/ \\__\\/\\__\\/ \\_\\/ \\_\\/ \\____/_/" +
                "\n\n";
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        // TODO remove from interface?
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.addBalance(payoutAmount);
        System.out.println("Paid " + payoutAmount + " tokens to " + account.getUserName());
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
