package view;

import javax.swing.*;

import controller.FloodItController;
import model.Colors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel{

	private static final long serialVersionUID = 1188589790801007209L;
	private final CardLayout mainLayout;
	
	private JComboBox<Integer> cmbColors;
	private JComboBox<Integer> cmbCells;

	public StartPanel(JPanel mainPanel, CardLayout mLayout, FloodItController controller) {
		this.mainLayout = mLayout;
		setBackground(Colors.LIGHT_BLUE.getActualColor());
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		JLabel lblTitle = new JLabel("Flood It!");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
    	lblTitle.setForeground(Colors.YELLOW.getActualColor());
		GridBagConstraints titleConstr = new GridBagConstraints();
		titleConstr.fill = GridBagConstraints.BOTH;
		titleConstr.insets = new Insets(0,0,5,0);
		titleConstr.gridwidth = 2;
		titleConstr.gridx = 1;
		titleConstr.gridy = 0;
		//titleConstr.weightx = 1;
		//titleConstr.weighty = 1;
		add(lblTitle, titleConstr);
		
		JLabel lblCells = new JLabel("Cells:");
		GridBagConstraints cellsConstr = new GridBagConstraints();
		cellsConstr.anchor = GridBagConstraints.EAST;
		cellsConstr.insets = new Insets(0,0,5,5);
		cellsConstr.gridx = 1;
		cellsConstr.gridy = 1;
		//cellsConstr.weightx = 1;
		//cellsConstr.weighty = 1;
		add(lblCells, cellsConstr);
		
		this.cmbCells = new JComboBox<>(new Integer[] {5, 10, 15});
		GridBagConstraints cellsComboConstr = new GridBagConstraints();
		cellsComboConstr.anchor = GridBagConstraints.WEST;
		cellsComboConstr.insets = new Insets(0,0,5,0);
		cellsComboConstr.gridx = 2;
		cellsComboConstr.gridy = 1;
		//cellsComboConstr.weightx = 1;
		//cellsComboConstr.weighty = 1;
		add(cmbCells, cellsComboConstr);
		
		JLabel lblColors = new JLabel("Colors:");
		GridBagConstraints colorsConstr = new GridBagConstraints();
		colorsConstr.anchor = GridBagConstraints.EAST;
		colorsConstr.insets = new Insets(0,0,5,5);
		colorsConstr.gridx = 1;
		colorsConstr.gridy = 2;
		//colorsConstr.weightx = 1;
		//colorsConstr.weighty = 1;
		add(lblColors, colorsConstr);
		
		this.cmbColors = new JComboBox<>(new Integer[] {4, 5, 6, 7, 8, 9, 10});
		GridBagConstraints colorsComboConstr = new GridBagConstraints();
		colorsComboConstr.anchor = GridBagConstraints.WEST;
		colorsComboConstr.insets = new Insets(0,0,5,0);
		colorsComboConstr.gridx = 2;
		colorsComboConstr.gridy = 2;
		//colorsComboConstr.weightx = 1;
		//colorsComboConstr.weighty = 1;
		add(cmbColors, colorsComboConstr);
		
		JButton btnStart = new StyledButton("START");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.newGame(getRowSize(), getColors());
				mainLayout.show(mainPanel, "2");
			}
		});
		GridBagConstraints startConstr = new GridBagConstraints();
		startConstr.fill = GridBagConstraints.VERTICAL;
		startConstr.gridwidth = 2;
		startConstr.gridx = 1;
		startConstr.gridy = 3;
		//startConstr.weightx = 1;
		//startConstr.weighty = 1;
		add(btnStart, startConstr);
	}
	
	public int getRowSize() {
    	return (int) cmbCells.getSelectedItem();
    }
    
    public int getColors() {
    	return (int) cmbColors.getSelectedItem();
    }
}
