package com.github.zipcodewilmington.casino.games;

import com.github.zipcodewilmington.Casino;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class SlotsGameTest {


    @Test
    public void test() throws IOException { // TODO
        // given
        Casino runnable = new Casino();

        // when
        runnable.run();

        // then
        Assert.assertNotNull(runnable.toString());
    }


}
