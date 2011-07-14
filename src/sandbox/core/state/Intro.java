package sandbox.core.state;

import sandbox.core.display.font.TextLayer;
import sandbox.core.fsm.GameState;
import forplay.core.Keyboard;

public class Intro extends GameState {
    
    public static final String NAME = "intro";
    private TextLayer spaceInfo; 
    
    private boolean isDisappearing = true;

    public Intro() {
        name = NAME;
    }

    @Override
    protected void display() {
        displayManager.clear();
        displayManager.fontManager.createTextLayer("goblinz dungeon", 230, 170);
        
        spaceInfo = displayManager.fontManager.createTextLayer("press space to begin", 200, 220);
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
        if (isDisappearing) {
            if (spaceInfo.alpha > 0) {
                spaceInfo.alpha -= 0.1;
                spaceInfo.refresh();
            } else {
                isDisappearing = false;
            }
            
        } else {
            if (spaceInfo.alpha <= 1) {
                spaceInfo.alpha += 0.1;
                spaceInfo.refresh();
            } else {
                isDisappearing = true;
            }
        }
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
