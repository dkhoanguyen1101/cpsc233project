# cpsc233project
To run, download project off of github onto editing software of choice(e.g. eclipse).
From there, just run from the console.

Our game is a turn-based rpg. This demo is controlled completely by the number-keys, and the goal of the game is to lower the opponents health to zero, while keeping yourself healthy.

The game takes place on a ten by ten space game board, with the upper right corner being position 0,0.

For each turn:
- you will be given the option to move, or stay out. You will be asked to select an x and y position to move to, and your character can only move up to 4 spaces per turn.
- You will then be given the option to either use an item, attack or end your turn.
- Attacking: if you are within 1 space of your opponent, you can attack your opponent, and their health will go down. If you are out of this range, you cannot attack.
- Using Items: you will be shown your characters inventory, and allowed to select an item, upon which the selected item will be used up and unavailable for the rest of the game. Your character starts with one health potion in this demo, which will raise their health by 50.
- After you have made your move, the opponent will choose from the same selection of actions, with the goal of attacking and lowering your characters health to zero.
- The turn order will then restart, and this will continue until either players health reaches zero.
- If the opponents health is dropped to zero, you win, and if your characters health drops to zero, you lose.
