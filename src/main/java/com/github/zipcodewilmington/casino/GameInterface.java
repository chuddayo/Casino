package com.github.zipcodewilmington.casino;

public interface GameInterface {
    void beginGame();

    String printInstructions();

    Player decideWinner();
//    /**
//     * adds a player to the game
//     * @param player the player to be removed from the game
//     */
//    void add(PlayerInterface player);
//
//    /**
//     * removes a player from the game
//     * @param player the player to be removed from the game
//     */
//    void remove(PlayerInterface player);
//
//    /**
//     * specifies how the game will run
//     */
//    void run();
}
