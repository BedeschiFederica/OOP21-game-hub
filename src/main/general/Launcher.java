package main.general;

import main.gamehub.controller.MainControllerImpl;
import main.games.floodit.controller.FloodItController;
import main.games.minefield.controller.MinefieldController;
import main.games.numericalbond.controller.NumericalBondControllerImpl;

/**
 * Class that represents the launcher of the application.
 */
public final class Launcher {

    private Launcher() {
    }

    /**
     * It launches the application.
     * @param args
     *          ignored
     */
    public static void main(final String[] args) {
        new MainControllerImpl(new NumericalBondControllerImpl(), new FloodItController(), new MinefieldController());
    }

}
