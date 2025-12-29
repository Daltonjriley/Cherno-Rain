package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class SpriteSheet {

    @SuppressWarnings("FieldMayBeFinal")
    private String path;
    public final int SIZE;
    public final int SPRITE_WIDTH;
    public final int SPRITE_HEIGHT;
    public final int WIDTH;
    public final int HEIGHT;
    public final int[] pixels;
    public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 256);
    public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_level.png", 48);
    public static SpriteSheet wizard_projectile = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

    public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 128, 96);
    public static SpriteSheet player_down = new SpriteSheet(SpriteSheet.player, 0, 0, 1, 3, 32);

    public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
        
        int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		SPRITE_WIDTH = w;
		SPRITE_HEIGHT = h;
        WIDTH = SPRITE_WIDTH;
        HEIGHT = SPRITE_HEIGHT;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.SPRITE_WIDTH];
			}
        }
    }

    public SpriteSheet(String path, int size) {
        
        this.path = path;
        this.SIZE = size;
        this.SPRITE_WIDTH = size;
        this.SPRITE_HEIGHT = size;
        this.WIDTH = this.SPRITE_WIDTH;
        this.HEIGHT = this.SPRITE_HEIGHT;
        this.pixels = new int[SIZE * SIZE];
        load();
    }

    public SpriteSheet(String path, int width, int height) {
        
        this.path = path;
        this.SIZE = -1;
        this.SPRITE_WIDTH = width;
        this.SPRITE_HEIGHT = height;
        this.WIDTH = this.SPRITE_WIDTH;
        this.HEIGHT = this.SPRITE_HEIGHT;
        this.pixels = new int[SPRITE_WIDTH * SPRITE_HEIGHT];
        load();
    }

    @SuppressWarnings("CallToPrintStackTrace")
    private void load() {

        try {
            BufferedImage image = null;
            java.io.InputStream is = SpriteSheet.class.getResourceAsStream(path);
            if (is != null) {
                image = ImageIO.read(is);
            } else {
                java.io.File f = new java.io.File("res" + path);
                if (f.exists()) {
                    image = ImageIO.read(f);
                }
            }
            if (image == null) throw new IOException("Could not load image: " + path);
            int w = image.getWidth();
            int h = image.getHeight();
            image.getRGB(0, 0, w, h, pixels, 0, w);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}