package sandbox.core.world;

import sandbox.core.entities.Enemy;
import sandbox.core.entities.Hero;

public class CollisionUtils {

    /**
     * Look if an enemy collides with the hero
     * 
     * @param enemy
     * @param hero
     */
    public static boolean checkCollision(Enemy enemy, Hero hero, int width) {
        boolean result = false;

        if (((enemy.getX() + width) >= hero.x) && (enemy.getX() <= (hero.x + width))) {
            if ((enemy.getY() + width >= hero.y) && (enemy.getY() <= (hero.x + width))) {
                result = true;
            }
        }
        
        return result;
    }

    public static boolean checkCollisionWithSword(Enemy enemy, Hero hero) {
        boolean result = false;
        int width = 2;
        
        if (((enemy.getX() + width) >= hero.x) && (enemy.getX() <= (hero.x + width))) {
            if ((enemy.getY() + width >= hero.y) && (enemy.getY() <= (hero.x + width))) {
                result = true;
            }
        }
        
        return result;
    }

}
