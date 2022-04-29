package main.gamehub.model;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
     * Adds inputs to the given map.
     * @param inputsMap
     *          the map to modify
     */
    protected abstract void addInputs(Map<String, List<Integer>> inputsMap);

    /**
     * {@inheritDoc}
     */
    @Override
    public final Map<String, List<Integer>> getInputs() {
        final Map<String, List<Integer>> inputsMap = new TreeMap<>();
        addInputs(inputsMap);
        return inputsMap;
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
