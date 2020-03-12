
import java.util.Scanner;
import java.math.*;
import java.util.ArrayList;
public class Game {

	private static Scanner userIn = new Scanner(System.in);
	private static mapGenerator genMap = new mapGenerator();
	
	public static void main(String[] args) {
		playGame();
	}
	
	/**begin playing the actual game
	 * 
	 */
	
	public static void main(String[] args){launch(args);}




    @Override
    public void start(Stage stage) throws Exception {
//the start screen setting
        Pane root = new Pane();
        Scene scene = new Scene(root,800,600);
        stage.setScene(scene);
        stage.show();
//the game scene setting
//if want to add anything please add to this
        Pane gameroot = new Pane();
        Scene gamescene = new Scene(gameroot,800,600);



        //start scene
        BackgroundImage bg = new BackgroundImage(new Image("start.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));

        Image duck = new Image("duck.png");
        ImageView dk = new ImageView(duck);
        dk.setLayoutX(53);
        dk.setLayoutY(55);
        root.getChildren().add(dk);

        Button button1 = new Button("About");
        button1.setMinSize(120,60);
        button1.setLayoutX(340);
        button1.setLayoutY(230);
        button1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button1.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(button1);

        Label label = new Label();
        Popup popup = new Popup();
        label.setLayoutX(100);
        label.setLayoutY(200);
        popup.getContent().add(label);
        label.setGraphic(new ImageView(new Image("name.png")));
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {

                    public void handle(ActionEvent e)
                    {

                        if (!popup.isShowing()){
                            popup.show(stage);
                            button1.setText("BACK");}
                        else{
                            popup.hide();
                            button1.setText("About");}
                    }
                };

        // when button is pressed
        button1.setOnAction(event);


        Button PLAY = new Button("play");
        PLAY.setMinSize(120,60);
        PLAY.setLayoutX(340);
        PLAY.setLayoutY(330);
        PLAY.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        PLAY.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(PLAY);


        PLAY.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {

                stage.setScene(gamescene);
                stage.show();


            }
        });


        //game scene
        Button button = new Button("1");
        button.setMinSize(80,40);
        button.setLayoutX(30);
        button.setLayoutY(510);
        button.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button.setStyle("-fx-background-color: #A9A9A9");
        gameroot.getChildren().add(button);


        Button button2 = new Button("2");
        button2.setMinSize(80,40);
        button2.setLayoutX(140);
        button2.setLayoutY(510);
        button2.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button2.setStyle("-fx-background-color: #A9A9A9");
        button2.textFillProperty();
        gameroot.getChildren().add(button2);

        Button button3 = new Button("3");
        button3.setMinSize(80,40);
        button3.setLayoutX(250);
        button3.setLayoutY(510);
        button3.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button3.setStyle("-fx-background-color: #A9A9A9");
        button3.textFillProperty();
        gameroot.getChildren().add(button3);

        Button button4 = new Button("4");
        button4.setMinSize(80,40);
        button4.setLayoutX(360);
        button4.setLayoutY(510);
        button4.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button4.setStyle("-fx-background-color: #A9A9A9");
        button4.textFillProperty();
        gameroot.getChildren().add(button4);


        BackgroundImage GameBg = new BackgroundImage(new Image("map.jpg"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        gameroot.setBackground(new Background(GameBg));
        
        
        
        
        Button Replay = new Button("Replay");
        Replay.setMinSize(80,40);
        Replay.setLayoutX(360);
        Replay.setLayoutY(510);
        Replay.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        Replay.setStyle("-fx-background-color: #A9A9A9");
        Replay.textFillProperty();
        gameroot.getChildren().add(Replay);




        //lose scene
        BackgroundImage LoseBg = new BackgroundImage(new Image("end.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        loseroot.setBackground(new Background(LoseBg));
        loseroot.getChildren().add(Replay);



        //win scene
        BackgroundImage WinBg = new BackgroundImage(new Image("WIN.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        loseroot.setBackground(new Background(WinBg));
        loseroot.getChildren().add(Replay);









    }
	private static void playGame() {
		map[] worldMap = {genMap.generate(), genMap.generate(), genMap.generate()}; //create three random maps to act as the whole world
		ArrayList<Chara> players = new ArrayList<Chara>(); //create a list to hold all player characters between iteration of map
		ArrayList<Chara> enemies = new ArrayList<Chara>(); //create a list to hold all enemies between iterations
		//make some default characters
		Chara toAdd = new KinesiologyMajor("Player 1", 1, 30, 300, 1, 30, 300, 5, 3, 1); //new close combat kines character (magic numbers are a bandaid right now)
		players.add(toAdd);//put them into the player character list
		toAdd = new EngMajor("Player 2", 2, 20, 250, 1, 20, 250, 5, 3, 3);//new ranged eng character
		players.add(toAdd);
		toAdd = new BiomedMajor("Player 3", 3, 10, 175, 2, 10, 175, 7, 3, 1);//new healer biomed character
		players.add(toAdd);
		boolean stillAlive = true; //boolean to keep track of the player list - if its empty the boolean is false
		for(map Map: worldMap) {// going through all maps in the world
			if(stillAlive) {// if the players havent died yet
				System.out.println("Enter an integer to go to the next map");//a sort of between map pause screen, so it doesn't just pop into another map after one is beaten
				int wait = userIn.nextInt();
				enemies = new ArrayList<Chara>(); //initialize a new enemy list for a new map
				for(int i = 4; i <= 6; i++) {//put some generic enemies in the enemy list
					toAdd = new Chara("Enemy", i, 15, 150, 0, 15, 150, 0, 3, 1);
					enemies.add(toAdd);
				}
				populateMap(players, enemies, Map);//put them all into the map (privacy leaks help with this)
				for(int j = 0; j < players.size(); j++) {//restore all characters to full health
					players.get(j).setHealth(players.get(j).getMaxHealth());
				}
				stillAlive = playMap(players, enemies, Map);//play the map until one team dies
			}
		}
		if (stillAlive) System.out.println("You won");
		else System.out.println("You lost");
	}
	
	/**put characters, enemies, and items on a map
	 *
	 *	@param players - list of player characters that end up in special spaces on the map
	 *	@param enemies - list of enemy characters that get their own spaces
	 *	@param map - the map to populate
	 */
	private static void populateMap(ArrayList<Chara> players, ArrayList<Chara> enemies, map Map) {
		int[] test = Map.getCharPos();// get default instatiated character position list
		int[] test2 = Map.getEnemyPos();// get default enemy posiyions
		for(int i = 0; i < players.size(); i++) {//for all player characters
			int index = i * 2;//get an index to use for the 1-D list of deafult positions (goes x then y)
			Map.setPos(players.get(i).getID(), Map.getCharPos()[index], Map.getCharPos()[index + 1]);//set the id of that character on the map
		}
		for(int i = 0; i < enemies.size(); i++) {//for all enemies
			int index = i * 2;//same thing as above
			Map.setPos(enemies.get(i).getID(), Map.getEnemyPos()[index], Map.getEnemyPos()[index + 1]);
		}
		Map.setPos(7, Map.getItemPos()[0], Map.getItemPos()[1]);//set the id of an item on the map (only one for now - will generate a random number when new items are eventually added
	}
	
	/** small function to restore every player's mana each turn
	 *
	 *	@param players - the player list to restore mana to
	 */
	private static void turnMana(ArrayList<Chara> players) {
		for(int i = 0; i < players.size(); i++) {
			Chara currentPlayer = players.get(i);
			currentPlayer.setMana(currentPlayer.getMana() + 1);
		}
	}
	
	/** play on a given map - facilitates turns and win/lose
	 *
	 *	@param players - list of player characters
	 *	@param enemies - list of all enemy characters
	 *	@param currentMap - the map to play on
	 *	@return boolean that reflects the status of the players (all dead means it returns false)
	 */
	private static boolean playMap(ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		int turnCounter = 1;//numer to reflect turn
		AI enemyAI = new AI(currentMap);//create a new AI based on the current map iteration
		boolean playersAlive = players.size() > 0, enemiesAlive = enemies.size() > 0;//booleans to facilitate turn execution (if some team dies then you can't execute their turn)
		while(playersAlive && enemiesAlive) {//the turn based part - while both teams are alive - each iteration is a turn
			turnMana(players);//start of a new turn - add some mana
			System.out.println("Turn: " + turnCounter + "\n");//display the current turn number
			System.out.println(currentMap.toString());//display the map
			printListOfChars(players, true);//display the state of the characters
			printListOfChars(enemies, false);//display the state of enemies
			if(playersAlive && enemiesAlive) {//if players are alive and have nemies to attack
				playerTurn(players, enemies, currentMap);//prompt the players turn
			}
			enemiesAlive = enemies.size() > 0;//check to make sure the opponentss are still alive so they can execute their turn
			if(enemiesAlive && playersAlive) {//if the enemies are still alive and have players to attack
				for(int i = 0; i < enemies.size(); i++) {//for each enemy in the list of enemies
					Chara currentEnemy = enemies.get(i);//get the current enemy in the list
					ArrayList<Integer> playerIDs = new ArrayList<Integer>();//create a list to get the IDs of players to pass into AI
					for(int j = 0; j < players.size(); j++) {//fill the player ID list
						playerIDs.add(new Integer(players.get(j).getID())); 
					}
					enemyAI.moveAITowards(currentEnemy.getID(), currentEnemy.getMove(), playerIDs); //move all enemies toward the players (all enemies are aggressive for now)
					Chara closestPlayer = getCharaFromID(enemyAI.checkClosest(currentEnemy.getID(), playerIDs), players); //get the character closest to the enemy
					if (isLegalAttack(closestPlayer, currentEnemy, currentMap)) currentEnemy.attack(closestPlayer); //if possible to attack, attack
					killChecker(players, currentMap);//check to see if the enemy killed a player
				}
			}
			playersAlive = players.size() > 0;//check to see if players remain
			turnCounter++;//turn over - increase the counter by 1 for the next turn
		}
		return playersAlive;//once a team dies loop is broken - return the state of players
	}

	private static void playerTurn(ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		ArrayList<Chara> playersDidntMove = new ArrayList<Chara>(players);
		ArrayList<Chara> playersDidntAct = new ArrayList<Chara>(players);
		boolean notAllMoved = playersDidntMove.size() > 0, notAllActed = playersDidntAct.size() > 0;
			while(notAllMoved || notAllActed) {
				if(notAllMoved) System.out.println("Enter 1 to move a character");
				if(notAllActed) System.out.println("Enter 2 to have a character do something");
				System.out.println("Enter 3 to end your turn now");
				int choice = userIn.nextInt();
				if(notAllMoved && choice == 1) {
					playerMove(players, playersDidntMove, currentMap);
				}
				if(notAllActed && choice == 2) {
					playerAct(players, playersDidntAct, enemies, currentMap);
				}
				killChecker(enemies, currentMap);
				notAllMoved = playersDidntMove.size() > 0;
				notAllActed = playersDidntAct.size() > 0;
				System.out.println(currentMap.toString());
				if(choice == 3) {
					notAllMoved = false;
					notAllActed = false;
				}
			}
	}
	
	private static void playerMove(ArrayList<Chara> players, ArrayList<Chara> didntMove, map currentMap) {
		System.out.println("These characters can move: ");
		for(int i = 0; i < didntMove.size(); i++) {
			System.out.print("ID: " + didntMove.get(i).getID() + " ");
		} System.out.println();
		System.out.println("Enter the ID of a character to move");
		int moveChoice = userIn.nextInt();
		boolean moved = false;
		if(checkIfIDInList(moveChoice, didntMove)) {
			moved = doMove(getCharaFromID(moveChoice, players), currentMap);
			if (moved) didntMove.remove(getCharaFromID(moveChoice, didntMove));
		}
	}
	
	private static void playerAct(ArrayList<Chara> players, ArrayList<Chara> didntAct, ArrayList<Chara> enemies, map currentMap) {
		System.out.println("These characters can act");
		for(int i = 0; i < didntAct.size(); i++) {
			System.out.print("ID: " + didntAct.get(i).getID() + " ");
		} System.out.println();
		System.out.println("Enter the ID of a character to act");
		int actCharChoice = userIn.nextInt();
		if(checkIfIDInList(actCharChoice, didntAct)) {
			boolean acted = false;
			System.out.println("Enter 1 to attack an enemy");
			System.out.println("Enter 2 to use an item");
			System.out.println("Enter 3 to use a character's special ability");
			System.out.println("Enter 4 to interact with an object");
			int actChoice = userIn.nextInt();
			//if(actChoice == 1) {
			switch(actChoice) {
			case 1:
				acted = doAttack(getCharaFromID(actCharChoice, players), enemies, currentMap); break;
			case 2:
				acted = useItem(getCharaFromID(actCharChoice, players)); break;
			case 3:
				acted = useSpecial(getCharaFromID(actCharChoice, players), players, enemies, currentMap); break;
			case 4:
				acted = interact(getCharaFromID(actCharChoice, players), currentMap);
			}
			if (acted) didntAct.remove(getCharaFromID(actCharChoice, didntAct));
		}
	}
	
	private static boolean doMove(Chara charToMove, map currentMap) {
		boolean didMove = false;
		System.out.println("Enter the x coordinate you want to move to");
		int newX = userIn.nextInt();
		System.out.println("enter the y coordinate you want to move to");
		int newY = userIn.nextInt();
		didMove = currentMap.move(charToMove.getID(), newY, newX, charToMove.getMove());
		return didMove;
	}
	
	/**prompting the attack action - taking user input for a target and attacking if possible
	 * 
	 * @param attacker	player character attacking
	 * @param enemiesList	list of enemies that the player character can damage
	 * @param currentMap	map the player and enemies sit on
	 * @return	boolean for successful attack
	 */
	private static boolean doAttack(Chara attacker, ArrayList<Chara> enemiesList, map currentMap) {
		System.out.println("You can attack in a range of " + attacker.getRange());
		System.out.println("Enter the ID of an enemy to attack");
		int choice = userIn.nextInt();
		Chara receiver = getCharaFromID(choice, enemiesList);
		boolean didAttack = false;
		if (checkIfIDInList(choice, enemiesList)) {
			if (isLegalAttack(attacker, receiver, currentMap)) {
				attacker.attack(receiver);
				System.out.println(attacker.getName() + " attacked " + receiver.getName() + ". " + receiver.getName() + " now has "
						+ receiver.getHealth() + " health.");
				didAttack = true;
			}
		} else {
			System.out.println("That enemy does not exist");
		}
		return didAttack;
	}
	/** Checks to make sure a target is withinn the attack range of a character
	 * 
	 * @param attacker	person trying to attack
	 * @param receiver	person attacker is trying to damage
	 * @param currentMap	the map the two caracters sit on
	 * @return	boolean if attacker is in range of receiver
	 */
	private static boolean isLegalAttack(Chara attacker, Chara receiver, map currentMap) {
		int atkRange = attacker.getRange();
		int[] atkPos = currentMap.getPos(attacker.getID());
		int[] recPos = currentMap.getPos(receiver.getID());
		int atkDist = (Math.abs(atkPos[0] - recPos[0]) + Math.abs(atkPos[1] - recPos[1]));
		if (atkDist > atkRange)  {
			System.out.println("That enemy is not in range!");
			return false;
		}
		else return true;
	}
	
	/** Kill off a character - Thanos snap them from the map and the list they belong to
	 * 
	 * @param charToKill	character to kill
	 * @param listContainingChar	the list the belong to that holds members (players, enemies)
	 * @param currentMap	the map the characters are on
	 */
	public static void kill(int IDToKill, int posToKill, ArrayList<Chara> listContainingChar, map currentMap) {
		System.out.println(listContainingChar.get(posToKill).getName() + " died!");
		int[] killCoords = currentMap.getPos(IDToKill);
		currentMap.setPos(0, killCoords[0], killCoords[1]);
		listContainingChar.remove(posToKill);
	}
	
	public static void killChecker(ArrayList<Chara> chars, map currentMap) {
		for(int i = 0; i < chars.size(); i++) {
			Chara dead = chars.get(i);
			if(dead.getHealth() <= 0) {
				kill(dead.getID(), i, chars, currentMap);
			}
		}
	}
	
	private static boolean useSpecial(Chara player, ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		boolean used = false;
		used = player.Special(currentMap, players, enemies);
		return used;
	}
	
	private static boolean useItem(Chara user) {
		System.out.println("Enter a number to use item");
		for (int i = 0; i < 3; i++) {
			System.out.println("" + (i + 1) + ". " + user.getItem(i).getName());
		}
		System.out.println("4. Do not use an item");
		int choice = userIn.nextInt();
		boolean itemWasUsed = false;
		switch(choice) {
		case 1:
			itemWasUsed = user.getItem(0).use(user); break;
		case 2:
			itemWasUsed = user.getItem(1).use(user); break;
		case 3:
			itemWasUsed = user.getItem(2).use(user); break;
		default:
			itemWasUsed = false;
		}
		if (itemWasUsed) {
			Item replace = new EmptyItem();
			user.setItem(replace, choice - 1);
		}
		
		return itemWasUsed;
	}
	
	private static boolean pickUpItem(Chara user, Item toAdd) {
		boolean pickedUp = false;
		for(int i = 0; i < 3; i++) {
			if(!pickedUp) {
				if(user.getItem(i).getName().equals("empty")) {
					user.setItem(toAdd, i);
					pickedUp = true;
				}
			}
		}
		return pickedUp;
	}
	
	private static boolean interact(Chara user, map currentMap) {
		boolean didSomething = false;
		System.out.println("Enter the ID of an item on the map you would like to pick up");
		int choice = userIn.nextInt();
		int[] itemCoords = currentMap.getPos(choice);
		int[] userCoords = currentMap.getPos(user.getID());
		int range = (Math.abs(userCoords[0] - itemCoords[0]) + Math.abs(userCoords[1] - itemCoords[1]));
		if(range == 1) {
			switch(choice) {
			case 7:
				Item toAdd = new HealthPotion(7);
				pickUpItem(user, toAdd);
				didSomething = true;
				currentMap.setPos(0, itemCoords[0], itemCoords[1]);
			}
		} else System.out.println("that item is not in range");
		return didSomething;
	}
	
	public static Chara getCharaFromID(int ID, ArrayList<Chara> listToCheck) {
		Chara toReturn = null;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (listToCheck.get(i).getID() == ID) toReturn = listToCheck.get(i);
		}
		return toReturn;
	}
	
	public static boolean checkIfIDInList(int ID, ArrayList<Chara> listToCheck) {
		boolean isThere = false;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (!isThere) {
				if (listToCheck.get(i).getID() == ID) isThere = true;
			}
		}
		return isThere;
	}
	
	public static void printListOfChars(ArrayList<Chara> chars, boolean arePlayers) {
		for(int i = 0; i < chars.size(); i++) {
			Chara theChar = chars.get(i);
			System.out.print(
					theChar.getName()
					+ " (ID: " + theChar.getID()
					+ ") Health: " + theChar.getHealth()  + "/" + theChar.getMaxHealth()
					+ " Atk: " + theChar.getAttack() + "/" + theChar.getMaxAttack());
			if(arePlayers) System.out.println(" Mana: " + theChar.getMana() + "/" + theChar.getMaxMana());
			else System.out.println("");
			
			System.out.println("");
		}
	}
	
}
