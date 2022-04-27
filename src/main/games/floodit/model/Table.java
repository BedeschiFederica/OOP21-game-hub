package main.games.floodit.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * Describes the table of the game, contains cells.
 */
public class Table {

    private final int numOfColors;
    private final int boardSize;
    private final List<Colors> selectedColors;
    private final List<Cell> board;

    public Table(final int tSize, final int colorsNumber, final List<Colors> selectedColors) {
        this.numOfColors = colorsNumber;
        this.boardSize = tSize;
        this.selectedColors = selectedColors;
        this.board = new LinkedList<>();
    }

    /**
     * Generates the table.
     */
    public void generateTable() {
        final Random rand = new Random();
        final List<Colors> colorMap = new LinkedList<>();

        int chosenColor;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                chosenColor = rand.nextInt(numOfColors);
                board.add(new Cell(selectedColors.get(chosenColor), new Pair<>(i, j)));
                colorMap.add(selectedColors.get(chosenColor));
            }
        }

        // checks if the table contains all the requested colors
        int pos;
        int color;
        while (!colorMap.containsAll(selectedColors)) {
            pos = rand.nextInt(board.size());
            color = rand.nextInt(numOfColors);
            board.get(pos).setColor(selectedColors.get(color));
            colorMap.remove(pos);
            colorMap.add(pos, selectedColors.get(color));
        }

        // Sets the adjacencies for all of the cells
        for (int k = 0; k < board.size(); k++) {
            setAdjacencies(k);
        }

    }

    /**
     * Finds and sets the adjacent cells.
     * 
     * @param cellPosition The position of the cell.
     */
    private void setAdjacencies(final int cellPosition) {
        Cell top = null;
        Cell bottom = null;
        Cell right = null;
        Cell left = null;

        if ((cellPosition - boardSize) >= 0) {
            top = board.get(cellPosition - boardSize);
        }

        if ((cellPosition + boardSize) < board.size()) {
            bottom = board.get(cellPosition + boardSize);
        }

        if ((cellPosition + 1) < board.size() && board.get(cellPosition + 1).getPosition().getY() != 0) {
            right = board.get(cellPosition + 1);
        }

        if ((cellPosition - 1) >= 0 && board.get(cellPosition - 1).getPosition().getY() != boardSize - 1) {
            left = board.get(cellPosition - 1);
        }

        board.get(cellPosition).setAdjacentCells(top, bottom, right, left);
    }

    /**
     * Gets the cell at the specified position.
     * 
     * @param x X position of the cell.
     * @param y Y position of the cell.
     * @return the cell at position (x,y).
     */
    public Cell getCell(final int x, final int y) {
        final List<Cell> requestedCell = board.stream()
                .filter(cell -> cell.getPosition().equals(new Pair<Integer, Integer>(x, y)))
                .collect(Collectors.toList());
        if (requestedCell.isEmpty()) {
            return null;
        }
        return requestedCell.get(0);
    }

    /**
     * @return All of the cells contained in the table.
     */
    public List<Cell> getAllCells() {
        return this.board;
    }

    /**
     * @return The board rows number.
     */
    public int getBoardSize() { 
        return this.boardSize;
    }
}
