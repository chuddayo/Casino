package com.github.zipcodewilmington.casino.games.TicTacToe;

import com.github.zipcodewilmington.casino.GameInterface;
import com.github.zipcodewilmington.casino.PlayerInterface;

import java.util.Objects;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class TicTacToe implements GameInterface {
    public static void main (String[] args){
        int attempts = 0;
        String[][] demo = {{"a", "b", "c"}, {"d", "e", "f"}, {"g", "h", "i"}};
        displayUI(demo);
        gamePlay(attempts);

    }

    String position= " ";

    private static boolean isValidPlay (String[][]userInterface, String position ){

       if (Objects.equals(userInterface[0][0], " ")){return true;}
       else if(Objects.equals(userInterface[0][1], " ")){return true;}
       else if(Objects.equals(userInterface[0][2], " ")){return true;}
       else if (Objects.equals(userInterface[1][0], " ")){return true;}
       else if (Objects.equals(userInterface[1][1], " ")){return true;}
       else if (Objects.equals(userInterface[1][2], " ")){return true;}
       else if (Objects.equals(userInterface[2][0], " ")){return true;}
       else if (Objects.equals(userInterface[2][1], " ")){return true;}
       else if (Objects.equals(userInterface[2][2], " ")){return true;}
       else {return false;}
    }
    private static void gamePlay(int attempts) {
       // while (attempts <= 5) {
            String[][] userInterface = {{" ", " ", " "}, {" ", " ", " "}, {" ", " ", " "}};

            userPlay(userInterface); //create while loop that runs nine times???? Nope.
            displayUI(userInterface);//create while loop that runs five times?
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface); //
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);

            userPlay(userInterface);
            displayUI(userInterface);
            userPlay2(userInterface);
            displayUI(userInterface);
            attempts++;
        }
   // }

    private static void userPlay(String[][] userInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        String userInput = scanner.nextLine();

        if (Objects.equals(userInput, "a")){
            userInterface[0][0] = "X";
        } else if (Objects.equals(userInput, "b")){
            userInterface[0][1] = "X";
        }else if (Objects.equals(userInput, "c")){
            userInterface[0][2] = "X";
        }else if (Objects.equals(userInput, "d")){
            userInterface[1][0] = "X";
        }else if (Objects.equals(userInput, "e")){
            userInterface[1][1] = "X";
        }else if (Objects.equals(userInput, "f")){
            userInterface[1][2] = "X";
        }else if (Objects.equals(userInput, "g")){
            userInterface[2][0] = "X";
        }else if (Objects.equals(userInput, "h")){
            userInterface[2][1] = "X";
        }else if (Objects.equals(userInput, "i")){
            userInterface[2][2] = "X";
        } else { System.out.println("Invalid input. Try again.");
        }
    }
    private static void userPlay2(String[][] userInterface) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Choose a position on the board. Options are 'a' through 'i'.");
        String userInput = scanner.nextLine();

        if (Objects.equals(userInput, "a")){
            userInterface[0][0] = "O";
        } else if (Objects.equals(userInput, "b")){
            userInterface[0][1] = "O";
        }else if (Objects.equals(userInput, "c")){
            userInterface[0][2] = "O";
        }else if (Objects.equals(userInput, "d")){
            userInterface[1][0] = "O";
        }else if (Objects.equals(userInput, "e")){
            userInterface[1][1] = "O";
        }else if (Objects.equals(userInput, "f")){
            userInterface[1][2] = "O";
        }else if (Objects.equals(userInput, "g")){
            userInterface[2][0] = "O";
        }else if (Objects.equals(userInput, "h")){
            userInterface[2][1] = "O";
        }else if (Objects.equals(userInput, "i")){
            userInterface[2][2] = "O";
        } else { System.out.println("Invalid input. Try again.");
        }
    }
    private static void displayUI(String[][] userInterface) {
        System.out.println(userInterface[0][0] + " | " + userInterface[0][1] + " | " + userInterface[0][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[1][0] + " | " + userInterface[1][1] + " | " + userInterface[1][2]);
        System.out.println("- + - + -");
        System.out.println(userInterface[2][0] + " | " + userInterface[2][1] + " | " + userInterface[2][2]);
    }

    @Override
    public void add(PlayerInterface player) {

    }

    @Override
    public void remove(PlayerInterface player) {

    }

    @Override
    public void run() {

    }
}