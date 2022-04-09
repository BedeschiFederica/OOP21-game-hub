package controller;

import java.util.LinkedList;
import java.util.List;

import model.Cell;
import model.Colors;
import model.FloodItModel;
import view.FloodItGUI;

public class FloodItController {
	
	private final FloodItModel model;
	private final FloodItGUI view;
	
	public FloodItController(int tSize, int colorsNumber){
		List<Colors> selectedColors = Colors.getRandomColors(colorsNumber);
		
		this.model = new FloodItModel(tSize, colorsNumber, selectedColors);
		this.view = new FloodItGUI(tSize, selectedColors);
		
		startingPuddleSetUp();
	}

	private void startingPuddleSetUp() {
		// Sets up the starting puddle and color
		model.getTable().getCells().get(0).flood();
		model.getMainPuddle().add(model.getTable().getCells().get(0));
		model.setCurrentColor(model.getTable().getCells().get(0).getColor());
		spreadPuddle(model.getMainPuddle(), model.getCurrentColor());
	}
	
	public void spreadPuddle(List<Cell> cellsToCheck, Colors currentColor) {
		List<Cell> checkList = new LinkedList<>();
		
		cellsToCheck.forEach(cell -> {
			cell.getAdjacentCells().forEach(c -> {
				if(c != null && !c.isFlooded() && c.getColor().equals(currentColor)) {
					c.flood();
					checkList.add(c);
				}
			});
		});
		
		if(!checkList.isEmpty()) {
			spreadPuddle(checkList, currentColor);
		}
	}
	
}
