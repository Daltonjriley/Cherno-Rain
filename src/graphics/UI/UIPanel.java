package graphics.UI;

import graphics.Screen;
import graphics.Sprite;
import java.util.ArrayList;
import java.util.List;
import util.Vector2i;

public class UIPanel {

    private List<UIComponent> components = new ArrayList<>();
    private Vector2i position;

    private Sprite sprite;

    public UIPanel(Vector2i position) {
        this.position = position;
        sprite = new Sprite(80, 168, 0XCACACA);
    }

    public void addComponent(UIComponent component) {
        components.add(component);
    }

    public void update() {
        for (UIComponent component : components) {
            component.setOffset(position);
            component.update();
        }
    }

    public void render(Screen screen) {
        screen.renderSprite(position.x, position.y, sprite, false);
        for (UIComponent component : components) {
            component.render(screen);
        }
    }
    
}
