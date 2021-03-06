package sandbox.core.state;

import java.util.ArrayList;
import java.util.List;

import sandbox.core.Globals;
import sandbox.core.entities.Enemy;
import sandbox.core.entities.Heart;
import sandbox.core.entities.Hero;
import sandbox.core.fsm.GameState;
import sandbox.core.world.Collision;
import sandbox.core.world.CollisionUtils;
import sandbox.core.world.GoblinzDungeonWorld;
import sandbox.core.world.WorldLoader;
import forplay.core.ForPlay;
import forplay.core.Keyboard;
import forplay.core.ResourceCallback;

public class Gaming extends GameState {

    public static final String NAME = "gaming";

    public boolean hasLoaded = false;

    private boolean isExitingLevel = false;

    private List<Heart> hearts;

    public Gaming() {
        name = NAME;
    }

    @Override
    protected void display() {

        if (!hasLoaded) {
            WorldLoader.loadLevel("levels/level1.json", Globals.getInstance().getWorld(), Globals.getInstance()
                    .getCharacterLayer(), Globals.getInstance(), new ResourceCallback<GoblinzDungeonWorld>() {

                @Override
                public void error(Throwable err) {
                    ForPlay.log().error("Error loading world: " + err.getMessage());
                }

                @Override
                public void done(GoblinzDungeonWorld resource) {
                    // System.out.println("loaded");
                    resource.setIsLoaded(true);
                    hasLoaded = true;
                }
            });
        }

        hearts = new ArrayList<Heart>();
        Heart heart1 = new Heart(displayManager.getTextLayer(), 25, 25);
        Heart heart2 = new Heart(displayManager.getTextLayer(), 65, 25);
        Heart heart3 = new Heart(displayManager.getTextLayer(), 105, 25);

        hearts.add(heart1);
        hearts.add(heart2);
        hearts.add(heart3);

        // displayManager.fontManager.addTextLayer("goblinz dungeon", 10, 10);
    }

    @Override
    protected void update(float d) {
        Hero hero = Globals.getInstance().getHero();
        if (hero.isDying()) {
            gameOver();

        } else if (isExitingLevel) {
            makeScreenDisappear();

        } else {

            checkEnemies(d);

            calculateJump();
            checkCollisions(d);
            checkMovements();
            gravityCheck();

            hero.update(d);

            if (hero.isSlicingFull()) {
                List<Enemy> enemies = Globals.getInstance().getWorld().getEnemies();
                for (Enemy enemy : enemies) {
                    if (isInRange(enemy, hero)) {
                        boolean collisionWithEnemyFound = CollisionUtils.checkCollisionWithSword(enemy, hero);
                        if (collisionWithEnemyFound) {
                            enemy.dies();
                        }
                    }
                }

            }

        }
    }

    private void checkEnemies(float d) {
        List<Enemy> enemies = Globals.getInstance().getWorld().getEnemies();
        Hero hero = Globals.getInstance().getHero();

        for (Enemy enemy : enemies) {
            if (isInRange(enemy, hero)) {
                enemy.attach();
                enemy.updateAll();

                if (isInCloseRange(enemy, hero)) {
                    
                    // FIXME
                    float random = ForPlay.random();
                    if (random < 0.1) {
                        enemy.strikes();
                    }
                }

                // Dans update, on regarde le comportement et puis on
                // affiche
                enemy.update(d);
                

                if (!enemy.isDead() && !enemy.isDying()) {
                    boolean collisionWithEnemyFound = false;
                    
                    if (enemy.isStrikingFull()) {
                        collisionWithEnemyFound = CollisionUtils.checkCollision(enemy, hero, 2);
                    } else {
                        collisionWithEnemyFound = CollisionUtils.checkCollision(enemy, hero, 1);
                    }
                    
                    if (collisionWithEnemyFound) {

                        // -_-'
                        if (!hero.isBlinking) {
                            removeHeart();
                        }

                        hero.collideWithEnemy();
                    }
                }
            }
        }
    }

    private boolean isInRange(Enemy enemy, Hero hero) {
        boolean isInRange = false;
        float enemyX = enemy.getX();
        float heroX = hero.x;

        int halfViewWidth = Globals.getInstance().getWorld().getHalfViewWidth() + 1;

        if (((heroX + halfViewWidth) >= enemyX) && ((enemyX >= (heroX - halfViewWidth)))) {
            isInRange = true;
        }

        return isInRange;
    }

    private boolean isInCloseRange(Enemy enemy, Hero hero) {
        boolean isInRange = false;
        float enemyX = enemy.getX();
        float heroX = hero.x;

        int width = 3;

        if ((hero.isFacingLeft && enemy.isFacingRight()) || (hero.isFacingRight && enemy.isFacingLeft())) {
            if (((heroX + width) >= enemyX) && ((enemyX >= (heroX - width)))) {
                isInRange = true;
            }
        }

        return isInRange;

    }

    private void makeScreenDisappear() {
        float alpha = displayManager.alpha;

        if (alpha > 0) {
            alpha -= 0.1;
            displayManager.setAlpha(alpha);
        } else {
            isExitingLevel = false;
            setEndState(End.NAME);
        }
    }

    private void gravityCheck() {

    }

    private void checkMovements() {
        Hero hero = Globals.getInstance().getHero();
        if (hero.isMovingLeft) {
            hero.moveLeft();
        } else if (hero.isMovingRight) {
            hero.moveRight();
        }
    }

    /**
     * TODO should be in hero ? CLEAN this ASAP !!
     */
    private void calculateJump() {
        Hero hero = Globals.getInstance().getHero();

        if (hero.isJumping()) {
            int speed = hero.speed;

            if (speed == 0) {
                hero.makeFall();
                return;
            }

            hero.speed = --speed;

            hero.newY = hero.y - (float) 0.1;

            if (hero.isJumpingRight) {
                hero.newX = hero.x + (float) 0.1;
            } else if (hero.isJumpingLeft) {
                hero.newX = hero.x - (float) 0.1;
            }

            return;
        }

        else if (hero.isFalling) {

            if (hero.isFallingRight()) {
                hero.newX = hero.x + (float) 0.1;
            } else if (hero.isFallingLeft()) {
                hero.newX = hero.x - (float) 0.1;
            }

            hero.newY = hero.y + (float) 0.1;
            return;

        } else {
            // Gravity
            hero.isMovingDown = true;
            hero.newY = hero.y + (float) 0.1;
        }

    }

    public boolean collideDown() {

        Collision collide = Collision.NONE;
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        float newY = hero.newY;
        float y = hero.y;

        if (newY != y) {
            collide = world.tellIsCollidableObject(hero.x, newY + 1);

            if (collide == Collision.BLOCK) {
                return true;
            }

            float absX = (int) hero.x;

            if (hero.x > absX) {
                collide = world.tellIsCollidableObject(hero.x + 1, newY + 1);
            }
        }

        if (collide == Collision.NONE)
            return false;
        else if (collide == Collision.BLOCK)
            return true;
        else if (collide == Collision.HOLE) {
            gameOver();
            return true;
        } else if (collide == Collision.END) {
            isExitingLevel = true;
            return false;
        } else
            return false;
    }

    private void gameOver() {
        setEndState(GameOver.NAME);
    }

    public boolean collideUp() {

        Collision collide = Collision.NONE;
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        float newY = hero.newY;
        float y = hero.y;

        if (newY != y) {
            collide = world.tellIsCollidableObject(hero.x, newY);

            if (collide == Collision.BLOCK) {
                return true;
            }

            float absX = (int) hero.x;

            if (hero.x > absX) {
                collide = world.tellIsCollidableObject(hero.x + 1, newY);
            }
        }

        if (collide == Collision.NONE)
            return false;
        else if (collide == Collision.BLOCK)
            return true;
        else if (collide == Collision.HOLE) {
            gameOver();
            return true;
        } else if (collide == Collision.END) {
            isExitingLevel = true;
            return false;
        } else
            return false;

    }

    public boolean collideLeft() {

        Collision collide = Collision.NONE;
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        float newX = hero.newX;
        float x = hero.x;

        if (newX != x) {
            collide = world.tellIsCollidableObject(newX, hero.y);

            if (collide == Collision.BLOCK) {
                return true;
            }

            float absY = (int) hero.y;

            if (hero.y > absY) {
                collide = world.tellIsCollidableObject(newX, hero.y + 1);
            }
        }

        if (collide == Collision.NONE)
            return false;
        else if (collide == Collision.BLOCK)
            return true;
        else if (collide == Collision.HOLE) {
            gameOver();
            return true;
        } else if (collide == Collision.END) {
            isExitingLevel = true;
            return false;
        } else
            return false;
    }

    public boolean collideRight() {

        Collision collide = Collision.NONE;
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        float newX = hero.newX;
        float x = hero.x;

        if (newX != x) {
            collide = world.tellIsCollidableObject(newX + 1, hero.y);

            if (collide == Collision.BLOCK) {
                return true;
            }

            float absY = (int) hero.y;

            if (hero.y > absY) {
                collide = world.tellIsCollidableObject(newX + 1, hero.y + 1);
            }
        }

        if (collide == Collision.NONE)
            return false;
        else if (collide == Collision.BLOCK)
            return true;
        else if (collide == Collision.HOLE) {
            gameOver();
            return true;
        } else if (collide == Collision.END) {
            isExitingLevel = true;
            return false;
        } else
            return false;

    }

    private void checkCollisions(float d) {
        Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();
        boolean hasCollisionX = false;
        boolean hasCollisionY = false;

        // MOVING LEFT OR RIGHT
        if (hero.newX != hero.x) {

            // Check world bounds
            if ((hero.newX < 0) || (hero.newX >= world.worldWidth)) {
                hero.newX = hero.x;
                hasCollisionX = true;
            }

            if (hero.isMovingLeft) {
                hasCollisionX = collideLeft();
                if (hasCollisionX) {
                    hero.isMovingLeft = false;
                }

            } else if (hero.isMovingRight) {
                hasCollisionX = collideRight();
                if (hasCollisionX) {
                    hero.isMovingRight = false;
                }
            }

        }

        // MOVING UP OR DOWN
        if (hero.newY != hero.y) {

            // Check world bounds
            if ((hero.newY < 0) || (hero.newY >= world.worldHeight)) {
                hero.newY = hero.y;
                hasCollisionY = true;
            }

            if (hero.isMovingDown) {
                hasCollisionY = collideDown();
                if (hasCollisionY) {
                    hero.isMovingDown = false;
                    hero.isFalling = false;
                }
            } else if (hero.isMovingUp) {
                hasCollisionY = collideUp();
                if (hasCollisionY) {
                    hero.makeFall();
                }
            }

        }

        // Put back
        if (!hasCollisionX) {
            hero.x = hero.newX;
        } else {
            hero.newX = hero.x;
        }

        if (!hasCollisionY) {
            hero.y = hero.newY;
        } else {
            hero.newY = hero.y;
            hero.setFallingLeft(false);
            hero.setFallingRight(false);
        }
    }

    @Override
    protected void activate() {

        Hero hero = Globals.getInstance().getHero();
        // hero.newX = 209;
        // hero.newY = 2;
        // hero.x = 209;
        // hero.y = 2;
        // hero.setPosition(209, 2);
        hero.reset();
        Globals.getInstance().getWorld().reset();

        endState = NAME;
        displayManager.show();
    }

    @Override
    protected void deactivate() {
        // displayManager.clear();
    }

    @Override
    public void onKeyDown(int keyCode) {
        super.onKeyDown(keyCode);

        switch (keyCode) {
        case Keyboard.KEY_LEFT:
            Globals.getInstance().getHero().moveLeft();
            break;
        case Keyboard.KEY_UP:
            Globals.getInstance().getHero().jump();
            break;
        case Keyboard.KEY_RIGHT:
            Globals.getInstance().getHero().moveRight();
            break;
        case Keyboard.KEY_DOWN:
            Globals.getInstance().getHero().moveDown();
            break;
        case Keyboard.KEY_SPACE:
            Globals.getInstance().getHero().sword();
            break;
        case Keyboard.KEY_ESC:
            endState = Intro.NAME;
            break;
        }

    }

    @Override
    public void onKeyUp(int keyCode) {
        super.onKeyUp(keyCode);

        switch (keyCode) {
        case Keyboard.KEY_LEFT:
            // System.out.println("isReleasedLeft");
            Globals.getInstance().getHero().upLeft();
            break;
        case Keyboard.KEY_RIGHT:
            // FIXME is there a forplay bug ?
            // System.out.println("isReleasedRight");
            Globals.getInstance().getHero().upRight();
            break;
        case Keyboard.KEY_DOWN:
            Globals.getInstance().getHero().upDown();
            break;
        }

    }

    private void removeHeart() {
        int i = hearts.size() - 1;

        while (i >= 0) {
            Heart heart = hearts.get(i);
            if (heart.isEmpty()) {
                i -= 1;
            } else {
                heart.delete();
                break;
            }
        }

    }

}
