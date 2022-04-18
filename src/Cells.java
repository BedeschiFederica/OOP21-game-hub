
public class Cells {

	//position of the cell
	int posX,posY;
	boolean visible; //true if the cell is clicked by the player
	boolean posMine; //true if the cell contains a mine 
	boolean flag;	//true if the cell contains a flag
	int closerMine;

	public Cells(int posX, int posY) {
		this.posX = posX;
		this.posY = posY;
		visible=false;
	}
	
	//dato temporaneamente una height e una widht io
		int widthCell=30;
		int heightCell=30;
		
	//da mettere poi in un'altra parte
	void Draw() {
		//position of the first cell
		int firstX=posX*widthCell;
		int firstY=posY*heightCell;
		//position of the last cell
		int lastX=posX+widthCell;
		int lasstY=posY+heightCell;
		//i quadratini che voglio disegnare devono essere senza bordi?
		//evento del mouse quando ci passa sopra ci serve per capire quando il player clicca su 
		//un quadrato o meno
		
	}
	
}