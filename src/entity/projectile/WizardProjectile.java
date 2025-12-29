package entity.projectile;

import entity.particle.Particle;
import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile {

    public static final int FIRE_RATE = 15;
    
    public WizardProjectile(int x, int y, double dir) {

        super(x, y, dir);
        range = random.nextInt(100) + 150;
        damage = 20;
        speed = 3;
        sprite = Sprite.wizard_projectile;
        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);

    }

    @Override
    public void update() {

        if (level.tileCollision(x, y, nx, ny, 7)) {
            Particle p = new Particle((int)x, (int)y, 10);
            level.add(p);
            remove();
        } 

        move();
    }

    @Override
    protected void move() {

        x += nx;
        y += ny;

        if (distance() > range) {
            remove();
        }

    }

    private double distance() {

        distance = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
        return distance;
    }

    @Override
    public void render(Screen screen){

        screen.renderProjectile((int)x - 8, (int)y -2, this); 
    }
}
