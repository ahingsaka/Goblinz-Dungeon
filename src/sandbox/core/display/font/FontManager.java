package sandbox.core.display.font;

import static forplay.core.ForPlay.assetManager;
import static forplay.core.ForPlay.graphics;

import java.util.HashMap;
import java.util.Map;

import forplay.core.GroupLayer;
import forplay.core.Image;
import forplay.core.ImageLayer;

public class FontManager {

    private static final int SPACE_WIDTH = 10;

    private static final int SPACE_WIDTH_BETWEEN_CHARS = 5;

    private GroupLayer groupTextLayer;
    
    private Map<String, Image> imageDictionary;
    
    public FontManager(GroupLayer textLayer) {
        this.groupTextLayer = textLayer;

        // Load all letters
        imageDictionary = new HashMap<String, Image>();
        Image aImage = assetManager().getImage("images/letters/a.png");
        Image bImage = assetManager().getImage("images/letters/b.png");
        Image cImage = assetManager().getImage("images/letters/c.png");
        Image dImage = assetManager().getImage("images/letters/d.png");
        Image eImage = assetManager().getImage("images/letters/e.png");
        Image fImage = assetManager().getImage("images/letters/f.png");
        Image gImage = assetManager().getImage("images/letters/g.png");
        Image hImage = assetManager().getImage("images/letters/h.png");
        Image iImage = assetManager().getImage("images/letters/i.png");
        Image jImage = assetManager().getImage("images/letters/j.png");
        Image kImage = assetManager().getImage("images/letters/k.png");
        Image lImage = assetManager().getImage("images/letters/l.png");
        Image mImage = assetManager().getImage("images/letters/m.png");
        Image nImage = assetManager().getImage("images/letters/n.png");
        Image oImage = assetManager().getImage("images/letters/o.png");
        Image pImage = assetManager().getImage("images/letters/p.png");
        Image rImage = assetManager().getImage("images/letters/r.png");
        Image sImage = assetManager().getImage("images/letters/s.png");
        Image tImage = assetManager().getImage("images/letters/t.png");
        Image uImage = assetManager().getImage("images/letters/u.png");
        Image vImage = assetManager().getImage("images/letters/v.png");
        Image wImage = assetManager().getImage("images/letters/w.png");
        Image xImage = assetManager().getImage("images/letters/x.png");
        Image yImage = assetManager().getImage("images/letters/y.png");
        Image zImage = assetManager().getImage("images/letters/z.png");
        
        imageDictionary.put("a", aImage);
        imageDictionary.put("b", bImage);
        imageDictionary.put("c", cImage);
        imageDictionary.put("d", dImage);
        imageDictionary.put("e", eImage);
        imageDictionary.put("f", fImage);
        imageDictionary.put("g", gImage);
        imageDictionary.put("h", hImage);
        imageDictionary.put("i", iImage);
        imageDictionary.put("j", jImage);
        imageDictionary.put("k", kImage);
        imageDictionary.put("l", lImage);
        imageDictionary.put("m", mImage);
        imageDictionary.put("n", nImage);
        imageDictionary.put("o", oImage);
        imageDictionary.put("p", pImage);        
        imageDictionary.put("r", rImage);
        imageDictionary.put("s", sImage);
        imageDictionary.put("t", tImage);
        imageDictionary.put("u", uImage);
        imageDictionary.put("v", vImage);
        imageDictionary.put("w", wImage);
        imageDictionary.put("x", xImage);
        imageDictionary.put("y", yImage);
        imageDictionary.put("z", zImage);
    }

    public TextLayer createTextLayer(String label, int x, int y) {
        
        TextLayer textLayer = new TextLayer(label);

        String[] characters = label.split("");
        for (int i = 0; i < characters.length; i++) {
            String character = characters[i];
            if (!character.isEmpty()) {

                if (character.equals(" ")) {
                    x += SPACE_WIDTH;
                } else {

                    Image image = imageDictionary.get(character.toLowerCase());
                    ImageLayer imageLayer = graphics().createImageLayer(image);
                    imageLayer.setTranslation(x, y);
                    
                    textLayer.addImageLayer(imageLayer);
                    
                    groupTextLayer.add(imageLayer);

                    x += image.width() + SPACE_WIDTH_BETWEEN_CHARS;
                }
            }

        }
        
        return textLayer;

    }

    public void clear() {
        groupTextLayer.clear();
    }

}
