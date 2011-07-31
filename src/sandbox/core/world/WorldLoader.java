package sandbox.core.world;

import sandbox.core.entities.statik.Door;
import sandbox.core.entities.statik.rock.InvisibleRock;
import sandbox.core.entities.statik.rock.Rock;
import sandbox.core.entities.statik.rock.RockLeft;
import sandbox.core.entities.statik.rock.RockMiddle;
import sandbox.core.entities.statik.rock.RockRight;
import sandbox.core.entities.statik.rock.RockUp;
import forplay.core.AssetWatcher;
import forplay.core.ForPlay;
import forplay.core.Json;
import forplay.core.Json.Array;
import forplay.core.Json.Object;
import forplay.core.ResourceCallback;

public class WorldLoader {

    public static void loadLevel(String level, final GoblinzDungeonWorld goblinzDungeonWorld,
            final ResourceCallback<GoblinzDungeonWorld> callback) {

        ForPlay.assetManager().getText(level, new ResourceCallback<String>() {

            @Override
            public void done(String resource) {

                RockUp rockUp = new RockUp();
                RockLeft rockLeft = new RockLeft();
                RockRight rockRight = new RockRight();
                RockMiddle rockMiddle = new RockMiddle();
                
                Rock rock = new Rock();
                Door door = new Door();
                InvisibleRock invisibleRock = new InvisibleRock();

                AssetWatcher assetWatcher = new AssetWatcher(new AssetWatcher.Listener() {

                    @Override
                    public void done() {
                        callback.done(goblinzDungeonWorld);
                    }

                    @Override
                    public void error(Throwable err) {
                        callback.error(err);
                    }

                });

                Json.Object document = ForPlay.json().parse(resource);

                int width = document.getInt("width");
                int height = document.getInt("height");

                goblinzDungeonWorld.initWorldTab(width, height);

                Array array = document.getArray("Entities");
                for (int i = 0; i < array.length(); i++) {

                    Object entity = array.getObject(i);
                    String type = entity.getString("type");
                    int x = entity.getInt("x");
                    int y = entity.getInt("y");

                    WorldObject worldObject = null;

                    if (Rock.TYPE.equalsIgnoreCase(type)) {
                        worldObject = rock;
                    } else if (RockUp.TYPE.equalsIgnoreCase(type)) {
                        worldObject = rockUp;
                    } else if (RockLeft.TYPE.equalsIgnoreCase(type)) {
                        worldObject = rockLeft;
                    } else if (RockRight.TYPE.equalsIgnoreCase(type)) {
                        worldObject = rockRight;
                    } else if (RockMiddle.TYPE.equalsIgnoreCase(type)) {
                        worldObject = rockMiddle;
                    } else if (Door.TYPE.equalsIgnoreCase(type)) {
                        worldObject = door;
                    } else if (InvisibleRock.TYPE.equalsIgnoreCase(type)) {
                        worldObject = invisibleRock;
                    }

                    if (entity != null) {
                        assetWatcher.add(worldObject.getImage());
                        goblinzDungeonWorld.add(worldObject, x, y);
                    }

                }

                assetWatcher.start();

            }

            @Override
            public void error(Throwable err) {
                callback.error(err);
            }
        });

    }
}
