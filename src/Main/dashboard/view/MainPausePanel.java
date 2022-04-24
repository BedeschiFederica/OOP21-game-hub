package main.dashboard.view;

import javax.swing.*;

import main.dashboard.controller.MainController;
import main.games.floodit.model.Colors;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPausePanel extends JPanel {

    private static final long serialVersionUID = -7762097272351186299L;
    private final CardLayout mainLayout;
    private final MainController mainController;

    public MainPausePanel(final JPanel mainPanel, final CardLayout mLayout, final MainController mainController) {
        this.mainLayout = mLayout;
        this.mainController = mainController;
        this.setBackground(Colors.LIGHT_BLUE.getActualColor());
        final GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        final JButton btnRestart = new JButton("New Game");
        btnRestart.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnRestart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                mainController.startGame(null);
            }
        });
        final GridBagConstraints restartConstr = new GridBagConstraints();
        restartConstr.fill = GridBagConstraints.BOTH;
        restartConstr.insets = new Insets(100, 100, 20, 100);
        restartConstr.gridx = 1;
        restartConstr.gridy = 1;
        restartConstr.weightx = 1;
        restartConstr.weighty = 1;
        add(btnRestart, restartConstr);

        final JButton btnResume = new JButton("Resume");
        btnResume.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnResume.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                mainController.resumeGame(null);
            }
        });
        final GridBagConstraints resumeConstr = new GridBagConstraints();
        resumeConstr.fill = GridBagConstraints.BOTH;
        resumeConstr.insets = new Insets(20, 100, 20, 100);
        resumeConstr.gridx = 1;
        resumeConstr.gridy = 2;
        resumeConstr.weightx = 1;
        resumeConstr.weighty = 1;
        add(btnResume, resumeConstr);

        final JButton btnRules = new JButton("Rules");
        btnRules.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnRules.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {

            }
        });
        final GridBagConstraints rulesConstr = new GridBagConstraints();
        rulesConstr.fill = GridBagConstraints.BOTH;
        rulesConstr.insets = new Insets(20, 100, 20, 100);
        rulesConstr.gridx = 1;
        rulesConstr.gridy = 3;
        rulesConstr.weightx = 1;
        rulesConstr.weighty = 1;
        add(btnRules, rulesConstr);

        final JButton btnExit = new JButton("Exit");
        btnExit.setFont(new Font("Tahoma", Font.PLAIN, 25));
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                mainController.showMainMenu();
            }
        });
        final GridBagConstraints exitConstr = new GridBagConstraints();
        exitConstr.fill = GridBagConstraints.BOTH;
        exitConstr.gridx = 1;
        exitConstr.gridy = 4;
        exitConstr.insets = new Insets(20, 100, 100, 100);
        exitConstr.weightx = 1;
        exitConstr.weighty = 1;
        add(btnExit, exitConstr);
    }
}
