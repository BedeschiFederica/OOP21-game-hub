package main.numericalbond.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    private final Map<JButton, Position> blocks = new HashMap<>();
    private final List<Line2D> links = new ArrayList<>();
    private boolean isOneBlockSelected;
    private JButton selectedBlock;
    private final NumericalBondController controller;

    public NumericalBondView(final NumericalBondController controller) {
        this.controller = controller;
        final int lines = this.controller.getNumGridLines();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(SIZE_CONST * lines, SIZE_CONST * lines));

        final JPanel panel = new JPanel(new GridLayout(lines, lines, SIZE_CONST * lines / (2 * lines - 1),
                SIZE_CONST * lines / (2 * lines - 1)));
        this.getContentPane().add(panel);

        final ActionListener al = e -> {
            final JButton jb = (JButton) e.getSource();
            if (!this.isOneBlockSelected) {
                select(jb);
            } else if (jb.equals(this.selectedBlock)) {
                deselect();
            } else if (!this.controller.canLink(this.blocks.get(this.selectedBlock), this.blocks.get(jb))) {
                // dialog?
                System.out.println("can't link");
            } else {
                createLink(this.selectedBlock, jb);
                this.controller.link(this.blocks.get(this.selectedBlock), this.blocks.get(jb));
                updateNumbers(jb);
                deselect();
                if (this.controller.gameEnded()) {
                    for (final JButton b : this.blocks.keySet()) {
                        b.setEnabled(false);
                    }
                    System.out.println("You won!");
                }
            }
        };

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < lines; j++) {
                final Position pos = new Position(i, j);
                final JButton jb = new JButton(Integer.toString(this.controller.getBlockNumber(pos)));
                this.blocks.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);

        //System.out.println(this.getContentPane().getSize());
        //System.out.println(this.getBounds());
    }

    private void updateNumbers(final JButton jb) {
        jb.setText(Integer.toString(this.controller.getBlockNumber(this.blocks.get(jb))));
        this.selectedBlock
                .setText(Integer.toString(this.controller.getBlockNumber(this.blocks.get(this.selectedBlock))));
    }

    private void select(final JButton jb) {
        this.selectedBlock = jb;
        this.selectedBlock.setBackground(Color.LIGHT_GRAY);
        this.isOneBlockSelected = true;
    }

    private void deselect() {
        this.selectedBlock.setBackground(null);
        this.isOneBlockSelected = false;
    }

    private void createLink(final JButton jb1, final JButton jb2) { // to be modified
        final int links = this.controller.getLinks(this.blocks.get(jb1), this.blocks.get(jb2));
        if (links == 0) {
            this.links.add(new Line2D.Double(jb1.getBounds().getCenterX() + getDiffX(),
                    jb1.getBounds().getCenterY() + getDiffY(), jb2.getBounds().getCenterX() + getDiffX(),
                    jb2.getBounds().getCenterY() + getDiffY()));
        } else if (links == 1) {
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
        revalidate();
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
