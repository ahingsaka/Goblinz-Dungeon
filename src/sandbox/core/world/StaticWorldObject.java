package sandbox.core.world;

import forplay.core.Image;

public abstract class StaticWorldObject extends WorldObject {
    
    protected Image image;

    @Override
    public Image getImage() {
        return image;
    }

}
