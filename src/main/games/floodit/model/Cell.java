package main.games.floodit.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a single cell and contains information about color, position,
 * adjacent cells and whether the cell is flooded or not.
 */
public class Cell {

    private Colors color;
    private final Pair<Integer, Integer> position;
    private boolean flooded;

    private Cell topCell;
    private Cell bottomCell;
    private Cell rightCell;
    private Cell leftCell;

    public Cell(final Colors color, final Pair<Integer, Integer> position) {
        this.color = color;
        this.position = position;
        this.flooded = false;
        this.topCell = null;
        this.bottomCell = null;
        this.rightCell = null;
        this.leftCell = null;
    }

    /**
     * Sets the cell's color.
     * 
     * @param newColor The color assigned to the cell.
     */
    public void setColor(final Colors newColor) {
        this.color = newColor;
    }

    /**
     * Floods the cell.
     */
    public void flood() {
        this.flooded = true;
    }

    /**
     * Sets the adjacent cells.
     * 
     * @param top The cell on top of the current one.
     * @param bottom The cell at the bottom of the current one.
     * @param right The cell on the right of the current one.
     * @param left The cell on the left of the current one.
     */
    public void setAdjacentCells(final Cell top, final Cell bottom, final Cell right, final Cell left) {
        this.topCell = top;
        this.bottomCell = bottom;
        this.rightCell = right;
        this.leftCell = left;
    }

    /**
     * Gets the color of the cell.
     * 
     * @return The cell's color.
     */
    public Colors getColor() {
        return this.color;
    }

    /**
     * Tells if the cell is flooded of not.
     * 
     * @return True if the cell is flooded, False if the cell isn't flooded.
     */
    public boolean isFlooded() {
        return this.flooded;
    }

    /**
     * Gets the position of the cell.
     * 
     * @return The cell's position.
     */
    public Pair<Integer, Integer> getPosition() {
        return this.position;
    }

    /**
     * Gets the adjacent cells.
     * @return A list of the adjacent cells.
     */
    public List<Cell> getAdjacentCells() {
        return new LinkedList<>(Arrays.asList(topCell, bottomCell, rightCell, leftCell));
    }

}
