package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import sandbox.core.world.WorldObject;

public class RockMiddle extends WorldObject {
    
    public static String TYPE = "rock-middle";
    private Image image;
    
    public RockMiddle() {
        image = assetManager().getImage("images/rock-middle.png");
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
