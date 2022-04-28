package main.dashboard.view;

import main.dashboard.controller.MainController;
import main.general.GameController;

import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.GridBagConstraints;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PauseMenu extends JFrame {

    private static final long serialVersionUID = -7762097272351186299L;
    private static final int SIZE_DIV = 3;
    private static final Dimension MINIMUM_FRAME_DIMENSION = new Dimension(600, 600);
    private static final Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 50);
    private static final Insets TOP_INSETS = new Insets(100, 100, 20, 100);
    private static final Insets CENTER_INSETS = new Insets(20, 100, 20, 100);
    private static final Insets BOTTOM_INSETS = new Insets(20, 100, 100, 100);

    public PauseMenu(final MainController mainController, final GameController gameController) {

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final JPanel panel = new JPanel();
        final GridBagLayout layout = new GridBagLayout();
        panel.setBackground(DashboardColor.BACKGROUND.getActualColor());
        panel.setLayout(layout);
        this.add(panel);

        final JButton btnRestart = new JButton("New Game");
        btnRestart.setBackground(DashboardColor.BUTTON.getActualColor());
        btnRestart.setBorder(BorderFactory.createRaisedBevelBorder());
        btnRestart.setFont(BUTTON_FONT);
        btnRestart.addActionListener(e -> {
            mainController.showStartPanel(gameController.getGameName());
            this.dispose();
        });
        final GridBagConstraints restartConstr = new GridBagConstraints();
        restartConstr.fill = GridBagConstraints.BOTH;
        restartConstr.insets = TOP_INSETS;
        restartConstr.gridx = 1;
        restartConstr.gridy = 1;
        restartConstr.weightx = 1;
        restartConstr.weighty = 1;
        panel.add(btnRestart, restartConstr);

        final JButton btnResume = new JButton("Resume");
        btnResume.setBackground(DashboardColor.BUTTON.getActualColor());
        btnResume.setBorder(BorderFactory.createRaisedBevelBorder());
        btnResume.setFont(BUTTON_FONT);
        btnResume.addActionListener(e -> {
            mainController.resumeGame(gameController);
            this.dispose();
        });
        final GridBagConstraints resumeConstr = new GridBagConstraints();
        resumeConstr.fill = GridBagConstraints.BOTH;
        resumeConstr.insets = CENTER_INSETS;
        resumeConstr.gridx = 1;
        resumeConstr.gridy = 2;
        resumeConstr.weightx = 1;
        resumeConstr.weighty = 1;
        panel.add(btnResume, resumeConstr);

        final JButton btnExit = new JButton("Exit");
        btnExit.setBackground(DashboardColor.BUTTON.getActualColor());
        btnExit.setBorder(BorderFactory.createRaisedBevelBorder());
        btnExit.setFont(BUTTON_FONT);
        btnExit.addActionListener(e -> {
            mainController.showMainMenu();
            this.dispose();
        });
        final GridBagConstraints exitConstr = new GridBagConstraints();
        exitConstr.fill = GridBagConstraints.BOTH;
        exitConstr.gridx = 1;
        exitConstr.gridy = 3;
        exitConstr.insets = BOTTOM_INSETS;
        exitConstr.weightx = 1;
        exitConstr.weighty = 1;
        panel.add(btnExit, exitConstr);
    }

    /**
     * Displays the pause menu.
     */
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.setSize(sw / SIZE_DIV, sw / SIZE_DIV);
        this.setMinimumSize(MINIMUM_FRAME_DIMENSION);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
