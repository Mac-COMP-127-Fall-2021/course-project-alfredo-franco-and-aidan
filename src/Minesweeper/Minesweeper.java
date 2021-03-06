package Minesweeper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.events.ModifierKey;

/**
 * author: Aidan, Alfredo, Franco. 
 * This class sets up and manages the game of minesweeper, specifically sets up the games. 
 * handles the on-click interactions, and provides information from adjacent tiles
 * and coordinates the removal of tiles. Also, handles the logic of lossing, winning and
 * re-starting a game. 
 */
public class Minesweeper {
    private static final int CANVAS_HEIGHT = 275;
    private static final int CANVAS_WIDTH = 250;
    private static final int NUM_OF_MINES = 10;

    CanvasWindow canvas;
    Tile[][] tileArray = new Tile[9][9];
    Random rand = new Random();
    boolean isAlive = true;
    Image resetButton = new Image("reset_button.png");

    public Minesweeper() {
        canvas = new CanvasWindow("Minesweeper", CANVAS_HEIGHT, CANVAS_WIDTH);
        resetButton.setMaxHeight(40);
        resetButton.setMaxWidth(40);
        setUpOneGame();
        canvas.onClick(event -> {
            if(isAlive) {
                getTileAtClick(event.getPosition(), event.getModifiers());
                checkForWin();
            } else {
                checkForReset(event.getPosition());
            }
        });
    }

    private void setUpOneGame() {
        canvas.removeAll();
        canvas.add(resetButton);
        resetButton.setPosition(117.5, 5);
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length ; j++) {
                tileArray[i][j] = new Tile(i * 20 + 50, j * 20 + 50, i, j);
                canvas.add(tileArray[i][j].getTileShape());
            }
        }
        placeMines();
        placeNumbers();
        canvas.draw();
    }

    private void getTileAtClick(Point position, Set <ModifierKey>modifier) { 
        int indexX = (int) (position.getX() - 50) / 20;
        int indexY = (int) (position.getY() - 50) / 20;
        if(indexX >= 0 && indexX < tileArray.length && indexY >= 0 && indexY < tileArray.length) { 
            if (modifier.contains(ModifierKey.SHIFT)) {
                tileArray[indexX][indexY].toggleFlag();
            } else {
                if(!tileArray[indexX][indexY].isCovered()) {
                    List<Tile> adjTiles = getAdjacent(indexX, indexY);
                    int adjacentFlagged = 0;
                    for (Tile tile : adjTiles) {
                        if(tile.isFlagged()) {
                            adjacentFlagged++;
                        }
                    }
                    if (tileArray[indexX][indexY].getNumAdjacent() == adjacentFlagged) {
                        for (Tile tile : adjTiles) {
                            uncoverAll(tile);
                        }
                    }
                }
                uncoverAll(tileArray[indexX][indexY]);
            }
        }
    }

    private void checkForWin() {
        int uncoveredTiles = 0;
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length; j++) {
                if(tileArray[i][j].isCovered() && !tileArray[i][j].isMine()) {
                    uncoveredTiles++;
                }
            }
        }
        if(uncoveredTiles == 0) {
            isAlive = false;
            resetButton.setImagePath("win_face.png");
        }
    }

    private void checkForReset(Point position) {
        if(canvas.getElementAt(position) == resetButton) {
            isAlive = true;
            resetButton.setImagePath("reset_button.png");
            setUpOneGame();
        }
    }

    private void mineTrigger(){
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length; j++) {
                if(tileArray[i][j].isMine()){
                    tileArray[i][j].removeCover();
                }
            }
        }
    }

    private void uncoverAll(Tile tile) {
        if(tile.isMine() && !tile.isFlagged()) {
            tile.removeCover();
            isAlive = false;
            mineTrigger();
            resetButton.setImagePath("loss_face.png");
        }
        if(tile.removeCover() && !tile.isMine()) {
            List<Tile> adjTiles = getAdjacent(tile.getXIndex(), tile.getYIndex());
            for (Tile adjTile : adjTiles) {
                uncoverAll(adjTile);
            }
        }
    }

    private void placeMines() {
        int minesPlaced = 0;
        while(minesPlaced < 10) {
            for(int i = 0; i < tileArray.length; i++) {
                for (int j = 0; j < tileArray.length; j++) {
                    if(minesPlaced < NUM_OF_MINES) { 
                        if(!tileArray[i][j].isMine()) {
                            if(rand.nextDouble() < 0.05) {
                                tileArray[i][j].makeMine();
                                minesPlaced++;
                            }
                        }
                    }
                }
            }
        }
    }

    private void placeNumbers() {
        for(int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length; j++) {
                if(!tileArray[i][j].isMine()) {
                    tileArray[i][j].setNumAdjMines(checkAdjacent(i, j));
                }
            }
        }
    }

    private int checkAdjacent(int i, int j) {
        int adjacentMines = 0;
        List<Tile> adjTiles = getAdjacent(i, j);
        for (Tile tile : adjTiles) {
            if(tile.isMine()){
                adjacentMines++;
            }
        }
        return adjacentMines;
    }
    
    private List<Tile> getAdjacent(int i, int j) {
        List<Tile> adjTiles = new ArrayList<>();
        for(int k = -1; k < 2; k++) {
            for(int l = -1; l < 2; l++) {
                if(i - k >= 0) {
                    if (j - l >= 0) {
                        if (i - k < 9) {
                            if (j - l < 9) {
                                adjTiles.add(tileArray[i - k][j - l]);
                            }
                        }
                    }
                }
            }
        }
        return adjTiles;
    }

    /**
    * This method counts the number of mines within a game grid and we use it for  
    * testing purposes too. 
    */
    public int countMines() {
        int numMines = 0;
        for(int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length; j++) {
                if(tileArray[i][j].isMine()) {
                    numMines++;
                }
            }
        }
        return numMines;
    }

    /**
    * This method starts the game of minesweeper. 
    */
    public static void main(String[] args) {
        new Minesweeper();
    }
}