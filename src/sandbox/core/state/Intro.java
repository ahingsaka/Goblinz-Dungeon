package sandbox.core.state;

import sandbox.core.fsm.GameState;
import forplay.core.Keyboard;

public class Intro extends GameState {
    
    public static final String NAME = "intro"; 

    public Intro() {
        name = NAME;
    }

    @Override
    protected void display() {
        displayManager.clear();
        displayManager.fontManager.addTextLayer("goblinz dungeon", 230, 170);
        displayManager.fontManager.addTextLayer("press space to begin", 200, 220);
    }

    @Override
    protected void activate() {
        endState = NAME;
    }

    @Override
    protected void deactivate() {
        displayManager.fontManager.clear();
    }

    @Override
    protected void update(float d) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onKeyDown(int keyCode) {
        super.onKeyDown(keyCode);

        switch (keyCode) {

        case Keyboard.KEY_SPACE:
            endState = Gaming.NAME;
            break;
        }

    }

}
