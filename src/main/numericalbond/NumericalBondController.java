package main.numericalbond;

import main.general.GameController;
import main.numericalbond.model.Grid;
import main.numericalbond.model.LevelGenerator;
import main.numericalbond.view.NumericalBondView;

public class NumericalBondController implements GameController {

    private static final int NUM_GRID_LINES = 3;

    private final Grid grid;

    public NumericalBondController() {
        this.grid = new LevelGenerator(NUM_GRID_LINES).getGrid();
        //System.out.println(this.grid);
        final NumericalBondView view = new NumericalBondView(this);
    }

    public int getNumGridLines() {
        return NUM_GRID_LINES;
    }

    public int getBlockNumber(final Position pos) {
        return this.grid.getBlockAt(pos).getMaxLinks() - this.grid.getBlockAt(pos).getCurrentLinks();
    }

    public boolean canLink(final Position pos1, final Position pos2) {
        return this.grid.canLink(pos1, pos2);
    }

    public void link(final Position pos1, final Position pos2) {
        if (!canLink(pos1, pos2)) {
            throw new IllegalStateException("Can't link");
        }
        this.grid.link(pos1, pos2);
    }

    public int getLinks(final Position pos1, final Position pos2) {
        return this.grid.getLinks(pos1, pos2);
    }

    public boolean gameEnded() {
        return this.grid.isFinished();
    }

}
