package Minesweeper;

import java.util.Random;

import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.Point;

public class Minesweeper {
    public static final int CANVAS_HEIGHT = 600;
    public static final int CANVAS_WIDTH = 600;
    public static final int NUM_OF_MINES = 10;

    CanvasWindow canvas;
    Tile[][] tileArray = new Tile[9][9];
    Random rand = new Random();

    public Minesweeper() {
        canvas = new CanvasWindow("Minesweeper", CANVAS_HEIGHT, CANVAS_WIDTH);
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length ; j++) {
                tileArray[i][j] = new Tile(i * 20, j * 20);
                canvas.add(tileArray[i][j].getTileShape());
            }
        }
        placeMines();
        placeNumbers();
        canvas.draw();

        canvas.onClick(event -> getTileAtClick(event.getPosition()));
    }

    private void getTileAtClick(Point position) {
        int indexX = (int) position.getX() / 20;
        int indexY = (int) position.getY() / 20;
        tileArray[indexX][indexY].removeCover();
    }

    private void placeMines() {
        int minesPlaced = 0;
        while(minesPlaced < 10) {
            for(int i = 0; i < tileArray.length; i++) {
                for (int j = 0; j < tileArray.length; j++) {
                    if(minesPlaced < NUM_OF_MINES) {
                        if(!tileArray[i][j].isMine()) {
                            if(rand.nextDouble() < 0.05) {
                                tileArray[i][j].makeBomb();
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
                    tileArray[i][j].setMines(checkAdjacent(i, j));
                }
            }
        }
    }

    private int checkAdjacent(int i, int j) {
        int adjacentMines = 0;
        for(int k = -1; k < 2; k++) {
            for(int l = -1; l < 2; l++) {
                if(i - k >= 0) {
                    if (j - l >= 0) {
                        if (i - k < 9) {
                            if (j - l < 9) {
                                if(tileArray[i - k][j - l].isMine()) {
                                    adjacentMines++;
                                }
                            }
                        }
                    }
                }
            }
        }
        return adjacentMines;
    }

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

    public static void main(String[] args) {
        new Minesweeper();
    }
}