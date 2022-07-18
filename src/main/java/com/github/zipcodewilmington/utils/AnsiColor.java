package com.github.zipcodewilmington.utils;

/**
 * Created by leon on 7/21/2020.
 * used to create color for output of `IOConsole`
 */
public enum AnsiColor {
    AUTO("\u001B[0m"),
    BLACK("\u001B[30m"),
    RED("\u001b[31;1m"),
    GREEN("\u001b[32;1m"),
    YELLOW("\u001b[33;1m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    CYAN("\u001b[36;1m"),
    WHITE("\u001b[37;1m"),
    MAGENTA("\u001b[35;1m");

    private final String color;

    AnsiColor(String ansiColor) {
        this.color = ansiColor;
    }

    public String getColor() {
        return color;
    }

}