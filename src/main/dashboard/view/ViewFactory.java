package main.dashboard.view;

import java.util.List;

import main.dashboard.controller.MainController;
import main.general.GameController;

/**
 * Interface that represents a factory for the view of the application.
 */
public interface ViewFactory {

    /**
     * Creates a main menu.
     * @param mainController
     *          the controller of the application
     * @param gameControllers
     *          the controllers of the games
     * @return the created main menu.
     */
    MainMenu createMainMenu(MainController mainController, List<GameController> gameControllers);

    /**
     * Creates a game start menu.
     * @param mainController
     *          the controller of the application
     * @param gameController
     *          the controller of the game that has to be started
     * @return the created game start menu.
     */
    GameStartMenu createGameStartMenu(MainController mainController, GameController gameController);

    /**
     * Creates a pause menu.
     * @param mainController
     *          the controller of the application
     * @param gameController
     *          the controller of the game that has to be paused
     * @return the created pause menu.
     */
    PauseMenu createPauseMenu(MainController mainController, GameController gameController);

    /**
     * Creates a game ending view.
     * @param mainController
     *          the controller of the application
     * @param gameController
     *          the controller of the game that ended
     * @param isVictory
     *          indicates whether the game has been won or not
     * @return the created game ending view.
     */
    GameEndingView createGameEndingView(MainController mainController, GameController gameController,
            boolean isVictory);

}
