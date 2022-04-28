package main.games.minefield.controller;


import java.util.List;

import main.dashboard.view.InputPanel;
import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;
import main.general.AbstractGameController;
import main.general.GameView;

public class MinefieldController extends AbstractGameController {

    /**
     * the GRINDSIZE is needed for knowing the size of the table.
     */
    public static final int GRIDSIZE = 8;
    /**
     * the NAME of the game.
    */
    public static final String NAME = "Minefield";
    private Handler handler = new Handler();
    private ViewField viewField;

    /**
     * @return the view of minefield
     */
    public GameView getView() {
        return viewField;
    }

    /**
     * @return the name of the game
     */
    public String getGameName() {
        return NAME;
    }
    /**
     * @return the list of inputs needed.
     */
    public List<InputPanel> getInputPanels() {
        return List.of(new InputPanel("Cells:", List.of(4, 5, 6, 7, 8, 9,10,11,12,13,14,15,16,17,18,19,20)), new InputPanel("Mines:", List.of(4, 5, 6, 7, 8, 9, 10)));
    }
    /**
     * @param inputs gives the game the inputs needed.
     */
    public void startGame(final int... inputs) {
        this.viewField = new ViewField(inputs[0], inputs[1], "Minefield - ", this, handler);
        new MinefieldController();
    }

}
