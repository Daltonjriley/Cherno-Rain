package entity.projectile;

import graphics.Screen;
import graphics.Sprite;

public class WizardProjectile extends Projectile {
    
    public WizardProjectile(int x, int y, double dir) {

        super(x, y, dir);
        range = 200;
        damage = 20;
        fireRate = 10;
        speed = 3;
        sprite = Sprite.wizard_projectile;

        nx = speed * Math.cos(angle);
        ny = speed * Math.sin(angle);

    }

    @Override
    public void update() {

        move();
    }

    @Override
    protected void move() {

        x += nx;
        y += ny;
    }

    @Override
    public void render(Screen screen){

        screen.renderProjectile(x, y, this);
    }
}
