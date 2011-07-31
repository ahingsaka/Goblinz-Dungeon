package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class RockMiddle extends StaticWorldObject {
    
    public static String TYPE = "rock-middle";
    
    public RockMiddle() {
        image = assetManager().getImage("images/rock-middle.png");
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
