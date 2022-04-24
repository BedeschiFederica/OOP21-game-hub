package main.general;

import main.dashboard.controller.MainController;

/**
 * Interface of a generic game controller.
 */
public interface GameController {

    /**
     * Sets the main controller of the application.
     * @param mainController
     *          the main controller
     */
    void setMainController(MainController mainController);

    /**
     * Gets the view of the game.
     * @return the view of the game
     */
    GameView getView();

    /**
     * Gets the name of the game.
     * @return the name of the game
     */
    String getGameName();

    /**
     * Starts the game.
     */
    void startGame();

    /**
     * Ends the game, showing its result.
     */
    void endGame();

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void resume();

    /**
     * Closes the game interrupting it. It returns to the main menu.
     */
    void closeGame();

}
