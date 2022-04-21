package main.numericalbond.view;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import main.general.GameView;
import main.numericalbond.NumericalBondController;
import main.numericalbond.Position;

public class NumericalBondView extends JFrame implements GameView {

    /**
     * 
     */
    private static final long serialVersionUID = 3090475070554411461L;
    private static final int SIZE_CONST = 100; // to be modified

    private final NumericalBondController controller;
    private final Map<JButton, Position> positions = new HashMap<>();
    private final Map<Position, JButton> blocks = new HashMap<>();
    private final List<Line2D> links = new ArrayList<>();
    private boolean isOneBlockSelected;
    private JButton selectedBlock;

    public NumericalBondView(final NumericalBondController controller, final int numLines) {
        this.controller = controller;

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SIZE_CONST * numLines, SIZE_CONST * numLines));

        final JPanel mainPanel = new JPanel(new BorderLayout());
        final JPanel gamePanel = new JPanel(new GridLayout(numLines, numLines,
                SIZE_CONST * numLines / (2 * numLines - 1),
                SIZE_CONST * numLines / (2 * numLines - 1)));
        mainPanel.add(gamePanel, BorderLayout.CENTER);
        final JPanel optionsPanel = new JPanel();               // temporary
        mainPanel.add(optionsPanel, BorderLayout.SOUTH);        // temporary
        this.getContentPane().add(mainPanel);

        for (int i = 0; i < numLines; i++) {
            for (int j = 0; j < numLines; j++) {
                final Position pos = new Position(i, j);
                final JButton jb = new JButton();
                this.positions.put(jb, pos);
                this.blocks.put(pos, jb);
                jb.addActionListener(e -> {
                    if (!this.isOneBlockSelected) {
                        select(jb);
                    } else if (jb.equals(this.selectedBlock)) {
                        deselect();
                    } else {
                        this.controller.link(this.positions.get(this.selectedBlock), this.positions.get(jb));
                    }
                });
                gamePanel.add(jb);
            }
        }
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

    private void select(final JButton jb) {
        this.selectedBlock = jb;
        this.selectedBlock.setBackground(Color.LIGHT_GRAY);
        this.isOneBlockSelected = true;
    }

    public void deselect() {
        this.selectedBlock.setBackground(null);
        this.isOneBlockSelected = false;
    }

    public void setBlockNumber(final Position pos, final int blockNumber) {
        this.blocks.get(pos).setText(Integer.toString(blockNumber));
    }

    public void createLink(final Position pos1, final Position pos2, final int links) {  // to be modified
        final JButton jb1 = this.blocks.get(pos1);
        final JButton jb2 = this.blocks.get(pos2);
        if (links == 1) {
            this.links.add(new Line2D.Double(jb1.getBounds().getCenterX() + getDiffX(),
                    jb1.getBounds().getCenterY() + getDiffY(), jb2.getBounds().getCenterX() + getDiffX(),
                    jb2.getBounds().getCenterY() + getDiffY()));
        } else if (links == 2) {
            this.links.add(new Line2D.Double(jb1.getBounds().getCenterX() + getDiffX() - 10,
                    jb1.getBounds().getCenterY() + getDiffY() - 10, jb2.getBounds().getCenterX() + getDiffX() - 10,
                    jb2.getBounds().getCenterY() + getDiffY() - 10));
        } else {
            this.links.remove(new Line2D.Double(jb1.getBounds().getCenterX() + getDiffX(),
                    jb1.getBounds().getCenterY() + getDiffY(), jb2.getBounds().getCenterX() + getDiffX(),
                    jb2.getBounds().getCenterY() + getDiffY()));
            this.links.remove(new Line2D.Double(jb1.getBounds().getCenterX() + getDiffX() - 10,
                    jb1.getBounds().getCenterY() + getDiffY() - 10, jb2.getBounds().getCenterX() + getDiffX() - 10,
                    jb2.getBounds().getCenterY() + getDiffY() - 10));
        }
        repaint();
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        //revalidate();
        final Graphics2D g2 = (Graphics2D) g;
        for (final Line2D line : this.links) {
            g2.draw(line);
        }
    }

    private double getDiffX() {
        return this.getBounds().getWidth() - this.getContentPane().getSize().getWidth();
    }

    private double getDiffY() {
        return this.getBounds().getHeight() - this.getContentPane().getSize().getHeight();
    }
}
