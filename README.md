# 2player-snake

Source code is in \NHF\src\NHF

Unit test can be found in \NHF\test\junittest

Snake Game for 2 Players
Introduction and User Interface Description
This document describes a classic game of Snake designed for 2 players. The game features obstacles that change positions randomly each time a fruit is collected.

Gameplay
1. Difficulty Levels

The game allows the user to choose from the following difficulty levels:

Easy: Up to 3 obstacles appear when fruits are collected.
Medium: Up to 5 obstacles appear when fruits are collected.
Hard: Up to 10 obstacles appear when fruits are collected.
Reload: Option to load the last saved game.
2. Scoring

Players' scores increase by 1 with each fruit collected.

3. Controls

Player 1: Up, Down, Right, Left keys.
Player 2: A, W, S, D keys.
4. Game End Scenarios

The game can end under the following conditions:

Player Collision: Game ends if a player collides with the other player. The player with the higher score wins.
Obstacle Collision: Game ends if a player collides with an obstacle, regardless of score.
Fruit Collection: Player scores increase by 1 and snake length increases. Obstacles increase by 1, with positions randomly changing.
Wall Collision: Game ends if a player collides with a wall, regardless of score.
5. Save and Reload

Save: Allows saving the current game state. A confirmation message "Press OK to return to the Menu" appears.
Reload: Option to retrieve the last saved game state.
