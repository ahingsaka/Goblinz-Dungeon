package sandbox.core.display.font;

import static forplay.core.ForPlay.log;
import sandbox.core.display.sprite.Sprite;
import sandbox.core.display.sprite.SpriteLoader;
import forplay.core.GroupLayer;
import forplay.core.ImageLayer;
import forplay.core.ResourceCallback;

public class FontManager {

    private GroupLayer textLayer;

    public FontManager(GroupLayer textLayer) {
        this.textLayer = textLayer;
    }

    public void addTextLayer(String label, int x, int y) {

        String[] characters = label.split("");
        for (int i = 0; i < characters.length; i++) {
            String character = characters[i];
            if (!character.isEmpty()) {

                if (character.equals(" ")) {
                    x += 10;
                } else {

                    ImageLayer imageLayer = null;
                    imageLayer = getSprite(character).layer();
                    imageLayer.setTranslation(x, y);

                    textLayer.add(imageLayer);

                    x += imageLayer.image().width() + 5;
                }
            }

        }

    }

    private Sprite getSprite(String letter) {
        final Sprite sprite = SpriteLoader.getSprite("sprites/" + letter.toLowerCase() + ".json");
        sprite.addCallback(new ResourceCallback<Sprite>() {

            @Override
            public void error(Throwable err) {
                log().error("Error loading Font image !", err);
            }

            @Override
            public void done(Sprite resource) {
                sprite.setSprite(0);
            }
        });

        return sprite;
    }

}
