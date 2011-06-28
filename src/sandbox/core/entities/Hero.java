package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.world.WorldObject;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Hero extends WorldObject {
    private static final float STEP = (float) 1;
    public static String JSON_IMAGE = "sprites/hero.json";
    private Sprite sprite;
    
    public float newX;
    public float newY;

    public float x;
    public float y;

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
        //x = x - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveUp() {
        newY = y - STEP;
        //y = y - STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveRight() {
        newX = x + STEP;
        //x = x + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    public void moveDown() {
        newY = y + STEP;
//        y = y + STEP;
        // sprite.layer().setTranslation(x, y);
    }

    @Override
    public Image getImage() {
        return sprite.layer().image();
    }

}
