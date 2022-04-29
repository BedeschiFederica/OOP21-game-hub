package main.games.minefield.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import main.games.minefield.controller.MinefieldController;
import main.games.minefield.model.Handler;
import main.gamehub.model.GameView;
import main.games.minefield.controller.Field;

public class ViewField implements GameView {

    /**
     * frame is here so it can be used in every method.
     */
    private static JFrame frame;
    private static String title;
    private static  int mines;
    private static int gridSize;

    /**
     * Constructor of field.
     * @param size needed to make the grid
     * @param title sets the title of the frame
     * @param startGame so it knows what game to start
     * @param mine needed to know how many mines are needed
     * @param handler that makes all the check for the game.
     * 
     */
    public ViewField(final int size, final int mine, final String title, final MinefieldController startGame, final Handler handler) {
        ViewField.title = title;
        this.mines = mine;
        this.gridSize = size;
        System.out.println("Size: " + this.gridSize + " " + size);
        System.out.println("Mines: " + this.mines + " " + mine);
        setFrame(new JFrame(title));
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel pausePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pausePanel.setBackground(Color.cyan);
        final JButton jbPause = new JButton("PAUSE");
        pausePanel.add(jbPause);
        jbPause.setBackground(Color.yellow);
        jbPause.addActionListener(e -> startGame.pause());
        JPanel jpField = new Field(new GridLayout(size, size), handler);
        mainPanel.add(pausePanel, BorderLayout.NORTH);
        mainPanel.add(jpField, BorderLayout.CENTER);
        getFrame().setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        getFrame().setLocationByPlatform(true);
        getFrame().getContentPane().add(mainPanel);
        update(0);
        getFrame().pack();
        getFrame().setVisible(true);
    }


    /**
     * Updates the title of the frame to know how many mines and flag are in the game.
     * @param flag needed to update in real time the number of flag
     */
    public static void update(final int flag) {
        getFrame().setTitle(title + "Mines: " + mines + " - Flags: " + flag);
    }

    /**
     * @Ovveride to set the frame visible.
     * @param visible true if the frame is set visible
     */
    public void setVisible(final boolean visible) {
        getFrame().setVisible(visible);
    }

    /**
     * @Override to dispose this frame
     */
    public void dispose() {
        getFrame().dispose();
    }

    public static JFrame getFrame() {
        return frame;
    }

    /**
     * @param frame needed to set the frame
     */
    public static void setFrame(final JFrame frame) {
        ViewField.frame = frame;
    }
    public static int getMines() {
        return mines;
    }
    public static int getGridSize() {
        return gridSize;
    }
//    public static void setGridSize(final int gridSize) {
//        ViewField.gridSize = gridSize;
//    }
}
