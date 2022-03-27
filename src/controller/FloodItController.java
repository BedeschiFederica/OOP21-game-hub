package controller;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import view.FloodItGUI;

public class FloodItController {
	
	private final int numOfCells;
	private final List<Integer> table;
	
	public FloodItController(int tSize, int colorsNumber){
		this.numOfCells = tSize*tSize;
		this.table =  new LinkedList<>();
		generateTable(colorsNumber);
	}

	public void generateTable(int nColors) {
		Random randNum = new Random();
		List<Integer> possibleColors=  new LinkedList<>();
		for(int j = 0; j < nColors; j++) {
			possibleColors.add(j);
		}
		
		for(int i = 0; i < numOfCells; i++) {
			table.add(randNum.nextInt(nColors));
		}
		while(!table.containsAll(possibleColors)) {
			int position = randNum.nextInt(numOfCells);
			table.remove(position);
			table.add(position, randNum.nextInt(nColors));
		}
		table.forEach(i -> System.out.println(i));
	}
	
}

