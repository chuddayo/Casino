package com.github.zipcodewilmington.casino;

public abstract class Player {
    protected Account account;

    public Player(Account account) {
        this.account = account;
    }

    public abstract String getPlayerName();

    public abstract Account getPlayerAccount();
}
