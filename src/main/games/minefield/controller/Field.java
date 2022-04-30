package main.games.minefield.controller;

import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;

public class Field extends JPanel {

    private static final long serialVersionUID = 1787913230237933232L;
    //needed to draw the table
    private final int grid = ViewField.getGridSize() * ViewField.getGridSize();
    //variable needed to know if a cell is picked to have a mine or not
    private boolean mine;
    //array that has the position of all the mines
    private final List<Integer> mines = new ArrayList<>();
    /**
     * array that has the position of all the cells.
    */
    private static List<Cell> cell = new ArrayList<>();

    public Field(final GridLayout grid, final Handler handler) {
        super(grid);
        cell.clear();
        createCells(handler);
        newCells();
    }

    /**
     * method that create the table needed and that saves the position of the mines.
     * @param handler that makes all the check for the game.
    */
    public final void createCells(final Handler handler) {
        for (int i = 1; i <= ViewField.getMines(); i++) {
            while (!mine) {
                final int minePosition = (int) (Math.random() * grid);
                if (!mines.contains(minePosition)) {
                    mines.add(minePosition);
                    mine = true;
                }
            }
            mine = false;
        }

        for (int i = 0; i < grid; i++) {
            if (mines.contains(i)) {
                getCell().add(new Cell(1, i, false, false, handler));
            } else if (i % ViewField.getGridSize() == 0) {
                if (mines.contains(i - ViewField.getGridSize()) 
                        || mines.contains(i - ViewField.getGridSize() + 1) 
                        || mines.contains(i + 1) 
                        || mines.contains(i + ViewField.getGridSize()) 
                        || mines.contains(i + ViewField.getGridSize() + 1)) {
                    getCell().add(new Cell(2, i, false, false, handler));
                } else {
                    getCell().add(new Cell(0, i, false, false, handler));
                }
            } else if (i % ViewField.getGridSize() == ViewField.getGridSize() - 1) {
                if (mines.contains(i - ViewField.getGridSize() - 1) 
                        || mines.contains(i - ViewField.getGridSize()) 
                        || mines.contains(i - 1)
                        || mines.contains(i + ViewField.getGridSize() - 1)
                        || mines.contains(i + ViewField.getGridSize())) {
                    getCell().add(new Cell(2, i, false, false, handler));
                } else {
                    getCell().add(new Cell(0, i, false, false, handler));
                }
            } else {
                if (mines.contains(i - ViewField.getGridSize() - 1) 
                        || mines.contains(i - ViewField.getGridSize()) 
                        || mines.contains(i - ViewField.getGridSize() + 1) 
                        || mines.contains(i - 1) 
                        || mines.contains(i + 1) 
                        || mines.contains(i + ViewField.getGridSize() - 1) 
                        || mines.contains(i + ViewField.getGridSize()) 
                        || mines.contains(i + ViewField.getGridSize() + 1)) {
                    getCell().add(new Cell(2, i, false, false, handler));
                } else {
                    getCell().add(new Cell(0, i, false, false, handler));
                }
            }
        }
    }

    /**
     * adding the cells to the field.
     */
    private void newCells() {
     for (int i = 0; i < getCell().size(); i++) {
            add(getCell().get(i));
        }
    }

    public static List<Cell> getCell() {
        return cell;
    }

    public static void setCell(final List<Cell> cell) {
        Field.cell = cell;
    }
}
