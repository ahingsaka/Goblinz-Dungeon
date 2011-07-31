package sandbox.core.entities;

import static forplay.core.ForPlay.assetManager;
import forplay.core.Image;
import sandbox.core.world.WorldObject;

public class RockLeft extends WorldObject {
    
    public static String TYPE = "rock-left";
    private Image image;
    
    public RockLeft() {
        image = assetManager().getImage("images/rock-left.png");
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
