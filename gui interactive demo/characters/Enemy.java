package characters;
import java.util.ArrayList;

import main.map;

public class Enemy extends Chara{
	//super class for character
	public Enemy(int id) {
		super("Enemy", id, 15, 150, 0, 15, 150, 0, 4, 1, "none", "Enemy.png");
	}
		
	/* basic enemies do not have specials.
	 * instead, they simply return that the special 
	 * cannot be used at all times	*/
		
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		return false;
	}
}
