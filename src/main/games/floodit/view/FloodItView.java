package main.games.floodit.view;

import main.games.floodit.controller.FloodItController;
import main.games.floodit.model.Cell;
import main.games.floodit.model.MaxMovesCounter;
import main.games.floodit.model.Table;
import main.general.GameView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class FloodItView implements GameView {

    private static final long serialVersionUID = -6218820567019985015L;
    private static final int SIZE_DIV = 2;
    private static final int MIN_WIDTH = 400;
    private static final int MIN_HEIGHT = 500;

    private final List<JButton> cellButtons = new ArrayList<>();
    private final Map<JButton, Cell> cellsMap;

    private final FloodItController controller;

    private final JFrame frame;
    private GamePanel gamePanel;

    public FloodItView(final FloodItController controller) {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        this.cellsMap = new HashMap<>();
        this.controller = controller;
    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.frame.setSize(sw / SIZE_DIV, sw / SIZE_DIV);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    public void updateCellVisualization(final Cell cellToUpdate) {
        cellButtons.forEach(b -> {
            if (cellsMap.get(b).equals(cellToUpdate)) {
                b.setBackground(cellToUpdate.getColor().getActualColor());
            }
        });
    }

    public void updateMovesVisualization(final String newString) {
        gamePanel.getLblMoves().setText(newString);
    }

    public void stop() {
        cellButtons.forEach(b -> b.setEnabled(false));
    }

    public JFrame getFrame() {
        return this.frame;
    }

    public void setGameTable(final Table newTable) {
        this.gamePanel = new GamePanel(controller, cellsMap, cellButtons, newTable, this);
        frame.getContentPane().removeAll();
        this.frame.getContentPane().add(gamePanel);
    }

    @Override
    public void setVisible(final boolean visible) {
            frame.setVisible(visible);
    }

}
