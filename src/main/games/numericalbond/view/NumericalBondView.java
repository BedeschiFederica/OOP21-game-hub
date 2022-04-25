package main.games.numericalbond.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.BorderLayout;

import main.games.numericalbond.controller.NumericalBondController;
import main.games.numericalbond.controller.Position;
import main.general.GameView;

public class NumericalBondView extends JFrame implements GameView {

    /**
     * 
     */
    private static final long serialVersionUID = 3090475070554411461L;
    private static final int SIZE_CONST = 100; // to be modified

    private final NumericalBondController controller;
    private final int numLines;
    private final GamePanel gamePanel;

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

    public void setBlockNumber(final Position pos, final int blockNumber) {
        this.gamePanel.setBlockNumber(pos, blockNumber);
    }

    public void createLink(final Position pos1, final Position pos2, final int links) {
        this.gamePanel.createLink(pos1, pos2, links);
    }

    public void deselect() {
        this.gamePanel.deselect();
    }

    public int getSizeConst() {
        return SIZE_CONST;
    }

    public NumericalBondController getController() {
        return this.controller;
    }

    public int getNumLines() {
        return this.numLines;
    }

}
