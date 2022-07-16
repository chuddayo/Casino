package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegames.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegames.HighLowDicePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.io.IOException;
import java.util.HashSet;

public class Casino {
    private final IOConsole console = new IOConsole(AnsiColor.BLUE);

    public void run() throws IOException {
        String dashBoardInput;
        AccountManager accountManager = new AccountManager();
        try {
            do {
               dashBoardInput = getDashboardInput();

               if ("select-game".equals(dashBoardInput)) {
                   String gameSelectionInput = getGameSelectionInput().toUpperCase();

                   if (gameSelectionInput.equals("HIGH LOW DICE")) {
                       HashSet<HighLowDicePlayer> dicePlayers = new HashSet<>();

                       while (true) {
                           String accountName = console.getStringInput("Enter your account name:");
                           String accountPassword = console.getStringInput("Enter your account password:");
                           Account account = accountManager.getAccount(accountName, accountPassword);

                           if (account != null) {
                               HighLowDicePlayer dicePlayer = new HighLowDicePlayer(account);
                               dicePlayers.add(dicePlayer);
                           } else {
                               noAccountFound(accountPassword, accountName);
                           }

                           int loginMoreOrPlay = console.getIntegerInput(
                                   "(1) Login another user (2) Begin Play  (3) Return to Main Lobby");
                           if (loginMoreOrPlay == 2) {
                               HighLowDice diceGame = new HighLowDice(dicePlayers);
                               diceGame.beginGame();
                               accountManager.updateAccounts();
                               break;
                           } else if (loginMoreOrPlay == 3) {
                               break;
                           }
                       }

                   } else if (gameSelectionInput.equals("THREE CARD POKER")) {
                       HashSet<ThreeCardPokerPlayer> threeCardPlayers = new HashSet<>();

                       while (true) {
                           String accountName = console.getStringInput("Enter your account name:");
                           String accountPassword = console.getStringInput("Enter your account password:");
                           Account account = accountManager.getAccount(accountName, accountPassword);

                           if (account != null) {
                               ThreeCardPokerPlayer threeCardPokerPlayer = new ThreeCardPokerPlayer(account);
                               threeCardPlayers.add(threeCardPokerPlayer);
                           } else {
                               noAccountFound(accountPassword, accountName);
                           }

                           int loginMoreOrPlay = console.getIntegerInput(
                                   "(1) Login another user (2) Begin Play  (3) Return to Main Lobby");
                           if (loginMoreOrPlay == 2) {
                               ThreeCardPokerGame threeCardPokerGame = new ThreeCardPokerGame(threeCardPlayers);
                               threeCardPokerGame.beginGame();
                               accountManager.updateAccounts();
                               break;
                           } else if (loginMoreOrPlay == 3) {
                               break;
                           }
                       }

                   } else if (gameSelectionInput.equals("SLOTS")) {
                       String accountName = console.getStringInput("Enter your account name:");
                       String accountPassword = console.getStringInput("Enter your account password:");
                       HashSet<SlotsPlayer> slotsPlayers = new HashSet<>();
                       Account account = accountManager.getAccount(accountName, accountPassword);
                       if (account != null) {
                           SlotsPlayer slotsPlayer = new SlotsPlayer(account);
                           slotsPlayers.add(slotsPlayer);
                           SlotsGame slotsGame = new SlotsGame(slotsPlayers);
                           slotsGame.beginGame();
                           accountManager.updateAccounts();
                       } else {
                           noAccountFound(accountPassword, accountName);
                       }
                   } else if (gameSelectionInput.equals("TIC TAC TOE")) {
                       String accountName = console.getStringInput("Enter your account name:");
                       String accountPassword = console.getStringInput("Enter your account password:");
                       HashSet<TicTacToePlayer> ticTacToePlayers = new HashSet<>();
                       Account account = accountManager.getAccount(accountName, accountPassword);
                       if (account != null) {
                           TicTacToePlayer ticTacToePlayer = new TicTacToePlayer(account);
                           ticTacToePlayers.add(ticTacToePlayer);
                           TicTacToe ticTacToe = new TicTacToe(ticTacToePlayers);
                           ticTacToe.beginGame();
                           accountManager.updateAccounts();
                       } else {
                           noAccountFound(accountPassword, accountName);
                       }
                   }
//                       else if (gameSelectionInput.equals("BLACK JACK")) {
//                           play(new BlackJackGame(), new BlackJackPlayer());
//                       } else if (gameSelectionInput.equals("ROULETTE")){
//                           play (new RouletteGame(), new RoulettePlayer());
//                       }
                   else {
                       String errorMessage = "[ %s ] is an invalid game selection";
                       throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                   }

               } else if ("create-account".equals(dashBoardInput)) {
                   console.println("Welcome to the account-creation screen.");
                   String accountName = console.getStringInput("Enter your account name:");
                   String accountPassword = console.getStringInput("Enter your account password:");
                   accountManager.createAccount(accountName, accountPassword);
                   accountManager.updateAccounts();
               }
           } while (!"logout".equals(dashBoardInput));
       } catch (RuntimeException e){System.out.println(e.getMessage());}
    }

    private String getDashboardInput() {
        return console.getStringInput("Welcome to the Venetian!" +
                "\nFrom here, you can select any of the following options:" +
                "\n\t[ create-account ]  [ select-game ]");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("From here, you can select any of the following games:" +
                "\n\t[ SLOTS ], [ THREE CARD POKER ], [ BLACK JACK ], [ ROULETTE ], [ HIGH LOW DICE ], [ TIC TAC TOE ]");
    }

    private void noAccountFound(String accountPassword, String accountName) {
        String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
        throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
    }
}
