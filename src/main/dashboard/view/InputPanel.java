package main.dashboard.view;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that represents a panel to get an input for a game.
 */
public class InputPanel extends JPanel {

    private static final long serialVersionUID = 8517532871235887502L;

    private final JComboBox<Integer> comboBox;

    public InputPanel(final String labelName, final List<Integer> valuesList) {
      final JLabel label = new JLabel(labelName + ": ");
      final GridBagConstraints constraints = new GridBagConstraints();
      constraints.anchor = GridBagConstraints.EAST;
      constraints.insets = new Insets(0, 0, 5, 5);
      constraints.gridx = 1;
      constraints.gridy = 2;
      // colorsConstr.weightx = 1;
      // colorsConstr.weighty = 1;
      add(label, constraints);

      this.comboBox = new JComboBox<>(valuesList.toArray(new Integer[0]));
      constraints.anchor = GridBagConstraints.WEST;
      constraints.insets = new Insets(0, 0, 5, 0);
      constraints.gridx = 2;
      constraints.gridy = 2;
      // colorsComboConstr.weightx = 1;
      // colorsComboConstr.weighty = 1;
      add(this.comboBox, constraints);
    }

    /**
     * Gets the selected input.
     * @return the selected input
     */
    public int getInput() {
        return (int) this.comboBox.getSelectedItem();
    }

}
