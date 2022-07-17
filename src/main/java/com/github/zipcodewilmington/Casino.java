package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.io.IOException;
import java.util.HashSet;

public class Casino {
    private final IOConsole console = new IOConsole(AnsiColor.CYAN);

    public void run() throws IOException {
        String dashBoardInput;
        AccountManager accountManager = new AccountManager();
        HashSet<Account> loggedInAccounts = new HashSet<>();
        try {
            do {
               dashBoardInput = getDashboardInput().toUpperCase();

                switch (dashBoardInput) {
                    case "CREATE ACCOUNT":
                    case "1":
                    case "CREATE":
                    case "C": {
                        console.println("Welcome to the account-creation screen.");
                        String accountName = console.getStringInput("Enter your account name:");
                        String accountPassword = console.getStringInput("Enter your account password:");
                        accountManager.createAccount(accountName, accountPassword);
                        accountManager.updateAccounts();
                        break;
                    }
                    case "SELECT GAME":
                    case "2":
                        String gameSelectionInput = getGameSelectionInput().toUpperCase();
                        boolean loginMore = false;
                        switch (gameSelectionInput) {
                            case "SLOTS":
                            case "1":
                            {
                                if (loggedInAccounts.size() > 1) {
                                    System.out.println("Too many accounts returning to lobby..");
                                    // TODO add sleep here?
                                } else if (loggedInAccounts.size() == 0) {
                                    String accountName = console.getStringInput("Enter your account name:");
                                    String accountPassword = console.getStringInput("Enter your account password:");
                                    Account account = accountManager.getAccount(accountName, accountPassword);
                                    if (account != null) {
                                        loggedInAccounts.add(account);
                                        HashSet<SlotsPlayer> slotsPlayers = new HashSet<>();
                                        SlotsPlayer slotsPlayer = new SlotsPlayer(account);
                                        slotsPlayers.add(slotsPlayer);
                                        SlotsGame slotsGame = new SlotsGame(slotsPlayers);
                                        slotsGame.beginGame();
                                        accountManager.updateAccounts();
                                    } else {
                                        noAccountFound(accountPassword, accountName);
                                    }
                                } else {
                                    HashSet<SlotsPlayer> slotsPlayers = new HashSet<>();
                                    for (Account account : loggedInAccounts) {
                                        SlotsPlayer slotsPlayer = new SlotsPlayer(account);
                                        slotsPlayers.add(slotsPlayer);
                                    }
                                    SlotsGame slotsGame = new SlotsGame(slotsPlayers);
                                    slotsGame.beginGame();
                                    accountManager.updateAccounts();
                                }
                                break;
                            }
                            case "THREE CARD POKER":
                            case "2":
                                HashSet<ThreeCardPokerPlayer> threeCardPlayers = new HashSet<>();

                                while (true) {
                                    while (loggedInAccounts.size() == 0 || loginMore) {
                                        String accountName = console.getStringInput("Enter your account name:");
                                        String accountPassword = console.getStringInput("Enter your account password:");
                                        Account account = accountManager.getAccount(accountName, accountPassword);

                                        if (account != null) {
                                            loggedInAccounts.add(account);
                                        } else {
                                            noAccountFound(accountPassword, accountName);
                                        }
                                    }

                                    int loginMoreOrPlay = console.getIntegerInput(
                                            "(1) Login another user  (2) Begin Play  (3) Return to Main Lobby");
                                    if (loginMoreOrPlay == 2) {
                                        for (Account account : loggedInAccounts) {
                                            ThreeCardPokerPlayer threeCardPokerPlayer = new ThreeCardPokerPlayer(account);
                                            threeCardPlayers.add(threeCardPokerPlayer);
                                        }
                                        ThreeCardPokerGame threeCardPokerGame = new ThreeCardPokerGame(threeCardPlayers);
                                        threeCardPokerGame.beginGame();
                                        accountManager.updateAccounts();
                                        break;
                                    } else if (loginMoreOrPlay == 3) {
                                        break;
                                    } else if (loginMoreOrPlay == 1) {
                                        loginMore = true;
                                    }
                                }

                                break;
                            case "HIGH LOW DICE":
                            case "3":
                                HashSet<HighLowDicePlayer> dicePlayers = new HashSet<>();

                                while (true) {
                                    while (loggedInAccounts.size() == 0 || loginMore) {
                                        String accountName = console.getStringInput("Enter your account name:");
                                        String accountPassword = console.getStringInput("Enter your account password:");
                                        Account account = accountManager.getAccount(accountName, accountPassword);

                                        if (account != null) {
                                            loggedInAccounts.add(account);
                                        } else {
                                            noAccountFound(accountPassword, accountName);
                                        }
                                    }

                                    int loginMoreOrPlay = console.getIntegerInput(
                                            "(1) Login another user  (2) Begin Play  (3) Return to Main Lobby");
                                    if (loginMoreOrPlay == 2) {
                                        for (Account account : loggedInAccounts) {
                                            HighLowDicePlayer dicePlayer = new HighLowDicePlayer(account);
                                            dicePlayers.add(dicePlayer);
                                        }
                                        HighLowDice diceGame = new HighLowDice(dicePlayers);
                                        diceGame.beginGame();
                                        accountManager.updateAccounts();
                                        break;
                                    } else if (loginMoreOrPlay == 3) {
                                        break;
                                    } else if (loginMoreOrPlay == 1) {
                                        loginMore = true;
                                    }
                                }
                                break;
                            case "TIC TAC TOE":
                            case "4":
                            {
                                if (loggedInAccounts.size() > 1) {
                                    System.out.println("Too many accounts returning to lobby..");
                                    // TODO add sleep here?
                                } else if (loggedInAccounts.size() == 0) {
                                    String accountName = console.getStringInput("Enter your account name:");
                                    String accountPassword = console.getStringInput("Enter your account password:");
                                    Account account = accountManager.getAccount(accountName, accountPassword);
                                    if (account != null) {
                                        loggedInAccounts.add(account);
                                        HashSet<TicTacToePlayer> ticTacToePlayers = new HashSet<>();
                                        TicTacToePlayer ticTacToePlayer = new TicTacToePlayer(account);
                                        ticTacToePlayers.add(ticTacToePlayer);
                                        TicTacToe ticTacToe = new TicTacToe(ticTacToePlayers);
                                        ticTacToe.beginGame();
                                        accountManager.updateAccounts();
                                    } else {
                                        noAccountFound(accountPassword, accountName);
                                    }
                                } else {
                                    HashSet<TicTacToePlayer> ticTacToePlayers = new HashSet<>();
                                    for (Account account : loggedInAccounts) {
                                        TicTacToePlayer ticTacToePlayer = new TicTacToePlayer(account);
                                        ticTacToePlayers.add(ticTacToePlayer);
                                    }
                                    TicTacToe ticTacToe = new TicTacToe(ticTacToePlayers);
                                    ticTacToe.beginGame();
                                    accountManager.updateAccounts();
                                }
                                break;
                            }
//                       else if (gameSelectionInput.equals("BLACK JACK")) {
//                           play(new BlackJackGame(), new BlackJackPlayer());
//                       } else if (gameSelectionInput.equals("ROULETTE")){
//                           play (new RouletteGame(), new RoulettePlayer());
//                       }
                            default:
                                String errorMessage = "[ %s ] is an invalid game selection";
                                throw new RuntimeException(String.format(errorMessage, gameSelectionInput));
                        }

                        break;
                    case "LOGOUT ACCOUNT":
                    case "3":
                    case "LOGOUT":
                    case "L":
                        String usernameToLogout = console.getStringInput("Username to logout:");
                        String passwordToLogout = console.getStringInput("Password to logout:");
                        loggedInAccounts.remove(accountManager.getAccount(usernameToLogout, passwordToLogout));
                        break;
                }
           } while (!"QUIT".equals(dashBoardInput));
       } catch (RuntimeException e){System.out.println(e.getMessage());}
    }

    private String getDashboardInput() {
        return console.getStringInput("\n\n\n\n\n" +
                "oooooo     oooo oooooooooooo ooooo      ooo oooooooooooo ooooooooooooo ooooo       .o.       ooooo      ooo \n" +
                " `888.     .8'  `888'     `8 `888b.     `8' `888'     `8 8'   888   `8 `888'      .888.      `888b.     `8' \n" +
                "  `888.   .8'    888          8 `88b.    8   888              888       888      .8 888.      8 `88b.    8  \n" +
                "   `888. .8'     888oooo8     8   `88b.  8   888oooo8         888       888     .8' `888.     8   `88b.  8  \n" +
                "    `888.8'      888          8     `88b.8   888              888       888    .88ooo8888.    8     `88b.8  \n" +
                "     `888'       888       o  8       `888   888       o      888       888   .8'     `888.   8       `888  \n" +
                "      `8'       o888ooooood8 o8o        `8  o888ooooood8     o888o     o888o o88o     o8888o o8o        `8  \n" +
                "                                                                                                            \n" +
                "                                                                                                            \n" +
                "\nSelect an option number:" +
                "\n(1) Create Account  (2) Select Game  (3) Logout Account");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("From here, you can select any of the following games:" +
                "\n(1) SLOTS  (2) THREE CARD POKER  (3) HIGH LOW DICE (4) TIC TAC TOE"); // roulette and blackjack to add
    }

    private void noAccountFound(String accountPassword, String accountName) {
        String errorMessage = "No account found with name of [ %s ] and password of [ %s ]";
        throw new RuntimeException(String.format(errorMessage, accountPassword, accountName));
    }
}
