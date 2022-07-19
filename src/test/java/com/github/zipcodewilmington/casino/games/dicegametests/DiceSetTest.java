package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.casino.games.dicegame.DiceSet;
import org.junit.Assert;
import org.junit.Test;

public class DiceSetTest {

    DiceSet pair = new DiceSet(2);
    DiceSet threeDice = new DiceSet(3);
    DiceSet tenDice = new DiceSet(10);


    @Test
    public void testPair() {
        // Roll dice 100 times to make sure it is always within the acceptable range
        for (int i = 0; i < 100; i++) {
            int roll = pair.tossAndSum();
            Assert.assertTrue(roll >= 2 && roll <= 12);
        }
    }

    @Test
    public void testThreeDice() {
        // Roll dice 100 times to make sure it is always within the acceptable range
        for (int i = 0; i < 100; i++) {
            int roll = threeDice.tossAndSum();
            Assert.assertTrue(roll >= 3 && roll <= 18);
        }
    }

    @Test
    public void testTenDice() {
        // Roll dice 100 times to make sure it is always within the acceptable range
        for (int i = 0; i < 100; i++) {
            int roll = tenDice.tossAndSum();
            Assert.assertTrue(roll >= 10 && roll <= 60);
        }
    }
}
