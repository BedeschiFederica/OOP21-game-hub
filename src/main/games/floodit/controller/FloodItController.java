package main.games.floodit.controller;

import java.util.LinkedList;
import java.util.List;

import main.games.floodit.model.Cell;
import main.games.floodit.model.Colors;
import main.games.floodit.model.FloodItModel;
import main.games.floodit.model.MaxMovesCounter;
import main.games.floodit.model.MovesCounter;
import main.games.floodit.view.FloodItView;
import main.general.AbstractGameController;
import main.general.GameView;

public class FloodItController extends AbstractGameController {

    private static final String GAME_NAME = "Flood It";
    private final FloodItModel model;
    private final FloodItView view;
    private MovesCounter mCounter;

    public FloodItController() {
        this.model = new FloodItModel();
        this.view = new FloodItView(this, this.model);
        this.mCounter = null;
    }

    private void startingPuddleSetup() {
        // Sets up the starting puddle and color
        model.getMainPuddle().clear();
        model.getTable().getCell(0, 0).flood();
        model.getMainPuddle().add(model.getTable().getCell(0, 0));
        model.setCurrentColor(model.getTable().getCell(0, 0).getColor());
        spreadPuddle(model.getMainPuddle(), model.getCurrentColor());
    }

    public void spreadPuddle(List<Cell> cellsToCheck, Colors currentColor) {
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

    private void updateFlooding() {
        model.getTable().getAllCells().forEach(c -> {
            if (c.isFlooded() && !model.getMainPuddle().contains(c)) {
                model.getMainPuddle().add(c);
            }
        });
    }

    private void changeMainPuddleColor(final Colors newColor) {
        model.getMainPuddle().forEach(c -> c.setColor(newColor));
    }

    public void onClick(final Cell clickedCell) {
        spreadPuddle(model.getMainPuddle(), clickedCell.getColor());
        updateFlooding();
        model.setCurrentColor(clickedCell.getColor());
        changeMainPuddleColor(clickedCell.getColor());
        model.incrementMoves();
        checkResult();
        updateView();
    }

    private void checkResult() {
        if (model.getMoves() > model.getMaxMoves()) {
            System.out.println("YOU LOST!");
        } else if (model.getMainPuddle().size() == model.getRowSize() * model.getRowSize()) {
            System.out.println("YOU WIN!");
            view.stop();
        }
    }

    public void newGame(final int size, final int colors) {
        model.clear();
        model.setTSize(size);
        model.setNumofColors(colors);
        model.setSelectedColors(Colors.getRandomColors(colors));
        model.setMaxMoves(mCounter.count());
        model.setTable();
        startingPuddleSetup();
        view.createGameboard();
        updateView();
    }

    public void updateView() {
        model.getMainPuddle().forEach(c -> {
            view.updateCellVisualization(c);
        });
        view.updateMovesVisualization(model.getMoves() + " / " + model.getMaxMoves());
    }

    public void setMCounter(MovesCounter newCounter) {
        this.mCounter = newCounter;
    }

    @Override
    public GameView getView() {
        return this.view;
    }

    @Override
    public String getGameName() {
        return GAME_NAME;
    }

    @Override
    public void startGame() {
        view.display();
    }

}
