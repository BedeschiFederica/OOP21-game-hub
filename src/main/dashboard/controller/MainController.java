package main.dashboard.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.dashboard.view.MainMenu;
import main.general.GameController;

public class MainController {

    private final Map<String, GameController> controllers = new HashMap<>();
    private final MainMenu menu;

    public MainController(final GameController... controllers) {
        for (final GameController c : controllers) {
            this.controllers.put(c.getGameName(), c);
            c.setMainController(this);
        }
        this.menu = new MainMenu(this, getGameNames());
    }

    private List<String> getGameNames() {
        return this.controllers.values().stream().map(c -> c.getGameName()).collect(Collectors.toList());
    }

    public void startGame(final String gameName) {
        this.controllers.get(gameName).startGame();
    }

    public void showGameEnding() {
        // calling class GameEnding

        // temporary (this will be called by class GameEnding)
        showMainMenu();
    }

    public void showMainMenu() { // called by class GameEnding
        //this.menu.pack();
        this.menu.setVisible(true);
    }

    public void pauseGame(final GameController controller) {
        // calling class PauseMenu (passing argument gameName)

        // temporary (this will be called by class PauseMenu)
        resumeGame(controller);
        System.out.println("pause");
    }

    public void resumeGame(final GameController controller) { // called by class PauseMenu
        controller.resume();
    }

}
