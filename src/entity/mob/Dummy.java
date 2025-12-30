package entity.mob;

import graphics.Screen;
import graphics.Sprite;

public class Dummy extends Mob {

    public Dummy(int x, int y) {
        this.x = x << 4;
        this.y = y << 4;
        sprite = Sprite.player_forward;
    }

    @Override
    public void update() {
        
    }

    @Override
    public void render(Screen screen) {
        screen.renderPlayer(x, y, sprite, 0);
        
    }
    
}
