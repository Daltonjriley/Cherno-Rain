package entity.mob;

import entity.projectile.Projectile;
import entity.projectile.WizardProjectile;
import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.Sprite;
import graphics.SpriteSheet;
import input.Keyboard;
import input.Mouse;
@SuppressWarnings("FieldMayBeFinal")
public class Player extends Mob {

    private Keyboard input;
    private Sprite sprite;
    private int anim = 0;
    private boolean walking = false;
    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;
    
    private int fireRate = 0;
    
    public Player(Keyboard input) {

        this.input = input;
        sprite = Sprite.player_forward;
    }

    public Player(int x, int y, Keyboard input) {

        this.x = x;
        this.y = y;
        this.input = input;
        sprite = Sprite.player_forward;
        fireRate = WizardProjectile.FIRE_RATE;
    }

    @Override
    public void update() {

        if(walking) animSprite.update();
        else animSprite.setFrame(0);
        if (WizardProjectile.FIRE_RATE > 0) {
            fireRate--;
        }

        int xa = 0, ya= 0;

        if (anim < 7500) anim++;
        else anim = 0;

        if(input.up)    {
            ya-=2;
            animSprite = up;
        } else if(input.down)  {
            ya+=2;
            animSprite = down;
        } else if(input.left)  {
            xa-=2;
            animSprite = left;
        } else if(input.right) {
            xa+=2;
            animSprite = right;
        }

        if (xa != 0 || ya != 0) {
            move(xa, ya);
            walking = true;
        } else {
            walking = false;
        }

        clear();
        updateShooting();
    }

    private void clear() {

        for (int i = 0; i < level.getProjectiles().size(); i++) {
            Projectile p = level.getProjectiles().get(i);
            if (p.isRemoved()) level.getProjectiles().remove(i);
        }
    }

    public void updateShooting() {

        if (Mouse.getB() == 1 && fireRate <= 0) {
            double dx = Mouse.getX() - (300 * 3) / 2;
            double dy = Mouse.getY() - (168 * 3) / 2;
            double dir = Math.atan2(dy, dx);
            shoot(x, y, dir);
            fireRate = WizardProjectile.FIRE_RATE;
        }
    }

    @Override
    public void render(Screen screen) {

        int flip = 0;
        sprite = animSprite.getSprite();
        screen.renderPlayer(x - 16, y - 16, sprite, flip);

    }
}
