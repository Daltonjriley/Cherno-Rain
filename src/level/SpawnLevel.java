package level;

import entity.mob.Chaser;
import entity.mob.Dummy;
import entity.mob.Searcher;
import java.io.IOException;

public class SpawnLevel extends Level {

    public SpawnLevel(String path) {
        super(path);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    protected void loadLevel(String path) {
        //System.out.println("Loaded level from " + path);

        try {

            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(SpawnLevel.class.getResource(path));
            int w = width = image.getWidth();
            int h = height = image.getHeight();
            tiles = new int[w * h];
            image.getRGB(0, 0, w, h, tiles, 0, w);

        } catch (IOException e) {
            System.out.println("Could not load level file!");
            e.printStackTrace();
        }
        for (int i = 0; i < 5; i++) {
            add(new Dummy(20 + i, 55));
        }
        add(new Chaser(20, 55));
        add(new Searcher(17, 30));
    }

    @Override
    protected void generateLevel() {


    }
}