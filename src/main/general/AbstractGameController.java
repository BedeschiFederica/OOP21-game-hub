package main.general;

import main.dashboard.controller.MainController;

/**
 * Abstract class that represents a generic controller of a game.
 */
public abstract class AbstractGameController implements GameController {

    private MainController mainController;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame(final boolean isVictory) {
        this.mainController.showGameEnding(this, isVictory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.mainController.pauseGame(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        getView().setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGame() {
        this.mainController.showMainMenu();
    }

}
