package main.games.numericalbond.view;

import java.util.Objects;
import java.util.Optional;

import javax.swing.JButton;
import java.awt.geom.Line2D;

/**
 * Class that represents a link between two blocks (JButtons).
 */
public class Link {

    private static final int SPACE_CONST = 6;

    private final JButton source;
    private final JButton destination;
    private int numLinks = 1;

    /**
     * Builds a new {@link Link}.
     * @param source
     *          the source of the link
     * @param destination
     *          the destination of the link
     */
    public Link(final JButton source, final JButton destination) {
        this.source = source;
        this.destination = destination;
    }

    /**
     * Sets the number of current links.
     * @param numLinks
     *          the number of links that has to be set
     */
    public void setNumLinks(final int numLinks) {
        this.numLinks = numLinks;
    }

    private double getXFromCenter(final JButton jb) {
        return jb.getWidth() / SPACE_CONST;
    }

    private double getYFromCenter(final JButton jb) {
        return jb.getHeight() / SPACE_CONST;
    }

    private Line2D getLine2D(final double delayX, final double delayY) {
        return new Line2D.Double(
                this.source.getBounds().getCenterX() + delayX,
                this.source.getBounds().getCenterY() + delayY,
                this.destination.getBounds().getCenterX() + delayX,
                this.destination.getBounds().getCenterY() + delayY
        );
    }

    /**
     * Gets the first line of the link.
     * @return the first line
     */
    public Line2D getFirstLine2D() {
        if (this.numLinks == 1) {
            return getLine2D(0, 0);
        }
        return getLine2D(-getXFromCenter(this.source), -getYFromCenter(this.source));
    }

    /**
     * Gets, if present, the second line of the link.
     * @return the second line, Optional.empty() if it is't present
     */
    public Optional<Line2D> getSecondLine2D() {
        if (this.numLinks == 2) {
            return Optional.of(getLine2D(getXFromCenter(this.source), getYFromCenter(this.source)));
        }
        return Optional.empty();
    }

    /**
     * Gets the source.
     * @return the source
     */
    public JButton getSource() {
        return this.source;
    }

    /**
     * Gets the destination.
     * @return the destination
     */
    public JButton getDestination() {
        return this.destination;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(destination, source);
    }

    /**
     * {@inheritDoc}
     */
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
        return Objects.equals(this.source, other.source) && Objects.equals(this.destination, other.destination)
                || Objects.equals(this.source, other.destination) && Objects.equals(this.destination, other.source);
    }

}
