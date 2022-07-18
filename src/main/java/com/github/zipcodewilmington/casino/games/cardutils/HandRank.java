package com.github.zipcodewilmington.casino.games.cardutils;

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

    @Override
    public String toString() {
        if (this.getHandRank() == 1) return "---- High Card ----";
        if (this.getHandRank() == 2) return "----- One Pair -----";
        if (this.getHandRank() == 3) return "------ Flush -------";
        if (this.getHandRank() == 4) return "----- Straight -----";
        if (this.getHandRank() == 5) return "- Three of a Kind -";
        if (this.getHandRank() == 6) return "STRAIGHT FLUSH! WOW";
        return "no hand representation";
    }
}
