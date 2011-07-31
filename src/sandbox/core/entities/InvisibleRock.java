package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.WorldObject;
import forplay.core.Image;

public class InvisibleRock extends WorldObject {

    public static String TYPE = "invisiblerock";
    private Image image;

    public InvisibleRock() {
        image = assetManager().getImage("images/door.png");
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
