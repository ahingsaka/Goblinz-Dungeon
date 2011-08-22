package sandbox.core.entities;

import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;

public abstract class Enemy extends WorldObject implements Updatable {
    
    public boolean isAttached = false;

    /**
     * Update positions, behaviour
     */
    public abstract void updateAll();

    public abstract void attach();
    
    public abstract void setPosition(int x, int y);
    
    public boolean isAttached() {
        return isAttached;
    }

}
