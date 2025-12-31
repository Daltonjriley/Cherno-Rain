package level;

import entity.Entity;
import entity.mob.Mob;
import entity.mob.Player;
import entity.particle.Particle;
import entity.projectile.Projectile;
import graphics.Screen;
import java.util.ArrayList;
import java.util.List;
import level.tile.Tile;

public class Level {

    protected  int width, height;
    protected  int[] tilesInt;
    protected int[] tiles;
    public static Level spawn = new SpawnLevel("/levels/spawn.png");

    @SuppressWarnings("FieldMayBeFinal")
    private List<Entity> entities = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Projectile> projectiles = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Particle> particles = new ArrayList<>();
    @SuppressWarnings("FieldMayBeFinal")
    private List<Player> players = new ArrayList<>();

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Level(int width, int height) {

        this.width = width;
        this.height = height;
        tilesInt = new int[width * height];
        generateLevel();
    }

    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Level(String path) {

        loadLevel(path);
        generateLevel();
    }

    protected void generateLevel() {}

    protected void loadLevel(String path) {}

    public void update() {

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).update();
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).update();
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).update();
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).update();
        }

        remove();
    }

    private void remove() {

        for (int i = 0; i < entities.size(); i++) {
            if (entities.get(i).isRemoved()) {
                entities.remove(i);
            }
        }

        for (int i = 0; i < projectiles.size(); i++) {
            if (projectiles.get(i).isRemoved()) {
                projectiles.remove(i);
            }
        }

        for (int i = 0; i < particles.size(); i++) {
            if (particles.get(i).isRemoved()) {
                particles.remove(i);
            }
        }

        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).isRemoved()) {
                players.remove(i);
            }
        }
    }

    //private void time() {}

    public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {

        boolean solid = false;
        for (int c = 0; c < 4; c++) {
            int xt = (x - c % 2 * size + xOffset) >> 4;
            int yt = (y - c / 2 * size + yOffset) >> 4;
            if (getTile(xt, yt).solid()) solid = true;
        }

        return solid;
    }

    public void render(int xScroll, int yScroll, Screen screen) {

        screen.setOffset(xScroll, yScroll);
        int x0 = xScroll >> 4;
        int x1 = (xScroll + screen.width + 16) >> 4;
        int y0 = yScroll >> 4;
        int y1 = (yScroll + screen.height + 16) >> 4;

        for (int y = y0; y < y1; y++) {
            for (int x = x0; x < x1; x++){

                getTile(x, y).render(x, y, screen);
           
            }
        }

        for (int i = 0; i < entities.size(); i++) {
            entities.get(i).render(screen);
        }

        for (int i = 0; i < projectiles.size(); i++) {
            projectiles.get(i).render(screen);
        }

        for (int i = 0; i < particles.size(); i++) {
            particles.get(i).render(screen);
        }

        for (int i = 0; i < players.size(); i++) {
            players.get(i).render(screen);
        }
    }

    public List<Player> getPlayers() {

        return players;
    }

    public Player getPlayerAt(int index) {

        return players.get(index);
    }

    public Player getClientPlayer() {

        return players.get(0);
    }

    public List<Entity> getEntities(Entity e, int radius) {

        List<Entity> result = new ArrayList<>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			if (entity.equals(e)) continue;
			int x = (int)entity.getX();
			int y = (int)entity.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}
		return result;
    }

    public List<Mob> getPlayers(Entity e, int radius) {
		List<Mob> result = new ArrayList<>();
		int ex = (int)e.getX();
		int ey = (int)e.getY();
		for (int i = 0; i < players.size(); i++) {
			Mob player = players.get(i);
			int x = (int)player.getX();
			int y = (int)player.getY();
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}

    public void add(Entity e) {

        e.init(this);
        switch (e) {
            case Particle particle -> particles.add(particle);
            case Projectile projectile -> projectiles.add(projectile);
            case Player player -> players.add(player);
            default -> entities.add(e);
        }
       
    }

    public Tile getTile(int x, int y) {

        if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
        
        if (tiles[x + y * width] == Tile.col_spawn_floor) return Tile.spawn_floor;
		if (tiles[x + y * width] == Tile.col_spawn_grass) return Tile.spawn_grass;
		if (tiles[x + y * width] == Tile.col_spawn_hedge) return Tile.spawn_hedge;
		if (tiles[x + y * width] == Tile.col_spawn_wall1) return Tile.spawn_wall1;
		if (tiles[x + y * width] == Tile.col_spawn_wall2) return Tile.spawn_wall2;
		if (tiles[x + y * width] == Tile.col_spawn_water) return Tile.spawn_water;
        return Tile.voidTile;
    }

    public List<Projectile> getProjectiles() {
        return projectiles;
    }
    
}
