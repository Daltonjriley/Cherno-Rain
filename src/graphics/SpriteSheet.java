package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    @SuppressWarnings("FieldMayBeFinal")
    private String path;
    public final int SIZE;
    public final int[] pixels;
    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_level.png", 48);
    public static SpriteSheet wizard_projectile = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

    public SpriteSheet(String path, int size) {
        
        this.path = path;
        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void load() {

        try {

            BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}