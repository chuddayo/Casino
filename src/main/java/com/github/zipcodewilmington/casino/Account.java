package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class Account {
    private final String userName;
    private final String password;
    private double balance;

    public Account(String userName, String password, double balance) {
        this.userName = userName;
        this.password = password;
        this.balance = balance;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double funds) {
        this.balance = funds;
    }

    public String toString() {
        return this.userName + "," + this.password + "," + String.format("%.2f", this.balance) + "\n";
    }
}
