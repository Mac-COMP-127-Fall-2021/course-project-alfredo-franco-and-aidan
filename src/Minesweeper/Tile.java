package Minesweeper;

import edu.macalester.graphics.Rectangle;

public class Tile {
    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;

    Rectangle tileShape;
    
    public Tile(double xPos, double yPos) {
        tileShape = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public Rectangle getTileShape() {
        return tileShape;
    }
}
