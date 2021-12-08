package Minesweeper;

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
    Image tile = new Image(0, 0, "Tile.png");
    GraphicsGroup image;
    Rectangle rectangle;
    GraphicsText numAdj;
    
    public Tile(double xPos, double yPos, int xIndex, int yIndex) {
        this.x = xIndex;
        this.y = yIndex;
        image = new GraphicsGroup();
        rectangle = new Rectangle(xPos, yPos, WIDTH, HEIGHT);
        numAdj = new GraphicsText("" + numOfAdjMines);
        image.add(numAdj);
        
        numAdj.setCenter(xPos + WIDTH / 2, yPos + HEIGHT / 2);
        numAdj.setFontSize(9);

        tile.setMaxWidth(17);
        tile.setMaxHeight(17);
        tile.setPosition(xPos, yPos);
        image.add(tile);
    }

    public GraphicsGroup getTileShape() {
        return image;
    }

    public void makeBomb() {
        isMine = true;
        image.remove(numAdj);
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
                if(isMine) {
                    tile.setImagePath("bomb.png");     
                } else {
                    image.remove(tile);
                    image.add(rectangle);
                }
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
                tile.setImagePath("flag.png");
                isFlagged = true;
            }else{
                tile.setImagePath("tile.png");
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

    public boolean isCovered() {
        return isCovered;
    }

    public boolean isFlagged() {
        return isFlagged;
    }

    public int getNumAdjacent() {
        return numOfAdjMines;
    }
}
