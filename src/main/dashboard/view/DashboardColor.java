package main.dashboard.view;

import java.awt.Color;

/**
 * Colors used by the dashboard.
 */
public enum DashboardColor {
    /**
     * Background color.
     */
    BACKGROUND("BackgroundColor", new Color(0, 57, 77)),
    /**
     * Buttons color.
     */
    BUTTON("ButtonsColor", new Color(0, 153, 204)),
    /**
     * Titles color.
     */
    TITLE("TitlesColor", new Color(179, 235, 255));

    private final String name;
    private final Color actualColor;

    DashboardColor(final String name, final Color color) {
        this.name = name;
        this.actualColor = color;
    }

    /**
     * @return The color's name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return The java.awt.Color associated to the color.
     */
    public Color getActualColor() {
        return this.actualColor;
    }

    @Override
    public String toString() {
        return this.name + ": " + this.actualColor;
    }
}
