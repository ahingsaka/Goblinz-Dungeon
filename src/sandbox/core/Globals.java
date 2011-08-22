package sandbox.core;

import forplay.core.GroupLayer;
import sandbox.core.entities.Hero;
import sandbox.core.world.GoblinzDungeonWorld;

public class Globals {

    private static final Globals INSTANCE = new Globals();

    private Hero hero;

    private GoblinzDungeonWorld world;

    private GroupLayer characterLayer;

    private Globals() {

    }

    public static Globals getInstance() {
        return INSTANCE;
    }

    public Hero getHero() {
        return hero;
    }
    
    public GoblinzDungeonWorld getWorld() {
        return world;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public void setWorld(GoblinzDungeonWorld world) {
        this.world = world;
    }

    public void setCharacterLayer(GroupLayer characterLayer) {
        this.characterLayer = characterLayer;
    }
    
    public GroupLayer getCharacterLayer() {
        return characterLayer;
    }

}
