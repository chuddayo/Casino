package com.github.zipcodewilmington.casino.games.roulette.typeofbet;

import com.github.zipcodewilmington.casino.games.roulette.Bet;
import com.github.zipcodewilmington.casino.games.roulette.WheelSpin;
import com.github.zipcodewilmington.casino.games.roulette.utilroulette.IOConsoleReader;

public class OddOrEven extends Bet {
    private String myChoice;

    public OddOrEven (String description, int odds) {
        super(description, odds);
        myChoice = "";
    }



    @Override
    public void place() {
        myChoice = IOConsoleReader.promptOneOf("Please Bet", "even", "odd");

    }

    @Override
    public boolean isMade(WheelSpin.SpinResult spinResult) {
        return (WheelSpin.getNumber() % 2 == 0 && myChoice.equals("even")) ||
                (WheelSpin.getNumber() % 2 == 1 && myChoice.equals("odd"));
    }


}
