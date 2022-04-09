package model;

import java.util.LinkedList;
import java.util.List;

public class FloodItModel {

	private final int numOfColors;
	private final int rowSize;
	private Colors currentColor;
	private final Table table;
	private final List<Cell> mainPuddle;
	private final List<Colors> selectedColors;
	
	public FloodItModel(int tSize, int colorsNumber, List<Colors> selectedColors){
		this.numOfColors = colorsNumber;
		this.rowSize = tSize;
		this.currentColor = null;
		this.table =  new Table(tSize, colorsNumber, selectedColors);
		this.mainPuddle =  new LinkedList<>();
		this.selectedColors = selectedColors;
	}
	
	public void setCurrentColor(Colors newColor) {
		this.currentColor = newColor;
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
	
	public List<Cell> getMainPuddle() {
		return this.mainPuddle;
	}
	
	public List<Colors> getSelectedColors() {
		return this.selectedColors;
	}
}
