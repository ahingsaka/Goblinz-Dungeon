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
    public static boolean checkCollision(Enemy enemy, Hero hero) {
        boolean result = false;

        int width = 1;
        if (((enemy.getX() + width) >= hero.x) && (enemy.getX() <= (hero.x + width))) {
            if ((enemy.getY() + width >= hero.y) && (enemy.getY() <= (hero.x + width))) {
                result = true;
//                System.out.println("collide");
            }
        }
        
        return result;
    }

}
