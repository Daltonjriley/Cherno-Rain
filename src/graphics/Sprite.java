package graphics;

public class Sprite {

    public final int SIZE;
    @SuppressWarnings("FieldMayBeFinal")
    private int x;
    @SuppressWarnings("FieldMayBeFinal")
    private int y;
    public int[] pixels;
    @SuppressWarnings("FieldMayBeFinal")
    private SpriteSheet sheet;

    public static Sprite grass = new Sprite(16, 0, 0, SpriteSheet.tiles);
    public static Sprite voidSprite = new Sprite(16, 0x1B87E0);

    public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
    public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
    public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

    public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
    public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
    public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

    public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);
    public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
    public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

    public Sprite(int size, int x, int y, SpriteSheet sheet) {

        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    public Sprite(int size, int color) {

        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        setColor(color);
    }

    private void setColor(int color) {

      for (int i = 0; i < SIZE*SIZE; i++){

        pixels[i] = color;
      }
    }

    private void load() {

        for (int yy = 0; yy < SIZE; yy++) {
            for (int xx = 0; xx < SIZE; xx++) {
                pixels[xx + yy * SIZE] = sheet.pixels[(xx + this.x) + (yy + this.y) * sheet.SIZE];
            }
        }
    }   
    
}
