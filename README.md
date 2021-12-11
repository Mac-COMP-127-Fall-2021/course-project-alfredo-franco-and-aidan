# Minesweeper


Aidan Lincicum, Franco Salinas, Alfredo Olaguez. 

# Brief Game Overview:
The classic game Minesweeper is reenacted and played throught the project. As a brief overview, the game is played on a board made up of several covered tiles and the object of the game is to remove all those tiles from the board without triggering any "mines" that would kill the player, with hints of nearby mines placed on uncovered tiles. The most common way to play the game also adds a flagging system to disable accidentally triggering a tile on a misclick.

# Brief Project Description:

Our game is played on a canvas screen whereby the player can interact with a 9x9 board filled with an object named "tile." The characteristics of the tiles is held in the class file of the same name. Said class holding the object's approx. size and determining through booleans if a given tile is flagged, a mine and/or covered. The Minesweeper class itself holds the on-click interactions the user can participate in. Holding "SHIFT" on click will create a flag to remove the ability to trigger an uncovering, as an example. In addition to holding the check for adjacent tiles which most of the code is built upon. The game ends when the user removes all but the 10 bomb tiles or, if they trigger a bomb. The option to restart is a button on the canvas window.

For the most part, the proposal was simplified in so far that, the five files mentioned 1) BoardMaker, 2) FlagHolder, 3) BombMaker 4) GameSetup and 5) GameTester were reduced to the three in the current model. GameSetup was eventually changed into the MineSweeper main file, still handling the major in-game interactions, such as checking adjacent tiles while also adopting the BoardMaker's main purpose of setting up the grid and board. The Tile file integrated aspects of the orignal FlagHolder and BombMaker files as it was deemed they could instead be treated as another type of tile method to be called by the MineSweeper file. No functionality was traded for this simplification and the additional interaction with the number tiles was even added into a new form.

Running the MineSweeper.java file will begin the game. Tile.java is not executable itself, serving more as a necessary supplementary file to the main MineSweeper file. 

Our greatest concern was setting up the code for checking the adjacent tiles. Specifically, it was challenging to figure out a concise way to check for adjacent tiles in corners and against the edges as we'd originally theorized it would result in adding several null-type statments to avoid an error screen. It was also an issue in so far that it had to be compatible with use in other method loops, specifically, what would become the recursion aspect of the uncoverAll class for blank tiles. The alternative to the code we have now would have required we write several more (nested) loops and using many additional variable letters, which would reduce legibility and increase the chance to find theoretical bugs. 






