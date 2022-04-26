package main.general;

import java.util.List;

import main.dashboard.controller.MainController;
import main.dashboard.view.InputPanel;

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
     * Gets the input panels of the game.
     * @return the input panels of the game
     */
    List<InputPanel> getInputPanels();

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
     * Gets the color of the game.
     * @return the color of the game
     */
    GameColor getGameColor();

    /**
     * Starts the game.
     * @param inputs
     *          the inputs needed by the game
     */
    void startGame(int... inputs);

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
