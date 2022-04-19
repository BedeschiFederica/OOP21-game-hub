import java.awt.*;
import javax.swing.*;

public class EndGame {

	//attribute used for displaying if the player won or not
	private JLabel state = new JLabel();
	
	//class used when the game ends with your status
	EndGame(){
		JFrame Field = new JFrame();
		Field.setTitle("SCORE");
		
		
		
		JButton jbNewGame = new JButton("New Game");
		jbNewGame.setBackground(Color.lightGray);
		JButton jbMenu = new JButton("Ritorna al menu principale");
		jbMenu.setBackground(Color.lightGray);
		JButton jbTryAgain = new JButton("Try Again");
		jbTryAgain.setBackground(Color.lightGray);
		
		
		
		
		//usare frame della Silvia per tutti i bottoni
		//jbPause.addActionListener(e -> {pause();});
		JPanel jpField=new JPanel();
		jpField.setBackground(Color.cyan);
		jpField.add(state);
		jpField.add(jbNewGame);
		jpField.add(jbTryAgain);
		jpField.add(jbMenu);
		Field.getContentPane().add(jpField);
		Field.pack();
	}
	
	//dopo da cambiare
	@SuppressWarnings("unused")
	private void show(JFrame frame) {
		frame.setVisible(true);
	}
	
	private void status(boolean state) {
		if (state==true) {
			this.state=new JLabel("You Won!!");
		}
		else
			{this.state=new JLabel("You Lost!! Try Again!");
			}
		
	}
}
