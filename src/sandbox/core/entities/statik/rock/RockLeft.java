package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class RockLeft extends StaticWorldObject {
    
    public static String TYPE = "rock-left";
    
    public RockLeft() {
        image = assetManager().getImage("images/rock-left.png");
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
