# 2player-snake

Source code is in \NHF\src\NHF

Unit test can be found in \NHF\test\junittest

## Program Description
This program is a version of the classic Snake game but with two players and obstacles on the playing field. The obstacles change their positions randomly every time a fruit is collected.

### Gameplay
Before starting the game, the user can select the difficulty level from the menu:

* **Easy:** Up to **3 obstacles** appear during the game.
* **Medium:** Up to **5 obstacles** appear during the game.
* **Hard:** Up to **10 obstacles** appear during the game.
* **Reload:** Loads the  **recently saved game**.
During gameplay, the program keeps track of each player's score, incremented by 1 for each fruit collected.

In our case, the game is played with two players, and a started game looks like this:
![description_2snake_game](https://github.com/obendi03/2player-snake/assets/144063799/fdd96b5f-685f-4889-9527-8f5ce81560c4)


### Controls
* **Player 1:** Uses arrow keys (**Up, Down, Left, Right**).
* **Player 2:** Uses keys (**A, W, S, D**).

### Game End Conditions
**Player collision**: If one player collides with the other, the game ends. The player with the higher score wins.

**Obstacle collision**: If a player collides with an obstacle, that player loses, even if they have a higher score.

**Fruit collection**: When a player collects a fruit, their score increases by 1, and their snake's length increases. The number of obstacles on the field increases by 1, and their positions are randomized.

**Wall collision**: If a player hits the wall, that player loses, even if they have a higher score.

### Save and Reload
Clicking the **Save** button saves the current game state. Clicking the Reload button loads the last saved game.

After clicking Save, a small window appears with the message:
"Press OK to return to the menu."
Clicking OK closes the game window and returns to the menu.
