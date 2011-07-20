package sandbox.core.display.sprite;

import java.util.List;

public class Animation {

    private float frameTime;
    private boolean looping;

    private boolean hasFinished;
    private boolean hasBegun;

    // Current time in milliseconds that the current frame has been shown for.
    private float time;

    // Current frame index in the animation
    private int frameIndex;

    private List<String> spriteIds;

    public Animation(int frameTime, boolean looping, List<String> spriteIds) {
        this.frameTime = frameTime;
        this.looping = looping;
        this.spriteIds = spriteIds;
        this.hasFinished = false;
        this.hasBegun = false;
    }

    public float getFrameTime() {
        return frameTime;
    }

    public boolean isLooping() {
        return looping;
    }

    public void update(float delta) {

        if (hasBegun) {
            time += delta;
            while (time > frameTime) {
                time -= frameTime;

                if (looping) {
                    frameIndex = (frameIndex + 1) % spriteIds.size();
                } else {
                    frameIndex = Math.min(frameIndex + 1, spriteIds.size() - 1);
                }
            }

        }

    }

    public String getCurrentFrameId() {

        if (hasFinished) {
            return null;
        }

        if (!looping) {
            if (frameIndex == spriteIds.size() - 1) {
                hasFinished = true;
                hasBegun = false;
            }
        }

        return spriteIds.get(frameIndex);
    }

    public void start() {
        if (!hasBegun) {
            this.time = 0;
            this.frameIndex = 0;
            this.hasFinished = false;
            this.hasBegun = true;
        }
    }

}
