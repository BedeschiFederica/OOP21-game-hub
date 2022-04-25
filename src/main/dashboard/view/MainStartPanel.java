package main.dashboard.view;

import javax.swing.*;

import main.dashboard.controller.MainController;
import main.games.floodit.model.Colors;
import main.general.GameController;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainStartPanel extends JPanel {

    private static final long serialVersionUID = 1188589790801007209L;
    private final CardLayout mainLayout;

    private JComboBox<Integer> cmbColors;
    private JComboBox<Integer> cmbCells;

    public MainStartPanel(final JPanel mainPanel, final CardLayout mLayout, GameController controller, MainController mainController) {
        this.mainLayout = mLayout;
        setBackground(Colors.LIGHT_BLUE.getActualColor());
        final GridBagLayout layout = new GridBagLayout();
        setLayout(layout);

        final JLabel lblTitle = new JLabel(controller.getGameName());
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
        lblTitle.setForeground(Colors.YELLOW.getActualColor());
        final GridBagConstraints titleConstr = new GridBagConstraints();
        titleConstr.fill = GridBagConstraints.BOTH;
        titleConstr.insets = new Insets(0, 0, 5, 0);
        titleConstr.gridwidth = 2;
        titleConstr.gridx = 1;
        titleConstr.gridy = 0;
        add(lblTitle, titleConstr);

        final JButton btnRules = new JButton("RULES");
        btnRules.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                //JDialog??
            }
        });
        final GridBagConstraints rulesConstr = new GridBagConstraints();
        rulesConstr.fill = GridBagConstraints.VERTICAL;
        rulesConstr.gridwidth = 2;
        rulesConstr.gridx = 1;
        rulesConstr.gridy = 3;
        add(btnRules, rulesConstr);

        final JButton btnStart = new JButton("PLAY");
        btnStart.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(final ActionEvent e) {
                mainController.startGame(controller.getGameName());
            }
        });
        final GridBagConstraints startConstr = new GridBagConstraints();
        startConstr.fill = GridBagConstraints.VERTICAL;
        startConstr.gridwidth = 2;
        startConstr.gridx = 2;
        startConstr.gridy = 3;
        // startConstr.weightx = 1;
        // startConstr.weighty = 1;
        add(btnStart, startConstr);
    }
}
