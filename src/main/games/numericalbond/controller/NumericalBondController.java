package main.games.numericalbond.controller;

import main.games.numericalbond.model.Grid;
import main.games.numericalbond.model.LevelGenerator;
import main.games.numericalbond.view.NumericalBondView;
import main.general.AbstractGameController;
import main.general.GameView;

/**
 * Class that represents a controller of the game Numerical bond.
 */
public class NumericalBondController extends AbstractGameController {

    private static final String GAME_NAME = "Numerical Bond";
    private static final int NUM_GRID_LINES = 3;

    private NumericalBondView view;
    private Grid grid;

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
    public void startGame() {
        this.grid = new LevelGenerator(NUM_GRID_LINES).getGrid();
        this.view = new NumericalBondView(this, NUM_GRID_LINES);
        setupView();
    }

    private void setupView() {
        this.grid.getBlocks().keySet()
            .forEach(p -> this.view.setBlockNumber(p, getBlockNumber(p)));
    }

    private int getBlockNumber(final Position pos) {
        return this.grid.getBlockAt(pos).getMaxLinks() - this.grid.getBlockAt(pos).getCurrentLinks();
    }

    private boolean gameEnded() {
        return this.grid.isComplete();
    }

    /**
     * Links the two blocks specified by the two given positions.
     * It manages both model and view.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     */
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
            System.out.println("You won!");
            this.view.dispose();
            endGame();
        }
    }

}
