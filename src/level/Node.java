package level;

import util.Vector2i;

public class Node {

    // Variables
    public Vector2i tile;
    public Node parent;
    public double gCost, fCost, hCost;

    // Constructor
    public Node(Vector2i tile, Node parent, double gCost, double hCost) {
        this.tile = tile;
        this.parent = parent;
        this.gCost = gCost;
        this.hCost = hCost;
        this.fCost = gCost + hCost;
    }
    
}
