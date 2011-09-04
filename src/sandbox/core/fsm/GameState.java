package sandbox.core.fsm;

import sandbox.core.display.DisplayManager;
import forplay.core.Keyboard;
import forplay.core.Pointer;

public abstract class GameState implements Keyboard.Listener, Pointer.Listener {

    protected String name;

    protected String endState;

    // TODO should be a Display obj
    protected DisplayManager displayManager;

    protected abstract void display();

    protected abstract void update(float d);

    protected abstract void activate();

    protected abstract void deactivate();

    protected String getEndState() {
        return endState;
    }

    public void setEndState(String endState) {
        this.endState = endState;
    }

    @Override
    public void onPointerStart(float x, float y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPointerEnd(float x, float y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onPointerDrag(float x, float y) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onKeyDown(int keyCode) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onKeyUp(int keyCode) {
    }

}
