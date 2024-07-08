# 2player-snake

Source code is in \NHF\src\NHF

Unit test can be found in \NHF\test\junittest


NHF User Documentation: Snake 2 Game
Introduction to the Program, User Interface Description
The document describes a two-player version of the classic game Snake, with the addition of obstacles on the field that randomly change their position each time a fruit is collected.
Gameplay
The game offers different difficulty levels that can be chosen from the menu before starting the game:
Easy: A maximum of 3 obstacles appear during the game when fruits are collected.
Medium: A maximum of 5 obstacles appear during the game when fruits are collected.
Hard: A maximum of 10 obstacles appear during the game when fruits are collected.
Reload: Loads the last saved game.
The players' scores are kept during the game, increasing by 1 each time a fruit is collected. The controls for the two players are as follows:
Player 1 is controlled with the Up, Down, Right, Left buttons.
Player 2 is controlled with the A, W, S, D buttons.
The game can end under the following conditions:
A player collides with another player. The game ends and the player with more points wins.
A player collides with an obstacle. The player who collided with the obstacle loses, regardless of their points.
A player collects a fruit. The player's score and the length of their snake increase by 1. The number of obstacles on the field also increases by 1, and their positions randomly change.
A player hits a wall. The player who collided with the wall loses, regardless of their points.
The game can be saved at any point by clicking on the Save button, and later retrieved by clicking on the Reload button. After saving, a small window appears with the message: "Press the OK button to return to the Menu". Clicking on the OK button closes the game window, allowing the player to choose from the menu options again.
