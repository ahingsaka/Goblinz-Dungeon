package sandbox.core.world;

import java.util.ArrayList;
import java.util.List;

import sandbox.core.Globals;
import sandbox.core.GoblinzDungeon;
import sandbox.core.entities.Enemy;
import sandbox.core.entities.Hero;
import sandbox.core.entities.statik.Door;
import sandbox.core.entities.statik.rock.InvisibleRock;
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
    
    private List<Enemy> enemies = new ArrayList<Enemy>();
    
    private int halfViewWidth = 0;
    
    public void reset() {
        for (Enemy enemy : enemies) {
            enemy.reset();
        }
    }

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
                draw(surf, ty, tx, sandboxObject);
            }
        }
    }

    public void draw(Surface surf, int ty, int tx, WorldObject sandboxObject) {
        if (sandboxObject != null) {
            // Figure out where the tile goes. If it's out of screen
            // bounds,
            // skip it
            int px = worldToPixelX(surf, tx);
            int py = worldToPixelY(surf, ty) - TILE_BASE;
            if ((px > surf.width()) || (py > surf.height()) || (px + TILE_WIDTH < 0)
                    || (py + TILE_IMAGE_HEIGHT < 0)) {
                return;
            }

            surf.drawImage(sandboxObject.getImage(), px, py);
        }
    }
    

    public void paintHero(Surface surf, float alpha) {
        Hero hero = Globals.getInstance().getHero();
        int px = worldToPixelX(surf, hero.x);
        int py = worldToPixelY(surf, hero.y) - TILE_BASE;

        // surf.drawImage(hero.getImage(), px, py);
        hero.setPosition(px, py);

    }
    
    public void paintEnemy(Enemy enemy, Surface surface, float alpha) {
        int px = worldToPixelX(surface, enemy.getX());
        int py = worldToPixelY(surface, enemy.getY()) - TILE_BASE;
        enemy.setPosition(px, py);
    }

    public void initWorldTab(int width, int height) {
        worldWidth = width;
        worldHeight = height;
        world = new WorldObject[worldWidth * worldHeight];
        
        halfViewWidth = (worldWidth / (GoblinzDungeon.SCREEN_WIDTH / TILE_WIDTH)) / 2;
        
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

        try {
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

            
        } catch (ArrayIndexOutOfBoundsException e) {
            // FIXME this is ugly
            return Collision.HOLE;
        }

        return result;
    }

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }
    
    public int getHalfViewWidth() {
        return halfViewWidth;
    }
    
    public WorldObject[] getWorld() {
        return world;
    }

}
