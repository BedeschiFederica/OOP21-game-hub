package main.games.numericalbond.view;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JButton;
import java.awt.geom.Line2D;

public class Link {

    private static final int SPACE_CONST = 6;

    private final JButton source;
    private final JButton destination;
    private int numLinks;

    public Link(final JButton source, final JButton destination, final int numLinks) {
        this.source = source;
        this.destination = destination;
        this.numLinks = numLinks;
    }

    public void setNumLinks(final int numLinks) {
        this.numLinks = numLinks;
    }

    private double getXFromCenter(final JButton jb) {
        return jb.getWidth() / SPACE_CONST;
    }

    private double getYFromCenter(final JButton jb) {
        return jb.getHeight() / SPACE_CONST;
    }
    
//    public Line2D getFirstLine2D() {
//        return new Line2D.Double(
//                this.source.getBounds().getCenterX() - getXFromCenter(this.source),
//                this.source.getBounds().getCenterY() - getYFromCenter(this.source),
//                this.destination.getBounds().getCenterX() - getXFromCenter(this.destination),
//                this.destination.getBounds().getCenterY() - getYFromCenter(this.destination)
//        );
//    }
//
//    public Optional<Line2D> getSecondLine2D() {
//        if (this.numLinks == 2) {
//            return Optional.of(new Line2D.Double(
//                    this.source.getBounds().getCenterX() + getXFromCenter(this.source),
//                    this.source.getBounds().getCenterY() + getYFromCenter(this.source),
//                    this.destination.getBounds().getCenterX() + getXFromCenter(this.destination),
//                    this.destination.getBounds().getCenterY() + getYFromCenter(this.destination)
//            ));
//        }
//        return Optional.empty();
//    }

    private Line2D getLine2D(final double delayX, final double delayY) {
        return new Line2D.Double(
                this.source.getBounds().getCenterX() + delayX,
                this.source.getBounds().getCenterY() + delayY,
                this.destination.getBounds().getCenterX() + delayX,
                this.destination.getBounds().getCenterY() + delayY
        );
    }

    public Line2D getFirstLine2D() {
        if (this.numLinks == 1) {
            return getLine2D(0, 0);
        }
        return getLine2D(-getXFromCenter(this.source), -getYFromCenter(this.source));
    }

    public Optional<Line2D> getSecondLine2D() {
        if (this.numLinks == 2) {
            return Optional.of(getLine2D(getXFromCenter(this.source), getYFromCenter(this.source)));
        }
        return Optional.empty();
    }

    public JButton getSource() {
        return this.source;
    }

    public JButton getDestination() {
        return this.destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(destination, source);
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        return (Objects.equals(this.source, other.source) && Objects.equals(this.destination, other.destination))
                || (Objects.equals(this.source, other.destination) && Objects.equals(this.destination, other.source));
    }

}
