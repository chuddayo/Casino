package com.github.zipcodewilmington.casino.games.dicegame;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingGameInterface;
import com.github.zipcodewilmington.utils.AnsiColor;

import java.util.HashSet;
import java.util.Scanner;

public class HighLowDice implements GamblingGameInterface {

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
        while (true && !this.players.isEmpty()) {
            checkPlayersBalance();
            if (!this.players.isEmpty()) {
                for (HighLowDicePlayer player : players) {
                    String username = player.getAccount().getUserName();
                    print("\n(( Your current balance is " + player.getAccount().getBalance() + " tokens. ))\n\n");
                    int betAmount = printAndScanInt("Hello " + username + "! Please enter the number of tokens you wish to wager");
                    player.placeBet(betAmount);
                    while (player.getBetAmount() == -1) {
                        betAmount = printAndScanInt("Invalid bet amount! Sorry " + username + ", please re-enter the number of tokens you wish to wager");
                        player.placeBet(betAmount);
                    }
                    String bet = printAndScanStr("Hello " + username + "! Please place your bet selection (high, low or seven)").toLowerCase();
                    player.bet(bet);
                    while (player.getBet() == "invalid") {
                        bet = printAndScanStr("Invalid bet selection! Sorry " + username + ", please re-enter your bet selection (high, low or seven)").toLowerCase();
                        player.bet(bet);
                    }
                }
                winningBet();
                payout();
                String input = printAndScanStr("This round of game has finished, would you like to play again? (yes or no)");
                if (input.toLowerCase().equals("no")) break;
            } else {
                print("There are no eligible players, returning to main menu!\n");
                break;
            }
        }
    }

    public void winningBet() {
        int roll = this.dicePair.tossAndSum();
        print(this.dicePair.getDiceArt() + "\n");
        print("(( The dice roll is: " + roll + "! ))\n");
        if (roll < 7) this.winningBet = "low";
        else if (roll > 7) this.winningBet = "high";
        else this.winningBet = "seven";
        print("(( The winning bet is: " + this.winningBet + "! ))\n\n");
    }

    public String getWinningBet() {
        return this.winningBet;
    }

    public void payout() {
        for (HighLowDicePlayer player : players) {
            if (player.getBet().equals(this.winningBet)) {
                print("Congratulations player " + player.getAccount().getUserName() + " on winning " + player.getBetAmount() + " tokens! Yay!!! :)\n");
                player.getAccount().addBalance(player.getBetAmount());
            } else {
                print("RIP " + player.getAccount().getUserName() + "... unfortunately you have lost " + player.getBetAmount() + " tokens! :(\n\n");
                player.getAccount().deductBalance(player.getBetAmount());
            }
            print("(( Player " + player.getAccount().getUserName() + ", your updated balance is " + player.getAccount().getBalance() + " tokens. ))\n\n");
        }
    }

    @Override
    public String printInstructions() {
        return  """
                                                             
                                                             
                                                             ============ Welcome to the High Low Dice game!============                                                                                                        
                                                                                                                                   .         .                         \s
                8 888888888o.      8 8888     ,o888888o.    8 8888888888                ,o888888o.          .8.                   ,8.       ,8.          8 8888888888  \s
                8 8888    `^888.   8 8888    8888     `88.  8 8888                     8888     `88.       .888.                 ,888.     ,888.         8 8888        \s
                8 8888        `88. 8 8888 ,8 8888       `8. 8 8888                  ,8 8888       `8.     :88888.               .`8888.   .`8888.        8 8888        \s
                8 8888         `88 8 8888 88 8888           8 8888                  88 8888              . `88888.             ,8.`8888. ,8.`8888.       8 8888        \s
                8 8888          88 8 8888 88 8888           8 888888888888          88 8888             .8. `88888.           ,8'8.`8888,8^8.`8888.      8 888888888888\s
                8 8888          88 8 8888 88 8888           8 8888                  88 8888            .8`8. `88888.         ,8' `8.`8888' `8.`8888.     8 8888        \s
                8 8888         ,88 8 8888 88 8888           8 8888                  88 8888   8888888 .8' `8. `88888.       ,8'   `8.`88'   `8.`8888.    8 8888        \s
                8 8888        ,88' 8 8888 `8 8888       .8' 8 8888                  `8 8888       .8'.8'   `8. `88888.     ,8'     `8.`'     `8.`8888.   8 8888        \s
                8 8888    ,o88P'   8 8888    8888     ,88'  8 8888                     8888     ,88'.888888888. `88888.   ,8'       `8        `8.`8888.  8 8888        \s
                8 888888888P'      8 8888     `8888888P'    8 888888888888              `8888888P' .8'       `8. `88888. ,8'         `         `8.`8888. 8 888888888888\s
                
                                                            ========================== Rules: ==========================
                                                            1. Place your bets on high, low or seven
                                                            2. High -- sum of the dice is 8, 9, 10, 11 or 12
                                                            3. Seven -- the outcome is 7
                                                            4. Low -- sum of the dice is 1, 2, 3, 4, 5, or 6
                                                            
                """;
//                "\n\n============ Welcome to the High Low Dice game!============\n" +
//                "\t\t\t\t\t" + "  ____\n" +
//                "\t\t\t\t\t" + " /\\' .\\    _____\n" +
//                "\t\t\t\t\t" + "/: \\___\\  / .  /\n" +
//                "\t\t\t\t\t" + "\\' / . / /____/..\\\n" +
//                "\t\t\t\t\t" + " \\/___/  \\'  '\\  /\n" +
//                "\t\t\t\t\t" + "          \\'__'\\/\n" +
//                "\n" +
//                "========================== Rules: ==========================\n\n" +
//                "1. Place your bets on high, low or seven\n" +
//                "2. High -- sum of the dice is 8, 9, 10, 11 or 12\n" +
//                "3. Seven -- the outcome is 7\n" +
//                "4. Low -- sum of the dice is 1, 2, 3, 4, 5, or 6\n" +
//                "\n";
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

    public HashSet<HighLowDicePlayer> getPlayers() {
        return this.players;
    }

    public void checkPlayersBalance() {
        HashSet<HighLowDicePlayer> updatedPlayers = new HashSet<>();
        for (HighLowDicePlayer player : this.players) {
            if (player.getAccount().getBalance() <= 0) {
                print("Sorry " + player.getAccount().getUserName() + ", you have no more tokens! Please load more tokens from the main menu!\n");
            } else {
                updatedPlayers.add(player);
            }
        }
        this.players = updatedPlayers;
    }

    @Override
    public void payout(Account account, int payoutAmount) {
        account.setBalance(account.getBalance() + payoutAmount);
    }
}
