package Minesweeper;

import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Rectangle;


/**
 * author: Aidan, Alfredo, Franco. 
 * This class sets the parameters for tiles and handles interactions dealing with
 * uncovering and flagging. In addition to checking if mine is triggered. 
 */


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

    /**
     * This method returns the basic tile graphics object to be inserted in the 
     * canvas. This object allows us to change the image and change their properties
     * efficiently in conjunction with other methods.
     */
    public GraphicsGroup getTileShape() {
        return image;
    }

    /**
     * This method sets the tile so that it is a mine and removes the number
     * of adjacent bombs. 
     */
    public void makeMine() {
        isMine = true;
        image.remove(numAdj);
    }

    /**
     * This method checks whether a tile is a mine.
     */
    public boolean isMine() {
        return isMine;
    }

    /**
     * This method set the number of adjacent mines to the tile and if number is 0  
     * it returns blank. 
     */
    public void setNumAdjMines(int mines) {
        numOfAdjMines = mines;
        if (mines != 0) {
            numAdj.setText("" + numOfAdjMines);
        }
        else {
            image.remove(numAdj);
        }
    }

    /**
     * 
     * This method checks that the tile is not flagged and that it's covered. 
     * If it's a mine it sets the image of the tile to a bomb. If it's not a mine it
     * uncovers the tile. It also checks if the number of adjacent mines is 0 and returns
     * true if it is. 
     */
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

    /**
     * Adds a flag to the tile if it's covered. 
     */
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

    /**
     * Returns the x index of the tile.  
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y index of the tile. 
     */
    public int getY() {
        return y;
    }

    /**
     * Checks if the tile is covered.
     */
    public boolean isCovered() {
        return isCovered;
    }

    /**
     * Checks if the tile is flagged. 
     */
    public boolean isFlagged() {
        return isFlagged;
    }

    /**
     * Checks the number of adjactent tiles. 
     */
    public int getNumAdjacent() {
        return numOfAdjMines;
    }
}
