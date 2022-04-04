package model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cell {

	private int color;
	private Pair<Integer, Integer> position;
	private boolean flooded;
	
	private Cell topCell;
	private Cell bottomCell;
	private Cell rightCell;
	private Cell leftCell;
	
	public Cell(int color, Pair<Integer, Integer> position, Cell top, Cell bottom, Cell right, Cell left) {
		this.color = color;
		this.position = position;
		this.flooded = false;
		this.topCell = top;
		this.bottomCell = bottom;
		this.rightCell = right;
		this.leftCell = left;
	}
	
	void setColor(int newColor) {
		this.color = newColor;
	}
	
	void flood() {
		this.flooded = true;
	}
	
	int getColor() {
		return this.color;
	}
	
	boolean isFlooded() {
		return this.flooded;
	}
	
	Pair<Integer, Integer> getPosition() {
		return this.position;
	}
	
	List<Cell> getAdjacentCells() {
		return new LinkedList<>(Arrays.asList(topCell, bottomCell, rightCell, leftCell));
	}

}
