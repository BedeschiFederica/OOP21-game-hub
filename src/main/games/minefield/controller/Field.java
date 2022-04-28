package main.games.minefield.controller;

import main.games.minefield.model.Handler;
import main.games.minefield.view.ViewField;

import javax.swing.JPanel;
import java.awt.GridLayout;
import java.util.ArrayList;

public class Field extends JPanel {

    private static final long serialVersionUID = 1787913230237933232L;
    //needed to draw the table
    private int grid = ViewField.getGridSize() * ViewField.getGridSize();
    //variable neede to know if a cell is picked to have a mine or not
    private boolean mine = false;
    //array that has the position of all the mines
    private ArrayList<Integer> mines = new ArrayList<Integer>();
    /**
     * array that has the position of all the cells.
    */
    private static ArrayList<Cell> cell = new ArrayList<Cell>();

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
    public void createCells(final Handler handler) {
        for (int i = 1; i <= ViewField.getMines(); i++) {
            while (!mine) {
                int minePosition = (int) (Math.random() * grid);
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

    public static ArrayList<Cell> getCell() {
        return cell;
    }

    public static void setCell(final ArrayList<Cell> cell) {
        Field.cell = cell;
    }
}
