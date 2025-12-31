package entity.mob;

import graphics.AnimatedSprite;
import graphics.Screen;
import graphics.SpriteSheet;
import java.util.List;

@SuppressWarnings("FieldMayBeFinal")
public class Chaser extends Mob{

    private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
    private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
    private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
    private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

    private AnimatedSprite animSprite = down;

	private double xa = 0;
	private double ya = 0;
    private boolean walking = false;
	private double speed = 1.0;

    public Chaser(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = down.getSprite();
    }

	private void chase() {

		xa = 0;
		ya = 0;
		
		List<Mob> players = level.getPlayers(this , 50);
		if (!players.isEmpty()) {
			Mob player = players.get(0);
			if((int)x < (int)player.getX()) xa += speed;
			if((int)x > (int)player.getX()) xa -= speed;
			if((int)y < (int)player.getY()) ya += speed;
			if((int)y > (int)player.getY()) ya -= speed;
		}
	
		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

    @Override
    public void update() {

		chase();
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
		
        
    }


    @Override
    public void render(Screen screen) {

        sprite = animSprite.getSprite();
        screen.renderMob((int)(x - 16), (int)(y - 16), this);
    }
    
}
