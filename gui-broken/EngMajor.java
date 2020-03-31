
import java.util.ArrayList;

public class EngMajor extends Chara {

	
	//instance variables
		
		//constructor
		public EngMajor(int id) {
			super("Engineering Major", id, 20, 200, 3, 20, 200, 5, 4, 4, "This special move attacks any enemies in the same chosen column or row as the Engineering Major (costs 3 mana). Select a tile in the same row or column as the engineering major to decide the direction to attack", "Engineering.png");
		}
	
	//this special attacks any enemies in the same row as the engineer"Engineering.png"
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		boolean didSomething = false;
		int row = theMap.getPos(getID())[1];
		int col = theMap.getPos(getID())[0];
		
		if (getMana() < 2) {
			System.out.println("This special requires 2 mana");
		
		//if same position as engineer return false
		} else if (this.getID() == theMap.getID(xPos, yPos)) {
			didSomething = false;
	
		//if column	
		} else if (theMap.getPos(this.getID())[0] == xPos) {
			
			//if above
			for(int each = 0; each < enemies.size(); each++) {
				if(theMap.getPos(enemies.get(each).getID())[0] == col && theMap.getPos(enemies.get(each).getID())[1] < theMap.getPos(this.getID())[1]) {
				attack(enemies.get(each));
				System.out.println("I attacked");
				}
			}
			
			//if below
			for(int each = 0; each < enemies.size(); each++) {
				if(theMap.getPos(enemies.get(each).getID())[0] == col && theMap.getPos(enemies.get(each).getID())[1] > theMap.getPos(this.getID())[1]) {
				attack(enemies.get(each));
				}
			}
			didSomething = true;
		//if row
		} else if (theMap.getPos(this.getID())[1] == yPos) {
			
			//if left
			for(int each = 0; each < enemies.size(); each++) {
				if(theMap.getPos(enemies.get(each).getID())[1] == row && theMap.getPos(enemies.get(each).getID())[0] < theMap.getPos(this.getID())[0]) {
				attack(enemies.get(each));
				}
			}
			
			//if right
			for(int each = 0; each < enemies.size(); each++) {
				if(theMap.getPos(enemies.get(each).getID())[1] == row && theMap.getPos(enemies.get(each).getID())[0] > theMap.getPos(this.getID())[0]) {
				attack(enemies.get(each));
				}
			}	
			didSomething = true;
		}
		
		if (didSomething == true) {
			setMana(getMana() - 2);
		}
		return didSomething;
	}
}
