package view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import controller.FloodItController;
import model.Cell;
import model.FloodItModel;

public class GamePanel extends JPanel {

	private static final long serialVersionUID = 5507460523553525591L;
	private final FloodItController controller;
	private final FloodItModel model;
	private final JPanel mainPanel;
	private final CardLayout mainLayout;
	private final JLabel lblMoves;
	private final Map<JButton, Cell> cellsMap;
	private final List<JButton> cellButtons;
	
	public GamePanel(JPanel mainPanel, CardLayout mLayout, FloodItController controller, FloodItModel model, Map<JButton, Cell> map, List<JButton> cellButtons) {
		this.controller = controller;
		this.model = model;
		this.mainPanel = mainPanel;
		this.mainLayout = mLayout;
		this.lblMoves = new JLabel();
		this.cellsMap = map;
		this.cellButtons = cellButtons;
		
		setLayout(new BorderLayout(0, 0));
		
		lblMoves.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoves.setText((model.getMoves() + " / " + model.getMaxMoves()));
	    add(lblMoves, BorderLayout.SOUTH);
	    
	    final JPanel topPanel = new JPanel();
	    add(topPanel, BorderLayout.NORTH);
	    
	    final JButton btnPause = new JButton("Menu");
	    btnPause.setHorizontalAlignment(SwingConstants.LEFT);
		btnPause.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(mainPanel, "3");
			}
		});
		topPanel.add(btnPause);
		
		final JLabel lblTitle = new JLabel("Flood It!");
		lblTitle.setFont(new Font("Tahome", Font.PLAIN, 20));
		topPanel.add(lblTitle);
		
		final JButton btnExit = new JButton("Exit");
		btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
		btnExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		topPanel.add(btnExit);
		
		final JPanel boardPanel = new JPanel(new GridLayout(model.getRowSize(), model.getRowSize()));
		
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
	    
	    add(boardPanel, BorderLayout.CENTER);
	    
	}
	
}
