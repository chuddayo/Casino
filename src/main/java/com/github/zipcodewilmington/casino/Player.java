package com.github.zipcodewilmington.casino;

public abstract class Player {
    Account account;

    public Player(Account account) {
        this.account = account;
    }
}
