package com.github.zipcodewilmington.casino.games;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToe;
import com.github.zipcodewilmington.casino.games.TicTacToe.TicTacToePlayer;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;

public class TicTacToeTest {


    @Test
public void declareWinnerTest1(){
//Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Tristan", "ok", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" x ", " x ", " x "},
                                    {" x ", "  ", " o "},
                                    {" o ", " x ", " o "}};
          //When
          boolean expected = false;
          boolean actual = ticTacToe.declareWinner(userInterface, "x");
          //Then
          Assert.assertEquals(expected,actual);

    }

    @Test
    public void declareWinnerTest2() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Tenae", "6", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" X ", " O ", " X "},
                                    {" X ",  "  ", " O "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest3() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " O ", " X "},
                                    {" X ", " X ", " O "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest4() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Helen", "7", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" O ", " O ", " X "},
                                    {" X ", "   ", " X "},
                                    {" X ", " O ", " X "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest5() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Jenny", "0", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ",  " O ", " O "},
                                    {" X ", " X ", " X "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest6() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " O ", " X "},
                                    {" X ", " O ", " O "},
                                    {" X ", " X ", " X "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest7() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Zara", "3", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" X ", " O ", "  "},
                                    {" O ", " X ", " O "},
                                    {" X ", " O ", " X "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest8() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" O "," X ", "  "},
                                   {" O ", " X ", " O "},
                                   {" X ", " X ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest9() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " O ", " O "},
                                    {" X ", " O ", " X "},
                                    {" O ", " X", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest10() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " X",  " X "},
                                    {" X ", " X ", " O "},
                                    {" O ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void declareWinnerTest11() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " X ", " X "},
                                    {" O ", " O ", " O "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest12() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" O ", " O ", " O "},
                                    {" X ", " X ", "  "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }
    @Test
    public void declareWinnerTest13() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" O ", " O ", " X "},
                                   {" O ", " X ", "  " },
                                   {" O ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest14() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ",  " O ", " X "},
                                    {" X ", " O ", " O "},
                                    {" X ", " O ", " X "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest15() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{"  ", " O ", " O"},
                                    {" X ", " X ", " O "},
                                     {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void declareWinnerTest16() {
        //Given
        HashSet<TicTacToePlayer> player = new HashSet<>();
        Account account1 = new Account("Penny", "8", 5000);
        TicTacToePlayer player1 = new TicTacToePlayer(account1);
        player.add(player1);
        TicTacToe ticTacToe = new TicTacToe(player);
        String[][] userInterface = {{" O ", "  ", " X "},
                                    {" X ", " O ", " O "},
                                    {" X ", " O ", " O "}};

        //When
        boolean expected = false;
        boolean actual = ticTacToe.declareWinner(userInterface, "X");
        //Then
        Assert.assertEquals(expected, actual);
    }
}


