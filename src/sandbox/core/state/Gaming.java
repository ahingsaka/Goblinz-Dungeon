package sandbox.core.state;

import forplay.core.ForPlay;
import forplay.core.Keyboard;
import forplay.core.ResourceCallback;
import sandbox.core.Globals;
import sandbox.core.entities.Hero;
import sandbox.core.fsm.GameState;
import sandbox.core.world.GoblinzDungeonWorld;
import sandbox.core.world.WorldLoader;

public class Gaming extends GameState {

    public Gaming() {
        name = this.getClass().getSimpleName();
    }

    @Override
    protected void display() {

        WorldLoader.loadLevel("levels/level1.json", Globals.getInstance().getWorld(),
                new ResourceCallback<GoblinzDungeonWorld>() {

                    @Override
                    public void error(Throwable err) {
                        ForPlay.log().error("Error loading world: " + err.getMessage());
                    }

                    @Override
                    public void done(GoblinzDungeonWorld resource) {
                        // System.out.println("loaded");
                        resource.setIsLoaded(true);
                    }
                });

        displayManager.fontManager.addTextLayer("hello world", 10, 10);
    }

    @Override
    protected void update(float d) {
        checkCollisions(d);
    }

    private void checkCollisions(float d) {
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();
        boolean hasCollisionX = false;
        boolean hasCollisionY = false;

        // Check world bounds
        if (hero.newX != hero.x) {
            if ((hero.newX < 0) || (hero.newX >= world.worldWidth)) {
                hero.newX = hero.x;
                hasCollisionX = true;
            } 
        }

        if (hero.newY != hero.y) {
            if ((hero.newY < 0) || (hero.newY >= world.worldHeight)) {
                hero.newY = hero.y;
                hasCollisionY = true;
            } 
        }

        // Check other objects
        boolean isCollidableObject = world.tellIsCollidableObject(hero.newX, hero.y);
        
        if (isCollidableObject) {
            hero.newX = hero.x;
            hasCollisionX = true;
        } 

        isCollidableObject = world.tellIsCollidableObject(hero.x, hero.newY);
        
        if (isCollidableObject) {
            hero.newY = hero.y;
            hasCollisionY = true;
        } 
        
        if (!hasCollisionX) {
            hero.x = hero.newX;
        }
        
        if (!hasCollisionY) {
            hero.y = hero.newY;
        }
    }

    @Override
    protected void activate() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void deactivate() {
        // TODO Auto-generated method stub

    }

    @Override
    public void onKeyDown(int keyCode) {
        super.onKeyDown(keyCode);

        switch (keyCode) {
        case Keyboard.KEY_LEFT:
            Globals.getInstance().getHero().moveLeft();
            break;
        case Keyboard.KEY_UP:
            Globals.getInstance().getHero().moveUp();
            break;
        case Keyboard.KEY_RIGHT:
            Globals.getInstance().getHero().moveRight();
            break;
        case Keyboard.KEY_DOWN:
            Globals.getInstance().getHero().moveDown();
            break;
        }

    }

}
