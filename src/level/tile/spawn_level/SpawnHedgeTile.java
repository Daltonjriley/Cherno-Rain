package level.tile.spawn_level;

import graphics.Screen;
import graphics.Sprite;
import level.tile.Tile;

public class SpawnHedgeTile extends Tile {

    public SpawnHedgeTile(Sprite sprite) {
        super(sprite);
    }   

    @Override
    public void render(int x, int y, Screen screen) {

        screen.renderTile(x << 4, y << 4, this);
    }

    @Override
    public boolean solid() {

        return true;
    }
    
}
