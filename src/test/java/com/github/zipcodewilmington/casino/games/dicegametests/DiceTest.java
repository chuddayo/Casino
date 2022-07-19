package com.github.zipcodewilmington.casino.games.dicegametests;

import com.github.zipcodewilmington.Casino;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class DiceTest {

    @Test
    public void test() throws IOException { // TODO
        // given
        Casino runnable = new Casino();

        // when
        runnable.run();

        // then
        Assert.assertNotNull(runnable.toString());
    }
//       Dice dice = new Dice();
//        Random random = new Random(1);
//        dice.setRandom(random);
//
//        Integer expected = 4;
//        Integer actual = dice.rollDice();
//
//        Assert.assertEquals(expected, actual);
//    }
}
