package main.gamehub.model;

import main.gamehub.controller.MainController;

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
        getView().dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.mainController.pauseGame(this);
        getView().dispose();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        getView().setVisible(true);
    }

}
