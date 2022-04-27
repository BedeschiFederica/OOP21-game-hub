package main.dashboard.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import main.dashboard.controller.MainController;
import main.general.GameController;

/** 
 * Class that is needed for the endGame.
 * 
 * 
 */
public class EndGame extends JFrame {

    private static final long serialVersionUID = -2947972819890283488L;
    private static final int FONTSIZE = 48;

//    private JLabel jlResult = new JLabel();
//    private JPanel jp = new JPanel();

    /**
     * Creation of the Gui graphic.
     * 
     * @param mainController needed to go to the main controller
     * @param gameController need to know what game had ended
     * @param isVictory need to know if it's a victory or not
     */
    public EndGame(final MainController mainController, final GameController gameController, final boolean isVictory) {
        final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setTitle("SCORE");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

        final JPanel jp = new JPanel();

        final JLabel jlResult;
        final Font fState = new Font("Georgia", Font.BOLD, FONTSIZE);
        if (isVictory) {
            jlResult = new JLabel("You Won!!!");
        } else {
            jlResult = new JLabel("You Lost!Try Again!");
        }
        jp.add(jlResult);
        jlResult.setAlignmentX(Component.CENTER_ALIGNMENT);
        jlResult.setFont(fState);

        final JButton jbNewGame = new JButton("NEW GAME");
        final JButton jbMenu = new JButton("RETURN TO MAIN MENU");
        jbNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
        jbNewGame.addActionListener(e -> {
            mainController.showStartPanel(gameController.getGameName());
            this.dispose();
        });
        jbMenu.addActionListener(e -> {
            mainController.showMainMenu();
            this.dispose();
        });
        jp.setBackground(Color.CYAN);
        jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
        jp.add(jbNewGame);
        jp.add(jbMenu);

        this.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
        this.getContentPane().add(jp);
        this.pack();
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }

//    /**
//     * Method used to show if the player has won or lost.
//     * 
//     * @param state if true the player has won, if false he has lost.
//     */
//    public void status(final boolean state) {
//        final Font fState = new Font("Georgia", Font.BOLD, FONTSIZE);
//        if (state == true) {
//            this.jlResult = new JLabel("You Won!!!");
//        } else {
//            this.jlResult = new JLabel("You Lost!Try Again!");
//        }
//        jp.add(jlResult);
//        jlResult.setAlignmentX(Component.CENTER_ALIGNMENT);
//        jlResult.setFont(fState);
//    }

//    /**
//     * Method used to show the frame.
//     */
//    public void show() {
//        jf.setVisible(true);
//    }
//
//    /**
//     * Method used to hide the frame.
//     */
//    private void hide() {
//        jf.setVisible(false);
//    }

}
