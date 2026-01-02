package graphics;

public class Sprite {

    public final int SIZE;
    @SuppressWarnings("FieldMayBeFinal")
    private int x, y;
    @SuppressWarnings("FieldMayBeFinal")
    private int width, height;
    public int[] pixels;
    @SuppressWarnings("FieldMayBeFinal")
    protected SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 4, SpriteSheet.tiles);
    public static Sprite flower = new Sprite(16, 1, 0, SpriteSheet.tiles);
    public static Sprite rock = new Sprite(16, 2, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    // Spawn Level Sprites
    public static Sprite spawn_grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
    public static Sprite spawn_hedge = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
    public static Sprite spawn_water = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
    public static Sprite spawn_wall1 = new Sprite(16, 0, 1, SpriteSheet.spawn_level);
    public static Sprite spawn_wall2 = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
    public static Sprite spawn_floor = new Sprite(16, 1, 1, SpriteSheet.spawn_level);


    // Player Sprites
    public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);
    public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);
    public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

    // Wizard Projectile Sprites
    public static Sprite wizard_projectile = new Sprite(16, 0, 0, SpriteSheet.wizard_projectile);

    // Particle Sprites
    public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);

    //public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);

    protected Sprite(SpriteSheet sheet, int width, int height) {

        if (width == height) SIZE = width;
        else SIZE = -1;
        this.width = width;
        this.height = height;
        this.sheet = sheet;
    }

    public Sprite(int size, int x, int y, SpriteSheet sheet) {

        this.SIZE = size;
        this.width = size;
        this.height = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int width, int height, int color) {

        SIZE = -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[width * height];
        setColor(color);
    }

    public Sprite(int size, int color) {

        this.SIZE = size;
        this.width = size;
        this.height = size;
        this.pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    public Sprite(int[] pixels, int width, int height) {
        SIZE = (width == height) ? width : -1;
        this.width = width;
        this.height = height;
        this.pixels = new int[pixels.length];
        System.arraycopy(pixels, 0, this.pixels, 0, pixels.length);
    }

    private void setColor(int color) {

      for (int i = 0; i < width * height; i++){

        pixels[i] = color;
      }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private void load() {

        for (int yy = 0; yy < SIZE; yy++) {
            for (int xx = 0; xx < SIZE; xx++) {
                pixels[xx + yy * SIZE] = sheet.pixels[(xx + this.x) + (yy + this.y) * sheet.SIZE];
            }
        }
    }
    
    public static Sprite[] split(SpriteSheet sheet) {
        int amount = (sheet.getWidth() * sheet.getHeight()) / (sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT);
		Sprite[] sprites = new Sprite[amount];
		int current = 0;
		int[] pixels = new int[sheet.SPRITE_WIDTH * sheet.SPRITE_HEIGHT];
		for (int yp = 0; yp < sheet.getHeight() / sheet.SPRITE_HEIGHT; yp++) {
			for (int xp = 0; xp < sheet.getWidth() / sheet.SPRITE_WIDTH; xp++) {

				for (int y = 0; y < sheet.SPRITE_HEIGHT; y++) {
					for (int x = 0; x < sheet.SPRITE_WIDTH; x++) {
						int xo = x + xp * sheet.SPRITE_WIDTH;
						int yo = y + yp * sheet.SPRITE_HEIGHT;
						pixels[x + y * sheet.SPRITE_WIDTH] = sheet.getPixels()[xo + yo * sheet.getWidth()];
					}
				}

				sprites[current++] = new Sprite(pixels, sheet.SPRITE_WIDTH, sheet.SPRITE_HEIGHT);
			}
        }
        return sprites;
    }
    
}
