package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import forplay.core.GroupLayer;
import forplay.core.ResourceCallback;

public class Heart {
    
    public static String JSON_IMAGE = "sprites/heart.json";
    private Sprite sprite;
    private boolean isEmpty = false;
    
    public Heart(final GroupLayer layer, final float x, final float y) {
        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        
        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void done(Sprite sprite) {
              sprite.setSprite(0);
              sprite.layer().setTranslation(x, y);
              layer.add(sprite.layer());
            }

            @Override
            public void error(Throwable err) {
              log().error("Error loading image!", err);
            }
          });
    }
    
    public void delete() {
        sprite.setSprite(1);
        isEmpty = true;
    }
    
    public boolean isEmpty() {
        return isEmpty;
    }
    

}
