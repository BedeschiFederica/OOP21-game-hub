package controller;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import model.Pair;

public class FloodItController {
	
	private final int numOfCells;
	private final int numOfColors;
	private final int currentColor;
	private final List<Pair<Integer,Integer>> table;
	private final List<Pair<Integer,Integer>> mainPuddle;
	private final List<List<Pair<Integer,Integer>>> allPuddles;
	
	public FloodItController(int tSize, int colorsNumber){
		this.numOfCells = tSize*tSize;
		this.numOfColors = colorsNumber;
		this.currentColor = 0;
		this.table =  new LinkedList<>();
		this.mainPuddle =  new LinkedList<>(Arrays.asList(this.table.get(0)));
		
		generateTable();
		findMainPuddle(this.mainPuddle.get(0));
	}

	public void generateTable() {
		Random randNum = new Random();
		List<Integer> possibleColors=  new LinkedList<>();  // Valutare la scelta casuale dei colori in metodo a parte!!!
		for(int j = 0; j < this.numOfColors; j++) {
			possibleColors.add(j);
		}
		
		for(int i = 0; i < this.numOfCells; i++) {
			this.table.add(randNum.nextInt(this.numOfColors));
		}
		
		//checks if the table contains all of the requested colors
		while(!this.table.containsAll(possibleColors)) {
			int position = randNum.nextInt(this.numOfCells);
			this.table.remove(position);
			this.table.add(position, randNum.nextInt(this.numOfColors));
		}
		this.table.forEach(i -> System.out.println(i));
	}
	
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
		
	}
	
}

