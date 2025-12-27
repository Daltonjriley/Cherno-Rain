package entity.projectile;

import entity.Entity;
import graphics.Sprite;

public abstract class Projectile extends Entity {

    protected final int xOrigin, yOrigin;
    protected final double angle;
    protected Sprite sprite;
    protected double nx, ny;
    protected double speed, fireRate, range, damage;
    
    public Projectile(int x, int y, double dir) {

        this.xOrigin = x;
        this.yOrigin = y;
        this.angle = dir;
        this.x = x;
        this.y = y;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public int getSpriteSize() {
        return sprite.SIZE;
    }

    protected void move() {}
}
