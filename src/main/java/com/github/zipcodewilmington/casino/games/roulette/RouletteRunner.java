package com.github.zipcodewilmington.casino.games.roulette;

import com.github.zipcodewilmington.casino.Account;

public class RouletteRunner {
    public static void main(String[] args) {
        Account account = new Account("Randy2", "ok", 2000);
        RoulettePlayer roulettePlayer = new RoulettePlayer(account);
        Roulette roulette = new Roulette(roulettePlayer);
        roulette.play(roulettePlayer.getAccount());
    }
}
