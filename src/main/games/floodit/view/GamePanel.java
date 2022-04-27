package main.games.floodit.view;

import main.games.floodit.controller.FloodItController;
import main.games.floodit.model.Cell;
import main.games.floodit.model.Colors;
import main.games.floodit.model.Table;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Map;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class GamePanel extends JPanel {

    private static final long serialVersionUID = 5507460523553525591L;
    private static final Font FONT = new Font("Tahoma", Font.PLAIN, 30);

    private final JLabel lblMoves;
    private final Map<JButton, Cell> cellsMap;

    public GamePanel(final FloodItController controller, final Map<JButton, Cell> map, final List<JButton> cellButtons, final Table gameTable, final FloodItView gameView) {
        this.lblMoves = new JLabel();
        this.cellsMap = map;

        setLayout(new BorderLayout(0, 0));
        setBackground(Colors.LIGHT_BLUE.getActualColor());

        lblMoves.setHorizontalAlignment(SwingConstants.CENTER);
        add(lblMoves, BorderLayout.SOUTH);

        final JPanel topPanel = new JPanel();
        topPanel.setBackground(Colors.LIGHT_BLUE.getActualColor());
        add(topPanel, BorderLayout.NORTH);

        final JButton btnPause = new JButton("Menu");
        btnPause.setBackground(Colors.YELLOW.getActualColor());
        btnPause.setBorder(BorderFactory.createRaisedBevelBorder());
        btnPause.setHorizontalAlignment(SwingConstants.LEFT);
        btnPause.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                controller.pause();
            }
        });
        topPanel.add(btnPause);

        final JLabel lblTitle = new JLabel("Flood It!");
        lblTitle.setFont(FONT);
        topPanel.add(lblTitle);

        final JButton btnExit = new JButton("Exit");
        btnExit.setBackground(Colors.YELLOW.getActualColor());
        btnExit.setBorder(BorderFactory.createRaisedBevelBorder());
        btnExit.setHorizontalAlignment(SwingConstants.RIGHT);
        btnExit.addActionListener(e -> {
                controller.closeGame();
                gameView.getFrame().dispose();
            });
        topPanel.add(btnExit);

        // Generates the buttons table
        final JPanel boardPanel = new JPanel(new GridLayout(gameTable.getBoardSize(), gameTable.getBoardSize()));
        boardPanel.setBackground(Colors.LIGHT_BLUE.getActualColor());

        final ActionListener al = e -> {
            final var clickedCell = (JButton) e.getSource();
            controller.onClick(cellsMap.get(clickedCell));
        };

        for (int i = 0; i < gameTable.getBoardSize(); i++) {
            for (int j = 0; j < gameTable.getBoardSize(); j++) {
                final JButton button = new JButton();
                button.setBackground(gameTable.getCell(i, j).getColor().getActualColor());
                button.addActionListener(al);
                cellButtons.add(button);
                this.cellsMap.put(button, gameTable.getCell(i, j));
                boardPanel.add(button);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

    }

    /**
     * @return The moves label.
     */
    public JLabel getLblMoves() {
        return this.lblMoves;
    }

}
