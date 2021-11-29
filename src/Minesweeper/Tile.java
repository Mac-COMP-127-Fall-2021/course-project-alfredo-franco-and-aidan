package Minesweeper;

import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Rectangle;

public class Tile {
    private static final double WIDTH = 10;
    private static final double HEIGHT = 10;
    private boolean isMine = false;
    private int numOfAdjMines = 0;

    GraphicsGroup image;
    Rectangle rectangle;
    GraphicsText numAdj;
    
    public Tile(double xPos, double yPos) {
        image = new GraphicsGroup();
        rectangle = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        image.add(rectangle);
        numAdj = new GraphicsText("" + numOfAdjMines);
        image.add(numAdj);
        numAdj.setCenter(xPos + WIDTH / 2, yPos + HEIGHT / 2);
        numAdj.setFontSize(9);
    }

    public GraphicsGroup getTileShape() {
        return image;
    }

    public void makeBomb() {
        isMine = true;
        rectangle.setFillColor(Color.RED);
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMines(int mines) {
        numOfAdjMines = mines;
        numAdj.setText("" + numOfAdjMines);
    }
}
