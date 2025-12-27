import entity.mob.Player;
import graphics.Screen;
import input.Keyboard;
import input.Mouse;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;
import level.Level;
import level.TileCoordinate;

public class Game extends Canvas implements Runnable {
    private static final long serialVersionUID = 1L;

    public static int width = 300;
    public static int height = 168;
    public static int scale = 3;

    private Thread thread;
    private JFrame frame;
    
    public JFrame getFrame() {
        return frame;
    }

    public void setFrame(JFrame frame) {
        this.frame = frame;
    }

    private Keyboard key = new Keyboard();
    @SuppressWarnings("FieldMayBeFinal")
    private Level level;
    @SuppressWarnings("FieldMayBeFinal")
    private Player player;
    private boolean running = false;
    
    @SuppressWarnings("FieldMayBeFinal")
    private Screen screen;
    @SuppressWarnings("FieldMayBeFinal")
    private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    @SuppressWarnings("FieldMayBeFinal")
    private int[] pixels = ((java.awt.image.DataBufferInt) image.getRaster().getDataBuffer()).getData();

    public Game() {

        Dimension size = new Dimension(width * scale, height * scale);
        setPreferredSize(size);

        screen = new Screen(width, height);
        frame = new JFrame();
        key = new Keyboard();
        level = Level.spawn;
        TileCoordinate playerSpawn = new TileCoordinate(19, 62);
        player = new Player(playerSpawn.getX(), playerSpawn.getY(), key);
        player.init(level);

        addKeyListener(key);

        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);

    }

    public synchronized void start() {

        running = true;
        thread = new Thread(this, "Display");
        thread.start();

    }

    @SuppressWarnings("CallToPrintStackTrace")
    public synchronized void stop() {

        running = false;

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    @Override
    public void run() {

        long timer = System.currentTimeMillis();
        long lastTime = System.nanoTime();
        final double nsPerUpdate = 1000000000.0 / 60.0;
        double delta = 0;

        int frames = 0;
        int updates = 0;

        requestFocus();

        while (running) {

            long now = System.nanoTime();
            delta += (now - lastTime) / nsPerUpdate;
            lastTime = now;

            while (delta >= 1) {
                update();
                updates++;
                delta--;
            }   

            render();
            frames++;

            if (System.currentTimeMillis() - timer >= 1000) {
                timer += 1000;
                frame.setTitle("Rain | " + updates + " ups, " + frames + " fps");
                updates = 0;
                frames = 0;
            }

        }
        stop();

    }

    public void update() {
        
        key.update();
        player.update();

    }


    @SuppressWarnings("UnnecessaryReturnStatement")
    public void render() {

        BufferStrategy bs = getBufferStrategy();
        if (bs == null) {
            createBufferStrategy(3);
            return;
        }

        screen.clear();

        int xScroll = player.x - screen.width / 2;
        int yScroll = player.y - screen.height / 2;
        level.render(xScroll, yScroll, screen);
        player.render(screen);

        System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

        Graphics g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
        g.dispose();
        bs.show();

    }

        public static void main(String[] args) {
        
        Game game = new Game();
        game.getFrame().setResizable(false);
        game.getFrame().setTitle("Rain");
        game.getFrame().add(game);
        game.getFrame().pack();
        game.getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        game.getFrame().setLocationRelativeTo(null);
        game.getFrame().setVisible(true);

        game.start();

    }

}