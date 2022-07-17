package com.github.zipcodewilmington.casino.games.dicegame;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.MultiplayerGamblingGame;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;

import java.util.HashSet;
import java.util.Scanner;

public class HighLowDice implements MultiplayerGamblingGame {

    private HashSet<HighLowDicePlayer> players;
    private final Scanner scan = new Scanner(System.in);
    private final DiceSet dicePair = new DiceSet(2);
    private final AnsiColor color = AnsiColor.MAGENTA;
    private String winningBet;

    public HighLowDice(HashSet<HighLowDicePlayer> players) {
        this.players = players;
    }

    @Override
    public void beginGame() {
        print(printInstructions());
        while (true) {
            for (HighLowDicePlayer player : players) {
                String username = player.getAccount().getUserName();
                print("Your current balance is " + player.getAccount().getBalance() + "\n");
                int betAmount = printAndScanInt("Hi player " + username + "! Please enter the number of tokens you wish to wager");
                player.placeBet(betAmount);
                while (player.getBetAmount() == -1) {
                    betAmount = printAndScanInt("Invalid bet amount! Sorry player " + username + ", please re-enter the number of tokens you wish to wager");
                    player.placeBet(betAmount);
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
        print(this.dicePair.getDiceArt() + "\n");
        print("The dice roll is: " + roll + "!\n");
        if (roll < 7) this.winningBet = "low";
        else if (roll > 7) this.winningBet = "high";
        else this.winningBet = "seven";
        print("The winning bet is: " + this.winningBet + "!\n");
    }

    public void payout() {
        for (HighLowDicePlayer player : players) {
            if (player.getBet().equals(this.winningBet)) {
                print("Congratulations player " + player.getAccount().getUserName() + " on winning " + player.getBetAmount() + " tokens! Yay!!! :)\n");
                player.getAccount().addBalance(player.getBetAmount());
            } else {
                print("RIP player " + player.getAccount().getUserName() + " unfortunately you lost " + player.getBetAmount() + " tokens! :(\n");
                player.getAccount().deductBalance(player.getBetAmount());
            }
            print(player.getAccount().getUserName() + ", your new balance is " + player.getAccount().getBalance() + "\n");
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
        System.out.format(color.getColor() + s + ": ");
        return scan.nextLine();
    }

    public void print(String s) {
        System.out.format(color.getColor() + s);
    }

    public int printAndScanInt(String s) {
        System.out.format(color.getColor() + s + ": ");
        String str = scan.nextLine();
        try {
            return Integer.parseInt(str);
        }
        catch (NumberFormatException ex) {
        }
        return -1;
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
    }
}
