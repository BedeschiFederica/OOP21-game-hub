package model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Table {

	private final int numOfColors;
	private final int boardSize;
	private final List<Colors> selectedColors;
	private final List<Cell> table;
	
	public Table(int tSize, int colorsNumber, List<Colors> selectedColors) {
		this.numOfColors = colorsNumber;
		this.boardSize = tSize;
		this.selectedColors = selectedColors;
		this.table = new LinkedList<>();
		
		generateTable();
	}
	
	private void generateTable() {
		Random rand = new Random();
		List<Colors> colorMap = new LinkedList<>();
		
		for (int i = 0; i < boardSize; i++) {
			for (int j = 0; j < boardSize; j++) {
				int chosenColor = rand.nextInt(numOfColors);
				table.add(new Cell(selectedColors.get(chosenColor), new Pair<>(i,j)));
				colorMap.add(selectedColors.get(chosenColor));
			}
		}
																				//si può ricercare anche facendo un foreach della lista di colori possibili
		//checks if the table contains all the requested colors
		while (!colorMap.containsAll(selectedColors)) {
			int pos = rand.nextInt(table.size());
			int color = rand.nextInt(numOfColors);
			table.get(pos).setColor(selectedColors.get(color));
			colorMap.remove(pos);
			colorMap.add(pos, selectedColors.get(color));
		}
		
		//Sets the adjacencies for all cells
		for (int k = 0; k < table.size(); k++) {
			setAdjacencies(k);
		}
		
	}
	
	private void setAdjacencies(int cellPosition) {
		Cell top = null;
		Cell bottom = null;
		Cell right = null;
		Cell left = null;
		
		if ((cellPosition - boardSize) >= 0) {
			top = table.get(cellPosition - boardSize);
		}
		
		if ((cellPosition + boardSize) < table.size()) {
			bottom = table.get(cellPosition + boardSize);
		}
		
		if ((cellPosition + 1) < table.size()) {
			if (table.get(cellPosition + 1).getPosition().getY() != 0) {
				right = table.get(cellPosition + 1);
			}
		}
		
		if ((cellPosition - 1) >= 0) {
			if (table.get(cellPosition - 1).getPosition().getY() != (boardSize - 1)) {
				left = table.get(cellPosition - 1);
			}
		}
		
		table.get(cellPosition).setAdjacentCells(top, bottom, right, left);
	}
	
	public List<Cell> getCells() {
		return this.table;
	}
}
