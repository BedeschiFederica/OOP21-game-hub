package controller;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Cell;
import model.Colors;
import model.Pair;

public class FloodItController {
	
	private final int numOfColors;
	private final int boardSize;
	private Colors currentColor;
	private final List<Cell> table;
	private final List<Cell> mainPuddle;
	private final List<Colors> selectedColors; 
	
	public FloodItController(int tSize, int colorsNumber){
		this.boardSize = tSize;
		this.numOfColors = colorsNumber;
		this.table =  new LinkedList<>();
		this.mainPuddle =  new LinkedList<>();
		this.selectedColors = Colors.getRandomColors(numOfColors);
		
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
		
		for (int k = 0; k < table.size(); k++) {
			setAdjacencies(k);
		}
		
		table.get(0).flood();
		mainPuddle.add(table.get(0));
		currentColor = table.get(0).getColor();
		spreadPuddle(this.mainPuddle);
	}
	
	
	/*
	private void findMainPuddle(int clicked) {
		//if(this.table.get(clicked + 1)) {
			
		//}
	}
	
	private List<Integer> findPuddleBorders() {
		for (int cell : mainPuddle) {
			if(mainPuddle.get(mainPuddle.index)!= cell) {
				
			}
		}
		return null;
	}
	
	private void makePuddles() {
		this.table =  new LinkedList<>();
	}*/
	
	public void spreadPuddle(List<Cell> cellsToCheck) {
		List<Cell> checkList = new LinkedList<>();
		
		cellsToCheck.forEach(cell -> {
			cell.getAdjacentCells().forEach(c -> {
				if(c != null && !c.isFlooded() && c.getColor().equals(currentColor)) {
					c.flood();
					checkList.add(c);
				}
			});
		});
		
		spreadPuddle(checkList);
		
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
	
}
