package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.AccountManager;

import java.io.IOException;
import java.util.HashSet;

public class SlotsGameTest {

    public static void main(String[] args) throws IOException {
        AccountManager ac = new AccountManager();
        ac.createAccount("Tristan", "ok");
        HashSet<SlotsPlayer> SlotPlayer = new HashSet<>();

        SlotsPlayer tristan = new SlotsPlayer(ac.getAccount("Tristan","ok"));

        SlotPlayer.add(tristan);

        SlotsGame game = new SlotsGame(SlotPlayer);
        game.beginGame();

        ac.updateAccounts();
    }
}



