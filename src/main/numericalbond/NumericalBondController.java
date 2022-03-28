package main.numericalbond;

import main.general.GameController;
import main.numericalbond.model.Grid;
import main.numericalbond.model.LevelGenerator;

public class NumericalBondController implements GameController {

	private static final int NUM_GRID_LINES = 3;
	
	private final Grid grid;
		
	public NumericalBondController() {
		LevelGenerator levelGenerator = new LevelGenerator(NUM_GRID_LINES);
		this.grid = levelGenerator.getGrid();
	}

}
