package main.games.floodit.model;

import java.util.LinkedList;
import java.util.List;

public class FloodItModel {

    private int numOfColors;
    private int rowSize;
    private Colors currentColor;
    private Table table;
    private int moves;
    private int maxMoves;
    private final List<Cell> mainPuddle;
    private final List<Colors> selectedColors;

    public FloodItModel() {
        this.numOfColors = 0;
        this.rowSize = 0;
        this.currentColor = null;
        this.table = null;// new Table(tSize, colorsNumber, selectedColors);
        this.moves = 0;
        this.maxMoves = 0;
        this.mainPuddle = new LinkedList<>();
        this.selectedColors = new LinkedList<>();
    }

    public void setCurrentColor(Colors newColor) {
        this.currentColor = newColor;
    }

    public void setTSize(int size) {
        this.rowSize = size;
    }

    public void setNumofColors(int colorsNum) {
        this.numOfColors = colorsNum;
    }

    public void setTable() {
        this.table = new Table(rowSize, numOfColors, selectedColors);
    }

    public void setMaxMoves(int mMoves) {
        this.maxMoves = mMoves;
    }

    public void setSelectedColors(List<Colors> newColors) {
        this.selectedColors.clear();
        this.selectedColors.addAll(newColors);
    }

    public void incrementMoves() {
        this.moves++;
    }

    public int getNumOfColors() {
        return this.numOfColors;
    }

    public int getRowSize() {
        return this.rowSize;
    }

    public Colors getCurrentColor() {
        return this.currentColor;
    }

    public Table getTable() {
        return this.table;
    }

    public int getMoves() {
        return this.moves;
    }

    public int getMaxMoves() {
        return this.maxMoves;
    }

    public List<Cell> getMainPuddle() {
        return this.mainPuddle;
    }

    public List<Colors> getSelectedColors() {
        return this.selectedColors;
    }

    public void clear() {
        this.numOfColors = 0;
        this.rowSize = 0;
        this.currentColor = null;
        this.table = null;
        this.moves = 0;
        this.maxMoves = 0;
        this.mainPuddle.clear();
        this.selectedColors.clear();
        ;
    }
}
