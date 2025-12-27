package input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener{

    private final boolean[] keys = new boolean[120];
    public boolean up, down, left, right;

    public void update() {

        up = keys[KeyEvent.VK_W] || keys[KeyEvent.VK_UP];
        down = keys[KeyEvent.VK_S] || keys[KeyEvent.VK_DOWN];
        left = keys[KeyEvent.VK_A] || keys[KeyEvent.VK_LEFT];
        right = keys[KeyEvent.VK_D] || keys[KeyEvent.VK_RIGHT];

        

    }

    @Override
    public void keyPressed(java.awt.event.KeyEvent e) {

        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = true;
        }
        
    }

    @Override
    public void keyReleased(java.awt.event.KeyEvent e) {

        if (e.getKeyCode() < keys.length) {
            keys[e.getKeyCode()] = false;
        }
        
    }

    @Override
    public void keyTyped(java.awt.event.KeyEvent e) {
        
    }
}
