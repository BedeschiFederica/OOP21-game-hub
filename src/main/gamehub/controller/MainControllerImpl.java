package main.gamehub.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.gamehub.model.GameController;
import main.gamehub.view.InputPanel;
import main.gamehub.view.MainMenu;
import main.gamehub.view.ViewFactory;
import main.gamehub.view.ViewFactoryImpl;

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

    private List<InputPanel> createInputPanels(final GameController controller) {
        final List<InputPanel> inputPanels = new ArrayList<>();
        for (final var e : controller.getInputs().entrySet()) {
            inputPanels.add(this.viewFactory.createInputPanel(e.getKey(), e.getValue()));
        }
        return inputPanels;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void showStartPanel(final String gameName) {
        this.mainMenu.setVisible(false);
        this.viewFactory.createGameStartMenu(this, this.gameControllers.get(gameName),
                createInputPanels(this.gameControllers.get(gameName))).setVisible(true);
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
