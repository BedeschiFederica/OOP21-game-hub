package main.games.floodit.controller;

import main.dashboard.view.InputPanel;
import main.games.floodit.model.Cell;
import main.games.floodit.model.Colors;
import main.games.floodit.model.FloodItModel;
import main.games.floodit.model.MovesCounter;
import main.games.floodit.view.FloodItView;
import main.general.AbstractGameController;
import main.general.GameView;

import java.util.LinkedList;
import java.util.List;

/**
 * The Flood It's game controller.
 */
public class FloodItController extends AbstractGameController {

    private static final String GAME_NAME = "Flood It";
    private static final List<Integer> POSSIBLE_CELLS = List.of(5, 10, 15);
    private static final List<Integer> POSSIBLE_COLORS = List.of(4, 5, 6, 7, 8, 9, 10);
    private final FloodItModel model;
    private final FloodItView view;
    private MovesCounter mCounter;

    public FloodItController() {
        this.model = new FloodItModel();
        this.view = new FloodItView(this);
        this.mCounter = null;
    }

    // Sets up the starting puddle and color.
    private void startingPuddleSetup() {
        model.getMainPuddle().clear();
        model.getTable().getCell(0, 0).flood();
        model.getMainPuddle().add(model.getTable().getCell(0, 0));
        model.setCurrentColor(model.getTable().getCell(0, 0).getColor());
        spreadPuddle(model.getMainPuddle(), model.getCurrentColor());
    }

    /**
     * Spreads the puddle of main color through the game board.
     * 
     * @param cellsToCheck The adjacent cells at the main puddle of color.
     * @param currentColor The main color.
     */
    public void spreadPuddle(final List<Cell> cellsToCheck, final Colors currentColor) {
        final List<Cell> checkList = new LinkedList<>();

        cellsToCheck.forEach(cell -> {
            cell.getAdjacentCells().forEach(c -> {
                if (c != null && !c.isFlooded() && c.getColor().equals(currentColor)) {
                    c.flood();
                    checkList.add(c);
                }
            });
        });

        if (!checkList.isEmpty()) {
            spreadPuddle(checkList, currentColor);
        }
    }

    // Adds the flooded cells to the main puddle.
    private void updateFlooding() {
        model.getTable().getAllCells().forEach(c -> {
            if (c.isFlooded() && !model.getMainPuddle().contains(c)) {
                model.getMainPuddle().add(c);
            }
        });
    }

    // Changes the color of all the main puddle cells.
    private void changeMainPuddleColor(final Colors newColor) {
        model.getMainPuddle().forEach(c -> c.setColor(newColor));
    }

    /**
     * Handles what happens when a cell is clicked.
     * 
     * @param clickedCell The clicked cell.
     */
    public void onClick(final Cell clickedCell) {
        spreadPuddle(model.getMainPuddle(), clickedCell.getColor());
        updateFlooding();
        model.setCurrentColor(clickedCell.getColor());
        changeMainPuddleColor(clickedCell.getColor());
        model.incrementMoves();
        checkResult();
        updateView();
    }

    // Checks if the player won or not.
    private void checkResult() {
        if (model.getMoves() > model.getMaxMoves()) {
            System.out.println("YOU LOST!");
        } else if (model.getMainPuddle().size() == model.getRowSize() * model.getRowSize()) {
            System.out.println("YOU WIN!");
            view.stop();
        }
    }

    /**
     * Starts a new game.
     * 
     * @param inputs size and number of colors.
     */
    public void newGame(final int... inputs) {
        final int size = inputs[0];
        final int colors = inputs[1];
        model.clear();
        model.setTSize(size);
        model.setNumofColors(colors);
        model.setSelectedColors(Colors.getRandomColors(colors));
        model.setMaxMoves(mCounter.count());
        model.setTable();
        startingPuddleSetup();
        view.setGameTable(model.getTable());
        view.createGameboard();
        updateView();
    }

    /**
     * Updates the cells and moves visualization.
     */
    public void updateView() {
        model.getMainPuddle().forEach(c -> {
            view.updateCellVisualization(c);
        });
        view.updateMovesVisualization(model.getMoves() + " / " + model.getMaxMoves());
    }

    /**
     * Sets the maximum moves counter.
     * 
     * @param newCounter The moves counter.
     */
    public void setMCounter(final MovesCounter newCounter) {
        this.mCounter = newCounter;
    }

    /**
     * Gets the game view.
     */
    @Override
    public GameView getView() {
        return this.view;
    }

    /**
     * Gets the game name.
     */
    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    /**
     * Starts the game.
     */
    @Override
    public void startGame(final int... inputs) {
        view.display();
    }

    /**
     * Shows flood it start panel.
     */
    public void showStartPanel() {
        view.showStart();
    }

    /**
     * Gets the input panels needed.
     */
    @Override
    public List<InputPanel> getInputPanels() {
        return List.of(new InputPanel("Cells", POSSIBLE_CELLS), new InputPanel("Colors", POSSIBLE_COLORS));
    }

}
