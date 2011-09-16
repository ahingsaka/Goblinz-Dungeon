package sandbox.core.state;

import sandbox.core.display.font.TextLayer;
import sandbox.core.fsm.GameState;
import forplay.core.Keyboard;

public class Intro extends GameState {

    public static final String NAME = "intro";
    private TextLayer start;
    private TextLayer credits;

    private boolean startIsDisappearing = true;
    private boolean creditsIsDisappearing = true;
    private boolean startIsChosen = true;
    private boolean creditsIsChosen = false;

    public Intro() {
        name = NAME;
    }

    @Override
    protected void display() {
        displayManager.clear();
        displayManager.fontManager.createTextLayer("goblinz dungeon", 230, 170);

        start = displayManager.fontManager.createTextLayer("start", 350, 310);
        credits = displayManager.fontManager.createTextLayer("credits", 335, 360);
    }

    @Override
    protected void activate() {
        endState = NAME;
        startIsChosen = true;
        creditsIsChosen = false;
    }

    @Override
    protected void deactivate() {
        displayManager.fontManager.clear();
    }

    @Override
    protected void update(float d) {

        if (startIsChosen) {
            if (startIsDisappearing) {
                if (start.alpha > 0) {
                    start.alpha -= 0.1;
                    start.refresh();
                } else {
                    startIsDisappearing = false;
                }

            } else {
                if (start.alpha <= 1) {
                    start.alpha += 0.1;
                    start.refresh();
                } else {
                    startIsDisappearing = true;
                }
            }

        } else {
            if (creditsIsDisappearing) {
                if (credits.alpha > 0) {
                    credits.alpha -= 0.1;
                    credits.refresh();
                } else {
                    creditsIsDisappearing = false;
                }

            } else {
                if (credits.alpha <= 1) {
                    credits.alpha += 0.1;
                    credits.refresh();
                } else {
                    creditsIsDisappearing = true;
                }
            }

        }

    }

    @Override
    public void onKeyDown(int keyCode) {
        super.onKeyDown(keyCode);

        switch (keyCode) {

        case Keyboard.KEY_DOWN:
            if (startIsChosen) {
                startIsChosen = false;
                start.alpha = 1;
                start.refresh();
                startIsDisappearing = true;
                creditsIsChosen = true;
            }
            break;

        case Keyboard.KEY_UP:
            if (creditsIsChosen) {
                creditsIsChosen = false;
                credits.alpha = 1;
                credits.refresh();
                creditsIsDisappearing = true;
                startIsChosen = true;
            }
            break;

        case Keyboard.KEY_SPACE:
            endState = Gaming.NAME;
            break;
        }

    }

}
