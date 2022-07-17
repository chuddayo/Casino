package com.github.zipcodewilmington.casino.games.roulette;

import java.util.Random;

public class WheelSpin {

    public static final String RED = "red";
    public static final String BLACK = "black";
    public static final String GREEN = "green";

    private static final String[] Spots = {
            GREEN,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            BLACK,
            RED,
            GREEN
    };

    public static final int NUM_SPOTS = Spots.length;
    private static final Random Randomizer = new Random();

    private int myNumSpins;
    private static int myValue;

    public WheelSpin() {
        myNumSpins = 0;
        myValue = 0;

    }

    public int getNumSpins() {
        return myNumSpins;
    }

    public SpinResult spin() {
        myNumSpins += 1;
        myValue = Randomizer.nextInt(Spots.length);
        return new SpinResult(getColor(), getNumber());
    }

    public static String getColor() {
        return Spots[myValue];
    }

    public static int getNumber() {
        return myValue;
    }

    public static class SpinResult {
        private String myColor;
        private int myNumber;

        public SpinResult (String color, int number) {
            myColor = color;
            myNumber = number;

        }

        public String getColor() {
            return myColor;
        }

        public int getNumber() {
            return myNumber;
        }

        @Override
        public String toString() {
            return getColor() + " " + getNumber();
        }
    }
}






