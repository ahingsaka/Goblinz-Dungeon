package sandbox.core.world;

import forplay.core.Image;

public abstract class WorldObject {
    
    protected float x;
    
    protected float y;
    
    public abstract Image getImage();

    public abstract boolean isCollidable();
    
    public abstract String getType();
    
    public float getX() {
        return x;
    }
    
    public float getY() {
        return y;
    }

}
