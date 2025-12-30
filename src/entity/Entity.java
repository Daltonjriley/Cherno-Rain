package entity;

import graphics.Screen;
import graphics.Sprite;
import java.util.Random;
import level.Level;

public class Entity {

    public int x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Sprite sprite;

    public void update() {}

    public void render(Screen screen) {}

    public void remove() {
        removed = true;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public boolean isRemoved() {
        return removed;
    }

    public void init(Level level) {
        this.level = level;
    }
    
}
