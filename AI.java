import java.util.ArrayList;

public class AI {
	
	//instance variables
	Map map;
	
	//constructor
	public AI(Map map) {
		this.map = map;
	}
	
	//checkClosest : returns the id of the closest player to the enemy
	//parameters : the IDs of the enemy moving, and all the player characters
	
	//shouldn't be used outside of class
	
	private int checkClosest(int enemyID, int player1ID, int player2ID, int player3ID) {
		//get distance of each item
		int[] posE = map.getPos(enemyID);
		int[] posP1 = map.getPos(player1ID);
		double dist1 = calDist(posP1[0], posP1[1], posE[0], posE[1]);
		int[] posP2 = map.getPos(player2ID);
		double dist2 = calDist(posP2[0], posP2[1], posE[0], posE[1]);
		int[] posP3 = map.getPos(player3ID);
		double dist3 = calDist(posP3[0], posP3[1], posE[0], posE[1]);
		//use loop to find shortest distance
		ArrayList<Double> dists = new ArrayList<Double>();
		dists.add(dist1);
		dists.add(dist2);
		dists.add(dist3);
		double closest = dist1;
		for (double item : dists) {
			if (item < closest) {
				closest = item;
			}
		}
		//return id of closest player
		if (closest == dist1) {
			return player1ID;
		} else if (closest == dist2) {
			return player2ID;
		} else if (closest == dist3) {
			return player3ID;
		}
	}
	
	//moveAIAway : moves character to the farthest location from the nearest player
	//parameters : the IDs of the enemy that is moving, and all the player characters
	
	public void moveAIAway(int enemyID, int player1ID, int player2ID, int player3ID) {
		//get position of both characters
		int nearestPlayerID = checkClosest(enemyID, player1ID, player2ID, player3ID);
		int [] enemyPos = map.getPos(enemyID);
		int [] playerPos = map.getPos(nearestPlayerID);
		double longest = 32;
			int colReturn = 0;
			int rowReturn = 0;
			for(int countRow = 0; countRow < row ; countRow++) {
				for(int countCol = 0; countCol < column; countCol++) {
					if(map.isEmpty(countRow, countCol)) {
						
						//how do i get the distance limit
						
						if (map.calDist(enemyPos[0], enemyPos[1], countCol, countRow) <= 4) {
							if(map.calDist(countCol, countRow, playerPos[0], playerPos[1]) > longest){
								colReturn = countCol;
								rowReturn = countRow;
								longest = map.calDist(countCol, countRow, playerPos[0], playerPos[1]);
							}
						} 
					}
				} 
			}
			map.setPos(0, enemyPos[1], enemyPos[0]);
			map.setPos(enemyID, rowReturn, colReturn);	
	}
		
	
	//moveAITowards : moves character to the closest location to the nearest player
	//parameters : the IDs of the enemy that is moving, and all the player characters
	
	public void moveAITowards(int enemyID, int player1ID, int player2ID, int player3ID) {
		//get position of both characters
		int nearestPlayerID = checkClosest(enemyID, player1ID, player2ID, player3ID);
		int [] enemyPos = map.getPos(enemyID);
		int [] playerPos = map.getPos(nearestPlayerID);
		double shortest = 32;
			int colReturn = 0;
			int rowReturn = 0;
			for(int countRow = 0; countRow < row ; countRow++) {
				for(int countCol = 0; countCol < column; countCol++) {
					if(cell.isEmpty(countRow, countCol)) {
						
						//how do i get the distance limit
						
						if (map.calDist(enemyPos[0], enemyPos[1], countCol, countRow) <= 4) {
							if(map.calDist(countCol, countRow, playerPos[0], playerPos[1]) < shortest){
								colReturn = countCol;
								rowReturn = countRow;
								shortest = map.calDist(countCol, countRow, playerPos[0], playerPos[1]);
							}
						} 
					}
				} 
			}
			map.setPos(0, enemyPos[1], enemyPos[0]);
			map.setPos(enemyID, rowReturn, colReturn);	
	}	
}
