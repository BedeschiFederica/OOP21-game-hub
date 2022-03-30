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
	private final List<Pair<Integer,Integer>> table;  		//Pair<Position, Color code>
	private final List<Pair<Integer,Integer>> mainPuddle;
	private final List<List<Pair<Integer,Integer>>> allPuddles;
	
	public FloodItController(int tSize, int colorsNumber){
		this.numOfCells = tSize*tSize;
		this.numOfColors = colorsNumber;
		this.currentColor = 0;
		this.table =  new LinkedList<>();
		this.allPuddles =  new LinkedList<>();
		
		generateTable();
		//findMainPuddle(this.mainPuddle.get(0));
		this.mainPuddle =  new LinkedList<>(Arrays.asList(this.table.get(0)));
	}

	public void generateTable() {
		Random randNum = new Random();
		int newColor = randNum.nextInt(this.numOfColors);
																				// Valutare la scelta casuale dei colori in metodo a parte!!!
		for (int i = 0; i < this.numOfCells; i++) {
			this.table.add(new Pair<>(i, randNum.nextInt(this.numOfColors)));
		}
		
		//checks if the table contains all of the requested colors
		while (!containsSelectedColors()) {
			int position = randNum.nextInt(this.numOfCells);
			
			//checks if in the chosen position there is the only occurrence of that color
			int cont = 0;
			boolean sameColor = false;
			for (int j = 0; j < this.numOfCells; j++) {
				if (this.table.get(j).getY() == this.table.get(position).getY()) {
					cont++;
				}
				//if (this.table.get(j).getY() == newColor) {
				//	sameColor = true;
				//}
			}
			if (cont > 1) {
				this.table.remove(position);
				this.table.add(position, new Pair<>(position, newColor));
				cont = 0;
			}
		}
		this.table.forEach(i -> System.out.println(i));
	}
	
	private void findMainPuddle(int clicked) {
		//if(this.table.get(clicked + 1)) {
			
		//}
	}
	
	/*private List<Integer> findPuddleBorders() {
		for (int cell : mainPuddle) {
			if(mainPuddle.get(mainPuddle.index)!= cell) {
				
			}
		}
		return null;
	}
	
	private void makePuddles() {
		this.table =  new LinkedList<>();
	}*/
	
	private boolean containsSelectedColors() {
		List<Integer> temp = new LinkedList<>();
		
		for(int i = 0; i < this.numOfCells; i++) {
			if(!temp.contains(this.table.get(i).getY())) {
				temp.add(this.table.get(i).getY());
			}
		}
		System.out.println("\n" + (temp.size() == numOfColors));
		table.forEach(i -> System.out.println(i));
		return temp.size() == numOfColors;
	}
	
}

