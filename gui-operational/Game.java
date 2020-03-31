
import java.math.*;
import java.util.ArrayList;

import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.Popup;
import javax.print.attribute.standard.PrinterName;
import java.awt.*;

public class Game {

	//references to all GUI elements
	protected Button btn1, btn2, btn3, btn4;
	protected Label lbl1, lbl2;
	protected ImageView imv;
	//protected ArrayList<ImageView> charImageFrames;
	protected Pane root;
	protected Scene scene;
	protected Scene intermittentScene;
	protected Scene gameOverScene;
	protected Stage stage;
	
	//lists to be used to hold references to the characters on the map
	protected ArrayList<Chara> players;
	protected ArrayList<Chara> enemies;
	protected ArrayList<CharaImageView> images;
	
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
	
	public Game(Stage st) {
		root = new Pane();
		BackgroundImage GameBg = new BackgroundImage(new Image("tan.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		root.setBackground(new Background(GameBg));
		lbl1 = new Label(); lbl2 = new Label(); imv = new ImageView();
		stage = st; root = new Pane();
		//intermittentScene = is; gameOverScene = gos;
		
		scene = new Scene(root, 800, 600);
		
		imv.setLayoutX(0.0); imv.setLayoutY(0.0);
		imv.setImage(new Image("Map1.png"));
		root.getChildren().add(imv);
		
		btn1 = new Button("1");
        btn1.setMinSize(80,40);
        btn1.setLayoutX(30);
        btn1.setLayoutY(530);
        btn1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        btn1.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(btn1);


        btn2 = new Button("2");
        btn2.setMinSize(80,40);
        btn2.setLayoutX(140);
        btn2.setLayoutY(530);
        btn2.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        btn2.setStyle("-fx-background-color: #A9A9A9");
        btn2.textFillProperty();
        root.getChildren().add(btn2);

        btn3 = new Button("3");
        btn3.setMinSize(80,40);
        btn3.setLayoutX(250);
        btn3.setLayoutY(530);
        btn3.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        btn3.setStyle("-fx-background-color: #A9A9A9");
        btn3.textFillProperty();
        root.getChildren().add(btn3);

        btn4 = new Button("4");
        btn4.setMinSize(80,40);
        btn4.setLayoutX(360);
        btn4.setLayoutY(530);
        btn4.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        btn4.setStyle("-fx-background-color: #A9A9A9");
        btn4.textFillProperty();
        root.getChildren().add(btn4);
		
		lbl1.setMaxSize(288, 300);
		lbl2.setMaxSize(288, 300);
		lbl1.setText("1"); lbl2.setText("2");
		lbl1.setLayoutX(512); lbl1.setLayoutY(0);
		lbl2.setLayoutX(512); lbl2.setLayoutY(300);
		root.getChildren().add(lbl1);
		root.getChildren().add(lbl2);
		
		stage.setScene(scene);
		stage.show();
		
		initialize();
	}
	
	/* Methods for setting btn1 functions and visuals in response to game
	 * 
	 */
	
	public void setButtonTextPlayerActionChoice() {
		
		lbl2.setText("Select a character to act: ");
		btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText("End turn");
		//System.out.println(currentMap.toString());
		turnChecker();
	}
	
	public void setButtonTextActions(int ID) {
		Chara toAct = setCharStatsLabel(ID);
		if(toAct != null && players.contains(toAct)) {
			if(notMoved.contains(toAct)) {
				btn1.setText("Move");
			}
			if(notActed.contains(toAct)) {
				btn2.setText("Attack");
				btn3.setText("Use item");
				btn4.setText("Use special");
			}
		} else {
			setButtonTextPlayerActionChoice();
		}
	}
	
	public void setButtonTextMove() {
		lbl2.setText("Select a space to move to");
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	public void setButtonTextAttack()  {
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
		lbl2.setText(toAct.getSpecDesc());
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	/* Methods for game facilitation, through and between maps
	 * 
	 */
	
	
	public void initialize() {
		/*Pane root = new Pane();
		intermittentScene = new Scene(root, 800, 600);
		Label textbox = new Label();
		textbox.setText("You win!");
		textbox.setMinSize(200, 100);
		textbox.setLayoutX(300);
		textbox.setLayoutY(100);
		btn1 cont = new btn1();
		cont.setText("Continue to next map");
		cont.setOnAction(f -> {
            cont.(e -> {
            	
            });
		});*/
		
		players = new ArrayList<Chara>();
		enemies = new ArrayList<Chara>();
		images = new ArrayList<CharaImageView>();
		Chara toAdd = new KinesiologyMajor(1); players.add(toAdd);
		/*toAdd = new ZoologyMajor(2); players.add(toAdd);
		toAdd = new BiomedMajor(3); players.add(toAdd);
		toAdd = new EngMajor(4); players.add(toAdd);
		toAdd = new PhilosophyMajor(5); players.add(toAdd);
		toAdd = new ChemistryMajor(6); players.add(toAdd);*/
		
		startNewMap();
	}
	
	public void startNewMap() {
		enemies.clear();
		if(!images.isEmpty()) {
			for(int i = 0; i < images.size(); i++) {
				CharaImageView current = images.get(i);
				root.getChildren().remove(current.getImage());
			}
		}
		images.clear();
		for(int i = 0; i < players.size(); i++) {
			CharaImageView toAdd = new CharaImageView(players.get(i).getID(), players.get(i).getImageUrl());
			images.add(toAdd);
			root.getChildren().add(toAdd.getImage());
		}
		for(int i = 0; i < 6; i++) {
			Chara toAdd = new Enemy(i + 7);
			enemies.add(toAdd);
			CharaImageView toAddImg = new CharaImageView(toAdd.getID(), toAdd.getImageUrl());
			images.add(toAddImg);
			root.getChildren().add(toAddImg.getImage());
		}
		currentMap = newMaps.generate();
		enemyAI = new AI(currentMap);
		imv.setImage(new Image(currentMap.getImageURL()));
		populateMap();
		turnNo = 1;
		mapNo++;
		
		refreshChars();
		notActed = new ArrayList<Chara>(players);
		notMoved = new ArrayList<Chara>(players);
		
		
		//System.out.println(currentMap.toString());
	}
	
	public void startNewTurn() {
		for(int i = 0; i < enemies.size(); i++) {
			if(players.size() > 0) {
				Chara currentEnemy = enemies.get(i);//get the current enemy in the list
				ArrayList<Integer> playerIDs = new ArrayList<Integer>();//create a list to get the IDs of players to pass into AI
				for(int j = 0; j < players.size(); j++) {//fill the player ID list
					playerIDs.add(new Integer(players.get(j).getID()));
				}
				int[] movedPos = enemyAI.moveAITowards(currentEnemy.getID(), currentEnemy.getMove(), playerIDs); //move all enemies toward the players (all enemies are aggressive for now)
				Chara closestPlayer = getCharaFromID(enemyAI.checkClosest(currentEnemy.getID(), playerIDs), players); //get the character closest to the enemy
				ImageView image = getImageViewFromID(currentEnemy.getID(), images);
				image.setLayoutX(movedPos[0] * cellSizeX);
				image.setLayoutY(movedPos[1] * cellSizeY);
				if (isLegalAttack(closestPlayer, currentEnemy)) currentEnemy.attack(closestPlayer); //if possible to attack, attack
				killChecker(players);//check to see if the enemy killed a player
			}
		}
		turnMana();
		notActed = new ArrayList<Chara>(players);
		notMoved = new ArrayList<Chara>(players);
		turnNo++;
	}
	
	public void populateMap() {
		int[] charPos = currentMap.getCharPos();
		int[] enemyPos = currentMap.getEnemyPos();
		int[] itemPos = currentMap.getItemPos();
		for(int i = 0; i < players.size(); i++) {
			//System.out.println(players.size());
			int index = i * 2;
			int id = players.get(i).getID();
			currentMap.setPos(id, charPos[index], charPos[index + 1]);
			ImageView image = getImageViewFromID(id, images);
			image.setLayoutX(charPos[index] * cellSizeX);
			image.setLayoutY(charPos[index + 1] * cellSizeY);
			//System.out.println("Putting " + id + " on space " + charPos[index] + ", " + charPos[index + 1]);
		}
		for(int i = 0; i < enemies.size(); i++) {
			int index = i * 2;
			int id = enemies.get(i).getID();
			currentMap.setPos(id, enemyPos[index], enemyPos[index + 1]);
			ImageView image = getImageViewFromID(id, images);
			image.setLayoutX(enemyPos[index] * cellSizeX);
			image.setLayoutY(enemyPos[index + 1] * cellSizeY);
		}
		Random numberGen = new Random();
		int newItem = numberGen.nextInt(5);
		CharaImageView toAdd;
		int newID = 0;
		switch(newItem) {
		case 0:
			newID = 30; break;
		case 1:
			newID = 32; break;
		case 2:
			newID = 33; break;
		case 3:
			newID = 34; break;
		case 4:
			newID = 35; break;
		}
		currentMap.setPos(newID, itemPos[0], itemPos[1]);
		toAdd = new CharaImageView(newID, "Item.png");
		images.add(toAdd);
		ImageView image = toAdd.getImage();
		root.getChildren().add(image);
		image.setLayoutX(itemPos[0] * cellSizeX);
		image.setLayoutY(itemPos[1] * cellSizeY);
	}
	
	public void turnChecker() {
		if(enemies.isEmpty()) {
			lbl1.setText("You Win!");
			lbl2.setText("");
			btn1.setText(null); btn1.setOnAction(null);
			btn2.setText(null); btn2.setOnAction(null);
			btn3.setText(null); btn3.setOnAction(null);
			btn4.setText("Continue");
			btn4.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					startNewMap();
				}
			});
		} else if(players.isEmpty()) {
			lbl1.setText("You lose!");
			lbl2.setText("");
			btn1.setText(null); btn1.setOnAction(null);
			btn2.setText(null); btn2.setOnAction(null);
			btn3.setText(null); btn3.setOnAction(null);
			btn4.setText(null); btn4.setOnAction(null);
		} else if(notMoved.isEmpty() && notActed.isEmpty()) {
			startNewTurn();
;		}
	}
	
	public void turnMana() {
		for(int i = 0; i < players.size(); i++) {
			Chara currentPlayer = players.get(i);
			currentPlayer.setMana(currentPlayer.getMana() + 1);
			
		}
	}
	
	public void refreshChars() {
		for(int i = 0; i < players.size(); i++) {
			Chara current = players.get(i);
			current.setAttack(current.getMaxAttack());
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
		ImageView image = getImageViewFromID(IDToKill, images);
		root.getChildren().remove(image);
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
	
	public ImageView getImageViewFromID(int ID, ArrayList<CharaImageView> listToCheck) {
		ImageView toReturn = null;
		for(int i = 0; i < listToCheck.size(); i++) {
			if (listToCheck.get(i).getID() == ID) toReturn = listToCheck.get(i).getImage();
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
		Chara toReturn = null;
		String toDisplay = null;
		if(checkIfIDInList(ID, players)) {
			//System.out.println("Player found");
			toReturn = getCharaFromID(ID, players);
			//System.out.println(toDisplay.getName());
			toDisplay = 
					toReturn.getName() + "\n"
							+ "Health: " + toReturn.getHealth() + "/" + toReturn.getMaxHealth() + "\n"
							+ "Attack: " + toReturn.getAttack() + "/" + toReturn.getMaxAttack() + "\n"
							+ "Mana" + toReturn.getMana() + "/" + toReturn.getMaxMana();
		} else if (checkIfIDInList(ID, enemies)) {
			//System.out.println("Enemy found");
			toReturn = getCharaFromID(ID, enemies);
			//System.out.println(toDisplay.getName());
			toDisplay = 
					toReturn.getName() + "\n"
							+ "Health: " + toReturn.getHealth() + "/" + toReturn.getMaxHealth() + "\n"
							+ "Attack: " + toReturn.getAttack() + "/" + toReturn.getMaxAttack();
		} else {
			lbl1.setText("");
		}
		
		lbl1.setText(toDisplay);
		return toReturn;
		
	}
}
