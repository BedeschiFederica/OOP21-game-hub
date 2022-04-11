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
        this.startPanel = new JPanel(new FlowLayout());
        this.pausePanel = new JPanel(new GridLayout(model.getRowSize(), model.getRowSize()));
        createGamePanel();
        createStartPanel();
        createPausePanel();
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
    
    private void createStartPanel() {
        
    	startPanel.setBackground(Colors.LIGHT_BLUE.getActualColor());
    	final JComboBox<Integer> cmbCells = new JComboBox<>(new Integer[] {5, 10, 15});
    	final JComboBox<Integer> cmbColors = new JComboBox<>(new Integer[] {4, 5, 6, 7, 8, 9, 10});
    	
    	final JLabel lblTitle = new JLabel("F L O O D    I T");
    	lblTitle.setForeground(Colors.YELLOW.getActualColor());
    	lblTitle.setFont(new Font("Serif", Font.BOLD, 50));
    	lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
    	lblTitle.setVerticalAlignment(SwingConstants.CENTER);
    	
    	final JLabel lblCells = new JLabel("Cells:");
    	lblCells.setFont(new Font("Sans", Font.PLAIN, 25));
    	
    	final JLabel lblColors = new JLabel("Colors:");
    	lblColors.setFont(new Font("Sans", Font.PLAIN, 25));
    	
    	final JButton btnStart = new JButton("S T A R T");
    	btnStart.setSize(startPanel.getWidth()/4, startPanel.getHeight()/4);
    	btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				layout.show(mainPanel, "2");
			}
    	});
    	
    	startPanel.add(lblTitle);
    	startPanel.add(lblCells);
    	startPanel.add(cmbCells);
    	startPanel.add(lblColors);
    	startPanel.add(cmbColors);
    	startPanel.add(btnStart);
        
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
    	
    	pausePanel.add(btnRestart);
    	pausePanel.add(btnResume);
    	pausePanel.add(btnRules);
    	pausePanel.add(btnExit);
    }
    
}