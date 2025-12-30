package entity.mob;

import entity.Entity;
import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.Screen;
import graphics.Sprite;

public abstract class Mob extends Entity{

    protected Sprite sprite;
    protected boolean moving = false;

    protected  enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    protected Direction dir;

    public void move(int xa, int ya) {

        if(xa != 0 && ya != 0) {
            move(xa, 0);
            move(0, ya);
            return;
        }

        if(xa > 0) dir = Direction.UP;
        if(xa < 0) dir = Direction.LEFT;
        if(ya > 0) dir = Direction.DOWN;
        if(ya < 0) dir = Direction.UP;

        if(!collision(xa, ya)) {
            x += xa;
            y += ya;
        }
    }

    @Override
    public abstract void update();

    protected void shoot(int x, int y, double dir) {

        
        Projectile p = new WizardProjectile(x, y, dir); 
        level.add(p);
    }

    private boolean collision(int xa, int ya) {

        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
            int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
            if (level.getTile(xt, yt).solid()) solid = true;
        }
        
        return solid;
    }

    @Override
    public abstract void render(Screen screen);
    
}
