package main.dashboard.view;

import main.dashboard.controller.MainController;
import main.general.GameController;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.text.AttributeSet.ColorAttribute;

import java.awt.Color;
import java.awt.Dimension;
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
    private static final Color BACKGROUND_COLOR = Color.ORANGE;
    private static final Color BUTTONS_COLOR = Color.YELLOW;

    private final MainController mainController;

    /**
     * Builds a new {@link MainController}.
     * @param mainController
     *          the main controller
     * @param controllers
     *          the controllers of the games
     */
    public MainMenuGUI(final MainController mainController, final List<GameController> controllers) {
        this.mainController = mainController;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / FRAME_SIZE_DIV, screenSize.height / FRAME_SIZE_DIV));

        final JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(BACKGROUND_COLOR);
        panel.add(Box.createVerticalGlue());
        this.getContentPane().add(panel);

        //final Random random = new Random();
        for (final GameController c : controllers) {
            final JButton jb = new JButton(c.getGameName());
            jb.addActionListener(e -> {
                this.mainController.showStartPanel(((JButton) e.getSource()).getText());
                this.dispose();
            });
            jb.setAlignmentX(CENTER_ALIGNMENT);
            jb.setBackground(BUTTONS_COLOR);
//            jb.setBackground(new Color(MIN_COLOR + random.nextInt(COLOR_RANGE),
//                    MIN_COLOR + random.nextInt(COLOR_RANGE), 
//                    MIN_COLOR + random.nextInt(COLOR_RANGE)));
            panel.add(jb);
            panel.add(Box.createVerticalGlue());
        }

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
