package sandbox.core.display.sprite;

import java.util.List;


public class Animation {
	
	private float frameTime;
	private boolean looping;
	
	private List<String> spriteIds;
		
	public Animation(int frameTime, boolean looping, List<String> spriteIds){
		this.frameTime = frameTime;
		this.looping = looping;
		this.spriteIds = spriteIds;
	}

	public float getFrameTime() {
		return frameTime;
	}

	public boolean isLooping() {
		return looping;
	}

//	public ImageLayer getCurrentFrame(){
//		return sprite.layer();
//	}
//
//	public void setCurrentFrame(int frameIndex) {
//		sprite.setSprite(frameIndex);
//	}
//	
//	public Sprite getSprite(){
//		return sprite;
//	}
}
