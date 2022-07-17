package com.github.zipcodewilmington.casino.games.dicegame;

import java.util.HashSet;

public class DiceSet {

    private HashSet<Dice> diceSet;
    private String diceArt;

    public DiceSet(int numDice) {
        this.diceSet = new HashSet<>();
        for (int i = 0; i < numDice; i++) {
            this.diceSet.add(new Dice());
        }
    }

    public int tossAndSum() {
        int value = 0;
        for (Dice dice : this.diceSet) {
            value += dice.toss();
        }
        return value;
    }

    public String getDiceArt() {

        this.diceArt = "\n";

        for (int i = 0; i < 5; i++) {
            for (Dice dice : this.diceSet) {
                this.diceArt += dice.getFace()[i] + "\t";
            }
            this.diceArt += "\n";
        }

        return this.diceArt;
    }
}
