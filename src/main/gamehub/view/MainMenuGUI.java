package main.gamehub.view;

import main.gamehub.controller.MainController;
import main.gamehub.model.GameController;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.util.List;

/**
 * Class that represents the main menu of the application.
 * It allows the user to choose which game he wants to play.
 */
public class MainMenuGUI extends JFrame implements MainMenu {

    /**
     * 
     */
    private static final long serialVersionUID = -7833926492439181130L;
    private static final int FRAME_SIZE_DIV = 3;
    private static final int BUTTON_SIZE_DIV = 18;
    private static final int FONT_SIZE_DIV = 36;
    private static final Dimension MINIMUM_FRAME_DIMENSION = new Dimension(400, 400);
    //private static final Font BUTTON_FONT = new Font("Tahoma", Font.BOLD, 35);
    //private static final Insets INSETS = new Insets(BUTTON_SIZE_DIV, BUTTON_SIZE_DIV, BUTTON_SIZE_DIV, BUTTON_SIZE_DIV);

    private final MainController mainController;

    /**
     * Builds a new {@link MainControllerImpl}.
     * @param mainController
     *          the main controller
     * @param gameControllers
     *          the controllers of the games
     */
    public MainMenuGUI(final MainController mainController, final List<GameController> gameControllers) {
        this.mainController = mainController;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / FRAME_SIZE_DIV, screenSize.height / FRAME_SIZE_DIV));
        this.setMinimumSize(MINIMUM_FRAME_DIMENSION);

        final Insets insets = new Insets(screenSize.height / BUTTON_SIZE_DIV, screenSize.width / BUTTON_SIZE_DIV,
                                    screenSize.height / BUTTON_SIZE_DIV, screenSize.width / BUTTON_SIZE_DIV);
        final Font buttonFont = new Font("Tahoma", Font.BOLD, screenSize.height / FONT_SIZE_DIV);

        final JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        panel.setBackground(DashboardColor.BACKGROUND.getActualColor());
        this.getContentPane().add(panel);

        final GridBagConstraints jbConstr = new GridBagConstraints();
        jbConstr.fill = GridBagConstraints.BOTH;
        jbConstr.insets = insets;
        jbConstr.gridx = 1;
        jbConstr.weightx = 1;
        jbConstr.weighty = 1;

        for (final GameController c : gameControllers) {
            final JButton jb = new JButton(c.getGameName());
            jb.setBorder(BorderFactory.createRaisedBevelBorder());
            jb.setMargin(getInsets());
            jb.setFont(buttonFont);
            jbConstr.gridy = gameControllers.indexOf(c);
            jb.addActionListener(e -> this.mainController.showStartPanel(((JButton) e.getSource()).getText()));
            jb.setAlignmentX(CENTER_ALIGNMENT);
            jb.setBackground(DashboardColor.BUTTON.getActualColor());
            panel.add(jb, jbConstr);
        }

        this.pack();
        this.setLocationByPlatform(true);
    }
}
