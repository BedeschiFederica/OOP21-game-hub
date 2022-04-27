package main.games.minefield.controller;


import java.util.List;

import main.dashboard.view.InputPanel;
import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;
import main.general.AbstractGameController;
import main.general.GameView;

public class MinefieldController extends AbstractGameController{

    /**
     * the GRINDSIZE is needed for knowing the size of the table.
     */
    public static final int GRIDSIZE = 8 ;
    /**
     * the MINES is needed for knowing how many mines there are.
     */
    public static final int MINES = (int) Math.round(GRIDSIZE * GRIDSIZE * .1);
    /**
     * the NAME of the game.
    */
    public static final String NAME = "Minefield";
    private Handler handler = new Handler();
    private final ViewField viewField = new ViewField(GRIDSIZE, "Minefield - ", this, handler);

    @Override
    public GameView getView() {
        // TODO Auto-generated method stub
        return viewField;
    }

    @Override
    public String getGameName() {
        return NAME;
    }
    @Override
    public List<InputPanel> getInputPanels() {
        return List.of(new InputPanel("Cells:", List.of(4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20)), new InputPanel("Mines:", List.of(4, 5, 6, 7, 8, 9, 10)));
    }
    @Override
    public void startGame(final int... inputs) {
        new ViewField(GRIDSIZE, "Minefield - ", this, handler);
        new MinefieldController();
    }

}
