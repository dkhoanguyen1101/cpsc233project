/**game runner portion - uses main to facilitate gameplay
 * 
 * @author Derrick
 *
 */
public class Game {

	public static void main(String[] args) {
		map demoMap = new map(6, 10); //make a generic 7x11 map
		Character player = new Character();	//generating the generic player character
		player.setAttack(10);
		player.setHealth(100);
		player.setId(1);
		Character enemy = new Character(); //generating the generic enemy character
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
		while(isGame) {
			if(isPlayerAlive) { //player phase
				//move();		//supplementary move method
				//attack();	//supplementary attack method
							//not sure how use item is going to work - will fix later
				if(enemy.getHealth() <= 0) { //if you killed the enemy
					isEnemyAlive = false; 	//its dead
					isGame = false;			//game is over
					isWin = true;			//you won
				}
			}
			if(isEnemyAlive) { //enemy phase
				//AI(enemy); //use AI function to determine moves for this character
				if(player.getHealth() <= 0) { //if the enemy killed you
					isPlayerAlive = false;	//you are dead
					isGame = false;
					isWin = false;			//you lost
				}
			}
			break; //temporary break so I can save this code in eclipse
		}
		if(isWin) { //end of demo message
			System.out.println("Congratulations you won!");
		} else {
			System.out.println("You lost.");
		}
	}

}
