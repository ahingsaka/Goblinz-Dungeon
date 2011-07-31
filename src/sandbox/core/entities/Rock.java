package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.WorldObject;
import forplay.core.Image;

public class Rock extends WorldObject {

    public static String TYPE = "rock";
    private Image image;

    public Rock() {
        image = assetManager().getImage("images/rock_56x58.png");
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
