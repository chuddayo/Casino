package com.github.zipcodewilmington;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.AccountManager;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerGame;
import com.github.zipcodewilmington.casino.games.cardgames.ThreeCardPokerPlayer;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDice;
import com.github.zipcodewilmington.casino.games.dicegame.HighLowDicePlayer;
import com.github.zipcodewilmington.casino.games.roulette.Roulette;
import com.github.zipcodewilmington.casino.games.roulette.RoulettePlayer;
import com.github.zipcodewilmington.casino.games.slots.SlotsGame;
import com.github.zipcodewilmington.casino.games.slots.SlotsPlayer;
import com.github.zipcodewilmington.utils.AnsiColor;
import com.github.zipcodewilmington.utils.IOConsole;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;

public class Casino {
    private final IOConsole console = new IOConsole(AnsiColor.CYAN);

    public void run() throws IOException {
        String dashBoardInput;
        AccountManager accountManager = new AccountManager();
        HashSet<Account> loggedInAccounts = new HashSet<>();
        printVenetianBanner();
        do {
            dashBoardInput = getDashboardInput().toUpperCase();

            switch (dashBoardInput) {
                case "CREATE ACCOUNT":
                case "1":
                case "CREATE":
                case "C": {
                    do {
                        console.println("Welcome to the account-creation screen.");
                        String accountName = console.getStringInput("Enter your account name:");
                        String accountPassword = console.getStringInput("Enter your account password:");
                        if (accountManager.getAccountUsernames().contains(accountName)) {
                            console.println("This username already exists.");
                            break;
                        } else {
                            accountManager.createAccount(accountName, accountPassword);
                            accountManager.updateAccounts();
                            break;
                        }
                    } while(true);
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
                            if (loggedInAccounts.size() > 1) System.out.println("Too many accounts returning to lobby...");
                            else {
                                Account account = null;
                                if (loggedInAccounts.size() == 0) {
                                    account = loginPrompt(accountManager);
                                    if (account == null) noAccountFound();
                                } else { // has to be one account logged in already
                                    for (Account loggedAccount : loggedInAccounts) account = loggedAccount;
                                }
                                if (account != null) {
                                    loggedInAccounts.add(account);
                                    new SlotsGame(new HashSet<>(Collections.singleton(new SlotsPlayer(account))));
                                    accountManager.updateAccounts();
                                }
                            }
                            break;
                        }

                        case "THREE CARD POKER":
                        case "2":
                            while (true) {
                                while (loggedInAccounts.size() == 0 || loginMore) {
                                    Account account = loginPrompt(accountManager);
                                    if (account != null) {
                                        loggedInAccounts.add(account);
                                        loginMore = false;
                                    } else {
                                        noAccountFound();
                                        break;
                                    }
                                }
                                int loginMoreOrPlay = console.getIntegerInput(
                                        "(1) Login another user  (2) Begin Play  (3) Return to Main Lobby");
                                if (loginMoreOrPlay == 2) {
                                    HashSet<ThreeCardPokerPlayer> threeCardPlayers = new HashSet<>();
                                    for (Account account : loggedInAccounts) {
                                        threeCardPlayers.add(new ThreeCardPokerPlayer(account));
                                    }
                                    new ThreeCardPokerGame(threeCardPlayers).beginGame();
                                    accountManager.updateAccounts();
                                    break;
                                } else if (loginMoreOrPlay == 3) { break; }
                                else if (loginMoreOrPlay == 1) { loginMore = true; }
                            }
                            break;

                        case "HIGH LOW DICE":
                        case "3":
                            while (true) {
                                while (loggedInAccounts.size() == 0 || loginMore) {
                                    Account account = loginPrompt(accountManager);
                                    if (account != null) {
                                        loggedInAccounts.add(account);
                                        loginMore = false;
                                    } else {
                                        noAccountFound();
                                        break;
                                    }
                                }
                                int loginMoreOrPlay = console.getIntegerInput(
                                        "(1) Login another user  (2) Begin Play  (3) Return to Main Lobby");
                                if (loginMoreOrPlay == 2) {
                                    HashSet<HighLowDicePlayer> dicePlayers = new HashSet<>();
                                    for (Account account : loggedInAccounts) {
                                        dicePlayers.add(new HighLowDicePlayer(account));
                                    }
                                    new HighLowDice(dicePlayers).beginGame();
                                    accountManager.updateAccounts();
                                    break;
                                } else if (loginMoreOrPlay == 3) { break; }
                                else if (loginMoreOrPlay == 1) { loginMore = true; }
                            }
                            break;

                        case "TIC TAC TOE":
                        case "4":
                        {
                            if (loggedInAccounts.size() > 1) System.out.println("Too many accounts returning to lobby...");
                            else {
                                Account account = null;
                                if (loggedInAccounts.size() == 0) {
                                    account = loginPrompt(accountManager);
                                    if (account == null) noAccountFound();
                                } else { // has to be one account logged in already
                                    for (Account loggedAccount : loggedInAccounts) account = loggedAccount;
                                }
                                if (account != null) {
                                    loggedInAccounts.add(account);
                                    new TicTacToe(new HashSet<>(Collections.singleton(new TicTacToePlayer(account)))).beginGame();
                                    accountManager.updateAccounts();
                                }
                            }
                            break;
                        }

                        case "ROULETTE":
                        case "5":
                        {
                            if (loggedInAccounts.size() > 1) System.out.println("Too many accounts returning to lobby...");
                            else {
                                Account account = null;
                                if (loggedInAccounts.size() == 0) {
                                    account = loginPrompt(accountManager);
                                    if (account == null) noAccountFound();
                                } else { // has to be one account logged in already
                                    for (Account loggedAccount : loggedInAccounts) account = loggedAccount;
                                }
                                if (account != null) {
                                    loggedInAccounts.add(account);
                                    new Roulette(new RoulettePlayer(account)).play(account);
                                    accountManager.updateAccounts();
                                }
                            }
                            break;
                        }

                        default:
                            String errorMessage = "[ %s ] is an invalid game selection";
                            System.out.format(errorMessage, gameSelectionInput);
                    }
                    printVenetianBanner();
                    break;

                case "LOGOUT ACCOUNT":
                case "LOGOUT":
                case "L":
                case "4":
                    if (loggedInAccounts.size() == 1) {
                        loggedInAccounts.clear();
                    } else if (loggedInAccounts.size() > 1) {
                        String usernameToLogout = console.getStringInput("Username to logout:");
                        String passwordToLogout = console.getStringInput("Password to logout:");
                        loggedInAccounts.remove(accountManager.getAccount(usernameToLogout, passwordToLogout));
                    } else {
                        System.out.println("No one is logged in.");
                        // TODO add sleep here?
                    }
                    break;
            }
        } while (!"QUIT".equalsIgnoreCase(dashBoardInput) && !"5".equals(dashBoardInput));
    }
    private void printVenetianBanner() {
        console.println("\n\n\n\n\n" +
                "oooooo     oooo oooooooooooo ooooo      ooo oooooooooooo ooooooooooooo ooooo       .o.       ooooo      ooo \n" +
                " `888.     .8'  `888'     `8 `888b.     `8' `888'     `8 8'   888   `8 `888'      .888.      `888b.     `8' \n" +
                "  `888.   .8'    888          8 `88b.    8   888              888       888      .8 888.      8 `88b.    8  \n" +
                "   `888. .8'     888oooo8     8   `88b.  8   888oooo8         888       888     .8' `888.     8   `88b.  8  \n" +
                "    `888.8'      888          8     `88b.8   888              888       888    .88ooo8888.    8     `88b.8  \n" +
                "     `888'       888       o  8       `888   888       o      888       888   .8'     `888.   8       `888  \n" +
                "      `8'       o888ooooood8 o8o        `8  o888ooooood8     o888o     o888o o88o     o8888o o8o        `8  \n" +
                "                                                                                                            \n" +
                "                                                                                                            \n");
    }

    private String getDashboardInput() {
        return console.getStringInput(
         "\nSelect an option number:" +
                "\n(1) Create New Account  (2) Select Game  (3) Cashier  (4) Logout Account  (5) Quit");
    }

    private String getGameSelectionInput() {
        return console.getStringInput("From here, you can select any of the following games:" +
                "\n(1) SLOTS  (2) THREE CARD POKER  (3) HIGH LOW DICE (4) TIC TAC TOE"); // roulette and blackjack to add
    }

    private Account loginPrompt(AccountManager accountManager) {
        String accountName = console.getStringInput("Enter your account name:");
        String accountPassword = console.getStringInput("Enter your account password:");
        return accountManager.getAccount(accountName, accountPassword);
    }

    private void noAccountFound() {
        console.println("No account found with that name and password.\n");
    }
}
