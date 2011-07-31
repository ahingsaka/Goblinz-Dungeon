package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class RockRight extends StaticWorldObject {
    
    public static String TYPE = "rock-right";
    
    public RockRight() {
        image = assetManager().getImage("images/rock-right.png");
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
