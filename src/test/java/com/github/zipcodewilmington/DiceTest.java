package com.github.zipcodewilmington;

import org.junit.Assert;
import org.junit.Test;

public class DiceTest {

    @Test
    public void test() { // TODO
        // given
        Runnable runnable = new Casino();

        // when
        runnable.run();

        // then
        Assert.assertNotNull(runnable.toString());
    }

}
