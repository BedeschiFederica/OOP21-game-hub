package main.games.numericalbond.controller;

import main.games.numericalbond.utility.Position;
import main.general.GameController;

/**
 * Interface that represents a controller of the game Numerical bond.
 */
public interface NumericalBondController extends GameController {

    /**
     * Links the two blocks specified by the two given positions.
     * It manages both model and view.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     */
    void link(Position pos1, Position pos2);

}
