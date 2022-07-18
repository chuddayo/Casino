package com.github.zipcodewilmington.casino.games.slots;

import com.github.zipcodewilmington.casino.Account;
import com.github.zipcodewilmington.casino.GamblingPlayer;
import com.github.zipcodewilmington.casino.Player;

public class SlotsPlayer extends Player implements GamblingPlayer {


        public SlotsPlayer(Account account) {
            super(account);
        }

        @Override
        public String getPlayerName() {
            return null;
        }

        @Override
        public Account getPlayerAccount() {
            return this.account;
        }

        @Override
        public void placeBet(int amount) {

        }
    }
