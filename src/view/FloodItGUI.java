package view;

import javax.swing.*;

import controller.FloodItController;
import model.Cell;
import model.Colors;
import model.FloodItModel;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloodItGUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cellButtons = new ArrayList<>();
    private final Map<JButton, Cell> cellsMap;
    
    private final FloodItController controller;
    private final FloodItModel model;
    
    private final CardLayout layout;
    private final JPanel mainPanel;
    private final JPanel gamePanel;
    private final StartPanel startPanel;
    private final JPanel pausePanel;
    final JLabel lblMoves;
    
    public FloodItGUI(FloodItController controller, FloodItModel model) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        cellsMap = new HashMap<>();
        this.controller = controller;
        this.model = model;
        layout = new CardLayout();
        lblMoves = new JLabel();
        
        this.mainPanel = new JPanel(layout);
        this.getContentPane().add(mainPanel);
        
        this.startPanel = new StartPanel(mainPanel, layout, controller);
        this.gamePanel = new GamePanel(mainPanel, layout, controller, model, cellsMap, cellButtons);
        this.pausePanel = new PausePanel(mainPanel, layout);
        //pausePanel.setLayout(new BoxLayout(pausePanel,BoxLayout.PAGE_AXIS));
        //createGamePanel();
        //createPausePanel();
        mainPanel.add(startPanel, "1");
        mainPanel.add(gamePanel, "2");
        mainPanel.add(pausePanel, "3");
        
        layout.show(mainPanel, "1");
        
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
    	lblMoves.setText((model.getMoves() + " / " + model.getMaxMoves()));
    }
    
    public int getComboSize() {
    	return startPanel.getRowSize();
    }
    
    public int getComboColors() {
    	return startPanel.getColors();
    }
    
}