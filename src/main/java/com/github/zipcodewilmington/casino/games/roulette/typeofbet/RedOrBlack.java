package com.github.zipcodewilmington.casino.games.roulette.typeofbet;

import com.github.zipcodewilmington.casino.games.roulette.Bet;
import com.github.zipcodewilmington.casino.games.roulette.WheelSpin;
import com.github.zipcodewilmington.casino.games.roulette.utilroulette.IOConsoleReader;

public class RedOrBlack extends Bet {

    private String myChoice;

    public RedOrBlack (String description, int odds) {
        super(description, odds);

    }



    @Override
    public void place() {
        myChoice = IOConsoleReader.promptOneOf("Please bet", WheelSpin.BLACK, WheelSpin.RED);

    }

    @Override
    public boolean isMade(WheelSpin.SpinResult spinResult) {
        return WheelSpin.getColor().equals(myChoice);
    }
}
