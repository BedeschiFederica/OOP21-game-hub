package main.games.minefield.controller;

import main.games.minefield.model.Handler;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import java.awt.event.*;

public class Cell extends JButton {

    private int type;
    private int position;
    private boolean discovered;
    private boolean flagged;

    private Handler handler;

    /**
     * variable used for knowing information about a cell.
     * @param type is used for knowing if the cell is empty, a mine or a number.
     * @param position is needed for knowing the position of the cell.
     * @param discovered says if the cell is discovered by the player or not.
     * @param flagged is used to know if the player put a flag in the cell or not.
     * @param handler that makes all the check for the game.
     *
     */
    public Cell(final int type, final int position, final boolean discovered, final boolean flagged, final Handler handler) {
        this.type = type;
        this.position = position;
        this.discovered = discovered;
        this.flagged = flagged;
        this.handler = handler;

        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(final MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    rightClickButton();
                } else {
                    clickButton();
                }
            }

            public void mouseEntered(final MouseEvent e) {
            }
            public void mouseExited(final MouseEvent e) {
            }
            public void mousePressed(final MouseEvent e) {
            }
            public void mouseReleased(final MouseEvent e) {
            }
        });
    }

    /**
     *@return method that returns the type.
     * - 0 if the cell is empty
     * - 1 if there is a mine
     * - 2 of there is a number
     */
    public int getType() {
        return type;
    }

    /**
     *@return the position of the cell.
     */
    public int getPosition() {
        return position;
    }

    /**
     *@return if the cell is discovered.
     */
    public boolean isDiscovered() {
        return discovered;
    }

    /**
     *sets if the cell is discovered or not.
     *@param discover if cell is pressed by the player.
     */
    public void setDiscovered(final boolean discover) {
        this.discovered = discover;
    }

    /**
     *@return if the cell is flagged.
     */
    public boolean isFlagged() {
        return flagged;
    }

    /**
     *@param flag used to set if cell is flagged
     */
    public void setFlagged(final boolean flag) {
        this.flagged = flag;
    }

    /**
     *method that discovers the cells.
     */
    public void clickButton() {
        handler.click(this);
    }

    /**
     * method that puts the flag in the cell.
     */
    public void rightClickButton() {
        handler.rightClick(this);
    }
}
