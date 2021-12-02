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
    private boolean isCovered = true;
    private boolean isFlagged = false;
    private int numOfAdjMines = 0;
    private int x,y;
    Image mine = new Image(0, 0, "bomb.png");
    Image tile = new Image(0, 0, "Tile.png");
    Image flag = new Image(0, 0, "flag.png");
    GraphicsGroup image;
    Rectangle rectangle;
    GraphicsText numAdj;
    Rectangle cover;
    
    public Tile(int x, int y) {
        double xPos = x*20;
        double yPos = y*20;
        this.x = x;
        this.y = y;
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
        flag.setMaxWidth(14);
        flag.setMaxHeight(14);
        flag.setPosition(xPos+1, yPos+1);
        
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
        if (mines != 0) {
            numAdj.setText("" + numOfAdjMines);
        }
        else {
            image.remove(numAdj);
        }
    }

    public boolean removeCover() {
        if (!isFlagged) {
            if(isCovered) {
                image.remove(tile);
                isCovered = false;
                if(numOfAdjMines == 0) {
                    return true;
                }
            }
        }
        return false;
    }
    public void toggleFlag() {
        if (isCovered){
            if(!isFlagged) {
                image.add(flag);
                isFlagged = true;
            }else{
                image.remove(flag);
                isFlagged = false;
            }
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
