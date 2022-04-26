package main.games.minefield.controller;

import main.games.minefield.model.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Field extends JPanel {

    // needed to draw the table
    private int grid = StartGame.GRIDSIZE * StartGame.GRIDSIZE;
    // variable neede to know if a cell is picked to have a mine or not
    private boolean mine = false;
    // array that has the position of all the mines
    private ArrayList<Integer> mines = new ArrayList<Integer>();
    // array that has the position of all the cells
    public static ArrayList<Cell> cell = new ArrayList<Cell>();

    public Field(GridLayout g, Handler h) {
        super(g);
        createCells(h);
        newCells();
    }

    public void createCells(Handler h) {
        /**
         * method that create the table needed and that saves the position of the mines
         * 
         */
        for (int i = 1; i <= StartGame.MINES; i++) {
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
                cell.add(new Cell(1, i, false, false, h));
            } else if (i % StartGame.GRIDSIZE == 0) {
                if (mines.contains(i - StartGame.GRIDSIZE) || mines.contains(i - StartGame.GRIDSIZE + 1)
                        || mines.contains(i + 1) || mines.contains(i + StartGame.GRIDSIZE)
                        || mines.contains(i + StartGame.GRIDSIZE + 1)) {
                    cell.add(new Cell(2, i, false, false, h));
                } else {
                    cell.add(new Cell(0, i, false, false, h));
                }
            } else if (i % StartGame.GRIDSIZE == StartGame.GRIDSIZE - 1) {
                if (mines.contains(i - StartGame.GRIDSIZE - 1) || mines.contains(i - StartGame.GRIDSIZE)
                        || mines.contains(i - 1) || mines.contains(i + StartGame.GRIDSIZE - 1)
                        || mines.contains(i + StartGame.GRIDSIZE)) {
                    cell.add(new Cell(2, i, false, false, h));
                } else {
                    cell.add(new Cell(0, i, false, false, h));
                }
            } else {
                if (mines.contains(i - StartGame.GRIDSIZE - 1) || mines.contains(i - StartGame.GRIDSIZE)
                        || mines.contains(i - StartGame.GRIDSIZE + 1) || mines.contains(i - 1) || mines.contains(i + 1)
                        || mines.contains(i + StartGame.GRIDSIZE - 1) || mines.contains(i + StartGame.GRIDSIZE)
                        || mines.contains(i + StartGame.GRIDSIZE + 1)) {
                    cell.add(new Cell(2, i, false, false, h));
                } else {
                    cell.add(new Cell(0, i, false, false, h));
                }
            }
        }
    }

    private void newCells() {
        // addig the cell to the field
        for (int i = 0; i < cell.size(); i++) {
            add(cell.get(i));
        }
    }
}
