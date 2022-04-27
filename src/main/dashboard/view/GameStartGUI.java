package main.dashboard.view;

import main.dashboard.controller.MainController;
import main.general.GameController;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameStartGUI extends JFrame {

    private static final long serialVersionUID = 1188589790801007209L;
    private static final int FRAME_SIZE_DIV = 2;
    private static final Font TITLE_FONT = new Font("Tahoma", Font.BOLD, 50);
    private static final Font NORMAL_FONT = new Font("Tahoma", Font.BOLD, 25);
    private static final Dimension MINIMUM_FRAME_DIMENSION = new Dimension(500, 500);

    private final List<InputPanel> inputPanels;

    public GameStartGUI(final MainController mainController, final GameController controller) {

        this.inputPanels = controller.getInputPanels();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setPreferredSize(new Dimension(screenSize.width / FRAME_SIZE_DIV, screenSize.height / FRAME_SIZE_DIV));
        this.setMinimumSize(MINIMUM_FRAME_DIMENSION);

        final JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(DashboardColor.BACKGROUND.getActualColor());
        this.getContentPane().add(mainPanel);

        final JLabel lblTitle = new JLabel(controller.getGameName());
        lblTitle.setFont(TITLE_FONT);
        lblTitle.setForeground(DashboardColor.TITLE.getActualColor());
        final GridBagConstraints titleConstr = new GridBagConstraints();
        titleConstr.fill = GridBagConstraints.BOTH;
        titleConstr.gridx = 0;
        titleConstr.gridy = 0;
        mainPanel.add(lblTitle, titleConstr);

        final JPanel inputPanel = new JPanel();
        inputPanel.setBackground(DashboardColor.BACKGROUND.getActualColor());
        final GridBagConstraints inputConstr = new GridBagConstraints();
        inputConstr.fill = GridBagConstraints.VERTICAL;
        inputConstr.gridx = 0;
        inputConstr.gridy = 1;
        mainPanel.add(inputPanel, inputConstr);
        this.inputPanels.forEach(p -> inputPanel.add(p));

        final JButton btnStart = new JButton("PLAY");
        btnStart.setFont(NORMAL_FONT);
        btnStart.setForeground(DashboardColor.BACKGROUND.getActualColor());
        btnStart.setBackground(DashboardColor.BUTTON.getActualColor());
        btnStart.setBorder(BorderFactory.createRaisedBevelBorder());
        btnStart.addActionListener(e -> {
            mainController.startGame(controller, this.inputPanels.stream().mapToInt(p -> p.getInput()).toArray());
            this.dispose();
        });
        final GridBagConstraints startConstr = new GridBagConstraints();
        startConstr.fill = GridBagConstraints.VERTICAL;
        startConstr.gridx = 0;
        startConstr.gridy = 2;
        mainPanel.add(btnStart, startConstr);

        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}
