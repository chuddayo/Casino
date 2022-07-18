package com.github.zipcodewilmington.utils;

public class Sleep {
    public static void sleep(Integer millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
