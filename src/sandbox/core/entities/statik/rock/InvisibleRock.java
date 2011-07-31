package sandbox.core.entities.statik.rock;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class InvisibleRock extends StaticWorldObject {

    public static String TYPE = "invisiblerock";

    public InvisibleRock() {
        image = assetManager().getImage("images/door.png");
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
