package main.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.dashboard.view.MainMenu;
import main.dashboard.view.ViewFactory;
import main.dashboard.view.ViewFactoryImpl;
import main.general.GameController;

/**
 * Class that represents the controller of the application.
 * It manages the dashboard view and the GameControllers.
 */
public class MainControllerImpl implements MainController {

    private final Map<String, GameController> gameControllers = new HashMap<>();
    private final ViewFactory viewFactory = new ViewFactoryImpl();
    private final MainMenu mainMenu;

    /**
     * Builds a new {@link MainControllerImpl}.
     * @param gameControllers
     *          the controllers of the games that the MainController has to manage
     */
    public MainControllerImpl(final GameController... gameControllers) {
        for (final GameController gc : gameControllers) {
            this.gameControllers.put(gc.getGameName(), gc);
            gc.setMainController(this);
        }
        this.mainMenu = this.viewFactory.createMainMenu(this, List.copyOf(this.gameControllers.values()));
        this.mainMenu.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStartPanel(final String gameName) {
        this.mainMenu.setVisible(false);
        this.viewFactory.createGameStartMenu(this, this.gameControllers.get(gameName)).setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame(final GameController controller, final int... inputs) {
        controller.startGame(inputs);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showGameEnding(final GameController controller, final boolean isVictory) {
        //new GameEndingGUI(this, controller, isVictory).setVisible(true);
        this.viewFactory.createGameEndingView(this, controller, isVictory).setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showMainMenu() {
        this.mainMenu.setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pauseGame(final GameController controller) {
        //new PauseMenuGUI(this, controller).display();
        this.viewFactory.createPauseMenu(this, controller).setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resumeGame(final GameController controller) {
        controller.resume();
    }

}
