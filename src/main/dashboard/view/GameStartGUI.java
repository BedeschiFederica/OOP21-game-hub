package main.dashboard.view;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.dashboard.controller.MainController;
import main.games.floodit.model.Colors;
import main.general.GameController;

public class GameStartGUI extends JFrame {

    private static final long serialVersionUID = 1188589790801007209L;
    private static final int FRAME_SIZE_DIV = 2;

    private final List<InputPanel> inputPanels;

    public GameStartGUI(final MainController mainController, final GameController controller) {
        this.inputPanels = controller.getInputPanels();

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / FRAME_SIZE_DIV, screenSize.height / FRAME_SIZE_DIV));

        setBackground(Colors.LIGHT_BLUE.getActualColor());
        final JPanel mainPanel = new JPanel(new GridBagLayout());
        this.getContentPane().add(mainPanel);

        final JLabel lblTitle = new JLabel(controller.getGameName());
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 50));
        lblTitle.setForeground(Colors.YELLOW.getActualColor());
        final GridBagConstraints titleConstr = new GridBagConstraints();
        titleConstr.fill = GridBagConstraints.BOTH;
        //titleConstr.insets = new Insets(0, 0, 5, 0);
        //titleConstr.gridwidth = 2;
        titleConstr.gridx = 0; // 1
        titleConstr.gridy = 0;
        mainPanel.add(lblTitle, titleConstr);

        final JPanel inputPanel = new JPanel();
        final GridBagConstraints inputConstr = new GridBagConstraints();
        inputConstr.fill = GridBagConstraints.VERTICAL;
        inputConstr.gridx = 0;
        inputConstr.gridy = 1;
        mainPanel.add(inputPanel, inputConstr);
        this.inputPanels.forEach(p -> inputPanel.add(p));

//        final JButton btnRules = new JButton("RULES");
//        btnRules.addActionListener(e -> System.out.println("Rules")); // JDialog?
//        final GridBagConstraints rulesConstr = new GridBagConstraints();
//        rulesConstr.fill = GridBagConstraints.VERTICAL;
//        //rulesConstr.gridwidth = 2;
//        rulesConstr.gridx = 0; // 1
//        rulesConstr.gridy = 2; // 3
//        mainPanel.add(btnRules, rulesConstr);

        final JButton btnStart = new JButton("PLAY");
        btnStart.addActionListener(e -> {
            mainController.startGame(controller, this.inputPanels.stream().mapToInt(p -> p.getInput()).toArray());
            this.dispose();
        });
        final GridBagConstraints startConstr = new GridBagConstraints();
        startConstr.fill = GridBagConstraints.VERTICAL;
        //startConstr.gridwidth = 2;
        startConstr.gridx = 0; // 2
        startConstr.gridy = 2; // 3
        // startConstr.weightx = 1;
        // startConstr.weighty = 1;
        mainPanel.add(btnStart, startConstr);

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
