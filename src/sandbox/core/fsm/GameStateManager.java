package sandbox.core.fsm;

import static forplay.core.ForPlay.keyboard;
import static forplay.core.ForPlay.pointer;

import java.util.HashMap;
import java.util.Map;

import sandbox.core.display.DisplayManager;

public class GameStateManager {

    Map<String, GameState> states;

    GameState currentState;

    private DisplayManager displayManager;

    public GameStateManager() {
        states = new HashMap<String, GameState>();
    }

    public void addState(GameState gameState) {
        gameState.setEndState(gameState.name);
        gameState.displayManager = displayManager;
        states.put(gameState.name, gameState);
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

        // Check if state is ending
        // If it is the case, deactivate the current one and go to next
        GameState nextState = states.get(currentState.getEndState());

        if (!currentState.equals(nextState)) {
            currentState.deactivate();
            currentState = nextState;
            pointer().setListener(currentState);
            keyboard().setListener(currentState);
            
            currentState.activate();
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
