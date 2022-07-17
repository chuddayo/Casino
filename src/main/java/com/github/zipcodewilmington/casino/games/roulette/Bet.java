package com.github.zipcodewilmington.casino.games.roulette;

public abstract class Bet {
    private String myDescription;
    private int myOdds;

    public Bet (String description, int odds) {
        myDescription = description;
        myOdds = odds;
    }

    public int payout (int wager) {
        return myOdds * wager;
    }

    public String toString () {
        return myDescription;
    }

    public abstract void place ();

    public abstract boolean isMade (WheelSpin.SpinResult spinResult);
}
