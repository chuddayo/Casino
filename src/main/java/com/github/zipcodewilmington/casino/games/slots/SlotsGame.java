package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.GameInterface;

import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;

/**
 * Created by leon on 7/21/2020.
 */
public class SlotsGame implements GameInterface {

    private SlotsPlayer slotPlayer = null;

    int iTokens = 150;
    int input;
    int iSlot1, iSlot2, iSlot3;

    @Override
    public void beginGame() {
        while(true) {

            beginningMessage();
           if (userInput()==2) break;
            spinSlot();
        }
    }
    public SlotsGame(HashSet<SlotsPlayer> SlotPlayer) {
        for (SlotsPlayer slotsPlayer: SlotPlayer) {
            slotPlayer = slotsPlayer;
        }
    }

//    public void SlotsGame(){
//
//    }

    public void beginningMessage(){
        System.out.println("Slot Machine");
        System.out.println("Token: " + slotPlayer.getPlayerAccount().getBalance());
        System.out.println("Press 1 to pull, 2 to quit");
    }

    public void spinSlot(){
        Random generator = new Random();
        iSlot1 = generator.nextInt(5) + 1;
        iSlot2 = generator.nextInt(5) + 1;
        iSlot3 = generator.nextInt(5) + 1;

        System.out.println(iSlot1 + " " + iSlot2 + " " + iSlot3);


        if (iSlot1 == iSlot2 && iSlot1 == iSlot3) {
            System.out.println("You win 10 tokens");
            slotPlayer.getPlayerAccount().addBalance(10);
        } else if (iSlot1 == iSlot2 || iSlot1 == iSlot3 || iSlot2 == iSlot3) {
            System.out.println("You win 5 tokens");
            slotPlayer.getPlayerAccount().addBalance(5);
        } else {
            System.out.println("You lost a token");
            slotPlayer.getPlayerAccount().deductBalance(1);
        }
    }

    public int userInput(){
        Scanner console = new Scanner(System.in);
          input = console.nextInt();
          return input;
    }

    public void Instructions() {

    }

    @Override
    public String printInstructions() {
        return null;
    }
}
