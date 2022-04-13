package view;

import model.Colors;

import javax.swing.*;
import java.awt.*;

public class PausePanel extends JPanel{

	private final JPanel mainPanel;
	private final CardLayout mainLayout;
	
	public PausePanel(JPanel mainPanel, CardLayout mLayout) {
		this.mainPanel = mainPanel;
		this.mainLayout = mLayout;
		GridBagLayout layout = new GridBagLayout();
		setLayout(layout);
		
		JButton btnRestart = new JButton("Restart");
		btnRestart.setBackground(Colors.YELLOW.getActualColor());
		GridBagConstraints restartConstr = new GridBagConstraints();
		restartConstr.fill = GridBagConstraints.BOTH;
		restartConstr.insets = new Insets(0, 0, 5, 0);
		restartConstr.anchor = GridBagConstraints.NORTH;
		restartConstr.gridx = 1;
		restartConstr.gridy = 1;
		add(btnRestart, restartConstr);
		
		JButton btnResume = new JButton("Resume");
		btnResume.setBackground(Colors.YELLOW.getActualColor());
		GridBagConstraints resumeConstr = new GridBagConstraints();
		resumeConstr.fill = GridBagConstraints.BOTH;
		resumeConstr.insets = new Insets(0, 0, 5, 0);
		resumeConstr.anchor = GridBagConstraints.NORTHWEST;
		resumeConstr.gridx = 1;
		resumeConstr.gridy = 2;
		add(btnResume, resumeConstr);
		
		JButton btnRules = new JButton("Rules");
		btnRules.setBackground(Colors.YELLOW.getActualColor());
		GridBagConstraints rulesConstr = new GridBagConstraints();
		rulesConstr.fill = GridBagConstraints.BOTH;
		rulesConstr.insets = new Insets(0, 0, 5, 0);
		rulesConstr.anchor = GridBagConstraints.NORTH;
		rulesConstr.gridx = 1;
		rulesConstr.gridy = 3;
		add(btnRules, rulesConstr);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Colors.YELLOW.getActualColor());
		GridBagConstraints exitConstr = new GridBagConstraints();
		exitConstr.fill = GridBagConstraints.BOTH;
		exitConstr.anchor = GridBagConstraints.NORTH;
		exitConstr.gridx = 1;
		exitConstr.gridy = 4;
		add(btnExit, exitConstr);
	}
}
