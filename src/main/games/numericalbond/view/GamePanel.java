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

/**
 * Class that represents the game panel of the game Numerical bond.
 * It shows a grid of blocks (JButtons).
 */
public class GamePanel extends JPanel {

    static final long serialVersionUID = -3838746067867960423L;

    private final NumericalBondView view;
    private final Map<JButton, Position> positions = new HashMap<>();
    private final Map<Position, JButton> blocks = new HashMap<>();
    private final List<Link> links = new ArrayList<>();
    private boolean isOneBlockSelected;
    private JButton selectedBlock;

    /**
     * Builds a new {@link GamePanel}.
     * @param view
     *          the view that has the panel
     */
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

    /**
     * Deselects the selected block.
     */
    public void deselect() {
        this.selectedBlock.setBackground(getBackground());
        this.isOneBlockSelected = false;
    }

    /**
     * Sets the number of the block specified by the given position to the given block number.
     * @param pos
     *          the position of the block
     * @param blockNumber
     *          the block number that has to be set
     */
    public void setBlockNumber(final Position pos, final int blockNumber) {
        this.blocks.get(pos).setText(Integer.toString(blockNumber));
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
        final JButton source = this.blocks.get(pos1);
        final JButton destination = this.blocks.get(pos2);
        if (links == 1) {
            this.links.add(new Link(source, destination));
        } else if (links == 2) {
            this.links.get(this.links.indexOf(new Link(source, destination))).setNumLinks(links);
        } else {
            this.links.remove(new Link(source, destination));
        }
        repaint();
    }

    /**
     * {@inheritDoc}
     */
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
