package entity.projectile;

import entity.Entity;
import graphics.Sprite;
import java.util.Random;

public abstract class Projectile extends Entity {

    protected final double xOrigin, yOrigin;
    protected final double angle;
    protected Sprite sprite;
    protected double x, y;
    protected double nx, ny;
    protected double distance = 0;
    protected double speed, range, damage;

    protected final Random random = new Random();
    
    public Projectile(double x, double y, double dir) {

        this.xOrigin = x;
        this.yOrigin = y;
        this.angle = dir;
        this.x = x;
        this.y = y;
    }

    @Override
    public Sprite getSprite() {
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move() {}
}
