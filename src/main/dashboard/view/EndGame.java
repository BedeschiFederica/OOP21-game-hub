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
public class EndGame {
  private JFrame jf = new JFrame();
  private JLabel jlResult = new JLabel();
  private JPanel jp = new JPanel();

  private static final int FONTSIZE = 48;
 
  /** 
   * Creation of the Gui graphic.
   * @param mainController needed to go to the main controller
   * @param gameController need to know what game had ended
   */
  public EndGame(final MainController mainController, final GameController gameController) {
    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    jf.setTitle("SCORE");
    jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    JButton jbNewGame = new JButton("NEW GAME");
    JButton jbMenu = new JButton("RETURN TO MAIN MENU");
    jbNewGame.setAlignmentX(Component.CENTER_ALIGNMENT);
    jbMenu.setAlignmentX(Component.CENTER_ALIGNMENT);
    jbNewGame.addActionListener(e -> {
      hide(); 
      mainController.startGame(gameController);
      });
    jbMenu.addActionListener(e -> { 
      hide(); 
      mainController.showMainMenu(); });
    jp.setBackground(Color.cyan);
    jp.setLayout(new BoxLayout(jp, BoxLayout.PAGE_AXIS));
    jp.add(jbNewGame);
    jp.add(jbMenu);
    jf.setPreferredSize(new Dimension(screenSize.width / 2, screenSize.height / 2));
    jf.getContentPane().add(jp);
    jf.pack();
  }
  /** 
   * Method used to show if the player has won or lost. 
   * @param state if true the player has won, if false he has lost.
   */
  public void status(final boolean state) {
      final Font fState = new Font("Georgia", Font.BOLD, FONTSIZE);
    if (state == true) {
      this.jlResult = new JLabel("You Won!!!");
    } else {
      this.jlResult = new JLabel("You Lost!Try Again!"); 
    }
    jp.add(jlResult);
    jlResult.setAlignmentX(Component.CENTER_ALIGNMENT);
    jlResult.setFont(fState);
  }
  /** 
   * Method used to show the frame. 
   */
  public void show() {
    jf.setVisible(true);
  }
  /** 
   * Method used to hide the frame. 
   */
  private void hide() {
    jf.setVisible(false);
  }
}
