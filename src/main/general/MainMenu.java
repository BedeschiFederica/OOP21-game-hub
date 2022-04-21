package main.general;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.geom.*;
import java.util.List;

public class MainMenu extends JFrame {

    /**
     * 
     */
    private static final long serialVersionUID = -7833926492439181130L;
    private static final int SIZE_CONST = 200; // to be modified

    private final MainController controller;

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
