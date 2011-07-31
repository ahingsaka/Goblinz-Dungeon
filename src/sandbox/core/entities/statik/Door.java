package sandbox.core.entities.statik;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class Door extends StaticWorldObject {

    public static String TYPE = "door";

    public Door() {
        image = assetManager().getImage("images/door.png");
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

    @Override
    public String getType() {
        return TYPE;
    }

}
