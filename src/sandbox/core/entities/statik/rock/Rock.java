package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class Rock extends StaticWorldObject {

    public static String TYPE = "rock";

    public Rock() {
        image = assetManager().getImage("images/rock_56x58.png");
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
