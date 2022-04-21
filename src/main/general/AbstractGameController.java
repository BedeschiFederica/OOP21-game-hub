package main.general;

public abstract class AbstractGameController implements GameController {

    private MainController mainController;

    /**
     * {@inheritDoc}
     */
    @Override
    public void setMainController(final MainController mainController) {
        this.mainController = mainController;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void endGame() {
        this.mainController.showGameEnding();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void pause() {
        this.mainController.pauseGame(this);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void resume() {
        getView().setVisible(true);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void closeGame() {
        this.mainController.showMainMenu();
    }

}
