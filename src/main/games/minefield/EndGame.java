package main.games.minefield;

import javax.swing.*;

import main.general.GameController;

public class EndGame {
    JFrame jf = new JFrame();
    JLabel jlResult = new JLabel();
    JPanel jp = new JPanel();

    public EndGame() { // final GameController controller, final MainController mainController
        // Creation of the Gui graphic

        jf.setTitle("SCORE");
        jf.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        JButton jbNewGame = new JButton("NEW GAME");
        JButton jbTryAgain = new JButton("TRY AGAIN");
        JButton jbMenu = new JButton("RETURN TO MAIN MENU");

        jbNewGame.addActionListener(e -> {
            hide(); // mainController.startGame(controller);
        });
        jbTryAgain.addActionListener(e -> {
            hide();
        });
        jbMenu.addActionListener(e -> {
            hide(); // mainController.showMainMenu();
        });
        jp.add(jbNewGame);
        jp.add(jbTryAgain);
        jp.add(jbMenu);
        jf.getContentPane().add(jp);
        jf.pack();

    }

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
