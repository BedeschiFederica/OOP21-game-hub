package main.games.floodit.view;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import main.games.floodIt.controller.FloodItController;
import main.games.floodIt.model.Cell;
import main.games.floodIt.model.Colors;
import main.games.floodIt.model.FloodItModel;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 5507460523553525591L;
    // private final FloodItController controller;
    private final FloodItModel model;
    // private final JPanel mainPanel;
    private final CardLayout mainLayout;
    private final JLabel lblMoves;
    private final Map<JButton, Cell> cellsMap;
    private final List<JButton> cellButtons;

    public GamePanel(JPanel mainPanel, CardLayout mLayout, FloodItController controller, FloodItModel model,
            Map<JButton, Cell> map, List<JButton> cellButtons) {
        // this.controller = controller;
        this.model = model;
        // this.mainPanel = mainPanel;
        this.mainLayout = mLayout;
        this.lblMoves = new JLabel();
        this.cellsMap = map;
        this.cellButtons = cellButtons;

        setLayout(new BorderLayout(0, 0));
        setBackground(Colors.LIGHT_BLUE.getActualColor());

        lblMoves.setHorizontalAlignment(SwingConstants.CENTER);
        lblMoves.setText((model.getMoves() + " / " + model.getMaxMoves()));
        add(lblMoves, BorderLayout.SOUTH);

        final JPanel topPanel = new JPanel();
        topPanel.setBackground(Colors.LIGHT_BLUE.getActualColor());
        add(topPanel, BorderLayout.NORTH);

        final JButton btnPause = new StyledButton("Menu");
        btnPause.setHorizontalAlignment(SwingConstants.LEFT);
        btnPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mainLayout.show(mainPanel, "3");
            }
        });
        topPanel.add(btnPause);

        final JLabel lblTitle = new JLabel("Flood It!");
        lblTitle.setFont(new Font("Tahome", Font.PLAIN, 30));
        topPanel.add(lblTitle);

        final JButton btnExit = new StyledButton("Exit");
        btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
        btnExit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        topPanel.add(btnExit);

        final JPanel boardPanel = new JPanel(new GridLayout(model.getRowSize(), model.getRowSize()));
        boardPanel.setBackground(Colors.LIGHT_BLUE.getActualColor());

        ActionListener al = e -> {
            var clickedCell = (JButton) e.getSource();
            controller.onClick(cellsMap.get(clickedCell));
        };

        for (int i = 0; i < model.getRowSize(); i++) {
            for (int j = 0; j < model.getRowSize(); j++) {
                final JButton button = new JButton(" ");
                button.setBackground(model.getTable().getCell(i, j).getColor().getActualColor());
                button.addActionListener(al);
                this.cellButtons.add(button);
                this.cellsMap.put(button, model.getTable().getCell(i, j));
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

    }

    public void updateLblMoves() {
        lblMoves.setText((model.getMoves() + " / " + model.getMaxMoves()));
    }

}
