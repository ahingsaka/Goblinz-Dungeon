package sandbox.core.state;

import forplay.core.Keyboard;
import sandbox.core.Globals;
import sandbox.core.display.font.TextLayer;
import sandbox.core.entities.Hero;
import sandbox.core.fsm.GameState;

public class GameOver extends GameState {

    public static final String NAME = "gameover";
    private TextLayer gameOverTextLayer;
    private boolean textHasLoaded;

    private boolean isHeroDying;

    public GameOver() {
        name = NAME;
    }

    @Override
    protected void display() {
        gameOverTextLayer = displayManager.fontManager.createTextLayer("Game Over", 300, 200);
        gameOverTextLayer.setAlpha(0);
    }

    @Override
    protected void update(float d) {

        if (isHeroDying) {
            Hero hero = Globals.getInstance().getHero();
            float alpha = hero.alpha;

            if (alpha > 0) {
                alpha -= 0.1;
                hero.setAlpha(alpha);
            } else {
                isHeroDying = false;
            }

        } else {
            float alpha = gameOverTextLayer.alpha;
            if (alpha < 1) {
                alpha += 0.1;
                gameOverTextLayer.setAlpha(alpha);
            } else {
                textHasLoaded = true;
            }

        }
    }

    @Override
    protected void activate() {
        endState = NAME;
        textHasLoaded = false;
        isHeroDying = true;

        Hero hero = Globals.getInstance().getHero();
        hero.dies();
        hero.alpha = 1;
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
            if (textHasLoaded) {
                Hero hero = Globals.getInstance().getHero();
                hero.reset();
                Globals.getInstance().getWorld().reset();
                endState = Intro.NAME;
            }
            break;
        }

    }

}
