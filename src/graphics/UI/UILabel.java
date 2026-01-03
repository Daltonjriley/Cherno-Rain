package graphics.UI;

import graphics.Font;
import graphics.Screen;
import util.Vector2i;

public class UILabel extends UIComponent {

    public String text;
    private Font font;

    public UILabel(Vector2i position, String text) {
        super(position);
        this.text = text;
        font = new Font();
    }

    @Override
    public void render(Screen screen) {
        font.render(position.x + offset.x, position.y + offset.y, -5, 0, text, screen);
    }
    
}
