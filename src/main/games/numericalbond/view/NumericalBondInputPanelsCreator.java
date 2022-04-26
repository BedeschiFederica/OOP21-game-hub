package main.games.numericalbond.view;

import java.util.List;

import main.dashboard.view.InputPanel;

/**
 * Utility class used to create the input panels of the game Numerical bond.
 */
public final class NumericalBondInputPanelsCreator {

    private NumericalBondInputPanelsCreator() {
    }

    /**
     * Gets the input panels.
     * @return the input panels
     */
    public static List<InputPanel> getInputPanels() {                   // just for testing
        return List.of(new InputPanel("Number of lines", List.of(3, 4)), new InputPanel("Something", List.of(1, 2, 3)));
    }
}
