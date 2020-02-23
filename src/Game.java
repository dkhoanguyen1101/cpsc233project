/**game runner portion - uses main to facilitate gameplay
 * 
 * @author Derrick
 *
 */
import java.util.Scanner;
public class Game {

	public static void main(String[] args) {
		map demoMap = new map(7, 11); //make a generic 7x11 map
		Chara player = new Chara();	//generating the generic player character
		player.setAttack(10);
		player.setHealth(100);
		player.setId(1);
		healthPotion healthPot = new healthPotion();
		player.setItem(healthPot, 0);
		Chara enemy = new Chara(); //generating the generic enemy character
		enemy.setAttack(5);
		enemy.setHealth(50);
		enemy.setId(2);
		demoMap.setPos(1, 4, 10); //putting player on right side of map
		demoMap.setPos(2, 4, 0); //putting enemy on left side of map
		boolean isPlayerAlive = true;	//booleans used to execute phase if not dead 
		boolean isEnemyAlive = true; 
		boolean isGame = true;	//boolean used to keep turns going is nobody is dead
		boolean isWin = true;	//boolean used to determine final message
		//beginning of the actual game
		//generic methods used for not, will update later
		Scanner input = new Scanner(System.in);
		while(isGame) {
			if(isPlayerAlive) { //player phase
				demoMap.toString();
				System.out.println("Enter coordinates for where you would like to move"); //prompting user to move on map
				System.out.print("row: ");
				int xMove = input.nextInt();
				System.out.println();
				System.out.print("column: ");
				int yMove = input.nextInt();
				move(player,x,y);
				//when character has moved, character must do action
				boolean didSomething = false; //boolean to check character had made legal move
				while(!didSomething) {
					System.out.println("Enter 1 to attack"); //use prompts for action
					System.out.println("Enter 2 to use an item");
					System.out.println("Enter 3 to end turn now");
					int choice = input.nextInt();
					switch(choice) {
					case 1: didSomething = attack(player); break; //supplementary methods will determine if legal action was taken
					case 2: didSomething = item(player); break;  //if so, boolean becomes true, moves on to next character
					case 3: didSomething = true; break; //if no action wants to be taken by user, breaks the loop without acting
					}
				}
				if(enemy.getHealth() <= 0) { //if you killed the enemy
					isEnemyAlive = false; 	//its dead
					isGame = false;			//game is over
					isWin = true;			//you won
				}
			}
			if(isEnemyAlive) { //enemy phase
				demoMap.toString();
				AI(enemy); //use AI function to determine moves for this character
				if(player.getHealth() <= 0) { //if the enemy killed you
					isPlayerAlive = false;	//you are dead
					isGame = false;
					isWin = false;			//you lost
				}
			}
		}
		if(isWin) { //end of demo message
			System.out.println("Congratulations you won!");
		} else {
			System.out.println("You lost.");
		}
	}

	
	//Attack, Item, and Move methods in the Game class are made by Robert Ferguson.
	
	
	public boolean Attack(Chara attacker, Chara receiver, Map map) {
		//check if attacker is in range
		int attackerX = map.getPos(attacker.getID())[0];
		int attackerY = map.getPos(attacker.getID())[1];
		int receiverX = map.getPos(receiver.getID())[0];
		int receiverY = map.getPos(receiver.getID())[1];
		if (((attackerX == receiverX + 1) || (attackerX == receiverX - 1)) && ((attackerY == receiverY + 1) || (attackerY == receiverY - 1))) {
			//lower receiver health
			receiver.setHealth((receiver.getHealth()) - (attacker.getAttack()));
			//change boolean to true
			return true;
		} else {
			//if not within range, return false
			System.out.println(attacker + " cannot reach anybody!");
			return false;
		}
		
			
	}
	
	public boolean Item(Chara user) {
		//check if inventory is empty
		String slot1 = user.getInventorys().get[0];
		String slot2 = user.getInventorys().get[1];
		String slot3 = user.getInventorys().get[2];
		if (slot1 == "Empty" && slot2 == "Empty" && slot3 == "Empty") {
			System.out.println(user + "'s inventory is empty");
			return false;
		} else {
			//ask for user input
			Scanner sc = new Scanner(System.in);
			int select = sc.nextInt();
			System.out.println("Select an Item");
			System.out.println("type 1 ("+ slot1 +"), 2 ("+ slot2 +"), or 3 ("+ slot3 +")");
			if (select == 1) {
				if (slot1 == "Health Potion") {
					//if input correct, make item, use item and change string to empty
					healthPotion hPotion = new item().new healthPotion();
					hPotion.setAttributes();
					hPotion.characterHealth(user.getID());
					
					
					//make method that lets me change specific slots
					user.setInventorys("Empty");
					
					
					//change boolean to true
					return true;
				} else {
					System.out.println("That slot is empty");
					return false;
				}
			} else if (select == 2) {
				if (slot2 == "Health Potion") {
					//if input correct, make item, use item and change string to empty
					healthPotion hPotion = new item().new healthPotion();
					hPotion.setAttributes();
					hPotion.characterHealth(user.getID());
					
					
					//make method that lets me change specific slots
					user.setInventorys("Empty");
					
					
					//change boolean to true
					return true;
				} else {
					System.out.println("That slot is empty");
					return false;
				}
			} else if (select == 3) {
				if (slot3 == "Health Potion") {
					//if input correct, make item, use item and change string to empty
					healthPotion hPotion = new item().new healthPotion();
					hPotion.setAttributes();
					hPotion.characterHealth(user.getID());
					
					
					//make method that lets me change specific slots
					user.setInventorys("Empty");
					
					
					//change boolean to true
					return true;
				} else {
					System.out.println("That slot is empty");
					return false;
				}
				
			} else {
				//send back to menu if item is not selected
				System.out.println("That is not a valid item");
				return false;
			}
		}
	}
	
	//moves chosen character to new location on map, provided location is legal
	
	public void Move(Chara chara, Map map) {
		//ask for user input (x and y positions)
		Scanner pick = new Scanner(System.in);
		System.out.println("Pick an x position to move to");
		int newX = pick.nextInt();
		System.out.println("Now pick a y position to move to");
		int newY = pick.nextInt();
		//ask for new x and y position if position is illegal
		while (map.isLegalMove(chara.getID(), newX, newY, 4) == false) {
			System.out.println("that is not a legal move");
			System.out.println("Pick another x position to move to");
			int newX = pick.nextInt();
			System.out.println("Now pick another y position to move to");
			int newY = pick.nextInt();
		}
		//once positions are legal, move to that position
		map.move(chara.getID(), newX, newY, 4);
	}
}
	
}
