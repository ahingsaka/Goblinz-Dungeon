package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.world.WorldObject;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Door extends WorldObject {

    public static String TYPE = "door";
    public static String JSON_IMAGE = "sprites/door.json";
    private Sprite sprite;
    private Image image;

    public Door() {

        sprite = SpriteLoader.getSprite(JSON_IMAGE);

        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
                image = sprite.layer().image();
            }

            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
        });

    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

}
