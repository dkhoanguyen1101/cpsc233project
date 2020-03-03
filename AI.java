import java.util.ArrayList;

public class AI {
	
	//instance variables
	Cell cell;
	
	//constructor
	public AI(Cell cell) {
		this.cell = cell;
	}
	//returns the id of the closest player to the enemy
	
	//shouldn't be used outside of class
	
	private int checkClosest(int enemyID, int player1ID, int player2ID, int player3ID) {
		//get distance of each item
		int[] posE = cell.getPos(enemyID);
		int[] posP1 = cell.getPos(player1ID);
		double dist1 = calDist(posP1[0], posP1[1], posE[0], posE[1]);
		int[] posP2 = cell.getPos(player2ID);
		double dist2 = calDist(posP2[0], posP2[1], posE[0], posE[1]);
		int[] posP3 = cell.getPos(player3ID);
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
	
	//moves character to the farthest location from the nearest player
	
	public void moveAIAway(int enemyID, int player1ID, int player2ID, int player3ID) {
		//find distance using setPos(id), getPos(id), isLegalMove(), isEmpty(), calDist()
		
	}
	
	//moves character to the closest location to the nearest player
	
	public void moveAITowards(int enemyID, int player1ID, int player2ID, int player3ID) {
		
	}
	
}