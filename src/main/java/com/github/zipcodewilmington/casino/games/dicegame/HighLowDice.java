package com.github.zipcodewilmington.casino.games.dicegame;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.MultiplayerGamblingGame;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.HashSet;
import java.util.Scanner;

public class HighLowDice implements MultiplayerGamblingGame {

    private final IOConsole console = new IOConsole(AnsiColor.PURPLE);
    private HashSet<HighLowDicePlayer> players;
    private final Scanner scan = new Scanner(System.in);
    private final DiceSet dicePair = new DiceSet(2);
    private String winningBet;

    public HighLowDice(HashSet<HighLowDicePlayer> players) {
        this.players = players;
    }

    @Override
    public void beginGame() {
        System.out.println(printInstructions());
        while (true) {
            for (HighLowDicePlayer player : players) {
                String username = player.getAccount().getUserName();
                // int betAmount = console.getIntegerInput("Hi player " + username + "! Please enter the number of tokens you wish to wager:");
                // player.placeBet(betAmount);
                // String bet = console.getStringInput("Hi player " + username + "! Please place your bet selection (high, low, or seven):").toLowerCase();
                // player.bet(bet);
                int betAmount = printAndScanInt("Hi player " + username + "! Please enter the number of tokens you wish to wager");
                player.placeBet(betAmount);
                scan.nextLine();
                while (player.getBetAmount() == -1) {
                    betAmount = printAndScanInt("Invalid bet amount! Sorry player " + username + ", please re-enter the number of tokens you wish to wager");
                    player.placeBet(betAmount);
                    scan.nextLine();
                }
                String bet = printAndScanStr("Hi player " + username + "! Please place your bet selection (high, low or seven)").toLowerCase();
                player.bet(bet);
                while (player.getBet() == "invalid") {
                    bet = printAndScanStr("Invalid bet selection! Sorry player " + username + ", please re-enter your bet selection (high, low or seven)").toLowerCase();
                    player.bet(bet);
                }
            }
            getWinningBet();
            payout();
            String input = printAndScanStr("The game has completed, would you like to play again? (yes or no)");
            if (input.toLowerCase().equals("no")) break;
        }
    }

    public void getWinningBet() {
        int roll = this.dicePair.tossAndSum();
        System.out.println(this.dicePair.getDiceArt());
        System.out.println("The dice roll is: " + roll + "!");
        if (roll < 7) this.winningBet = "low";
        else if (roll > 7) this.winningBet = "high";
        else this.winningBet = "seven";
        System.out.println("The winning bet is: " + this.winningBet + "!");
    }

    public void payout() {
        for (HighLowDicePlayer player : players) {
            if (player.getBet().equals(this.winningBet)) {
                System.out.println("Congratulations player " + player.getAccount().getUserName() + " on winning " + player.getBetAmount() + " tokens! Yay!!! :)");
                player.getAccount().addBalance(player.getBetAmount());
            } else {
                System.out.println("RIP player " + player.getAccount().getUserName() + " unfortunately you lost " + player.getBetAmount() + " tokens! :(");
                player.getAccount().deductBalance(player.getBetAmount());
            }
        }
    }

    @Override
    public String printInstructions() {
        return  "============ Welcome to the high low dice game!============\n" +
                "\t\t\t\t\t" + "  ____\n" +
                "\t\t\t\t\t" + " /\\' .\\    _____\n" +
                "\t\t\t\t\t" + "/: \\___\\  / .  /\n" +
                "\t\t\t\t\t" + "\\' / . / /____/..\\\n" +
                "\t\t\t\t\t" + " \\/___/  \\'  '\\  /\n" +
                "\t\t\t\t\t" + "          \\'__'\\/\n" +
                "\n" +
                "========================== Rules: ==========================\n" +
                "1. Place your bets on high, low or seven\n" +
                "2. High -- sum of the dice is 8, 9, 10, 11 or 12\n" +
                "3. Seven -- the outcome is 7\n" +
                "4. Low -- sum of the dice is 1, 2, 3, 4, 5, or 6\n" +
                "\n";
    }

    public String printAndScanStr(String s) {
        System.out.print(s + ": ");
        return scan.nextLine();
    }

    public int printAndScanInt(String s) {
        System.out.print(s + ": ");
        return scan.nextInt();
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
    }
}
