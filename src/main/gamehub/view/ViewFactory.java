package main.gamehub.view;

import java.util.List;

import main.gamehub.controller.MainController;
import main.gamehub.model.GameController;

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
     * @param inputPanels
     *          the input panels that game start menu has to have
     * @return the created game start menu.
     */
    GameStartMenu createGameStartMenu(MainController mainController, GameController gameController,
            List<InputPanel> inputPanels);

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

    /**
     * Creates an input panel.
     * @param inputName
     *          the name of the input
     * @param inputValuesList
     *          the list of values of the input
     * @return the created input panel
     */
    InputPanel createInputPanel(String inputName, List<Integer> inputValuesList);

}
