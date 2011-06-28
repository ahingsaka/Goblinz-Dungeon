package sandbox.core.display;

import static forplay.core.ForPlay.graphics;
import sandbox.core.Globals;
import sandbox.core.GoblinzDungeon;
import sandbox.core.display.font.FontManager;
import sandbox.core.entities.Hero;
import sandbox.core.world.GoblinzDungeonWorld;
import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;
import forplay.core.Surface;
import forplay.core.SurfaceLayer;

public class DisplayManager {

    private ImageLayer backgroundLayer;
    private GroupLayer textLayer;
    public GroupLayer characterLayer;

    private SurfaceLayer gameLayer;

    public FontManager fontManager;
    private GoblinzDungeonWorld world;
    private float viewOriginX;
    private float viewOriginY;

    public DisplayManager() {
        textLayer = graphics().createGroupLayer();
        characterLayer = graphics().createGroupLayer();

        gameLayer = graphics().createSurfaceLayer(GoblinzDungeon.SCREEN_WIDTH, GoblinzDungeon.SCREEN_HEIGHT);

        fontManager = new FontManager(textLayer);
    }

    public void createBackground(Image image) {
        backgroundLayer = graphics().createImageLayer(image);
    }

    public void setSize(int screenWidth, int screenHeight) {
        graphics().setSize(screenWidth, screenHeight);
    }

    public GroupLayer getTextLayer() {
        return textLayer;
    }

    public void init() {
        graphics().rootLayer().add(backgroundLayer);
        graphics().rootLayer().add(gameLayer);
        graphics().rootLayer().add(textLayer);
        graphics().rootLayer().add(characterLayer);
    }

    public void setWorld(GoblinzDungeonWorld world) {
        this.world = world;
    }

    public void paint(float alpha) {
        if (world.isLoaded()) {
            Hero hero = Globals.getInstance().getHero();
            world.setViewOrigin(hero.x, hero.y);

            Surface surface = gameLayer.surface();
            surface.clear();

            world.paint(surface, alpha);
            world.paintHero(surface, alpha);
        }
    }

}
