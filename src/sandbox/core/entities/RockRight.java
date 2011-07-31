package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import sandbox.core.world.WorldObject;

public class RockRight extends WorldObject {
    
    public static String TYPE = "rock-right";
    private Image image;
    
    public RockRight() {
        image = assetManager().getImage("images/rock-right.png");
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
