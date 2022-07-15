package com.github.zipcodewilmington.casino;

public enum HandRank {
    HIGHCARD(1),
    ONEPAIR(2),
    FLUSH(3),
    STRAIGHT(4),
    THREEOFAKIND(5),
    STRAIGHTFLUSH(6);

    private final int handRank;

    HandRank(int rankValue) {
        handRank = rankValue;
    }

    public int getHandRank() {
        return handRank;
    }
}
