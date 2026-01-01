package util;

public class Vector2i {

    // Variables
    private int x;
    private int y;
    
    // Constructors
    public Vector2i() {
        this.x = 0;
        this.y = 0; 
    }
    public Vector2i(int x, int y) {
        this.x = x;
        this.y = y; 
    }
    public Vector2i(double x, double y) {
        this.x = (int) x;
        this.y = (int) y; 
    }
    public Vector2i(Vector2i other) {
        this.x = other.x;
        this.y = other.y; 
    }

    // Getters and Setters
    public void set(int x, int y) {
        this.x = x;
        this.y = y;
    }
    public int getX() {
        return x;
    }
    public Vector2i setX(int x) {
        this.x = x;
        return this;
    }
    public int getY() {
        return y;
    }
    public Vector2i setY(int y) {
        this.y = y;
        return this;
    }

    // Vector Operations
    public Vector2i add(Vector2i other) {
        this.x += other.x;
        this.y += other.y;
        return this;
    }
    public Vector2i subtract(Vector2i other) {
        this.x -= other.x;
        this.y -= other.y;
        return this;
    }

    @Override
    public boolean equals(Object object) {
		if (!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}

    public static double getDistance(Vector2i v0, Vector2i other) {
        int dx = v0.x - other.x;
        int dy = v0.y - other.y;
        return Math.sqrt(dx * dx + dy * dy);
    }
}
