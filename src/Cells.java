import java.awt.*;

import javax.swing.JButton;
public class Cells {
	private int SPACING =5;
	private int rows=8;
	private int colums=8;

	protected void cell(Graphics cell) {
		for (int i=0;i< rows; i++) {
			for (int j=0;j<colums;j++) {
				cell.fillRect(SPACING+i*80, SPACING+j*80+80, 80-2*SPACING, 80-2*SPACING);
			}
		}
	}
}