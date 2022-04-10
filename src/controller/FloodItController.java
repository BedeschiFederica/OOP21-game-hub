package controller;

import java.util.LinkedList;
import java.util.List;
import java.awt.event.ActionListener;

import model.Cell;
import model.Colors;
import model.FloodItModel;
import view.FloodItGUI;

public class FloodItController {
	
	private final FloodItModel model;
	private final FloodItGUI view;
	private final ActionListener onClick;
	
	public FloodItController(int tSize, int maxMoves, int colorsNumber){
		List<Colors> selectedColors = Colors.getRandomColors(colorsNumber);
		
		this.model = new FloodItModel(tSize, colorsNumber, maxMoves, selectedColors);
		this.onClick = e -> {
        	model.setCurrentColor(null);
        };
		this.view = new FloodItGUI(this, this.model);
		
		startingPuddleSetUp();
		view.display();
	}

	private void startingPuddleSetUp() {
		// Sets up the starting puddle and color
		model.getTable().getCell(0, 0).flood();
		model.getMainPuddle().add(model.getTable().getCell(0, 0));
		model.setCurrentColor(model.getTable().getCell(0, 0).getColor());
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
	
	private void updateFlooding() {
		model.getTable().getAllCells().forEach(c -> {
			if (c.isFlooded()) {
				model.getMainPuddle().add(c);
			}
		});
	}
	
	private void changeMainPuddleColor(Colors newColor) {
		model.getMainPuddle().forEach(c -> c.setColor(newColor));
	}
	
	public void onClick(Cell clickedCell) {
		spreadPuddle(model.getMainPuddle(), clickedCell.getColor());
		updateFlooding();
		model.setCurrentColor(clickedCell.getColor());
		changeMainPuddleColor(clickedCell.getColor());
		view.updateView();
	}
	
}
