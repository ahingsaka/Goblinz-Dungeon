package sandbox.core.entities;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ResourceCallback;

public class Goblin extends Enemy {

    public static String TYPE = "goblin";

    public static String JSON_IMAGE = "sprites/goblin.json";
    private Sprite sprite;

    private GroupLayer layer;

    public Goblin(final GroupLayer characterLayer, final int x, final int y) {
        this.x = x;
        this.y = y;
        sprite = SpriteLoader.getSprite(JSON_IMAGE);
        sprite.addCallback(new ResourceCallback<Sprite>() {
            @Override
            public void error(Throwable err) {
                log().error("Error loading image!", err);
            }

            @Override
            public void done(Sprite sprite) {
                sprite.setSprite(0);
                layer = characterLayer;
                // FIXME hell, there must be a fucking bug, coz, the hero sprite
                // is added as well !!! (change the goblin size to see this !!)
                // characterLayer.add(sprite.layer());
                // setPosition(x, y);
            }
        });
    }

    public void setPosition(int x, int y) {
        sprite.layer().setTranslation(x, y);
    }

    public void addToGroupLayer(GroupLayer characterLayer) {
        characterLayer.add(sprite.layer());
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void updateAll() {

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

    @Override
    public void attach() {
        if (!isAttached) {
            layer.add(sprite.layer());
            setPosition((int) x, (int) y);
            isAttached = true;
        }
    }

}
