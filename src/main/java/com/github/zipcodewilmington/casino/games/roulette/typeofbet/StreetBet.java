package com.github.zipcodewilmington.casino.games.roulette.typeofbet;

import com.github.zipcodewilmington.casino.games.roulette.Bet;
import com.github.zipcodewilmington.casino.games.roulette.WheelSpin;
import com.github.zipcodewilmington.casino.games.roulette.utilroulette.IOConsoleReader;

public class StreetBet extends Bet {
    public final int NUM_CONSECUTIVE = 3;
    private int myStart;

    public StreetBet (String description, int odds) {
        super(description, odds);
    }

    @Override
    public void place() {
        myStart = IOConsoleReader.promptRange("Enter first three consecutive numbers",
                1, (WheelSpin.NUM_SPOTS + 1) - NUM_CONSECUTIVE);

    }

    @Override
    public boolean isMade(WheelSpin.SpinResult spinResult) {
        return (myStart <= WheelSpin.getNumber() && WheelSpin.getNumber() < myStart + NUM_CONSECUTIVE);
    }
}
