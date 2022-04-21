package main.general;

import main.dashboard.controller.MainController;

public interface GameController {

    /**
     * 
     * @param mainController
     */
    void setMainController(MainController mainController);

    /**
     * 
     * @return
     */
    GameView getView();

    /**
     * 
     * @return 
     */
    String getGameName();

    /**
     * 
     */
    void startGame();

    /**
     * 
     */
    void endGame();

    /**
     * 
     */
    void pause();

    /**
     * 
     */
    void resume();

    /**
     * 
     */
    void closeGame();
}
