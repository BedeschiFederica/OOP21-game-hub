package main.games.numericalbond.view;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSeparator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.BorderLayout;

import main.games.numericalbond.controller.NumericalBondController;
import main.games.numericalbond.controller.Position;
import main.general.GameView;

/**
 * Class that represents the view of the game Numerical bond.
 */
public class NumericalBondView extends JFrame implements GameView {

    private static final long serialVersionUID = 3090475070554411461L;
    private static final int FRAME_SIZE_DIV = 2;
    private static final int SEPARATOR_GAP_DIV_X = 200;
    private static final int SEPARATOR_GAP_DIV_Y = 100;
    private static final Color UP_PANEL_COLOR = Color.BLUE;
    private static final Color OPTIONS_PANEL_COLOR = Color.ORANGE;
    private static final Color PAUSE_BUTTON_COLOR = Color.YELLOW;
    private static final Color PAUSE_BUTTON_TEXT_COLOR = Color.RED;

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
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / FRAME_SIZE_DIV, screenSize.height / FRAME_SIZE_DIV));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final GamePanel gamePanel = new GamePanel(this);
        this.gamePanel = gamePanel;
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        final JPanel upPanel = new JPanel(new BorderLayout());
        final JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.TRAILING));
        optionsPanel.setBackground(OPTIONS_PANEL_COLOR);
        upPanel.add(optionsPanel, BorderLayout.CENTER);
        upPanel.add(new JSeparator(JSeparator.HORIZONTAL), BorderLayout.SOUTH);
        upPanel.setBackground(UP_PANEL_COLOR);
        //mainPanel.add(optionsPanel, BorderLayout.NORTH);
        mainPanel.add(upPanel, BorderLayout.NORTH);
        final int gapX = screenSize.width / SEPARATOR_GAP_DIV_X;
        final int gapY = screenSize.height / SEPARATOR_GAP_DIV_Y;
        gamePanel.setBorder(BorderFactory.createEmptyBorder(gapY, gapX, gapY, gapX));
        this.getContentPane().add(mainPanel);

        final JButton pause = new JButton("Pause");
        pause.addActionListener(e -> {
            this.setVisible(false);
            this.controller.pause();
        });
        pause.setBackground(PAUSE_BUTTON_COLOR);
        pause.setForeground(PAUSE_BUTTON_TEXT_COLOR);
        optionsPanel.add(pause);
        // mainPanel.add(pause, BorderLayout.SOUTH);

//        // temporary
//        final JButton menu = new JButton("Menu");
//        menu.addActionListener(e -> {
//            this.setVisible(false);
//            this.controller.closeGame();
//        });
//        optionsPanel.add(menu);

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
