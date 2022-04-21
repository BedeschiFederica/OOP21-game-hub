package Main.Games.Minefield.Controller;

import Main.Games.Minefield.Model.*;
import javax.swing.*;
import java.awt.event.*;

public class Cell extends JButton {

    private int type;
    private int position;
    private boolean discovered;
    private boolean flagged;

    private Handler handler;

    public Cell(int type, int position, boolean discovered, boolean flagged, Handler handler) {
    	/**
    	 * variable used for knowing information about a cell
    	 *
    	 */
        this.type = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(SwingUtilities.isRightMouseButton(e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }

            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
        });
    }

    public int getType() {
    	/**
		 * method that returns:
		 * - 0 if the cell is empty
		 * - 1 if there is a mine
		 * - 2 of there is a number
		 */
        return type;
    }

    public int getPosition() {
        return position;
    }

    public boolean isDiscovered() {
        return discovered;
    }

    public void setDiscovered(boolean d) {
        this.discovered = d;
    }

    public boolean isFlagged() {
        return flagged;
    }

    public void setFlagged(boolean f) {
        this.flagged = f;
    }

    public void clickButton() {
    	/**
		 * method that discovers the cells
		 */
        handler.click(this);
    }

    public void rightClickButton() {
    	/**
		 * method that puts the flag in the cell
		 */
        handler.rightClick(this);
    }
}
