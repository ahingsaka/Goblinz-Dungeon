package sandbox.core.entities;

import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;

public abstract class Enemy extends WorldObject implements Updatable {

    public boolean isAttached = false;

    private boolean isMovingLeft = false;

    private boolean isMovingRight = false;
    
    private boolean isDead = false;
    
    private boolean isDying = false;
    
    private boolean isStrikingFull = false;

    public boolean isStrikingFull() {
        return isStrikingFull;
    }

    public void setStrikingFull(boolean isStrikingFull) {
        this.isStrikingFull = isStrikingFull;
    }

    private int width = 56;

    public int getWidth() {
        return width;
    }

    public boolean isFacingLeft() {
        return isFacingLeft;
    }

    public void setFacingLeft(boolean isFacingLeft) {
        this.isFacingLeft = isFacingLeft;
    }

    public boolean isFacingRight() {
        return isFacingRight;
    }

    public void setFacingRight(boolean isFacingRight) {
        this.isFacingRight = isFacingRight;
    }

    private boolean isFacingLeft = false;

    private boolean isFacingRight = false;

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
    
    public boolean isDead() {
        return isDead;
    }
    
    public boolean isDying() {
        return isDying;
    }
    
    public void setDying(boolean isDying) {
        this.isDying = isDying;
    }
    
    public void setDead(boolean isDead) {
        this.isDead = isDead;
    }

    public abstract void dies();
    
    public abstract void strikes();

    public void reset() {
        isDying = false;
        isDead = false;
    }

}
