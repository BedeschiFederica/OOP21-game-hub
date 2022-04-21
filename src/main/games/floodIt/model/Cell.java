package main.games.floodIt.model;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Cell {

	private Colors color;
	private Pair<Integer, Integer> position;
	private boolean flooded;
	
	private Cell topCell;
	private Cell bottomCell;
	private Cell rightCell;
	private Cell leftCell;
	
	public Cell(Colors color, Pair<Integer, Integer> position) {
		this.color = color;
		this.position = position;
		this.flooded = false;
		this.topCell = null;
		this.bottomCell = null;
		this.rightCell = null;
		this.leftCell = null;
	}
	
	public void setColor(Colors newColor) {
		this.color = newColor;
	}
	
	public void flood() {
		this.flooded = true;
	}
	
	public void setAdjacentCells(Cell top, Cell bottom, Cell right, Cell left) {
		this.topCell = top;
		this.bottomCell = bottom;
		this.rightCell = right;
		this.leftCell = left;
	}
	
	public Colors getColor() {
		return this.color;
	}
	
	public boolean isFlooded() {
		return this.flooded;
	}
	
	public Pair<Integer, Integer> getPosition() {
		return this.position;
	}
	
	public List<Cell> getAdjacentCells() {
		return new LinkedList<>(Arrays.asList(topCell, bottomCell, rightCell, leftCell));
	}

}
