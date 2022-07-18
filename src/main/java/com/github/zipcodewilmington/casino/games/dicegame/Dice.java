package com.github.zipcodewilmington.casino.games.dicegame;

import java.util.Random;

public class Dice {

    private static final String[][] facesArr = {
            {
                    "+-------+",
                    "|       |",
                    "|   o   |",
                    "|       |",
                    "+-------+",
            },
            {
                    "+-------+",
                    "| o     |",
                    "|       |",
                    "|     o |",
                    "+-------+",
            },
            {
                    "+-------+",
                    "| o     |",
                    "|   o   |",
                    "|     o |",
                    "+-------+",
            },
            {
                    "+-------+",
                    "| o   o |",
                    "|       |",
                    "| o   o |",
                    "+-------+",
            },
            {
                    "+-------+",
                    "| o   o |",
                    "|   o   |",
                    "| o   o |",
                    "+-------+",
            },
            {
                    "+-------+",
                    "| o   o |",
                    "| o   o |",
                    "| o   o |",
                    "+-------+",
            }
    };

    private Random rand;
    private String[] face;

    public Dice() {
        this.rand = new Random();
    }

    public int toss() {
        int value = rand.nextInt(6) + 1;
        generateFace(value);
        return value;
    }

    public void generateFace(int value) {
        this.face = this.facesArr[value - 1];
    }

    public String[] getFace() {
        return this.face;
    }
}
