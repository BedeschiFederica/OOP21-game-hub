package main.games.numericalbond.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;

import main.games.numericalbond.controller.NumericalBondController;
import main.games.numericalbond.controller.Position;
import main.general.GameView;

/**
 * Class that represents the view of the game Numerical bond.
 */
public class NumericalBondView extends JFrame implements GameView {

    private static final long serialVersionUID = 3090475070554411461L;
    private static final int SIZE_CONST = 100; // to be modified

    private final NumericalBondController controller;
    private final int numLines;
    private final GamePanel gamePanel;

    /**
     * Builds a new {@link NumericalBondView}.
     * @param controller
     *          the controller of the game
     * @param numLines
     *          the number of lines that the grid of the game will have
     */
    public NumericalBondView(final NumericalBondController controller, final int numLines) {
        this.controller = controller;
        this.numLines = numLines;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SIZE_CONST * numLines, SIZE_CONST * numLines));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final GamePanel gamePanel = new GamePanel(this);
        this.gamePanel = gamePanel;
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        final JPanel optionsPanel = new JPanel();               // temporary
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);        // temporary
        this.getContentPane().add(mainPanel);

        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> {
            this.setVisible(false);
            this.controller.pause();
        });
        optionsPanel.add(pause); // temporary
        // mainPanel.add(pause, BorderLayout.SOUTH);

        // temporary
        final JButton menu = new JButton("Menu");
        menu.addActionListener(e -> {
            this.setVisible(false);
            this.controller.closeGame();
        });
        optionsPanel.add(menu);

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

    /**
     * Sets the number of the block specified by the given position to the given block number.
     * @param pos
     *          the position of the block
     * @param blockNumber
     *          the block number that has to be set
     */
    public void setBlockNumber(final Position pos, final int blockNumber) {
        this.gamePanel.setBlockNumber(pos, blockNumber);
    }

    /**
     * Creates a link between the two blocks specified by the given positions.
     * @param pos1
     *          the position of the first block
     * @param pos2
     *          the position of the second block
     * @param links
     *          the number of existing links between the two blocks
     */
    public void createLink(final Position pos1, final Position pos2, final int links) {
        this.gamePanel.createLink(pos1, pos2, links);
    }

    /**
     * Deselects the selected block.
     */
    public void deselect() {
        this.gamePanel.deselect();
    }

    /**
     * Gets the size constant of the frame.
     * @return the size constant of the frame
     */
    public int getSizeConst() {
        return SIZE_CONST;
    }

    /**
     * Gets the controller of the game.
     * @return the controller of the game
     */
    public NumericalBondController getController() {
        return this.controller;
    }

    /**
     * Gets the number of lines of the game grid.
     * @return the number of lines of the game grid.
     */
    public int getNumLines() {
        return this.numLines;
    }

}
