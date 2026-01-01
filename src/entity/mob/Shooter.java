package entity.mob;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.SpriteSheet;

@SuppressWarnings("FieldMayBeFinal")
public class Shooter extends Mob{

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

    private int time = 0;
	private int xa = 0;
	private int ya = 0;
    private boolean walking = false;

    public Shooter(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = down.getSprite();
    }

    @Override
    public void update() {

        time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(4) == 0) {
				xa = 0;
				ya = 0;
			}
		}
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
        
        Player player = level.getClientPlayer();
        double dx = player.getX() - x;
        double dy = player.getY() - y;
        double pdir = Math.atan2(dy, dx);
        if (time % 100 == 0) shoot(x, y, pdir);
    }

    @Override
    public void render(Screen screen) {

        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - 16), (int)(y - 16), this);
    }
    
}
