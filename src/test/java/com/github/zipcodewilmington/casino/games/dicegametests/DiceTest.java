package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.casino.games.dicegame.Dice;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DiceTest {

    Dice dice = new Dice();

    @Test
    public void testDice() {
        // Roll dice 100 times to make sure it is always within the acceptable range
        for (int i = 0; i < 100; i++) {
            int roll = dice.toss();
            Assert.assertTrue(roll >= 1 && roll <= 6);
        }
    }
}
