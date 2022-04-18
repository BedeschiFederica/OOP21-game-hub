import java.awt.*;

public class ViewField {

	//creation of the field of minefield
	
	//this information is chose from the player
	private int rows=8;
	private int colums=8;
	private int mine=2;
	Cells[][] cell;
	//da vedere come si fa
	
	//private int widthCell, heightCell;
	
	void build() {
		//widthCell= getScreenSize().getWidth()/rows;
		//heightCell=height/colums;
		
		//creating the table for the game and the position of the cells
		cell= new Cells[rows][colums];
		for (int i=0;i< rows; i++) {
			for (int j=0;j<colums;j++) {
				cell[i][j]= new Cells(i,j);
			}
		}
		//Da toglire appena si usano le mine 
		System.out.print(mine);
	}
	
	//questa deve essere implementato solo nella view del gioco
	void draw() {
		
	}
	
	
	
}
