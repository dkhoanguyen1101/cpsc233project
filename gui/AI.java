import java.util.ArrayList;

public class AI {
	
	//instance variable
	map map;
	
	//constructor
	public AI(map map) {
		this.map = map;
	}
	
	//methods
	
	//checkClosest : returns the id of the closest player to the enemy
	//parameters : the IDs of the enemy moving, and all the player characters
	
	//shouldn't be used outside of the AI class
	
	protected int checkClosest(int enemyID, ArrayList<Integer> playerIDs) {
		//get position of enemy that is moving
		int[] posE = map.getPos(enemyID);
		//run a for loop that finds the shortest distance and returns the id of the player character at that position
		int closestPlayerID = playerIDs.get(0);
		int closest = map.shortestWay((map.getPos(playerIDs.get(0)))[0], (map.getPos(playerIDs.get(0)))[1], posE[0], posE[1]);
		for ( int pos : playerIDs) {
			int newDist = map.shortestWay(map.getPos(pos)[0], map.getPos(pos)[1], posE[0], posE[1]); 
			//if  newDist is smaller than closest, replace
			if (newDist < closest) {
				closest = newDist;
				closestPlayerID = pos;
			}
		}
		//return the closest player characters id
		return closestPlayerID;
	}
	
	//moveAIAway : moves character to the farthest location possible from the nearest player
	//parameters : the IDs of the enemy that is moving, the movement limit of the same enemy, and an array list of all the player characters IDs
	
	public void moveAIAway(int enemyID, int enemyMoveLimit, ArrayList<Integer> playerIDs) {
		//get position of both characters
		int nearestPlayerID = checkClosest(enemyID, playerIDs);
		int [] enemyPos = map.getPos(enemyID);
		int [] playerPos = map.getPos(nearestPlayerID);
		double longest = 32;
			int colReturn = 0;
			int rowReturn = 0;
			for(int countRow = 0; countRow < 16 ; countRow++) {
				for(int countCol = 0; countCol < 16; countCol++) {
					if(map.isEmpty(countRow, countCol)) {
						if (map.shortestWay(enemyPos[0], enemyPos[1], countCol, countRow) <= enemyMoveLimit) {
							if(map.shortestWay(countCol, countRow, playerPos[0], playerPos[1]) > longest){
								colReturn = countCol;
								rowReturn = countRow;
								longest = map.shortestWay(countCol, countRow, playerPos[0], playerPos[1]);
							}
						} 
					}
				} 
			}
		//set enemys position to the farthest location from the nearest player character
			map.setPos(0, enemyPos[0], enemyPos[1]);
			map.setPos(enemyID, colReturn, rowReturn);	
	}
		
	
	//moveAITowards : moves character to the closest location possible to the nearest player
	//parameters : the IDs of the enemy that is moving, the movement limit of the same enemy, and an array list of all the player characters IDs

	public void moveAITowards(int enemyID, int enemyMoveLimit, ArrayList<Integer> playerIDs) {
		//get position of both characters
		int nearestPlayerID = checkClosest(enemyID, playerIDs);
		int [] enemyPos = map.getPos(enemyID);
		int [] playerPos = map.getPos(nearestPlayerID);
		double longest = 32;
			int colReturn = 0;
			int rowReturn = 0;
			for(int countRow = 0; countRow < 16 ; countRow++) {
				for(int countCol = 0; countCol < 16; countCol++) {
					if(map.isEmpty(countRow, countCol)) {		
						if (map.shortestWay(enemyPos[0], enemyPos[1], countCol, countRow) <= enemyMoveLimit) {
							if(map.shortestWay(countCol, countRow, playerPos[0], playerPos[1]) < longest){
								colReturn = countCol;
								rowReturn = countRow;
								longest = map.shortestWay(countCol, countRow, playerPos[0], playerPos[1]);
							}
						} 
					}
				} 
			}
		//set enemys position to the closest position to the closest player character
			map.setPos(0, enemyPos[0], enemyPos[1]);
			map.setPos(enemyID, colReturn, rowReturn);	
	}
}
