package Main.Games.Minefield.View;

import Main.Games.Minefield.Controller.*;
import Main.Games.Minefield.Model.*;
import javax.swing.*;
import java.awt.*;

public class ViewField {

    public static JFrame frame;
    private static String title;

    public ViewField(int Size, String title, StartGame StartGame, Handler handler) {
        ViewField.title = title;
        frame = new JFrame(title);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel jpField = new Field(new GridLayout(Size, Size), handler);

        frame.setContentPane(jpField);
        update(0);
        frame.pack();
        frame.setVisible(true);
    }

    public static void update(int flag) {
        frame.setTitle(title + "Mines: " + StartGame.MINES + " - Flags: " + flag);
    }
}
