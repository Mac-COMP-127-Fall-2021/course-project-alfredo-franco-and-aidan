package Minesweeper;

import edu.macalester.graphics.CanvasWindow;

public class Minesweeper {
    public static final int CANVAS_HEIGHT = 600;
    public static final int CANVAS_WIDTH = 600;

    CanvasWindow canvas;
    Tile[][] tileArray = new Tile[9][9];

    public Minesweeper() {
        canvas = new CanvasWindow("Minesweeper", CANVAS_HEIGHT, CANVAS_WIDTH);
        for (int i = 0; i < tileArray.length; i++) {
            for (int j = 0; j < tileArray.length ; j++) {
                tileArray[i][j] = new Tile(i * 20, j * 20);
                canvas.add(tileArray[i][j].getTileShape());
            }
        }
        canvas.draw();
    }

    public static void main(String[] args) {
        new Minesweeper();
    }
}
