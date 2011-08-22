package sandbox.core.entities;

import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;

public abstract class Enemy extends WorldObject implements Updatable {

    /**
     * Update positions, behaviour
     */
    public abstract void updateAll();

    public abstract void attach();

}
