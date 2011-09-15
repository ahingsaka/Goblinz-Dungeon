package sandbox.core.entities.statik;

import static forplay.core.ForPlay.assetManager;
import sandbox.core.world.StaticWorldObject;

public class Wall extends StaticWorldObject {
    
    public static String TYPE = "wall";
    
    public Wall() {
        image = assetManager().getImage("images/wall.png");
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
