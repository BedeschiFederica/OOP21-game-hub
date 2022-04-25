package main.games.numericalbond.view;

import javax.swing.JButton;
import javax.swing.JPanel;

import main.games.numericalbond.controller.Position;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GamePanel extends JPanel {

    /**
     * 
     */
    private static final long serialVersionUID = -3838746067867960423L;

    private final NumericalBondView view;
    private final Map<JButton, Position> positions = new HashMap<>();
    private final Map<Position, JButton> blocks = new HashMap<>();
    private final List<Link> links = new ArrayList<>();
    private boolean isOneBlockSelected;
    private JButton selectedBlock;

    public GamePanel(final NumericalBondView view) {
        super();

        this.view = view;
        final int numLines = this.view.getNumLines();
        this.setLayout(new GridLayout(numLines, numLines,
                this.view.getSizeConst() * numLines / (2 * numLines - 1),
                this.view.getSizeConst() * numLines / (2 * numLines - 1)));

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
                        this.view.getController().link(this.positions.get(this.selectedBlock), this.positions.get(jb));
                    }
                });
                this.add(jb);
            }
        }
    }

    private void select(final JButton jb) {
        this.selectedBlock = jb;
        this.selectedBlock.setBackground(Color.LIGHT_GRAY);
        this.isOneBlockSelected = true;
    }

    public void deselect() {
        this.selectedBlock.setBackground(getBackground());
        this.isOneBlockSelected = false;
    }

    public void setBlockNumber(final Position pos, final int blockNumber) {
        this.blocks.get(pos).setText(Integer.toString(blockNumber));
    }

    public void createLink(final Position pos1, final Position pos2, final int links) {
        final JButton source = this.blocks.get(pos1);
        final JButton destination = this.blocks.get(pos2);
        if (links == 1) {
            this.links.add(new Link(source, destination, links));
        } else if (links == 2) {
            this.links.get(this.links.indexOf(new Link(source, destination, links))).setNumLinks(links);
        } else {
            this.links.remove(new Link(source, destination, links));
        }
        repaint();
    }

    @Override
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);

        final Graphics2D g2d = (Graphics2D) g.create();
        for (final Link link : this.links) {
            g2d.draw(link.getFirstLine2D());
            if (!link.getSecondLine2D().isEmpty()) {
                g2d.draw(link.getSecondLine2D().get());
            }
        }
    }

}
