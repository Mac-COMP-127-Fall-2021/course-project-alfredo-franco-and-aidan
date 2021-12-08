# Minesweeper

Info about your project goes here
Aidan Lincicum, Franco Salinas, Alfredo Olaguez 

Brief project description:

The classic game Minesweeper is reenacted and played throught the project. As a brief overview, the game is played on a canvas screen whereby the player can interact with a 9x9 board filled with an object named "tile." The characteristics of the tiles is held in the class file of the same name. Said class holding the object's approx. size and determining through booleans if a given tile is flagged, a mine and/or covered. The Minesweeper class itself holds the on-click interactions the user can participate in. Holding "ENTER"  on click will create a flag to remove the ability to trigger an uncovering, as an example. In addition to holding the check for adjacent tiles which most of the code is built upon. 
The game ends when the user removes all but the 10 bomb tiles or, if they trigger a bomb. The option to restart is a button on the canvas window.

For the most part, the proposal was simplified in so far that, the five files mentioned 1) BoardMaker, 2) FlagHolder, 3) BombMaker 4) GameSetup and 5) GameTester were reduced to the three in the current model. No functionality was traded for this simplification and the additional interaction with the number tiles was even added into a new form.

How to Play:

Running the MineSweeper.java file will begin the game. The canvas window will be setup and call Tile.java for the parameters of the object class that will be created in the 9x9 grid. 

