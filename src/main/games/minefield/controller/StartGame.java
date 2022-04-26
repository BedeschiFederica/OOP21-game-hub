package main.games.minefield.controller;

import main.games.minefield.model.*;
import main.games.minefield.view.*;

public class StartGame {

    public static final int GRIDSIZE = 4;
    public static final int MINES = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);

    private Handler handler = new Handler();

    public StartGame() {
        new ViewField(GRIDSIZE, "Minefield - ", this, handler);
    }

    public static void main(String[] args) {
        new StartGame();
    }

}