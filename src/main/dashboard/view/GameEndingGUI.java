package main.dashboard.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.dashboard.controller.MainController;
import main.general.GameController;

/** 
 * Class that is needed for the endGame.
 * 
 * 
 */
public class GameEndingGUI extends JFrame implements GameEndingView {

    private static final long serialVersionUID = -2947972819890283488L;
    private static final int BUTTON_SIZE_DIV = 25;
    private static final int FONTSIZE = 48;
    /**
     * Creation of the Gui graphic.
     * 
     * @param mainController needed to go to the main controller
     * @param gameController need to know what game had ended
     * @param isVictory need to know if it's a victory or not
     */
    public GameEndingGUI(final MainController mainController, final GameController gameController, final boolean isVictory) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("SCORE");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel jp = new JPanel();
        jp.setLayout(new GridBagLayout());

        final Insets insets = new Insets(screenSize.height / BUTTON_SIZE_DIV, screenSize.width / BUTTON_SIZE_DIV,
                screenSize.height / BUTTON_SIZE_DIV, screenSize.width / BUTTON_SIZE_DIV);
        final GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.insets = insets;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 1;
        constraints.weighty = 1;

        final JLabel jlResult;
        final Font fState = new Font("Tahoma", Font.BOLD, FONTSIZE);
        if (isVictory) {
            jlResult = new JLabel("You Won!!!", SwingConstants.CENTER);
        } else {
            jlResult = new JLabel("You Lost! Try Again!", SwingConstants.CENTER);
        }
        jlResult.setForeground(DashboardColor.TITLE.getActualColor());
        //jlResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlResult.setFont(fState);
        jp.add(jlResult, constraints);

        final JButton jbNewGame = new JButton("NEW GAME");
        final JButton jbMenu = new JButton("RETURN TO MAIN MENU");
        //jbNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        //jbMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbNewGame.setBackground(DashboardColor.BUTTON.getActualColor());
        jbMenu.setBackground(DashboardColor.BUTTON.getActualColor());
        jbNewGame.setBorder(BorderFactory.createRaisedBevelBorder());
        jbMenu.setBorder(BorderFactory.createRaisedBevelBorder());
        jbNewGame.addActionListener(e -> {
            mainController.showStartPanel(gameController.getGameName());
            this.dispose();
        });
        jbMenu.addActionListener(e -> {
            mainController.showMainMenu();
            this.dispose();
        });
        jp.setBackground(DashboardColor.BACKGROUND.getActualColor());
        //jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        constraints.gridy = 1;
        jp.add(jbNewGame, constraints);
        constraints.gridy = 2;
        jp.add(jbMenu, constraints);

        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.getContentPane().add(jp);
        this.pack();
        this.setLocationByPlatform(true);
    }

}
