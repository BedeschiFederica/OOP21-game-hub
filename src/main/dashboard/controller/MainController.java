package main.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dashboard.view.EndGame;
import main.dashboard.view.GameStartGUI;
import main.dashboard.view.MainMenu;
import main.dashboard.view.MainMenuGUI;
import main.dashboard.view.PauseMenu;
import main.general.GameController;

/**
 * Class that represents the controller of the application.
 * It manages the MainMenu and the GameControllers.
 */
public class MainController {

    private final Map<String, GameController> controllers = new HashMap<>();
    private final MainMenu menu;

    /**
     * Builds a new {@link MainController}.
     * @param controllers
     *          the controllers of the games that the MainController has to manage
     */
    public MainController(final GameController... controllers) {
        for (final GameController c : controllers) {
            this.controllers.put(c.getGameName(), c);
            c.setMainController(this);
        }
        this.menu = new MainMenuGUI(this, List.copyOf(this.controllers.values()));
    }

    /**
     * Shows the start panel of the game specified by the given name.
     * @param gameName
     *         the name of the game
     */
    public void showStartPanel(final String gameName) {
        new GameStartGUI(this, this.controllers.get(gameName));
    }

    /**
     * Starts the game specified by the given controller.
     * @param controller
     *          the controller of the game
     * @param inputs
     *          the inputs needed by the game
     */
    public void startGame(final GameController controller, final int... inputs) {
        controller.startGame(inputs);
    }

    /**
     * Shows the game ending of the game specified by the given controller.
     * @param controller
     *          the controller of the game
     */
    public void showGameEnding(final GameController controller, final boolean isVictory) {
        new EndGame(this, controller, isVictory);
    }

    /**
     * Shows the main menu of the application.
     */
    public void showMainMenu() {
        this.menu.setVisible(true);
    }

    /**
     * Pauses the game specified by the given controller.
     * @param controller
     *          the controller of the game
     */
    public void pauseGame(final GameController controller) {
        new PauseMenu(this, controller).display();
    }

    /**
     * Resumes the game specified by the given controller.
     * @param controller
     *          the controller of the game
     */
    public void resumeGame(final GameController controller) {
        controller.resume();
    }

}
