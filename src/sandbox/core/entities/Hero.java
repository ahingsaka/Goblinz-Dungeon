package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Animation;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Hero extends WorldObject implements Updatable {

    public static String TYPE = "hero";
    private static final float STEP = (float) 0.1;
    public static final int JUMP = 20;
    public static String JSON_IMAGE = "sprites/hero.json";
    private Sprite sprite;

    public float alpha;
    
    public boolean isFacingLeft;
    public boolean isFacingRight;

    public boolean isMovingLeft;
    public boolean isMovingRight;
    public boolean isMovingUp;
    public boolean isMovingDown;

    public boolean isJumping;
    public boolean isJumpingRight;
    public boolean isJumpingLeft;

    public int speed;

    public float newX = 0;
    public float newY = 6;

    public float x = 0;
    public float y = 6;

    public boolean isFalling;
    public boolean isFallingRight;
    public boolean isFallingLeft;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;

        sprite = SpriteLoader.getSprite(JSON_IMAGE);

        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
                sprite.setSprite("hero_left");
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });

    }

    public Hero(final GroupLayer characterLayer, int x, int y) {

        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        this.x = x;
        this.y = y;

        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
                // sprite.layer().setTranslation(x, y);
                characterLayer.add(sprite.layer());
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });

    }

    public void setPosition(float x, float y) {
        sprite.layer().setTranslation(x, y);
    }

    public void moveLeft() {
        newX = x - STEP;
        isMovingRight = false;
        isMovingLeft = true;
        
        isFacingRight = false;
        isFacingLeft = true;

        if (!isJumping) {
            sprite.setSprite("hero_left");
        }
        // x = x - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveUp() {
        // newY = y - STEP;
        // isMovingUp = true;
        // isMovingDown = false;
        // System.out.println(newY);
        // y = y - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveRight() {
        newX = x + STEP;
        isMovingRight = true;
        isMovingLeft = false;
        
        isFacingRight = true;
        isFacingLeft = false;

        if (!isJumping) {
            // TODO do not set if already set
            sprite.setSprite("hero_right");
        }
        // x = x + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveDown() {
        // newY = y + STEP;
        // isMovingUp = false;
        // isMovingDown = true;
        // System.out.println(newY);
        // y = y + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    @Override
    public Image getImage() {
        // return sprite.current().image();
        return sprite.layer().image();
    }

    public void upLeft() {
        isMovingLeft = false;
    }

    public void upUp() {
        isMovingUp = false;
    }

    public void upRight() {
        isMovingRight = false;
    }

    public void upDown() {
        isMovingDown = false;
    }

    public void jump() {
        if (!isJumping && !isFalling) {

            if (isMovingRight) {
                isJumpingRight = true;
            } else if (isMovingLeft) {
                isJumpingLeft = true;
            }

            isJumping = true;
            speed = JUMP;

        }
    }

    public void sword() {
        sprite.setCurrentAnimation("hero_sword");
        sprite.getCurrentAnimation().start();
    }

    @Override
    public boolean isCollidable() {
        return true;
    }

    @Override
    public String getType() {
        return TYPE;
    }

    public void dies() {
        sprite.setSprite("hero_die");
    }

    public void reset() {
        sprite.setSprite(0);
        setAlpha(1);
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
        sprite.layer().setAlpha(alpha);
    }

    @Override
    public void update(float delta) {
        Animation currentAnimation = sprite.getCurrentAnimation();

        if (currentAnimation != null) {
            currentAnimation.update(delta);
            String currentFrameId = currentAnimation.getCurrentFrameId();

            if (currentFrameId != null) {
                sprite.setSprite(currentFrameId);
            } else {
                sprite.setCurrentAnimation(null);
                
                if (isFacingLeft) {
                    sprite.setSprite("hero_left");
                } else if (isFacingRight) {
                    sprite.setSprite("hero_right");
                }
                
            }
        }
    }

}
