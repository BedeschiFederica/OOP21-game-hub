package main.games.minefield.controller;


import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;

public class StartGame {

    /**
     * the GRINDSIZE is needed for knowing the size of the table.
     */
    public static final int GRIDSIZE = 4;
    /**
     * the MINES is needed for knowing how many mines there are.
     */
    public static final int MINES = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);

    private Handler handler = new Handler();

    public StartGame() {
        new ViewField(GRIDSIZE, "Minefield - ", this, handler);
    }

    public static void main(final String[] args) {
        new StartGame();
    }

}
