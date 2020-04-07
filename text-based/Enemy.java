import java.util.ArrayList;

public class Enemy extends Chara{
		
	public Enemy(String name, int id, int att, int health, int mana, 
			int maxAtt, int maxHealth, int maxMana, int move, int range) {
		super("Enemy", id, 15, 150, 0, 15, 150, 0, 4, 1, "none");
	}
		
	/* basic enemies do not have specials.
	 * instead, they simply return that the special 
	 * cannot be used at all times	*/
		
	public boolean Special(map theMap, ArrayList<Chara> players, ArrayList<Chara> enemies, int xPos, int yPos) {
		return false;
	}
}
