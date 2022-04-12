package view;

import javax.swing.*;

import model.Colors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartPanel extends JPanel{

	private static final long serialVersionUID = 1188589790801007209L;
	private final JPanel mainPanel;
	private final CardLayout mainLayout;

	public StartPanel(JPanel mainPanel, CardLayout mLayout) {
		this.mainPanel = mainPanel;
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
		
		JComboBox<Integer> cmbCells = new JComboBox<>(new Integer[] {5, 10, 15});
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
		
		JComboBox<Integer> cmbColors = new JComboBox<>(new Integer[] {4, 5, 6, 7, 8, 9, 10});
		GridBagConstraints colorsComboConstr = new GridBagConstraints();
		colorsComboConstr.anchor = GridBagConstraints.WEST;
		colorsComboConstr.insets = new Insets(0,0,5,0);
		colorsComboConstr.gridx = 2;
		colorsComboConstr.gridy = 2;
		//colorsComboConstr.weightx = 1;
		//colorsComboConstr.weighty = 1;
		add(cmbColors, colorsComboConstr);
		
		JButton btnStart = new JButton("START");
		btnStart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
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
}
