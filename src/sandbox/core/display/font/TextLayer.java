package sandbox.core.display.font;

import java.util.ArrayList;
import java.util.List;

import forplay.core.ImageLayer;

public class TextLayer {
    
    public List<ImageLayer> imageLayers;
    
    public String text;
    
    public float alpha = (float) 1.0;
    
    public TextLayer(String text) {
        this.text = text;
        imageLayers = new ArrayList<ImageLayer>();
    }
    
    public void addImageLayer(ImageLayer imageLayer) {
        this.imageLayers.add(imageLayer);
    }
    
    public void setAlpha(float alpha) {
        this.alpha = alpha;
        for (ImageLayer imageLayer : imageLayers) {
            imageLayer.setAlpha(alpha);
        }
    }
    
    public void refresh() {
        setAlpha(alpha);
    }

}
