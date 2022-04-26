package main.games.minefield.view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import main.games.minefield.controller.StartGame;
import main.games.minefield.model.Handler;
import main.general.GameView;
import main.games.minefield.controller.Field;

public class ViewField implements GameView {

    /**
     * frame is here so it can be used in every method.
     */
    public static JFrame frame;
    private static String title;

    /**
     * Constructor of field.
     * @param size needed to make the grid
     * @param title sets the title of the frame
     * @param startGame so it knows what game to start
     * @param handler that makes all the check for the game.
     * 
     */
    public ViewField(final int size, final String title, final StartGame startGame, final Handler handler) {
        ViewField.title = title;
        frame = new JFrame(title);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jpField = new Field(new GridLayout(size, size), handler);
        //frame.setSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        frame.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        frame.setLocationByPlatform(true);
        frame.setContentPane(jpField);
        update(0);
        frame.pack();
    }

    /**
     * Updates the title of the frame to know how many mines and flag are in the game.
     * @param flag needed to update in real time the number of flag
     */
    public static void update(final int flag) {
        frame.setTitle(title + "Mines: " + StartGame.MINES + " - Flags: " + flag);
    }

    @Override
    public void setVisible(final boolean visible) {
        frame.setVisible(visible);
    }
}
