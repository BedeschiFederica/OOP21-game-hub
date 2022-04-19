import java.awt.*;
import java.util.*;

import javax.swing.*;

public class ViewField {

	//creation of the field of minefield
	
	//this information is chose from the player
	private int rows=8;
	private int colums=8;
	private int mine=2;
	Cells[][] cell;
	
	
	//private int widthCell, heightCell;
	
	
	ViewField() {
		
		JFrame Field = new JFrame();
		Field.setTitle("CAMPO MINATO");
		
		
		//da riguardare button della pausa
		JButton jbPause = new JButton("PAUSE");
		jbPause.setBackground(Color.lightGray);
		jbPause.setPreferredSize(new Dimension(500,50));
		JLabel jlMine = new JLabel("Mine presenti:" + mine);
		
		
		
		
		
		//da capire se usare il frame della Silvia
		//jbPause.addActionListener(e -> {pause();});
		JPanel jpField=new JPanel();
		jpField.setBackground(Color.cyan);
		jpField.add(jbPause);
		jpField.add(jlMine);
		Field.getContentPane().add(jpField);
		Field.pack();
		Field.setVisible(true);
		
	}


		
		
		
		
		/*
		//widthCell= getWidth()/rows;
		heightCell=getHeight()/colums;
		
		creating the table for the game and the position of the cells
		cell= new Cells[rows][colums];
		for (int i=0;i< rows; i++) {
			for (int j=0;j<colums;j++) {
				cell[i][j]= new Cells(i,j);
			}
		}
		//Da toglire appena si usano le mine 
		System.out.print(mine);
		}*/
	
	
	
	
}
