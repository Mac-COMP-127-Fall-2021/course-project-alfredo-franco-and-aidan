package Minesweeper;

import java.awt.Color;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;

public class Tile {
    private static final double WIDTH = 15;
    private static final double HEIGHT = 15;
    private boolean isMine = false;
    private int numOfAdjMines = 0;
    Image mine = new Image(0, 0, "bomb.png");
    Image tile = new Image(0, 0, "Tile.png");
    GraphicsGroup image;
    Rectangle rectangle;
    GraphicsText numAdj;
    Rectangle cover;
    
    public Tile(double xPos, double yPos) {
        image = new GraphicsGroup();
        rectangle = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        image.add(rectangle);
        numAdj = new GraphicsText("" + numOfAdjMines);
        image.add(numAdj);
        
        numAdj.setCenter(xPos + WIDTH / 2, yPos + HEIGHT / 2);
        numAdj.setFontSize(9);

        mine.setMaxWidth(14);
        mine.setMaxHeight(14);
        mine.setPosition(xPos+1, yPos+1);
        tile.setMaxWidth(17);
        tile.setMaxHeight(17);
        tile.setPosition(xPos, yPos);
        
        // cover = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        // cover.setFillColor(Color.GRAY);
        image.add(tile);
    }

    public GraphicsGroup getTileShape() {
        return image;
    }

    public void makeBomb() {
        isMine = true;
        rectangle.setFillColor(Color.RED);
        image.add(mine);
        image.remove(numAdj);
        image.add(tile);
    }

    public boolean isMine() {
        return isMine;
    }

    public void setMines(int mines) {
        numOfAdjMines = mines;
        numAdj.setText("" + numOfAdjMines);
    }

    public void removeCover() {
        image.remove(tile);
    }
}
