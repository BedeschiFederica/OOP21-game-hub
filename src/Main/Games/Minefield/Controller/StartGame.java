package Main.Games.Minefield.Controller;

import Main.Games.Minefield.Model.*;
import Main.Games.Minefield.View.*;

public class StartGame {

    public static final int GRIDSIZE = 4;
    public static final int MINES = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);

    private Handler handler = new Handler();

    public StartGame() {
        new ViewField( GRIDSIZE,"Minefield - " ,this, handler);
    }

    public static void main(String[] args) {
        new StartGame();
        
    }

}