package sandbox.core.world;

import sandbox.core.Globals;
import sandbox.core.entities.Door;
import sandbox.core.entities.Hero;
import sandbox.core.entities.InvisibleRock;
import forplay.core.Surface;

public class GoblinzDungeonWorld {

    public int worldWidth;
    public int worldHeight;

    private static final int TILE_HEIGHT = 57;

    private static final int TILE_BASE = 0;

    // Taille reelle des images de tiles (-1)
    private static final int TILE_WIDTH = 55;
    private static final int TILE_IMAGE_HEIGHT = 57;

    public WorldObject[] world;
    private float viewOriginX = 0;
    private float viewOriginY = (float) 2.5;
    private boolean isLoaded = false;

    public void setViewOrigin(float x, float y) {
        viewOriginX = x;
        viewOriginY = y;
    }

    private int worldToPixelX(Surface surf, double x) {
        double center = surf.width() * 0.5;
        return (int) (center - (viewOriginX * TILE_WIDTH) + x * TILE_WIDTH);
    }

    private int worldToPixelY(Surface surf, double y) {
        double center = surf.height() * 0.5;
        return (int) (center - (viewOriginY * TILE_HEIGHT) + y * TILE_HEIGHT);
    }

    private double pixelToWorldX(Surface surf, int x) {
        double center = surf.width() * 0.5;
        return (int) (((viewOriginX * TILE_WIDTH) + x - center) / TILE_WIDTH);
    }

    private double pixelToWorldY(Surface surf, int y) {
        double center = surf.height() * 0.5;
        return (y + (viewOriginY * TILE_HEIGHT) - center) / TILE_HEIGHT;
    }

    public void paint(Surface surf, float alpha) {

        int startX = (int) pixelToWorldX(surf, 0);
        int endX = (int) pixelToWorldX(surf, surf.width());
        if (startX < 0)
            startX = 0;
        if (endX < 0)
            endX = 0;
        if (startX >= worldWidth)
            startX = worldWidth - 1;
        if (endX >= worldWidth)
            endX = worldWidth - 1;

        int startY = (int) pixelToWorldY(surf, 0);
        int endY = (int) pixelToWorldY(surf, surf.height());

        if (startY < 0)
            startY = 0;
        if (endY < 0)
            endY = 0;
        if (startY >= worldHeight)
            startY = worldHeight - 1;
        if (endY >= worldHeight)
            endY = worldHeight - 1;

        for (int ty = startY; ty <= endY; ++ty) {
            for (int tx = startX; tx <= endX; ++tx) {

                WorldObject sandboxObject = world[ty * worldWidth + tx];

                if (sandboxObject != null) {

                    // Figure out where the tile goes. If it's out of screen
                    // bounds,
                    // skip it
                    int px = worldToPixelX(surf, tx);
                    int py = worldToPixelY(surf, ty) - TILE_BASE;
                    if ((px > surf.width()) || (py > surf.height()) || (px + TILE_WIDTH < 0)
                            || (py + TILE_IMAGE_HEIGHT < 0)) {
                        continue;
                    }

                    surf.drawImage(sandboxObject.getImage(), px, py);
                }

            }

        }
    }

    public void paintHero(Surface surf, float alpha) {
        Hero hero = Globals.getInstance().getHero();
        int px = worldToPixelX(surf, hero.x);
        int py = worldToPixelY(surf, hero.y) - TILE_BASE;

        // surf.drawImage(hero.getImage(), px, py);
        hero.setPosition(px, py);

    }

    public void initWorldTab(int width, int height) {
        worldWidth = width;
        worldHeight = height;
        world = new WorldObject[worldWidth * worldHeight];
    }

    public void add(WorldObject worldObject, int x, int y) {
        world[y * worldWidth + x] = worldObject;
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public void setIsLoaded(boolean isLoaded) {
        this.isLoaded = isLoaded;
    }

    public Collision tellIsCollidableObject(float x, float y) {
        Collision result = Collision.NONE;

        int y2 = (int) y;
        int x2 = (int) x;
        // System.out.println(x2 + "," + y2);

        WorldObject worldObject = world[y2 * worldWidth + x2];

        if (worldObject != null) {
            if (worldObject.isCollidable()) {
                result = Collision.BLOCK;
            } else if (Door.TYPE.equals(worldObject.getType())) {
                result = Collision.END;
            } else if (InvisibleRock.TYPE.equals(worldObject.getType())) {
                result = Collision.HOLE;
            }
        }

        return result;
    }
}
