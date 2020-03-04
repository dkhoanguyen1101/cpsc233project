/**controller - facilitates gameplay
 *
 * @author Derrick
 *
 */
import java.util.Scanner;
import java.math.*;
import java.util.ArrayList;
public class Game {

	private static Scanner userIn = new Scanner(System.in);
	private static mapGenerator genMap = new mapGenerator();
	
	public static void main(String[] args) {
		playGame();
	}
	
	private static void playGame() {
		map[] worldMap = {genMap.genRandomMap(), genMap.genRandomMap(), genMap.genRandomMap()};
		ArrayList<Chara> players = new ArrayList<Chara>();
		ArrayList<Chara> enemies; 
		boolean stillAlive = true;
		for(map Map: worldMap) {
			if(stillAlive) {
				for(int i = 0; i < players.length; i++) players.get(i).setHealth(players.get(i).getBaseHealth());
				stillAlive = playMap(players, enemies, Map);
			}
		}
		if (stillAlive) System.out.println("You won");
		else System.out.println("You lost");
	}
	
	private static boolean playMap(ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		int turnCounter = 0;
		AI enemyAI = new AI(currentMap);
		boolean playersAlive = players.size() > 0, enemiesAlive = enemies.size() > 0;
		while(playersAlive && enemiesAlive) {
			if(playersAlive) {
				playerTurn(players, enemies, currentMap);
			}
			if(enemiesAlive) {
				for(int i = 0; i < enemies.length(); i++) {
					Enemy currentEnemy = enemies.size(i);
					ArrayList<int[]> playersPos = new ArrayList<int[]>();
					for(int j = 0; j < players.length; j++) playersPos.add(currentMap.getPos(players.get(i).getUniqueID)); 
					if (currentEnemy.getNature() == 'A') enemyAI.moveAITowards(currentEnemy.getUniqueID(), playersPos);
					else if (currentEnemy.getNature() == 'P') enemyAI.moveAIAway(currentEnemy.getUniqueID(), playersPos);
					Chara closestPlayer = getCharaFromID(enemyAI.checkClosest(currentEnemy.getUniqueID(), playersPos), players);
					if (isLegalAttack(closestPlayer, currentEnemy)) currentEnemy.attack(closestPlayer);
				}
			}
			playersAlive = players.size() > 0;
			enemiesAlive = enemies.size() > 0;
		}
		return playersAlive;
	}

	private static void playerTurn(ArrayList<Chara> players, ArrayList<Chara> enemies, map currentMap) {
		ArrayList<Chara> playersDidntMove = new ArrayList<Chara>(players);
		ArrayList<Chara> playersDidntAct = new ArrayList<Chara>(players);
		boolean notAllMoved = playersDidntMove.size() > 0, notAllActed = playersDidntAct.length() > 0;
			while(notAllMoved || notAllActed) {
				if(notAllMoved) System.out.println("Enter 1 to move a character");
				if(notAllActed) System.out.println("Enter 2 to have a character do something");
				int choice = userIn.nextInt();
				if(notAllMoved && choice == 1) {
					System.out.println("These characters can move: ");
					for(int i = 0; i < playersDidntMove.size(); i++) {
						System.out.print("ID: " + playersDidntMove.get(i).getUniqueID());
					} System.out.println();
					System.out.println("Enter the ID of a character to move");
					int moveChoice = userIn.nextInt();
					if(checkIfIDInList(moveChoice, playersDidntMove)) {
						boolean moved = false;
						moved = doMove(getCharaFromList(moveChoice, players));
						if (moved) playersDidntMove.remove(getCharaFromList(moveChoice, playersDidntMove));
					}
				}
				if(notAllActed && choice == 2) {
					System.out.println("These characters can act");
					for(int i = 0; i < playersDidntAct.size(); i++) {
						System.out.print(" ID: " + playersDidntAct.get(i).getUniqueID());
					} System.out.println();
					System.out.println("Enter the ID of a character to act");
					int actCharChoice = userIn.nextInt();
					if(checkIfIDInList(actCharChoice, playersDidntAct)) {
						boolean acted = false;
						System.out.println("Enter 1 to attack an enemy");
						System.out.println("Enter 2 to use an item");
						int actChoice = userIn.nextInt();
						if(actChoice == 1) {
							acted = doAttack(getCharaFromID(actCharChoice, players), currentMap);
						} else if(actChoice == 2) {
							acted = useItem(getCharaFromID(actCharChoice, players));
						}
						if (acted) playersDidntAct.remove(getCharaFromList(actCharChoice, playersDidntAct));
					}
				}
				notAllMoved = playersDidntMove.size() > 0;
				notAllActed = playersDidntAct.size() > 0;
			}
	}
	
	private static boolean doMove(Chara charToMove, map currentMap) {
		boolean didMove = false;
		System.out.println("Enter the x coordinate you want to move to");
		int newX = userIn.nextInt();
		System.out.println("enter the y coordinate you want to move to");
		int newY = userIn.nextInt();
		didMove = currentMap.move(charToMove.getUniqueID, newY, newX, charToMove.getMove());
		return didMove;
	}
	
	private static boolean doAttack(Chara attacker, ArrayList<Chara> enemiesList, map currentMap) {
		System.out.println("You can attack in a range of " + attacker.getAtkRange());
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
	
	private static boolean isLegalAttack(Chara attacker, Chara receiver, map currentMap) {
		int atkRange = attacker.getAtkRange();
		int[] atkPos = currentMap.getPos(attacker.getUniqueID());
		int[] recPos = currentMap.getPos(receiver.getUniqueID());
		int atkDist = (Math.abs(atkPos[0] - recPos[0]) + Math.abs(atkPos[1] - recPos[1]));
		if (atkDist > atkRange) return false;
		else return true;
	}
	
	public static void kill(Chara charToKill, ArrayList<Chara> listContainingChar, map currentMap) {
		int[] killCoords = getPos(charToKill.getUniqueID());
		currentMap.setPos(0, killCoords[0], killCoords[1]);
		listContainingChar.remove(charToKill);
	}
	
	private static boolean useItem(Chara user) {
		System.out.println("Enter a number to use item");
		for (int i = 0, i < 3, i++) {
			System.out.println("" + (i + 1) + ". " + user.getItem(i).getName());
		}
		System.out.println("4. Do not use an item");
		int choice = userIn.nextInt();
		boolean itemWasUsed = false;
		switch(choice) {
		case 1:
			itemWasUsed: user.getItem(0).use(); break;
		case 2:
			itemWasUsed: user.getItem(1).use(); break;
		case 3:
			itemWasUsed: user.getItem(2).use(); break;
		default:
			itemWasUsed = false;
		}
		if (itemWasUsed) user.setItem(choice - 1, new emptyItem());
		
		return itemWasUsed;
	}
	
	public static Chara getCharaFromID(int ID, ArrayList<Chara> listToCheck) {
		Chara toReturn = null;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (listToCheck.get(i).getUniqueID() == ID) toReturn = listToCheck.get(i);
		}
		return toReturn;
	}
	
	public static boolean checkIfIDInList(int ID, ArrayList<Chara> listToCheck) {
		boolean isThere = false;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (!isThere) {
				if (listToCheck.get(i).getUniqueID() == ID) isThere = true;
			}
		}
		return isThere;
	}
