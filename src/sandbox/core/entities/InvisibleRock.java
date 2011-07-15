package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import forplay.core.Image;
import forplay.core.ResourceCallback;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.world.WorldObject;

public class InvisibleRock extends WorldObject {

    public static String TYPE = "invisiblerock";
    public static String JSON_IMAGE = "sprites/door.json";
    private Sprite sprite;
    private Image image;

    public InvisibleRock() {

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
        return true;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
