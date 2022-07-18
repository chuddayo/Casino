package com.github.zipcodewilmington.utils;

public class Sleep {
    private static final IOConsole console = new IOConsole();
    public static void sleep(Integer millisecs) {
        try {
            Thread.sleep(millisecs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
