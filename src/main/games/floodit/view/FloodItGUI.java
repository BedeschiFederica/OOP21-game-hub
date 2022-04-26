package main.games.floodit.view;

import javax.swing.*;

import main.games.floodit.controller.FloodItController;
import main.games.floodit.model.Cell;
import main.games.floodit.model.FloodItModel;

import java.util.*;
import java.util.List;
import java.awt.*;

public class FloodItGUI extends JFrame {

    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cellButtons = new ArrayList<>();
    private final Map<JButton, Cell> cellsMap;

    private final FloodItController controller;
    private final FloodItModel model;

    private final CardLayout layout;
    private final JPanel mainPanel;
    private GamePanel gamePanel;
    private final StartPanel startPanel;
    private final JPanel pausePanel;
    final JLabel lblMoves;

    public FloodItGUI(FloodItController controller, FloodItModel model) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(400, 500));

        cellsMap = new HashMap<>();
        this.controller = controller;
        this.model = model;
        layout = new CardLayout();
        lblMoves = new JLabel();

        this.mainPanel = new JPanel(layout);
        this.getContentPane().add(mainPanel);

        this.startPanel = new StartPanel(mainPanel, layout, controller);
        this.gamePanel = null;
        this.pausePanel = new PausePanel(mainPanel, layout);

        mainPanel.add(startPanel, "1");
        mainPanel.add(pausePanel, "3");

        layout.show(mainPanel, "1");

    }

    public void display() {
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.setSize(sw / 2, sw / 2);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    public void createGameboard() {
        this.gamePanel = new GamePanel(mainPanel, layout, controller, model, cellsMap, cellButtons);
        this.mainPanel.add(gamePanel, "2");
    }

    public void updateCellVisualization(Cell cellToUpdate) {
        cellButtons.forEach(b -> {
            if (cellsMap.get(b).equals(cellToUpdate)) {
                b.setBackground(cellToUpdate.getColor().getActualColor());
            }
        });
    }

    public void updateMovesVisualization() {
        gamePanel.updateLblMoves();
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

}
