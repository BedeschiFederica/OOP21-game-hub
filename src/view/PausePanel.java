package view;

import model.Colors;

import javax.swing.*;
import javax.swing.border.EtchedBorder;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PausePanel extends JPanel{

	private final JPanel mainPanel;
	private final CardLayout mainLayout;
	
	public PausePanel(JPanel mainPanel, CardLayout mLayout) {
		this.mainPanel = mainPanel;
		this.mainLayout = mLayout;
		this.setBackground(Colors.LIGHT_BLUE.getActualColor());
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		JButton btnRestart = new JButton("New Game");
		btnRestart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(mainPanel, "1");
			}
		});
		btnRestart.setBackground(Colors.YELLOW.getActualColor());
		btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRestart.setBorder(BorderFactory.createRaisedBevelBorder());
		GridBagConstraints restartConstr = new GridBagConstraints();
		restartConstr.fill = GridBagConstraints.BOTH;
		restartConstr.insets = new Insets(100, 100, 20, 100);
		restartConstr.gridx = 1;
		restartConstr.gridy = 1;
		restartConstr.weightx = 1;
		restartConstr.weighty = 1;
		add(btnRestart, restartConstr);
		
		JButton btnResume = new JButton("Resume");
		btnResume.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				mainLayout.show(mainPanel, "2");
			}
		});
		btnResume.setBackground(Colors.YELLOW.getActualColor());
		btnResume.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnResume.setBorder(BorderFactory.createRaisedBevelBorder());
		GridBagConstraints resumeConstr = new GridBagConstraints();
		resumeConstr.fill = GridBagConstraints.BOTH;
		resumeConstr.insets = new Insets(20, 100, 20, 100);
		resumeConstr.gridx = 1;
		resumeConstr.gridy = 2;
		resumeConstr.weightx = 1;
		resumeConstr.weighty = 1;
		add(btnResume, resumeConstr);
		
		JButton btnRules = new JButton("Rules");
		btnRules.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnRules.setBackground(Colors.YELLOW.getActualColor());
		btnRules.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnRules.setBorder(BorderFactory.createRaisedBevelBorder());
		GridBagConstraints rulesConstr = new GridBagConstraints();
		rulesConstr.fill = GridBagConstraints.BOTH;
		rulesConstr.insets = new Insets(20, 100, 20, 100);
		rulesConstr.gridx = 1;
		rulesConstr.gridy = 3;
		rulesConstr.weightx = 1;
		rulesConstr.weighty = 1;
		add(btnRules, rulesConstr);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setBackground(Colors.YELLOW.getActualColor());
		btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
		btnExit.setBorder(BorderFactory.createRaisedBevelBorder());
		GridBagConstraints exitConstr = new GridBagConstraints();
		exitConstr.fill = GridBagConstraints.BOTH;
		exitConstr.gridx = 1;
		exitConstr.gridy = 4;
		exitConstr.insets = new Insets(20, 100, 100, 100);
		exitConstr.weightx = 1;
		exitConstr.weighty = 1;
		add(btnExit, exitConstr);
	}
}
