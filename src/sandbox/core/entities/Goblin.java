package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.Globals;
import sandbox.core.display.sprite.Animation;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.world.Collision;
import sandbox.core.world.GoblinzDungeonWorld;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Goblin extends Enemy {

    public static String TYPE = "goblin";

    private static final float STEP = (float) 0.05;
    
    private static final int BLINKING_TIME = 1000;
    
    private int blinkingTime;
    
    public float alpha;

    public static String JSON_IMAGE = "sprites/goblin.json";
    private Sprite sprite;

    private GroupLayer layer;

    public Goblin(final GroupLayer characterLayer, final int x, final int y) {
        this.x = x;
        this.y = y;
        newX = x - STEP;

        setMovingLeft(true);
        setFacingLeft(true);

        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }

            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
                layer = characterLayer;
                // FIXME hell, there must be a fucking bug, coz, the hero sprite
                // is added as well !!! (change the goblin size to see this !!)
                // characterLayer.add(sprite.layer());
                // setPosition(x, y);
            }
        });
    }

    public void setPosition(int x, int y) {
        sprite.layer().setTranslation(x, y);
    }

    public void addToGroupLayer(GroupLayer characterLayer) {
        characterLayer.add(sprite.layer());
    }

    @Override
    public void update(float delta) {

        if (isDying()) {
            sprite.setCurrentAnimation(null);
           blink(delta);

        } else {

            checkCollisions(delta);
            checkMovements();

            Animation currentAnimation = sprite.getCurrentAnimation();

            if (currentAnimation != null) {
                currentAnimation.update(delta);
                String currentFrameId = currentAnimation.getCurrentFrameId();

                if (currentFrameId != null) {
                    sprite.setSprite(currentFrameId);
                } else {
                    sprite.setCurrentAnimation(null);

                    if (isFacingLeft() && !isMovingLeft()) {
                        // sprite.setSprite("hero_left");
                    } else if (isFacingRight() && !isMovingRight()) {
                        // sprite.setSprite("hero_right");
                    }

                }
            }

        }

    }

    private void checkCollisions(float delta) {
        boolean hasCollisionX = false;
        // GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        if (newX != x) {

            // Check world bounds
            // if ((newX < 0) || (newX >= world.worldWidth)) {
            // newX = x;
            // hasCollisionX = true;
            // }

            if (isMovingLeft()) {
                hasCollisionX = collideLeft();
                if (hasCollisionX) {
                    setMovingLeft(false);
                    setMovingRight(true);
                }

            } else if (isMovingRight()) {
                hasCollisionX = collideRight();
                if (hasCollisionX) {
                    setMovingRight(false);
                    setMovingLeft(true);
                }
            }

            if (!hasCollisionX) {
                x = newX;
            } else {
                newX = x;
            }

        }

    }

    public boolean collideLeft() {

        Collision collide = Collision.NONE;
        // Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        if (newX != x) {
            collide = world.tellIsCollidableObject(newX, y);

            if (collide == Collision.BLOCK) {
                return true;
            }

            // float absY = (int) y;
            //
            // if (y > absY) {
            collide = world.tellIsCollidableObject(newX, y + 1);
            // }
        }

        if (collide != Collision.BLOCK) {
            return true;
        } else {
            return false;
        }

        // if (collide == Collision.NONE)
        // return false;
        // else if (collide == Collision.BLOCK)
        // return true;
        // else if (collide == Collision.HOLE) {
        // //gameOver();
        // return true;
        // } else if (collide == Collision.END) {
        // //isExitingLevel = true;
        // return false;
        // } else
        // return false;
    }

    public boolean collideRight() {

        Collision collide = Collision.NONE;
        // Hero hero = Globals.getInstance().getHero();
        GoblinzDungeonWorld world = Globals.getInstance().getWorld();

        // float newX = hero.newX;
        // float x = hero.x;

        if (newX != x) {
            collide = world.tellIsCollidableObject(newX + 1, y);

            if (collide == Collision.BLOCK) {
                return true;
            }

            // float absY = (int) y;
            //
            // if (y > absY) {
            collide = world.tellIsCollidableObject(newX + 1, y + 1);
            // }
        }

        if (collide != Collision.BLOCK) {
            return true;
        } else {
            return false;
        }

        // if (collide == Collision.NONE)
        // return false;
        // else if (collide == Collision.BLOCK)
        // return true;
        // else if (collide == Collision.HOLE) {
        // //gameOver();
        // return true;
        // } else if (collide == Collision.END) {
        // //isExitingLevel = true;
        // return false;
        // } else
        // return false;

    }

    private void checkMovements() {
        if (isMovingLeft()) {
            moveLeft();
        } else if (isMovingRight()) {
            moveRight();
        }
    }

    private void moveRight() {
        newX = x + STEP;

        setMovingRight(true);
        setMovingLeft(false);

        if (isFacingRight()) {
            // FIXME
            sprite.setCurrentAnimation("goblin_walk_right");
            sprite.getCurrentAnimation().start();
        } else {
            // sprite.setSprite("hero_right");
        }

        setFacingRight(true);
        setFacingLeft(false);
    }

    private void moveLeft() {
        newX = x - STEP;

        setMovingRight(false);
        setMovingLeft(true);

        if (isFacingLeft()) {
            sprite.setCurrentAnimation("goblin_walk_left");
            sprite.getCurrentAnimation().start();
        } else {
            // sprite.setSprite("hero_right");
        }

        setFacingRight(false);
        setFacingLeft(true);

    }

    @Override
    public void updateAll() {

    }

    @Override
    public Image getImage() {
        return sprite.layer().image();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public void attach() {
        if (!isAttached) {
            layer.add(sprite.layer());
            setPosition((int) x, (int) y);
            isAttached = true;
        }
    }

    @Override
    public void dies() {
        if (!isDying()) {
            setDying(true);
            blinkingTime = BLINKING_TIME;
            setMovingLeft(false);
            setMovingRight(false);
        }
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        sprite.layer().setAlpha(alpha);
    }
    
    
    
    
    @Override
    public void reset() {
        super.reset();
        setAlpha(1);
        setMovingLeft(true);
        setFacingLeft(true);
    }

    private void blink(float delta) {
        blinkingTime -= delta;

        if (blinkingTime <= 0) {
            setDead(true);
            setAlpha(0);

        } else {
            if (alpha == 0) {
                setAlpha(1);
            } else {
                setAlpha(0);
            }
        }
    }

}
