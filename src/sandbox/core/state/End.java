package sandbox.core.state;

import forplay.core.Keyboard;
import sandbox.core.fsm.GameState;

public class End extends GameState {

    public static final String NAME = "end";
    
    public End() {
        name = NAME;
    }

    @Override
    protected void display() {
        displayManager.clear();
        displayManager.fontManager.createTextLayer("Congratulations", 230, 170);
    }

    @Override
    protected void update(float d) {
        // TODO Auto-generated method stub

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
    public void onKeyDown(int keyCode) {
        super.onKeyDown(keyCode);

        switch (keyCode) {

        case Keyboard.KEY_SPACE:
            endState = Intro.NAME;
            break;
        }

    }

}
