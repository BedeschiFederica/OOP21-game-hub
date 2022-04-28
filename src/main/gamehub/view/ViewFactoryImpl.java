package main.gamehub.view;

import java.util.List;

import main.gamehub.controller.MainController;
import main.gamehub.model.GameController;

/**
 * Class that represents a factory for the view of the application.
 */
public class ViewFactoryImpl implements ViewFactory {

    /**
     * {@inheritDoc}
     */
    @Override
    public MainMenu createMainMenu(final MainController mainController,
            final List<GameController> gameControllers) {
        return new MainMenuGUI(mainController, gameControllers);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameStartMenu createGameStartMenu(final MainController mainController,
            final GameController gameController, final List<InputPanel> inputPanels) {
        return new GameStartMenuGUI(mainController, gameController, inputPanels);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PauseMenu createPauseMenu(final MainController mainController,
            final GameController gameController) {
        return new PauseMenuGUI(mainController, gameController);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameEndingView createGameEndingView(final MainController mainController,
            final GameController gameController, final boolean isVictory) {
        return new GameEndingGUI(mainController, gameController, isVictory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputPanelImpl createInputPanel(final String inputName, final List<Integer> inputValuesList) {
        return new InputPanelImpl(inputName, inputValuesList);
    }

}
