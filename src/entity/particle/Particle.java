package entity.particle;

import entity.Entity;
import graphics.Screen;
import graphics.Sprite;

public class Particle extends Entity {

    private Sprite sprite;

    private int life;

    protected double xx, yy, xa, ya;

    public Particle(int x, int y, int life) {

        this.x = x;
        this.y = y;
        this.life = life;
        sprite = Sprite.particle_normal;
        this.xx = x;
        this.yy = y;
        this.xa = random.nextGaussian();
        this.ya = random.nextGaussian();
    }
    
    @Override
    public void update() {

        this.xx += xa;
        this.yy += ya;
    }

    @Override
    public void render(Screen screen) {

        screen.renderSprite((int)xx, (int)yy, sprite, true);
    }
        
}
