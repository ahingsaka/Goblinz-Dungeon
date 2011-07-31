package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import sandbox.core.world.WorldObject;

public class RockUp extends WorldObject {
    
    public static String TYPE = "rock-up";
    private Image image;
    
    public RockUp() {
        image = assetManager().getImage("images/rock-up.png");
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
