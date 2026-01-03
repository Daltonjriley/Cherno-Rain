package graphics.UI;

import graphics.Screen;
import util.Vector2i;

public class UIComponent {


    public Vector2i position, offset;
    public int backgroundColor;

    public UIComponent(Vector2i position) {
        this.position = position;
        this.offset = new Vector2i();
    }
    
    public void update() {}   
    
    public void render(Screen screen) {}
    
    void setOffset(Vector2i offset) {
        this.offset = offset;
    }
}
