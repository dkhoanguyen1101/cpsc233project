package GUI;

import java.math.*;
import java.util.ArrayList;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Game {

	//references to all GUI elements
	protected Button btn1, btn2, btn3, btn4;
	protected Label lbl1, lbl2;
	protected Image img;
	protected ImageView imv;
	protected ArrayList<ImageView> charImageFrames;
	protected Scene scene;
	
	//lists to be used to hold references to the characters on the map
	protected ArrayList<Chara> players;
	protected ArrayList<Chara> enemies;
	
	//lists to keep track of player characters the act as part of the player's turn
	protected ArrayList<Chara> notMoved;
	protected ArrayList<Chara> notActed;
	
	//elements that track the individual instances of map
	protected int turnNo = 1, mapNo = 0;
	protected map currentMap;
	protected mapGenerator newMaps = new mapGenerator();
	
	//Reference to current AI
	protected AI enemyAI;
	
	//other technical information
	protected final int cellSizeX = 32, cellSizeY = 32, mapSizeX = 16, mapSizeY = 16;
	
	public Game(Button b1, Button b2, Button b3, Button b4,
			Label l1, Label l2, Image image, ImageView imageV, Scene s) {
		btn1 = b1; btn2 = b2; btn3 = b3; btn4 = b4;
		lbl1 = l1; lbl2 = l2; img = image; imv = imageV; scene = s;
	}
	
	/* Methods for setting button functions and visuals in response to game
	 * 
	 */
	
	public void setButtonTextPlayerActionChoice() {
		lbl2.setText("Select a character to act: ");
		btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText("");
	}
	
	public void setButtonTextActions(int ID) {
		Chara toAct = setCharStatsLabel(ID);
		if(toAct != null && players.contains(toAct)) {
			if(!notMoved.contains(toAct)) {
				btn1.setText("Move");
			}
			if(!notActed.contains(toAct)) {
				btn2.setText("Attack");
				btn3.setText("Use item");
				btn4.setText("Use special");
			}
		} else {
			setButtonTextPlayerActionChoice();
			lbl1.setText("");
		}
	}
	
	public void setButtonTextMove() {
		lbl2.setText("Select a space to move to");
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	public void setButtonTextAttack() {
		lbl2.setText("Select an enemy to attack");
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	public void setButtonTextItems(int ID) {
		Chara toAct = getCharaFromID(ID, players);
		Item[] inventory = toAct.getInventory();
		lbl2.setText("Select an item to use");
		btn1.setText(inventory[0].getName());
		btn2.setText(inventory[1].getName());
		btn3.setText(inventory[2].getName());
		btn4.setText("Back");
	}
	
	public void setButtonTextSpecial(int ID) {
		Chara toAct = getCharaFromID(ID, players);
		lbl2.setText(toAct.getSpecialDescription());
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	/* Methods for game facilitation, through and between maps
	 * 
	 */
	
	public void startNewMap() {
		enemies.clear();
		for(int i = 0; i < 3; i++) {
			Chara toAdd = new Enemy(i + 4);
			enemies.add(toAdd);
		}
		currentMap = newMaps.generate();
		enemyAI = new AI(currentMap);
		populateMap();
		turnNo = 1;
		mapNo++;
		startNewTurn();
	}
	
	public void startNewTurn() {
		for(int i = 0; i < enemies.size(); i++) {
			Chara currentEnemy = enemies.get(i);
			
		}
		turnNo++;
	}
	
	public void populateMap() {
		for(int i = 0; i < players.size(); i++) {
			int index = i * 2;
			currentMap.setPos(players.get(i).getID(), currentMap.getCharPos()[index], currentMap.getCharPos()[index + 1]);
		}
		for(int i = 0; i < enemies.size(); i++) {
			int index = i * 2;
			currentMap.setPos(enemies.get(i).getID(), currentMap.getEnemyPos()[index], currentMap.getEnemyPos()[index + 1]);
		}
		currentMap.setPos(7, currentMap.getItemPos()[0], currentMap.getItemPos()[1]);
	}
	
	public void turnMana() {
		for(int i = 0; i < players.size(); i++) {
			Chara currentPlayer = players.get(i);
			currentPlayer.setMana(currentPlayer.getMana() + 1);
			
		}
	}
	
	/* Methods used for attacks and the killing of characters
	 * 
	 */
	
	public boolean isLegalAttack(Chara attacker, Chara receiver) {
		int atkRange = attacker.getRange();
		int[] atkPos = currentMap.getPos(attacker.getID());
		int[] recPos = currentMap.getPos(receiver.getID());
		int atkDist = (Math.abs(atkPos[0] - recPos[0]) + Math.abs(atkPos[1] - recPos[1]));
		if (atkDist > atkRange)  {
			return false;
		}
		else return true;
	}
	
	public void kill(int IDToKill, int posToKill, ArrayList<Chara> listContainingChar) {
		System.out.println(listContainingChar.get(posToKill).getName() + " died!");
		int[] killCoords = currentMap.getPos(IDToKill);
		currentMap.setPos(0, killCoords[0], killCoords[1]);
		listContainingChar.remove(posToKill);
	}
	
	public void killChecker(ArrayList<Chara> chars) {
		for(int i = 0; i < chars.size(); i++) {
			Chara dead = chars.get(i);
			if(dead.getHealth() <= 0) {
				kill(dead.getID(), i, chars);
			}
		}
	}

	/* Utility functions - useful stuff
	 * 
	 */
	
	/**
	 * 
	 * @param ID
	 * @param listToCheck
	 * @return
	 */
	public Chara getCharaFromID(int ID, ArrayList<Chara> listToCheck) {
		Chara toReturn = null;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (listToCheck.get(i).getID() == ID) toReturn = listToCheck.get(i);
		}
		return toReturn;
	}
	
	public boolean checkIfIDInList(int ID, ArrayList<Chara> listToCheck) {
		boolean isThere = false;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (!isThere) {
				if (listToCheck.get(i).getID() == ID) isThere = true;
			}
		}
		return isThere;
	}
	
	/*public void printListOfChars(ArrayList<Chara> chars, boolean arePlayers) {
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
	}*/

	public Chara setCharStatsLabel(int ID) {
		Chara toDisplay = null;
		if(checkIfIDInList(ID, players)) {
			toDisplay = getCharaFromID(ID, players);
			lbl1.setText(
					toDisplay.getName() + "\n"
							+ "Health: " + toDisplay.getHealth() + "/" + toDisplay.getMaxHealth() + "\n"
							+ "Attack: " + toDisplay.getAttack() + "/" + toDisplay.getMaxAttack() + "\n"
							+ "Mana" + toDisplay.getMana() + "/" + toDisplay.getMaxMana());
		} else if (checkIfIDInList(ID, enemies)) {
			toDisplay = getCharaFromID(ID, enemies);
			lbl1.setText(
					toDisplay.getName() + "\n"
							+ "Health: " + toDisplay.getHealth() + "/" + toDisplay.getMaxHealth() + "\n"
							+ "Attack: " + toDisplay.getAttack() + "/" + toDisplay.getMaxAttack() + "\n");
		} else {
			lbl1.setText("");
		}
		return toDisplay;
		
	}
}
