package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class RockUp extends StaticWorldObject {
    
    public static String TYPE = "rock-up";
    
    public RockUp() {
        image = assetManager().getImage("images/rock-up.png");
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
