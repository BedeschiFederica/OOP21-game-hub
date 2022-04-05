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
	
	//private final int numOfCells;
	private final int numOfColors;
	private final int tableSize;
	//private final int currentColor;
	private final List<Cell> table;
	//private final List<Pair<Integer,Integer>> mainPuddle;
	//private final List<List<Pair<Integer,Integer>>> allPuddles;
	private final List<Colors> selectedColors; 
	
	public FloodItController(int tSize, int colorsNumber){
		//this.numOfCells = tSize*tSize;
		this.tableSize = tSize;
		this.numOfColors = colorsNumber;
		//this.currentColor = 0;
		this.table =  new LinkedList<>();
		//this.allPuddles =  new LinkedList<>();
		this.selectedColors = Colors.getRandomColors(numOfColors);
		
		generateTable();
		//findMainPuddle(this.mainPuddle.get(0));
		//this.mainPuddle =  new LinkedList<>(Arrays.asList(this.table.get(0)));
	}

	private void generateTable() {
		
		Random rand = new Random();
		List<Colors> colorMap = new LinkedList<>();
		
		for (int i = 0; i < tableSize; i++) {
			for (int j = 0; j < tableSize; j++) {
				int chosenColor = rand.nextInt(numOfColors);
				table.add(new Cell(selectedColors.get(chosenColor), new Pair<>(i,j)));
				colorMap.add(selectedColors.get(chosenColor));
			}
		}
		
		//selectedColors.forEach(col -> {
		//	if (!colorMap.contains(col)) {
		//		
		//	}
		//	});
		
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
	
	private void setAdjacencies(int cellPosition) {
		Cell top = null;
		Cell bottom = null;
		Cell right = null;
		Cell left = null;
		
		if ((cellPosition - tableSize) >= 0) {
			top = table.get(cellPosition - tableSize);
		}
		
		if ((cellPosition + tableSize) < table.size()) {
			bottom = table.get(cellPosition + tableSize);
		}
		
		if ((cellPosition + 1) < table.size()) {
			if (table.get(cellPosition + 1).getPosition().getY() != 0) {
				right = table.get(cellPosition + 1);
			}
		}
		
		if ((cellPosition - 1) >= 0) {
			if (table.get(cellPosition - 1).getPosition().getY() != (tableSize - 1)) {
				left = table.get(cellPosition - 1);
			}
		}
		
		table.get(cellPosition).setAdjacentCells(top, bottom, right, left);
	}
	
}
