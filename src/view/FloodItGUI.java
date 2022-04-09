package view;

import javax.swing.*;

import model.Colors;

import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class FloodItGUI extends JFrame {
    
    private static final long serialVersionUID = -6218820567019985015L;
    private final List<JButton> cells = new ArrayList<>();
    Random randColor = new Random();
    
    public FloodItGUI(int size, List<Colors> colors) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
        	
        };
                
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(" ");
                this.cells.add(jb);
                jb.setBackground(colors.get(randColor.nextInt(colors.size())).getActualColor());     //getChosenColor(randColor.nextInt(colors)));
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        this.setSize(sw / 2, sw / 2);
        this.setLocationByPlatform(true);
        this.setVisible(true);
    }
}