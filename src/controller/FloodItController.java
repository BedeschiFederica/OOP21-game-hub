package controller;

import java.util.LinkedList;
import java.util.List;

import model.Cell;
import model.Colors;
import model.FloodItModel;
import view.FloodItGUI;

public class FloodItController {
	
	private static final int MAX_MOVES_5 = 16;
	private static final int MAX_MOVES_10 = 28;
	private static final int MAX_MOVES_15 = 40;
	private final FloodItModel model;
	private final FloodItGUI view;
	
	public FloodItController(){
		this.model = new FloodItModel();
		this.view = new FloodItGUI(this, this.model);
		view.display();
	}

	private void startingPuddleSetup() {
		// Sets up the starting puddle and color
		model.getMainPuddle().clear();
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
			if (c.isFlooded() && !model.getMainPuddle().contains(c)) {
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
		model.incrementMoves();
		checkResult();
		updateView();
	}
	
	private int findMaxMoves(int size, int colorsNum) {
		switch(size) {
		case 5:
			return MAX_MOVES_5;
		case 10:
			return MAX_MOVES_10;
		case 15:
			return MAX_MOVES_15;
		default: 
			return 0;
		}
	}
	
	private void checkResult() {
		if (model.getMoves() > model.getMaxMoves()) {
			System.out.println("YOU LOST!");
		} else if ( model.getMainPuddle().size() == (model.getRowSize() * model.getRowSize()) ) {
			System.out.println("YOU WIN!");
			view.stop();
		}
	}
	
	public void newGame(int size, int colors) {
		model.clear();
		model.setTSize(size);
		model.setNumofColors(colors);
		model.setSelectedColors(Colors.getRandomColors(colors));
		model.setMaxMoves(findMaxMoves(size, colors));
		model.setTable();
		startingPuddleSetup();
		view.createGameboard();
		updateView();
	}
	
	public void updateView() {
		model.getMainPuddle().forEach(c -> {
    		view.updateCellVisualization(c);
    	});
		view.updateMovesVisualization();
    }
	
}
