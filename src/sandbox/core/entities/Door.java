package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.WorldObject;
import forplay.core.Image;

public class Door extends WorldObject {

    public static String TYPE = "door";
    private Image image;

    public Door() {
        image = assetManager().getImage("images/door.png");
    }

    @Override
    public Image getImage() {
        return image;
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
