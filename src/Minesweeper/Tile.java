package Minesweeper;

import java.awt.Color;

import edu.macalester.graphics.Rectangle;

public class Tile {
    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;
    private boolean isMine = false;
    private int numOfAdjMines = 0;

    Rectangle tileShape;
    
    public Tile(double xPos, double yPos) {
        tileShape = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
    }

    public Rectangle getTileShape() {
        return tileShape;
    }

    public void makeBomb() {
        isMine = true;
        tileShape.setFillColor(Color.RED);
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMines(int mines) {
        numOfAdjMines = mines;
    }
}
