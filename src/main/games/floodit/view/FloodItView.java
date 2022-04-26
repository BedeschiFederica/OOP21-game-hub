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
    private Table gameTable;

    private final JFrame frame;
    private final CardLayout layout;
    private final JPanel mainPanel;
    private GamePanel gamePanel;
    private final StartPanel startPanel;
    private final JPanel pausePanel;

    public FloodItView(final FloodItController controller) {
        this.frame = new JFrame();
        this.frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.frame.setMinimumSize(new Dimension(MIN_WIDTH, MIN_HEIGHT));

        this.cellsMap = new HashMap<>();
        this.controller = controller;
        this.gameTable = null;
        this.layout = new CardLayout();

        this.mainPanel = new JPanel(layout);
        this.frame.getContentPane().add(mainPanel);

        this.startPanel = new StartPanel(mainPanel, layout, controller);
        this.gamePanel = null;
        this.pausePanel = new JPanel();

        this.mainPanel.add(startPanel, "1");
        this.mainPanel.add(pausePanel, "3");

        this.layout.show(mainPanel, "1");

    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.frame.setSize(sw / SIZE_DIV, sw / SIZE_DIV);
        this.frame.setLocationByPlatform(true);
        this.frame.setVisible(true);
    }

    public void createGameboard() {
        this.gamePanel = new GamePanel(mainPanel, layout, controller, cellsMap, cellButtons, gameTable);
        this.mainPanel.add(gamePanel, "2");
    }

    public void updateCellVisualization(Cell cellToUpdate) {
        cellButtons.forEach(b -> {
            if (cellsMap.get(b).equals(cellToUpdate)) {
                b.setBackground(cellToUpdate.getColor().getActualColor());
            }
        });
    }

    public void updateMovesVisualization(String newString) {
        gamePanel.getLblMoves().setText(newString);
    }

    public int getComboSize() {
        return this.startPanel.getRowSize();
    }

    public int getComboColors() {
        return this.startPanel.getColors();
    }

    public void stop() {
        cellButtons.forEach(b -> b.setEnabled(false));
    }

    public void setGameTable(Table newTable) {
        this.gameTable = newTable;
    }

    @Override
    public void setVisible(final boolean visible) {
        if (visible) {
            layout.show(mainPanel, "2");
        }
    }

    public void showStart() {
        this.layout.show(mainPanel, "1");
    }
}
