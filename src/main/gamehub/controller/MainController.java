package main.gamehub.controller;

import main.gamehub.model.GameController;

/**
 * Interface that represents the controller of the application.
 * It manages the dashboard view and the GameControllers.
 */
public interface MainController {

    /**
     * Shows the start panel of the game specified by the given name.
     * @param gameName
     *         the name of the game
     */
    void showStartPanel(String gameName);

    /**
     * Starts the game specified by the given controller.
     * @param controller
     *          the controller of the game
     * @param inputs
     *          the inputs needed by the game
     */
    void startGame(GameController controller, int... inputs);

    /**
     * Shows the game ending of the game specified by the given controller.
     * @param controller
     *          the controller of the game
     * @param isVictory 
     *          needed to know if the player has won
     */
    void showGameEnding(GameController controller, boolean isVictory);

    /**
     * Shows the main menu of the application.
     */
    void showMainMenu();

    /**
     * Pauses the game specified by the given controller.
     * @param controller
     *          the controller of the game
     */
    void pauseGame(GameController controller);

    /**
     * Resumes the game specified by the given controller.
     * @param controller
     *          the controller of the game
     */
    void resumeGame(GameController controller);

}
