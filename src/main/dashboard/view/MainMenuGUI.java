package main.dashboard.view;

import main.dashboard.controller.MainController;
import main.general.GameColor;
import main.general.GameController;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    /**
     * MIN_COLOR has to be in range 0 - 255.
     * Note: it must be true that MIN_COLOR + COLOR_RANGE <= 256
     */
    private static final int MIN_COLOR = 0;

    /**
     * COLOR_RANGE has to be in range 0 - 256.
     * Note: it must be true that MIN_COLOR + COLOR_RANGE <= 256
     */
    private static final int COLOR_RANGE = 256;

    private static final Map<GameColor, Color> COLORS;
    static {
        final Map<GameColor, Color> map = new HashMap<>();
        map.put(GameColor.WHITE, Color.WHITE);
        map.put(GameColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        map.put(GameColor.GRAY, Color.GRAY);
        map.put(GameColor.DARK_GRAY, Color.DARK_GRAY);
        map.put(GameColor.BLACK, Color.BLACK);
        map.put(GameColor.RED, Color.RED);
        map.put(GameColor.PINK, Color.PINK);
        map.put(GameColor.ORANGE, Color.ORANGE);
        map.put(GameColor.YELLOW, Color.YELLOW);
        map.put(GameColor.GREEN, Color.GREEN);
        map.put(GameColor.MAGENTA, Color.MAGENTA);
        map.put(GameColor.CYAN, Color.CYAN);
        map.put(GameColor.BLUE, Color.BLUE);
        COLORS = Collections.unmodifiableMap(map);
    }

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
            jb.setBackground(COLORS.get(c.getGameColor()));
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
