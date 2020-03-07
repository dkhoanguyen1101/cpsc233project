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
	private static void playGame() {
		map[] worldMap = {genMap.generate(), genMap.generate(), genMap.generate()};
		ArrayList<Chara> players = new ArrayList<Chara>();
		ArrayList<Chara> enemies = new ArrayList<Chara>();
		
		Chara toAdd = new KinesiologyMajor("Player 1", 1, 30, 300, 1, 30, 300, 5, 3, 1);
		players.add(toAdd);
		toAdd = new EngMajor("Player 2", 2, 20, 250, 1, 20, 250, 5, 3, 3);
		players.add(toAdd);
		toAdd = new BiomedMajor("Player 3", 3, 10, 175, 2, 10, 175, 7, 3, 1);
		players.add(toAdd);
		boolean stillAlive = true;
		for(map Map: worldMap) {
			if(stillAlive) {
				System.out.println("Enter an integer to go to the next map");
				int wait = userIn.nextInt();
				enemies = new ArrayList<Chara>();
				for(int i = 4; i <= 6; i++) {
					toAdd = new Chara("Enemy", i, 15, 150, 0, 15, 150, 0, 3, 1);
					enemies.add(toAdd);
				}
				populateMap(players, enemies, Map);
				for(int j = 0; j < players.size(); j++) {
					players.get(j).setHealth(players.get(j).getMaxHealth());
				}
				stillAlive = playMap(players, enemies, Map);
			}
		}
		if (stillAlive) System.out.println("You won");
		else System.out.println("You lost");
	}
	
	private static void populateMap(ArrayList<Chara> players, ArrayList<Chara> enemies, map Map) {
		int[] test = Map.getCharPos();
		int[] test2 = Map.getEnemyPos();
		for(int i = 0; i < players.size(); i++) {
			int index = i * 2;
			Map.setPos(players.get(i).getID(), Map.getCharPos()[index], Map.getCharPos()[index + 1]);
		}
		for(int i = 0; i < enemies.size(); i++) {
			int index = i * 2;
			Map.setPos(enemies.get(i).getID(), Map.getEnemyPos()[index], Map.getEnemyPos()[index + 1]);
		}
		Map.setPos(7, Map.getItemPos()[0], Map.getItemPos()[1]);
	}
	
	private static void turnMana(ArrayList<Chara> players) {
		for(int i = 0; i < players.size(); i++) {
			Chara currentPlayer = players.get(i);
			currentPlayer.setMana(currentPlayer.getMana() + 1);
		}
	}
	
	private static boolean playMap(ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		int turnCounter = 1;
		AI enemyAI = new AI(currentMap);
		boolean playersAlive = players.size() > 0, enemiesAlive = enemies.size() > 0;
		while(playersAlive && enemiesAlive) {
			turnMana(players);
			System.out.println("Turn: " + turnCounter + "\n");
			System.out.println(currentMap.toString());
			printListOfChars(players, true);
			printListOfChars(enemies, false);
			if(playersAlive) {
				playerTurn(players, enemies, currentMap);
			}
			if(enemiesAlive) {
				for(int i = 0; i < enemies.size(); i++) {
					Chara currentEnemy = enemies.get(i);
					ArrayList<Integer> playerIDs = new ArrayList<Integer>();
					for(int j = 0; j < players.size(); j++) playerIDs.add(new Integer(players.get(i).getID())); 
					/*if (currentEnemy.getNature() == 'A')*/ enemyAI.moveAITowards(currentEnemy.getID(), currentEnemy.getMove(), playerIDs);
					//else if (currentEnemy.getNature() == 'P') enemyAI.moveAIAway(currentEnemy.getID(), playersPos);
					Chara closestPlayer = getCharaFromID(enemyAI.checkClosest(currentEnemy.getID(), playerIDs), players);
					if (isLegalAttack(closestPlayer, currentEnemy, currentMap)) currentEnemy.attack(closestPlayer);
					killChecker(players, currentMap);
				}
			}
			playersAlive = players.size() > 0;
			enemiesAlive = enemies.size() > 0;
			turnCounter++;
		}
		return playersAlive;
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
	public static void kill(Chara charToKill, ArrayList<Chara> listContainingChar, map currentMap) {
		int[] killCoords = currentMap.getPos(charToKill.getID());
		currentMap.setPos(0, killCoords[0], killCoords[1]);
		listContainingChar.remove(charToKill);
	}
	
	public static void killChecker(ArrayList<Chara> chars, map currentMap) {
		for(int i = 0; i < chars.size(); i++) {
			if(chars.get(i).getHealth() <= 0) kill(chars.get(i), chars, currentMap);
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
