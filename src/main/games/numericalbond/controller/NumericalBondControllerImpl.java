package main.games.numericalbond.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.gamehub.model.AbstractGameController;
import main.gamehub.model.GameView;
import main.games.numericalbond.model.Grid;
import main.games.numericalbond.model.LevelGeneratorImpl;
import main.games.numericalbond.utility.Position;
import main.games.numericalbond.view.NumericalBondGUI;
import main.games.numericalbond.view.NumericalBondView;

/**
 * Class that represents a controller of the game Numerical bond.
 */
public class NumericalBondControllerImpl extends AbstractGameController implements NumericalBondController {

    private static final String GAME_NAME = "Numerical Bond";
    private static final String INPUT_NAME = "Number of lines";
    private static final List<Integer> INPUT_VALUES = List.of(3, 4);

    private NumericalBondView view;
    private Grid grid;

    /**
     * {@inheritDoc}
     */
    @Override
    public Map<String, List<Integer>> getInputs() {
        final Map<String, List<Integer>> inputs = new HashMap<>();
        inputs.put(INPUT_NAME, INPUT_VALUES);
        return inputs;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public GameView getView() {
        return this.view;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void startGame(final int... inputs) {
        final int numGridLines = inputs[0];
        this.grid = new LevelGeneratorImpl(numGridLines).getGrid();
        this.view = new NumericalBondGUI(this, numGridLines);
        setupView();
    }

    private void setupView() {
        this.grid.getBlocks().keySet()
            .forEach(p -> this.view.setBlockNumber(p, getBlockNumber(p)));
    }

    private int getBlockNumber(final Position pos) {
        return this.grid.getBlockAt(pos).getLinksToHave() - this.grid.getBlockAt(pos).getCurrentLinks();
    }

    private boolean gameEnded() {
        return this.grid.isComplete();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void link(final Position pos1, final Position pos2) {
        if (!this.grid.canLink(pos1, pos2)) {
            return;
        }
        this.grid.link(pos1, pos2);
        this.view.createLink(pos1, pos2, this.grid.getLinks(pos1, pos2));
        this.view.setBlockNumber(pos1, getBlockNumber(pos1));
        this.view.setBlockNumber(pos2, getBlockNumber(pos2));
        this.view.deselect();
        if (gameEnded()) {
            endGame(true);
        }
    }

}
