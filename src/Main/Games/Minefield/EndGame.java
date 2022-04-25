package Main.Games.Minefield;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


/** 
 * Class that is needed for the endGame.
 * 
 * 
 */
public class EndGame {
  JFrame jf = new JFrame();
  JLabel jlResult = new JLabel();
  JPanel jp = new JPanel();
  
  /** 
   * Creation of the Gui graphic. 
   */
  public EndGame() {
        
    jf.setTitle("SCORE");
    jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    JButton jbNewGame = new JButton("NEW GAME");
    JButton jbMenu = new JButton("RETURN TO MAIN MENU");
        
    jbNewGame.addActionListener(e -> {
      hide(); });
    jbMenu.addActionListener(e -> { 
      hide(); });
    jp.add(jbNewGame);
    jp.add(jbMenu);
    jf.getContentPane().add(jp);
    jf.pack();
         
  }
  
  
  /** 
   * Method used to show if the player has won or lost. 
   */
  public void status(boolean state) {
    if (state == true) {
      this.jlResult = new JLabel("You Won!!!");
    } else {
      this.jlResult = new JLabel("You Lost!Try Again!"); 
    }
    jp.add(jlResult);
  }

  public void show() {
    jf.setVisible(true);
  }
  
  private void hide() {
    jf.setVisible(false);
  }

}
