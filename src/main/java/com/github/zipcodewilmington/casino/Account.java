package com.github.zipcodewilmington.casino;

/**
 * Created by leon on 7/21/2020.
 * `ArcadeAccount` is registered for each user of the `Arcade`.
 * The `ArcadeAccount` is used to log into the system to select a `Game` to play.
 */
public class Account {
    private String name;
    private String password;
    private double funds;

    public Account(String name, String password, double fund) {
        this.name = name;
        this.password = password;
        this.funds = fund;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getFunds() {
        return funds;
    }

    public void setFunds(double funds) {
        this.funds = funds;
    }

    public String toString() {
        return this.name + "," + this.password + "," + String.format("%.2f", this.funds) + "\n";
    }
}
