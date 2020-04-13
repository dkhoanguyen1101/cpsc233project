package main;

import java.util.Random;

import com.sun.javafx.collections.MappingChange.Map;

import characters.Chara;
import characters.Enemy;
import item.Item;
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

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

/** The controller for the game - tracks characters, enemies, items, positions on the map both code-wise and GUI-wise
 *  sets button action descriptions, and more.
 * 
 * @author Derrick C
 * contributions by Robert and Khoa
 */
public class Game {

	//references to all GUI elements
	protected Button btn1, btn2, btn3, btn4, btn5;
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
	
	//used during game save/load
	protected boolean isLoading;
	
	//numbers for scores
	private double score = 0;
	private double GPA = 0.0;
	private double averageTime = 20;
	
	//other technical information
	protected final int cellSizeX = 32, cellSizeY = 32, mapSizeX = 16, mapSizeY = 16;
	
	/** constructor for a new controller - player of the game
	 * 
	 * @param st  stage to set scenes on as the game progresses
	 * @param selected  list of characters selected on the previous screen
	 * @param isLoading  boolean that determines if a game is loaded
	 */
	public Game(Stage st, ArrayList<Chara> selected, boolean isLoading) {
		this.isLoading = isLoading; //set loading to be checked later
		stage = st; //save the stage to set
		
		//creation of the game scene
		root = new Pane(); //new pane for the stage
		BackgroundImage GameBg = new BackgroundImage(new Image("tan.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
		root.setBackground(new Background(GameBg)); //setting the background
		
		scene = new Scene(root, 800, 600); //producing the new scene
		
		imv = new ImageView(); //setting the view for the map image
		imv.setLayoutX(0.0); imv.setLayoutY(0.0); //constrain to the top left corner
		root.getChildren().add(imv);
		
		//creation of the intermission screen - going between one map to another when you finish 
		Pane intRoot = new Pane(); //create a different root to build the new scene
		intermittentScene = new Scene(intRoot, 800, 600);
		Label textbox = new Label(); //label that is used to display the "you win" message
		textbox.setText("You win!");
		textbox.setMinSize(200, 100); //set size, orientation
		textbox.setLayoutX(300);
		textbox.setLayoutY(100);
		Button cont = new Button(); // button which will continue the game onto a new map
		cont.setText("Continue to next map"); 
		cont.setOnAction(new EventHandler<ActionEvent>() { //setting the handler for when the button is clicked
			@Override
			public void handle(ActionEvent e) {
				startNewMap(); //begin a new map
				stage.setScene(scene); //set the stage back to the game scene
				stage.show();
			}
		});
		cont.setMinSize(100, 50); //set size, orientation
		cont.setLayoutX(350);
		cont.setLayoutY(400);
		intRoot.getChildren().add(textbox); //add both the win message and continue buttons
		intRoot.getChildren().add(cont);
		
		//creation of the game over screen
		Pane goRoot = new Pane(); //new root
		gameOverScene = new Scene(goRoot, 800, 600);
		Label goLabel = new Label(); //label which displays game over and final score
		goLabel .setText("Game Over! \n"
				+ "Final Score: " + score + "\n"
				+ "Final GPA: " + GPA);
		goLabel.setMinSize(200, 100); //set size, orientation
		goLabel.setLayoutX(300);
		goLabel.setLayoutY(100);
		goRoot.getChildren().add(goLabel); //add to the scene
		
		Button end = new Button(); //button to quit the game
		end.setText("Quit");
		end.setOnAction(new EventHandler<ActionEvent>() { //setting the handler for the quit button
			@Override
			public void handle(ActionEvent e) {
				stage.close(); //close the window - end the program
			}
		});
		end.setMinSize(100, 50); //set size, orientation
		end.setLayoutX(350);
		end.setLayoutY(400);
		goRoot.getChildren().add(end); //add to the scene
		
		
		//setting the buttons used to select actions
		
		btn1 = new Button("1"); //create a new button
        btn1.setMinSize(80,40); //set size
        btn1.setLayoutX(30);	//set x and y positions on the scene
        btn1.setLayoutY(530);
        btn1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;"); //set a nicer border
        btn1.setStyle("-fx-background-color: #A9A9A9"); //and colour
        root.getChildren().add(btn1); 


        btn2 = new Button("2"); //repeat above another 4 times
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
        
        btn5 = new Button("Save"); //5th button will be used for saving only
		btn5.setMinSize(80,40);
		btn5.setLayoutX(470);
		btn5.setLayoutY(530);
		btn5.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
		btn5.setStyle("-fx-background-color: #A9A9A9");
		btn5.textFillProperty();
		root.getChildren().add(btn5);
		
		lbl1 = new Label(); //building labels which will be used to display game information
		lbl2 = new Label();
		lbl1.setMaxSize(288, 300); //setting the available space for both of them
		lbl2.setMaxSize(288, 300);
		lbl1.setText("1"); lbl2.setText("2");
		lbl1.setLayoutX(512); lbl1.setLayoutY(0);
		lbl2.setLayoutX(512); lbl2.setLayoutY(300);
		root.getChildren().add(lbl1);
		root.getChildren().add(lbl2);
		
		stage.setScene(scene);//set the stage to the new game scene
		stage.show();//show the game
		
		initialize(selected);//initialize the actual game
	}
	
	/* Methods for setting button functions and visuals in response to game
	 * 
	 */
	
	/** for choosing a player character to act
	 * 
	 */
	public void setButtonTextPlayerActionChoice() {
		
		lbl2.setText("Select a character to act: "); //display instruction
		btn1.setText(""); btn2.setText(""); btn3.setText(""); btn4.setText("End turn"); //display available actions
		turnChecker(); //check to see if a new turn must be set, done every time this method is called.
	}
	
	/** for selecting an action for the selected character
	 * 
	 * @param ID  the id of the space selected
	 */
	public void setButtonTextActions(int ID) {
		Chara toAct = setCharStatsLabel(ID); //retrieve and display the stats of the selected id
		if(toAct != null && players.contains(toAct)) { //if the selected id is a player character
			if(notMoved.contains(toAct)) { //if the player has not yet moved
				btn1.setText("Move"); //display the available action
			}
			if(notActed.contains(toAct)) { //if the player has not yet acted
				btn2.setText("Attack"); //display the available actions
				btn3.setText("Use item"); 
				btn4.setText("Use special");
			}
		} else { //if not a player character
			setButtonTextPlayerActionChoice(); //reset the general actions
		}
	}
	
	/** for selecting a space to move a character
	 * 
	 */
	public void setButtonTextMove() {
		lbl2.setText("Select a space to move to"); //display instruction
		btn1.setText(""); btn2.setText(""); btn3.setText(""); //display actions
		btn4.setText("Back");
	}
	
	/** for selecting a space to attack
	 * 
	 */
	public void setButtonTextAttack()  {
		lbl2.setText("Select an enemy to attack"); //see above
		btn1.setText(""); btn2.setText(""); btn3.setText("");
		btn4.setText("Back");
	}
	
	/** for selecting a character's item to use
	 * 
	 * @param ID  the selected player character's ID
	 */
	public void setButtonTextItems(int ID) {
		Chara toAct = getCharaFromID(ID, players); //get the character from it's ID
		Item[] inventory = toAct.getInventory(); //retrieve that character's inventory
		lbl2.setText("Select an item to use"); //display instructions
		btn1.setText(inventory[0].getName()); //display the available items to use
		btn2.setText(inventory[1].getName());
		btn3.setText(inventory[2].getName());
		btn4.setText("Back");
	}
	
	/** for selecting a character's special ability
	 * 
	 * @param ID  character to act
	 */
	public void setButtonTextSpecial(int ID) {
		Chara toAct = getCharaFromID(ID, players); //get character from it's ID
		lbl2.setText(toAct.getSpecDesc()); //get and set the character's special description/instructions
		btn1.setText(""); btn2.setText(""); btn3.setText(""); //display available actions
		btn4.setText("Back");
	}
	
	/* Methods for game facilitation, through and between maps
	 * 
	 */
	
	/** set the logic portions of the game
	 * 
	 * @param selectedPlayers  list of player selected in the character selection screen, null if the game is loaded
	 */
	public void initialize(ArrayList<Chara> selectedPlayers) {
		
		if(selectedPlayers != null) { //if the list of players exists
			players = selectedPlayers; //take that as the list of players
		} else {
			players = new ArrayList<Chara>(); //if not initialize a list to be loaded
		}
		enemies = new ArrayList<Chara>(); //initialize a list to contain enemies
		images = new ArrayList<CharaImageView>(); //initialize a list to contain the images of each character
		
		if(isLoading) //check for loading game or starting new game
		{
			loadMap();
		}else{
			startNewMap();
		}
	}
	
	/** begin a new map
	 * 
	 */
	public void startNewMap() {
		enemies.clear(); //as a precaution, clear the list of enemies
		if(!images.isEmpty()) { //if the list of images is not empty yet
			for(int i = 0; i < images.size(); i++) { //for all images in the list
				CharaImageView current = images.get(i); //get the image
				root.getChildren().remove(current.getImage()); //remove it from the game stage
			}
		}
		images.clear(); //finally clear the images
		
		for(int i = 0; i < players.size(); i++) { //for all player characters
			CharaImageView toAdd = new CharaImageView(players.get(i).getID(), players.get(i).getImageUrl()); //create a new imageView for tgem
			images.add(toAdd); //add them to the list of all images
			root.getChildren().add(toAdd.getImage()); //add the character's image to the board
		}
		
		for(int i = 0; i < 3; i++) { //creating three new enemies
			Chara toAdd = new Enemy(i + 4); //set them to IDs above the alloted three player IDs
			enemies.add(toAdd); //add them to the list od enemies
			CharaImageView toAddImg = new CharaImageView(toAdd.getID(), toAdd.getImageUrl()); //create a new imageView for each enemy
			images.add(toAddImg); //add them to the list
			root.getChildren().add(toAddImg.getImage()); //add their images to the board
		}
		currentMap = newMaps.generate(); //generate a new map
		enemyAI = new AI(currentMap); //generate a new AI based on the new map
		imv.setImage(new Image(currentMap.getImageURL())); //set the new map image
		populateMap(); //populate the new map
		turnNo = 1; //reset the turn number
		mapNo++; //increase the total maps beat by 1
		
		refreshChars(); //reset characters to start a new map
		notActed = new ArrayList<Chara>(players); //prep characters for a new turn
		notMoved = new ArrayList<Chara>(players);
		setButtonTextPlayerActionChoice();
	}
	
	/** load a current game
	 * 
	 */
	public void loadMap()
	{
		try  {

			ObjectInputStream output = new ObjectInputStream(new FileInputStream("output.bin"));//get the save file
			
			players = (ArrayList<Chara>) output.readObject(); //unpack all of the objects from the save file
			enemies = (ArrayList<Chara>) output.readObject();
			notMoved = (ArrayList<Chara>) output.readObject();
			notActed = (ArrayList<Chara>) output.readObject();
			currentMap = (map) output.readObject();
			score = (double) output.readObject();
			GPA = (double) output.readObject();
			turnNo = (int) output.readObject();
			mapNo = (int) output.readObject();
			output.close();
			
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		imv.setImage(new Image(currentMap.getImageURL())); //set the image of the map
		enemyAI = new AI(currentMap); //initialize a new AI for that map
		//set all of the images for the characters, enemies and the item
		for(Chara i:players) { //for all player characters
			CharaImageView toAdd = new CharaImageView(i.getID(), i.getImageUrl()); //create a new imageView for them
			images.add(toAdd); //add to list of images
			int[] pos = currentMap.getPos(i.getID()); //get the position of that character on the map
			ImageView image = toAdd.getImage(); //get the image from the object
			image.setLayoutX(pos[0] * cellSizeX); image.setLayoutY(pos[1] * cellSizeY); //set the proper layout of the image
			root.getChildren().add(image); //add the image to the stage
		}
		for(Chara i:enemies) { //repeat above process for enemies
			CharaImageView toAdd = new CharaImageView(i.getID(), i.getImageUrl()); 
			images.add(toAdd);
			int[] pos = currentMap.getPos(i.getID());
			ImageView image = toAdd.getImage();
			image.setLayoutX(pos[0] * cellSizeX); image.setLayoutY(pos[1] * cellSizeY);
			root.getChildren().add(image);
		}
		int itemCheck = currentMap.getID(currentMap.getItemPos()[0], currentMap.getItemPos()[1]); //look for item in default space
		if(itemCheck >= 30 & itemCheck <= 35) { //if an item designated ID is in that space on the map
			CharaImageView toAdd = new CharaImageView(itemCheck, "Item.png"); //create it a new imageView
			images.add(toAdd); //and so on
			ImageView image = toAdd.getImage();
			image.setLayoutX(currentMap.getItemPos()[0] * cellSizeX);
			image.setLayoutY(currentMap.getItemPos()[1] * cellSizeY);
			root.getChildren().add(image);
		}
		
	}
	
	/** begin a new turn
	 * 
	 */
	public void startNewTurn() {
		for(int i = 0; i < enemies.size(); i++) { //for all enemies
			if(!players.isEmpty()) { //if players are all not dead
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
		turnMana(); //increase all player's mana
		calcScoreAndGPA(); //update the score
		notActed = new ArrayList<Chara>(players); //fresh turn - reset trackers for moved and acted
		notMoved = new ArrayList<Chara>(players);
		turnNo++; //iincrease turn number
	}
	/** fill a new map
	 * 
	 */
	public void populateMap() {
		int[] charPos = currentMap.getCharPos(); //get default positions from map
		int[] enemyPos = currentMap.getEnemyPos();
		int[] itemPos = currentMap.getItemPos();
		for(int i = 0; i < players.size(); i++) { //for all players
			int index = i * 2; //get accounted index to set character positions
			int id = players.get(i).getID(); //get the ID of the character to place on the map
			currentMap.setPos(id, charPos[index], charPos[index + 1]); //set place on the map
			ImageView image = getImageViewFromID(id, images); //get the image of the character set
			image.setLayoutX(charPos[index] * cellSizeX); //set its x and y layout in the window
			image.setLayoutY(charPos[index + 1] * cellSizeY);
		}
		for(int i = 0; i < enemies.size(); i++) { //repeat for enemies
			int index = i * 2;
			int id = enemies.get(i).getID();
			currentMap.setPos(id, enemyPos[index], enemyPos[index + 1]);
			ImageView image = getImageViewFromID(id, images);
			image.setLayoutX(enemyPos[index] * cellSizeX);
			image.setLayoutY(enemyPos[index + 1] * cellSizeY);
		}
		Random numberGen = new Random(); //generate a random potion to put on the map
		int newItem = numberGen.nextInt(5); //5 items in total, preset to certain items
		CharaImageView toAdd;
		int newID = 0;
		switch(newItem) { //determine item ID from generated number
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
		currentMap.setPos(newID, itemPos[0], itemPos[1]); //set place on map
		toAdd = new CharaImageView(newID, "Item.png"); //set a new image
		images.add(toAdd); //add to list
		ImageView image = toAdd.getImage(); //get image
		root.getChildren().add(image); //add to scene
		image.setLayoutX(itemPos[0] * cellSizeX); //set orientation
		image.setLayoutY(itemPos[1] * cellSizeY);
	}
	
	/** check if a turn or game is over
	 * 
	 */
	public void turnChecker() {
		if(enemies.isEmpty()) { //if no enemies alive
			stage.setScene(intermittentScene); //display the intermission scene
			stage.show();
		} else if(players.isEmpty()) { //if no players alive
			stage.setScene(gameOverScene); //display game over scene
			stage.show();
		} else if(notMoved.isEmpty() && notActed.isEmpty()) { //if all players actions are done
			startNewTurn(); //begin a new turn automatically
;		}
	}
	
	/** increase each player's mana as a new turn is started
	 *  
	 */
	public void turnMana() { 
		for(int i = 0; i < players.size(); i++) { //for all players
			Chara currentPlayer = players.get(i);
			currentPlayer.setMana(currentPlayer.getMana() + 1); //increase mana by 1 point
			
		}
	}
	
	/** restore all characters to base stats as a new map is started
	 *  
	 */
	public void refreshChars() {
		for(int i = 0; i < players.size(); i++) {
			Chara current = players.get(i);
			current.setAttack(current.getMaxAttack());
		}
	}
	
	/* Methods used for attacks and the killing of characters
	 * 
	 */
	
	/** checker for if one player can attack another
	 * 
	 * @param attacker  character attacking
	 * @param receiver  character receiving the attack
	 * @return  boolean as to whether or not the attack is legal
	 */
	public boolean isLegalAttack(Chara attacker, Chara receiver) {
		int atkRange = attacker.getRange(); //get the range at which the attacker can attack
		int[] atkPos = currentMap.getPos(attacker.getID()); //get position of the attacker
		int[] recPos = currentMap.getPos(receiver.getID()); //get position of the receiver
		int atkDist = (Math.abs(atkPos[0] - recPos[0]) + Math.abs(atkPos[1] - recPos[1])); //get the distance between the two characters
		if (atkDist > atkRange)  { //if that distance is longer than the range
			return false; //not legal attack
		}
		else return true; //if not, attack is legal
	}
	
	/** thanos snap a character from existence
	 * 
	 * @param IDToKill  ID of character to kill
	 * @param posToKill  position in list of the character to kill
	 * @param listContainingChar  list the character belongs to (players or enemies)
	 */
	public void kill(int IDToKill, int posToKill, ArrayList<Chara> listContainingChar) {
		int[] killCoords = currentMap.getPos(IDToKill); //get coordinates of the character to kill
		currentMap.setPos(0, killCoords[0], killCoords[1]); //set the space that character was on to empty
		listContainingChar.remove(posToKill); //remove character from arrayList
		ImageView image = getImageViewFromID(IDToKill, images); //get the imageView of the character
		root.getChildren().remove(image); //remove it from the map
	}
	
	/** Check a list see if a character needs to be killed
	 * 
	 * @param chars  list of characters to check
	 */
	public void killChecker(ArrayList<Chara> chars) {
		for(int i = 0; i < chars.size(); i++) { //for all characters in the specified list
			Chara dead = chars.get(i);
			if(dead.getHealth() <= 0) { //if it's health is zero or less
				kill(dead.getID(), i, chars); //kill the character off
			}
		}
	}

	/* Utility functions - useful stuff
	 * 
	 */
	
	/** Get a Chara object from its unique ID
	 * 
	 * @param ID  ID of the character to find
	 * @param listToCheck  list to look for character ID
	 * @return  character with the specified ID from the list - null if not there
	 */
	public Chara getCharaFromID(int ID, ArrayList<Chara> listToCheck) {
		Chara toReturn = null; //declare and initialize a return value to null (in case the character is not there)
		for(int i = 0; i < listToCheck.size(); i++) { //for all characters in the list
			if (listToCheck.get(i).getID() == ID) toReturn = listToCheck.get(i); //if that character's id matches specified, set return value to that character
		}
		return toReturn; //return the character
	}
	
	/** get an ImageView for a character from a list based on a character's ID
	 * 
	 * @param ID  ID of image to get
	 * @param listToCheck  list of CharaImageViews to look through
	 * @return  the ImageView for the specified IDs character - returns null if not there
	 */
	public ImageView getImageViewFromID(int ID, ArrayList<CharaImageView> listToCheck) {
		ImageView toReturn = null; //declare and initialize return value to null (in case the character's image is not there)
		for(int i = 0; i < listToCheck.size(); i++) { //for all CharaImageViews in specified list
			if (listToCheck.get(i).getID() == ID) toReturn = listToCheck.get(i).getImage(); //if the ID matches specified, return the ImageView 
		}
		return toReturn;
	}
	
	/** Check if a character is in a list base on ID
	 * 
	 * @param ID  ID of character to look for
	 * @param listToCheck  list to check for ID
	 * @return  boolean as to whether or not the character is in that list- null if not
	 */
	public boolean checkIfIDInList(int ID, ArrayList<Chara> listToCheck) {
		boolean isThere = false; //declare and initialize a return value to false (in case the character is not there
		for(int i = 0; i < listToCheck.size(); i++) { //for all characters in the list
			if (!isThere) { //if the character has not already been found
				if (listToCheck.get(i).getID() == ID) isThere = true; //if the current character's ID matches the specified, return value becomes true
			}
		}
		return isThere;
	}
	
	/** set the label for character stats
	 * 
	 * @param ID  ID of character to set stats for
	 * @return  the character who's stats were set
	 */
	public Chara setCharStatsLabel(int ID) {
		Chara toReturn = null; //initialize a return variable to null (in case not there)
		String toDisplay = ""; //string to store the message displayed - initialized to an empty string incase the character is not found
		if(checkIfIDInList(ID, players)) { //if character is a player
			toReturn = getCharaFromID(ID, players); //set character return vale
			toDisplay = //set message to display as
					toReturn.getName() + "\n" // name
							+ "Health: " + toReturn.getHealth() + "/" + toReturn.getMaxHealth() + "\n" //health out of max
							+ "Attack: " + toReturn.getAttack() + "/" + toReturn.getMaxAttack() + "\n" //attack out of base
							+ "Energy: " + toReturn.getMana() + "/" + toReturn.getMaxMana() + "\n"//energy out of max
							+ "Attack Range: " + toReturn.getRange() + " Space(s)\n"//attack range
							+ "Movement Range: " + toReturn.getMove() + " Spaces";//movement range
		} else if (checkIfIDInList(ID, enemies)) { //if character is an enemy
			toReturn = getCharaFromID(ID, enemies); //get return variable
			toDisplay =  //set message to display as
					toReturn.getName() + "\n" //name
							+ "Health: " + toReturn.getHealth() + "/" + toReturn.getMaxHealth() + "\n" //health out of max
							+ "Attack: " + toReturn.getAttack() + "/" + toReturn.getMaxAttack(); //attack out of base
							//enemies have no special, so no mana
		}
		lbl1.setText(toDisplay); //set the display text
		return toReturn;
		
	}
	
	/** Calculate the current score and the GPA
	 *  Code done by Robert
	 */
	public void calcScoreAndGPA() {
		//needs turn number and average health, how do i get it
		//health is easy, just use Chara.getHealth();
		//turnNo is a variable I can access that has the amount of turns
		//average team health value = current added health / total added Health
		//turn number can probably be considered good if less than 20, we can change this later
		//for every turn over, -5%. For every turn under, +5% (based on averageTime being 20, this can be changed)
		//so value = 20 - turns taken
		//then value gets added to score value

		double teamTotalHealth = 0.0;
		double teamCurrentHealth = 0.0;
		
		//get a multiplier based on how much health the team has
		for (Chara member : players) {
			teamTotalHealth = teamTotalHealth + member.getMaxHealth();
			teamCurrentHealth = teamCurrentHealth + member.getHealth();
		}
		double teamHealthMultiplier = teamCurrentHealth / teamTotalHealth;
		
		//get a multiplier based on how long the battle took
		double turnMultiplier = turnNo / averageTime;
		
		//get the average score for the level
		double levelScore = 10000 * teamHealthMultiplier * turnMultiplier;
		
		//add the level's score to the total
		score = score + new Double(levelScore).intValue();
		
		//take average of each levels score and turn it into a GPA (if mapNo is 0, just leave the GPA alone)
		if (mapNo != 0) {
			double unroundedGPA = (score / mapNo) / 2500;
			GPA = Math.round(unroundedGPA * 10) / 10.0;
			if (GPA > 4.3) {
				GPA = 4.3;
			}
		} 
		
	}
	
	public double getGPA() {
		return GPA;
	}
	
	public double getScore() {
		return score;
	}
}
