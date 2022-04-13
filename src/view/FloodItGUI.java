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
    private final JPanel startPanel;
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
        
        this.gamePanel = new JPanel(new BorderLayout());
        this.startPanel = new StartPanel(mainPanel, layout, controller);
        this.pausePanel = new PausePanel(mainPanel, layout);
        //pausePanel.setLayout(new BoxLayout(pausePanel,BoxLayout.PAGE_AXIS));
        createGamePanel();
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
    
    private void createGamePanel() {
    	final JPanel boardPanel = new JPanel(new GridLayout(model.getRowSize(), model.getRowSize()));
        
    	final JButton btnPause = new JButton("Pause");
    	btnPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainPanel, "3");
			}
    	});
    	
    	gamePanel.add(btnPause, BorderLayout.NORTH);
    	
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
                boardPanel.add(button);
            }
        }
        
        gamePanel.add(boardPanel, BorderLayout.CENTER);
        
        lblMoves.setText((model.getMoves() + " / " + model.getMaxMoves()));
        gamePanel.add(lblMoves, BorderLayout.SOUTH);
    }
    
    private void createPausePanel() {
        
    	final JButton btnRestart = new JButton("Restart");
    	final JButton btnResume = new JButton("Resume");
    	final JButton btnRules = new JButton("Rules");
    	final JButton btnExit = new JButton("Exit");
    	
    	btnResume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainPanel, "2");
			}
    	});
    	
    	pausePanel.add(btnRestart, BorderLayout.CENTER);
    	pausePanel.add(btnResume, BorderLayout.CENTER);
    	pausePanel.add(btnRules, BorderLayout.CENTER);
    	pausePanel.add(btnExit, BorderLayout.CENTER);
    }
    
}