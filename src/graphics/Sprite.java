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

    public Sprite(int size, int x, int y, SpriteSheet sheet) {

        this.SIZE = size;
        this.pixels = new int[SIZE * SIZE];
        this.x = x * size;
        this.y = y * size;
        this.sheet = sheet;
        load();
    }

    private void load() {

        for (int yy = 0; yy < SIZE; yy++) {
            for (int xx = 0; xx < SIZE; xx++) {
                pixels[xx + yy * SIZE] = sheet.pixels[(xx + this.x) + (yy + this.y) * sheet.SIZE];
            }
        }
    }   
    
}
