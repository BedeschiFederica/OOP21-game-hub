package main.gamehub.model;

/**
 * Interface of a generic game view.
 */
public interface GameView {

    /**
     * Sets the visibility of the view.
     * @param visible
     *          indicates if the view has to be visible or not
     */
    void setVisible(boolean visible);

    /**
     * Closes the view.
     */
    void dispose();

}
