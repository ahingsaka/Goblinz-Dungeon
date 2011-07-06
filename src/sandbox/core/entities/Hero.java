package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.world.WorldObject;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Hero extends WorldObject {
    private static final float STEP = (float) 0.1;
    public static final int JUMP = 20;
    public static String JSON_IMAGE = "sprites/hero.json";
    private Sprite sprite;
    
    public boolean isMovingLeft;
    public boolean isMovingRight;
    public boolean isMovingUp;
    public boolean isMovingDown;
    
    public boolean isJumping;
    
    public int speed;
    
    public float newX = 0;
    public float newY = 6;

    public float x = 0;
    public float y = 6;
    public boolean isFalling;

    public Hero(int x, int y) {
        this.x = x;
        this.y = y;

        sprite = SpriteLoader.getSprite(JSON_IMAGE);

        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });

    }

    public Hero(final GroupLayer characterLayer) {

        sprite = SpriteLoader.getSprite(JSON_IMAGE);

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

    public Hero(final GroupLayer characterLayer, final float x, final float y) {
        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        this.x = x;
        this.y = y;

        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
                sprite.layer().setTranslation(x, y);
                characterLayer.add(sprite.layer());
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });
    }

    public void moveLeft() {
        newX = x - STEP;
        isMovingRight = false;
        isMovingLeft = true;
        //x = x - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveUp() {
//        newY = y - STEP;
//        isMovingUp = true;
//        isMovingDown = false;
//        System.out.println(newY);
        //y = y - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveRight() {
        newX = x + STEP;
        isMovingRight = true;
        isMovingLeft = false;
        //x = x + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveDown() {
//        newY = y + STEP;
//        isMovingUp = false;
//        isMovingDown = true;
//        System.out.println(newY);
//        y = y + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    @Override
    public Image getImage() {
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
    
    /**
     *  v = vitesse
     *  a = acceleration
     *  g = gravite (10)
     *  
     *  t = temps
     *  
     *  chute= 1/2 * (g*t^2)
     *  
     *  
     *  donc saut : 
     *  1ere partie
     *  y = -1/2 * (g*t^2) + vitesse de depart * t
     *  
     */
    public void jump() {
        if (!isJumping && !isFalling) {
            isJumping = true;
            speed = JUMP;
            
        }
    }

}
