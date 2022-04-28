package main.games.minefield.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.gamehub.model.AbstractGameController;
import main.gamehub.model.GameView;
import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;

public class MinefieldController extends AbstractGameController {

    /**
     * the NAME of the game.
    */
    public static final String NAME = "Minefield";
    private Handler handler = new Handler(this);
    private ViewField viewField;
    private static final List<Integer> POS_GRID = List.of(4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20);
    private static final List<Integer> POS_MINES = List.of(4, 5, 6, 7, 8, 9, 10);

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
    public Map<String, List<Integer>> getInputs() {
        final Map<String, List<Integer>> inputs = new HashMap<>();
        inputs.put("Cells", POS_GRID);
        inputs.put("Mines:", POS_MINES);
        return inputs;
    }
    /**
     * @param inputs gives the game the inputs needed.
     */
    public void startGame(final int... inputs) {
        this.viewField = new ViewField(inputs[0], inputs[1], "Minefield - ", this, handler);
    }
 }
