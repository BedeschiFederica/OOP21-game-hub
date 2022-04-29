package main.gamehub.model;

import java.util.List;
import java.util.Map;

import main.gamehub.controller.MainController;

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
     * Gets the inputs that the game needs.
     * @return the input that the game needs.
     */
    Map<String, List<Integer>> getInputs();

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
     * @param inputs
     *          the inputs needed by the game
     */
    void startGame(int... inputs);

    /**
     * Ends the game, showing its result.
     * @param isVictory
     *          indicates if it's a victory or not
     */
    void endGame(boolean isVictory);

    /**
     * Pauses the game.
     */
    void pause();

    /**
     * Resumes the game.
     */
    void resume();

}
