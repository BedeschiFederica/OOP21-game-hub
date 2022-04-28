package main.games.floodit.view;

import main.games.floodit.controller.FloodItController;
import main.games.floodit.model.Cell;
import main.games.floodit.model.Table;
import main.general.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FloodItView implements GameView {

    private static final int SIZE_DIV = 2;
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 500;

    private final List<JButton> cellButtons = new ArrayList<>();
    private final Map<JButton, Cell> cellsMap;

    private final FloodItController controller;

    private final JFrame frame;
    private GamePanel gamePanel;

    public FloodItView(final FloodItController controller) {
        this.frame = new JFrame("GAME HUB - Flood It");
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        this.cellsMap = new HashMap<>();
        this.controller = controller;
    }

    /**
     * Sets the size of the frame and shows the view.
     */
    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.frame.setSize(sw / SIZE_DIV, sw / SIZE_DIV);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    /**
     * Updates the visualization of the cells.
     * 
     * @param cellToUpdate The cells that needs to be updated.
     */
    public void updateCellVisualization(final Cell cellToUpdate) {
        cellButtons.forEach(b -> {
            if (cellsMap.get(b).equals(cellToUpdate)) {
                b.setBackground(cellToUpdate.getColor().getActualColor());
            }
        });
    }

    /**
     * Updates the visualization of the moves.
     * 
     * @param newString The string that describes the moves situation.
     */
    public void updateMovesVisualization(final String newString) {
        gamePanel.getLblMoves().setText(newString);
    }

    /**
     * @return The view frame.
     */
    public JFrame getFrame() {
        return this.frame;
    }

    /**
     * Creates the game panel.
     * 
     * @param newTable The cells table to show.
     */
    public void setGamePanel(final Table newTable) {
        this.gamePanel = new GamePanel(controller, cellsMap, cellButtons, newTable);
        frame.getContentPane().removeAll();
        this.frame.getContentPane().add(gamePanel);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setVisible(final boolean visible) {
            frame.setVisible(visible);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void dispose() {
        frame.dispose();
    }

}
