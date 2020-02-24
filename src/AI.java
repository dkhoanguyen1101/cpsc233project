
import java.util.ArrayList;

class AI extends map {
	
	
    public int EnemyID;
    public int PlayerID;
    public int xpos = 0;
    public int ypos = 0;
    
    int[] Position = new int[2];
    //make a loop so that every time the enemy walk a step, it will check is there any player around it


	public boolean check() {
			Position = EnemyID.getPos();
			xpos = Position[0];
			ypos = Position[1];

			//get the current index of the enemy
			int xindex = Arrays.asList(map).indexOfs(xpos);
			int yindex = Arrays.asList(map).indexOf(ypos);


			//to check the front back left and right hand side of the enemy if its a chara
			if (map[xindex + 1][yindex] || map[xindex][yindex + 1] || map[xindex - 1][yindex] || map[xindex][yindex - 1] == PlayerID) {
				return true;
			}
			//if it is a player chara then return true
			//if not then return false
			return false
		}
	}
	
	

