package level;

public class TileCoordinate {

    @SuppressWarnings("FieldMayBeFinal")
    private int x;
    @SuppressWarnings("FieldMayBeFinal")
    private int y;
    private final int TILE_SIZE = 16;

    public TileCoordinate(int x, int y) {

        this.x = x * TILE_SIZE;
        this.y = y * TILE_SIZE;

    }

    public int getX() {

        return x;

    }

    public int getY() {

        return y;

    }

    public int[] xy() {

        int[] xy = new int[2];
        xy[0] = x;
        xy[1] = y;  
        return xy;

    }
    
}
