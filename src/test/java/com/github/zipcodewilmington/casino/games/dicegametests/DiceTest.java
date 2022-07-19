package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.Casino;
import com.github.zipcodewilmington.casino.games.dicegame.Dice;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DiceTest {

    @Test
    public void testDiceEmptyConstructor()
    {
        // Construct an Die object
        Dice die = new Dice();
    }

    @Test
    public void testDieSidesConstructor()
    {
        int sides = 6;

        // Construct an Die object
        Dice dice = new Dice();

        // Check to see if the die has the expected number of sides
    }
}
