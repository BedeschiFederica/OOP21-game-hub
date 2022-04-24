package main.dashboard.view;

import main.dashboard.controller.MainController;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Class that represents the main menu of the application.
 * It allows the user to choose which game he wants to play.
 */
public class MainMenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -7833926492439181130L;
    private static final int SIZE_CONST = 200; // to be modified

    private final MainController controller;

    /**
     * Builds a new {@link MainController}.
     * @param controller
     *          the main controller
     * @param gameNames
     *          the names of the available games
     */
    public MainMenu(final MainController controller, final List<String> gameNames) {
        this.controller = controller;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SIZE_CONST, SIZE_CONST));

        final JPanel panel = new JPanel();
        this.getContentPane().add(panel);

        for (final String name : gameNames) {
            final JButton jb = new JButton(name);
            jb.addActionListener(e -> {
                this.controller.startGame(((JButton) e.getSource()).getText());
                this.dispose();
            });
            panel.add(jb);
        }

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
