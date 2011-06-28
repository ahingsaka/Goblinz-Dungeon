package sandbox.java;

import sandbox.core.GoblinzDungeon;
import forplay.core.ForPlay;
import forplay.java.JavaAssetManager;
import forplay.java.JavaPlatform;

public class SandboxGameJava {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JavaAssetManager assets = JavaPlatform.register().assetManager();
        assets.setPathPrefix("src/sandbox/resources");
        ForPlay.run(new GoblinzDungeon());
    }

}
