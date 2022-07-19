package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.MultiplayerGamblingGame;
import com.github.zipcodewilmington.casino.Player;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.OddOrEven;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.RedOrBlack;
import com.github.zipcodewilmington.casino.games.roulette.typeofbet.StreetBet;
import com.github.zipcodewilmington.casino.games.roulette.utilroulette.IOConsoleReader;

import java.util.HashSet;


public class Roulette implements MultiplayerGamblingGame {
    private  RoulettePlayer player;
    private final WheelSpin myWheel;
    public Roulette (RoulettePlayer player) {
        this.player = player;
        myWheel = new WheelSpin();
    }


    private final String R = "Roulette";
    private Bet[] myPossibleBets = {
            new RedOrBlack("Red or Black", 1),
            new OddOrEven("Odd or Even", 1),
            new StreetBet("Three in a Row", 11)
    };


    public String getAccount() {
        return null;
    }

    public void play(Account account) {
        System.out.print(printInstructions());


        int amount = IOConsoleReader.promptRange("How much would you like to bet?", 0, account.getBalance());
                                                                                    //will need to get right method
        Bet bet = promptForBet();
        bet.place();

        System.out.print("Spinning . . . ");
        WheelSpin.SpinResult spinResult = myWheel.spin();
        System.out.println(String.format("Dropped into %s", spinResult));
        if (bet.isMade(spinResult)) {
            System.out.println("Congrats! You win!");
            amount = bet.payout(amount); //I'll have to change this later
        } else {
            System.out.println("Sorry, you have lost");
            amount *= -1;
        }
        account.setBalance(amount); //Will have to change this later


    }

    private Bet promptForBet() {
        System.out.println("You can make one of these bets:");
        for (int k = 0; k < myPossibleBets.length; k++) {
            System.out.println(String.format("%d) %s", (k + 1), myPossibleBets[k]));
        }

        int response = IOConsoleReader.promptRange("Please make a choice", 1, myPossibleBets.length);
        return myPossibleBets[response - 1];
    }





    @Override
    public void payout(Account account, int payoutAmount) {

    }

    @Override
    public void beginGame() {

    }

    @Override
    public String printInstructions() {
        return """ 
                                                         ============ Welcome to!============
                                                                                                                                                 \s
                8 888888888o.      ,o888888o.     8 8888      88 8 8888         8 8888888888 8888888 8888888888 8888888 8888888888 8 8888888888  \s
                8 8888    `88.  . 8888     `88.   8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     `88 ,8 8888       `8b  8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     ,88 88 8888        `8b 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888.   ,88' 88 8888         88 8 8888      88 8 8888         8 888888888888     8 8888             8 8888       8 888888888888\s
                8 888888888P'  88 8888         88 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888`8b      88 8888        ,8P 8 8888      88 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888 `8b.    `8 8888       ,8P  ` 8888     ,8P 8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888   `8b.   ` 8888     ,88'     8888   ,d8P  8 8888         8 8888             8 8888             8 8888       8 8888        \s
                8 8888     `88.    `8888888P'        `Y88888P'   8 888888888888 8 888888888888     8 8888             8 8888       8 888888888888\s
                                
                                
                                
                                                 ========================== Rules: ==========================
                                                 1. Under Construction
                                                 2. Under Construction
                                                 3. Under Construction
                                                 4. Under Construction
                                
                                
                """;
    }

    @Override
    public HashSet<Player> decideWinner(HashSet<Player> players) {
        return null;
    }
}
