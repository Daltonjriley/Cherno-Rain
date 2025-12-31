package entity;

import graphics.Screen;
import graphics.Sprite;
import java.util.Random;
import level.Level;

public class Entity {

    protected double x, y;
    private boolean removed = false;
    protected Level level;
    protected final Random random = new Random();
    protected Sprite sprite;

    public void update() {}

    public void render(Screen screen) {

        if (sprite != null) {
            screen.renderSprite((int)x, (int)y, sprite, true);
        }
    }

    public void remove() {
        removed = true;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
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
