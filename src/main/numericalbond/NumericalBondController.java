package main.numericalbond;

import main.general.GameController;
import main.numericalbond.model.NumericalBondGame;

public class NumericalBondController implements GameController{

	@Override
	public void start() {
		NumericalBondGame game = new NumericalBondGame();
	}

}
