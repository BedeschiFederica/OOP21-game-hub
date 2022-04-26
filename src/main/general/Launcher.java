package main.general;

import main.dashboard.controller.MainController;
import main.games.floodit.controller.FloodItController;
import main.games.numericalbond.controller.NumericalBondController;

public final class Launcher {

    private Launcher() {
    }

    public static void main(final String[] args) {
        new MainController(new NumericalBondController(), new FloodItController()); //, new MinefieldController();
    }

}
