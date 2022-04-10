package view;

import javax.swing.*;

import controller.FloodItController;
import model.Cell;
import model.FloodItModel;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class FloodItGUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cellButtons = new ArrayList<>();
    private final Map<JButton, Cell> cellsMap;
    //private final FloodItController controller;
    private final FloodItModel model;
    
    public FloodItGUI(FloodItController controller, FloodItModel model) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        cellsMap = new HashMap<>();
        //this.controller = controller;
        this.model = model;
        
        JPanel panel = new JPanel(new GridLayout(model.getRowSize(), model.getRowSize()));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	var clickedCell = (JButton)e.getSource();
        	controller.onClick(cellsMap.get(clickedCell));
        };
                
        for (int i = 0; i < model.getRowSize(); i++){
            for (int j = 0; j < model.getRowSize(); j++){
                final JButton button = new JButton(" ");
                button.setBackground(model.getTable().getCell(i, j).getColor().getActualColor());
                button.addActionListener(al);
                this.cellButtons.add(button);
                this.cellsMap.put(button, model.getTable().getCell(i, j));
                panel.add(button);
            }
        }
    }
    
    public void display() {
    	final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.setSize(sw / 2, sw / 2);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
    
    public void updateView() {
    	cellButtons.forEach(b -> {
    		if (model.getMainPuddle().contains(cellsMap.get(b))) {
    			b.setBackground(model.getCurrentColor().getActualColor());
    		}
    	});
    }
}