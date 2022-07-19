package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.games.slots.*;
import org.junit.Test;

import java.util.HashSet;

public class SlotsGamePlayerTest {

    @Test
    public void test() {
        Account acct1 = new Account("tristan", "ok", 1000);

        SlotsPlayer p1 = new SlotsPlayer(acct1);

        HashSet<SlotsPlayer> players = new HashSet<>();
        players.add(p1);

        SlotsGame game = new SlotsGame(players);

        System.out.println(acct1.getBalance());
    }
}
