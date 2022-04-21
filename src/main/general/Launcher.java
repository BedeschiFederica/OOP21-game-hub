package main.general;

import main.numericalbond.NumericalBondController;

public final class Launcher {

    private Launcher() {
    }

    public static void main(final String[] args) {
        new MainController(new NumericalBondController());
    }

}
