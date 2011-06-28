package sandbox.core.fsm;

import static forplay.core.ForPlay.keyboard;
import static forplay.core.ForPlay.pointer;

import java.util.ArrayList;
import java.util.List;

import sandbox.core.display.DisplayManager;

public class GameStateManager {

    List<GameState> states;

    GameState currentState;

    private DisplayManager displayManager;

    public GameStateManager() {
        states = new ArrayList<GameState>();
    }

    public void addState(GameState gameState) {
        gameState.setEndState(gameState);
        gameState.displayManager = displayManager;
        states.add(gameState);
    }

    public void addFirstState(GameState gameState) {
        currentState = gameState;

        pointer().setListener(gameState);
        keyboard().setListener(gameState);

        addState(gameState);
    }

    public void update(float delta) {

        // Activate and display the current state
        currentState.update(delta);
        currentState.activate();

        // Check if state is ending
        // If it is the case, deactivate the current one and go to next
        GameState nextState = currentState.getEndState();

        if (!currentState.equals(nextState)) {
            currentState.deactivate();
            currentState = nextState;
            pointer().setListener(currentState);
            keyboard().setListener(currentState);
            currentState.display();
        }

    }

    public void setDisplayManager(DisplayManager displayManager) {
        this.displayManager = displayManager;
    }

    public void init() {
        displayManager.init();
        currentState.display();
    }

}
