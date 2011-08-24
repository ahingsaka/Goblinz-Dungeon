package sandbox.core.entities;

import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;

public abstract class Enemy extends WorldObject implements Updatable {
    
    public boolean isAttached = false;

    private boolean isMovingLeft = false;
    
    private boolean isMovingRight = false;
    
    public float newX;
    public float newY;

    /**
     * Update positions, behaviour
     */
    public abstract void updateAll();

    public abstract void attach();
    
    public abstract void setPosition(int x, int y);
    
    public boolean isAttached() {
        return isAttached;
    }
    
    public boolean isMovingLeft() {
        return isMovingLeft;
    }
    
    public boolean isMovingRight() {
        return isMovingRight;
    }

    public void setMovingLeft(boolean isMovingLeft) {
        this.isMovingLeft = isMovingLeft;
    }
    
    public void setMovingRight(boolean isMovingRight) {
        this.isMovingRight = isMovingRight;
    }
}
