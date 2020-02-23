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

}
