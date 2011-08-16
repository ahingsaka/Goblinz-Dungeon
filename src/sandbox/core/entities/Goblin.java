package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import sandbox.core.display.sprite.Updatable;
import sandbox.core.world.WorldObject;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Goblin extends WorldObject implements Updatable {
    
    public static String TYPE = "goblin";
    
    public float x = 0;
    public float y = 0;
    
    public static String JSON_IMAGE = "sprites/goblin.json";
    private Sprite sprite;
    
    public Goblin(int x, int y) {
        this.x = x;
        this.y = y;
        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }
            
            @Override
            public void done(Sprite resource) {
//                sprite.setSprite("");
            }
        });
    }
    
    public void addToGroupLayer(GroupLayer characterLayer) {
        characterLayer.add(sprite.layer());
    }

    @Override
    public void update(float delta) {
        // TODO Auto-generated method stub

    }

    @Override
    public Image getImage() {
        return sprite.layer().image();
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
